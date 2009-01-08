package mx.com.cargainformacionipc.servicio.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.com.analisispreciosmercado.conf.CiConfLinks;
import mx.com.business.service.indices.IndicesSrv;
import mx.com.business.service.seriesoperadas.SeriesOperadasSrv;
import mx.com.cargainformacionipc.persistencia.service.CiConfLinksSrv;
import mx.com.cargainformacionipc.persistencia.service.ConfiguracionSplitSrv;
import mx.com.cargainformacionipc.persistencia.service.DiasFestivosSrv;
import mx.com.cargainformacionipc.servicio.CargaInformacionSrv;
import mx.com.cargainformacionipc.util.CreaVO;
import mx.com.cargainformacionipc.util.IndicesVOBuilder;
import mx.com.cargainformacionipc.util.SeriesOperadasVOBuilder;
import mx.com.cargainformacionipc.vo.IndicesVO;
import mx.com.cargainformacionipc.vo.SeriesOperadasVO;
import mx.com.infracomunes.impl.vo.IndicesMercadoCapitalesVO;
import mx.com.infracomunes.impl.vo.ResumenEmisoraVO;
import mx.com.infracomunes.impl.vo.SeriesOperadasMercadoCapitalesVO;
import mx.com.infracomunes.vo.ObjetoTablaVO;
import mx.com.infracomunes.vo.ObjetoTransportadorVO;
import mx.com.infraestructura.exceptions.BusinessException;
import mx.com.infraparseohtml.conftablas.exception.PaginaSinDatosException;
import mx.com.infraparseohtml.servicio.ServicioParseoTabla;
import mx.com.infraparseohtml.util.Constantes;

import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;


public class CargaInformacionSrvImpl implements CargaInformacionSrv{
	private Logger logger = Logger.getLogger(this.getClass());
	private ServicioParseoTabla servicioParseoTabla;
	private CiConfLinksSrv ciConfLinksSrv;
	private SeriesOperadasSrv seriesOperadasSrv;
	private DiasFestivosSrv diasFestivosSrv;
	private ConfiguracionSplitSrv configuracionSplitSrv;
	private MailSender mailSender;
    private SimpleMailMessage templateMessage;
    private IndicesSrv indicesSrv;
    
	public void cargaInformacion()throws BusinessException{
		logger.debug("En cargaInformacion");
		SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
		int intContadorIntentos = 0;
		
		try{
			ejecutaCarga();
		}catch(BusinessException e2){
			if(intContadorIntentos==Constantes.MAX_INTENTOS){
				logger.debug(Constantes.STR_MSG_ERROR_IRRECUPERABLE);
				logger.error(Constantes.STR_MSG_ERROR_IRRECUPERABLE,e2);
				throw new BusinessException(Constantes.STR_MSG_ERROR_IRRECUPERABLE);
			}else{
				try {
					ejecutaCarga();
					intContadorIntentos++;
					logger.debug("Se trata de reejecutar el servicio por: " + (intContadorIntentos+1) + " vez");
				} catch (PaginaSinDatosException e) {
					logger.debug("La pagina no contiene informaci˜n intento:" + intContadorIntentos);
					msg.setTo("sigfrido.perez.coapango@gmail.com");
			        msg.setText("La pagina no contiene informaci˜n intento:" + intContadorIntentos);
			        try{
			            this.mailSender.send(msg);
			        }
			        catch(MailException ex) {
			        	ex.printStackTrace();
			        	logger.error("Error al enviar el E-Mail", ex);
			        	throw new BusinessException("Error al enviar el E-Mail ERROR::" + ex.getMessage());
			        }
				}
			}
		}catch(PaginaSinDatosException e){
			logger.debug("La pagina no contiene informaci˜n");
			msg.setTo("sigfrido.perez.coapango@gmail.com");
	        msg.setText("La pagina no contiene informaci˜n");
	        try{
	            this.mailSender.send(msg);
	        }
	        catch(MailException ex) {
	        	ex.printStackTrace();
	        	logger.error("Error al enviar el E-Mail", ex);
	        	throw new BusinessException("Error al enviar el E-Mail ERROR::" + ex.getMessage());
	        }
		}
	}

	public void ejecutaCarga() throws BusinessException,PaginaSinDatosException{
		ObjetoTransportadorVO tablaVO;
		CiConfLinks lstLinkBMV;
		CiConfLinks lstLinkYahoo;
		CiConfLinks lstLinkBMVResumen;
		List<SeriesOperadasVO> lstSeriesOperadasVO;
		List<IndicesVO> lstIndicesVO;
		List<ObjetoTablaVO> lstObjetosTablaVO;
		ResumenEmisoraVO objetoTablaVOYahoo;
		SeriesOperadasMercadoCapitalesVO seriesVO;
		IndicesMercadoCapitalesVO indiceVO;
		SeriesOperadasVOBuilder voBuilder = new SeriesOperadasVOBuilder();
		IndicesVOBuilder indiceBuilder = new IndicesVOBuilder();
		Date fecha = new Date();
		CreaVO creaVO = new CreaVO(diasFestivosSrv,indicesSrv,seriesOperadasSrv,configuracionSplitSrv);
		
		logger.debug("obtenemos la configuracion de las series operadas del BMV");
		lstLinkBMV = ciConfLinksSrv.getCiConfLinks(Integer.valueOf(1));
		lstLinkYahoo = ciConfLinksSrv.getCiConfLinks(Integer.valueOf(2));
		StringBuffer strURLYAHOO;
		
		if(lstLinkBMV!=null){
			logger.debug("Cargamos las series operadas del BMV");
			tablaVO = servicioParseoTabla.getTabla(lstLinkBMV.getIpConfTablasHtmlParsear().getIdTabla(), lstLinkBMV.getLink());
			if(tablaVO!=null){
				lstObjetosTablaVO = tablaVO.getLstObjetos(); 
				if(lstObjetosTablaVO!=null){
					logger.debug("Recuperada correctamente la lista de la bolsa mexicana de valores::" + lstObjetosTablaVO.size());
					logger.debug("Por cada elemento de la lista lo buscamos en Yahoo para confirmar la informacion");
					lstSeriesOperadasVO = new ArrayList<SeriesOperadasVO>();
					for(ObjetoTablaVO vo:lstObjetosTablaVO){
						if(vo!=null){
							seriesVO = (SeriesOperadasMercadoCapitalesVO)vo;
							
							strURLYAHOO = new StringBuffer(lstLinkYahoo.getLink()+(seriesVO.getStrEmisora().replaceAll("&", "%26"))+ (seriesVO.getStrSerie().equals("*")?"":seriesVO.getStrSerie()) + ".MX"); 
							if(seriesVO.getDblPPP()==0 && seriesVO.getDblUltimo()==0){
								logger.debug("Verificamos en yahoo");
								
								objetoTablaVOYahoo = (ResumenEmisoraVO)servicioParseoTabla.getTabla(lstLinkYahoo.getIpConfTablasHtmlParsear().getIdTabla(),strURLYAHOO.toString()).getObjetoTablaVO();
								seriesVO.setDblPPP(objetoTablaVOYahoo.getDblUltimaTransac());
							}else if(seriesVO.getDblPPP()==0){
								logger.debug("Verificamos en yahoo");
								
								objetoTablaVOYahoo = (ResumenEmisoraVO)servicioParseoTabla.getTabla(lstLinkYahoo.getIpConfTablasHtmlParsear().getIdTabla(),strURLYAHOO.toString()).getObjetoTablaVO();
								seriesVO.setDblPPP(objetoTablaVOYahoo.getDblUltimaTransac());
								if(objetoTablaVOYahoo.getDblUltimaTransac()!=seriesVO.getDblUltimo() && objetoTablaVOYahoo.getDblUltimaTransac()!=0){
									seriesVO.setDblPPP(objetoTablaVOYahoo.getDblUltimaTransac());
								}else{
									seriesVO.setDblPPP(seriesVO.getDblUltimo());
								}
							}
							logger.debug("Creamos la lista que se guardara en la BD");
							lstSeriesOperadasVO.add(creaVO.creaSeriesOperadasVO(fecha,seriesVO));
						}
					}
					logger.debug("Guardamos la informacion: lstSeriesOperadasVO.size::::" + lstSeriesOperadasVO.size());
					seriesOperadasSrv.txInsertLstSeriesOperadas(voBuilder.getLstSeriesOperadas(lstSeriesOperadasVO));
					logger.debug("Se carga el IPC");
					lstLinkBMVResumen = ciConfLinksSrv.getCiConfLinks(Integer.valueOf(3));
					if(lstLinkBMVResumen!=null){
						logger.debug("Cargamos el Resumen del Mercado BMV");
						tablaVO = servicioParseoTabla.getTabla(lstLinkBMVResumen.getIpConfTablasHtmlParsear().getIdTabla(), lstLinkBMVResumen.getLink());
						if(tablaVO!=null){
							lstObjetosTablaVO = tablaVO.getLstObjetos(); 
							if(lstObjetosTablaVO!=null){
								logger.debug("Recuperada correctamente la lista de la bolsa mexicana de valores::" + lstObjetosTablaVO.size());
								logger.debug("Creamos la lista que se guardara en la BD");
								lstIndicesVO = new ArrayList<IndicesVO>();
								for(ObjetoTablaVO vo:lstObjetosTablaVO){
									if(vo!=null){
										indiceVO = (IndicesMercadoCapitalesVO)vo;
										lstIndicesVO.add(creaVO.creaIndiceVO(fecha,indiceVO));
									}
								}
								logger.debug("Guardamos la informacion: lstSeriesOperadasVO.size::::" + lstIndicesVO.size());
								indicesSrv.txInsertLstIndices(indiceBuilder.getLstIndices(lstIndicesVO));
								logger.debug("Carga finalizada");
							}else{
								throw new PaginaSinDatosException("No hay informacion en la pagina del BMV");
							}
						}else{
							throw new PaginaSinDatosException("No hay informacion en la pagina del BMV");
						}
					}
					
					logger.debug("La informacion se guardo correctamente finaliza la carga");
				}else{
					throw new PaginaSinDatosException("No hay informacion en la pagina del BMV");
				}
			}else{
				throw new PaginaSinDatosException("No hay informacion en la pagina del BMV");
			}
		}
	}
	
	/*private IndicesVO creaIndiceVO(IndicesMercadoCapitalesVO indiceMCVO) throws BusinessException{
		IndicesVO indicesVO = new IndicesVO();
		List<DiasFestivos> lstDiasFestivos;
		Calendar calHoy = Calendar.getInstance();
		Calendar diasFestivos = Calendar.getInstance();
		Indices voTmp;
		IndicesVOBuilder builder = new IndicesVOBuilder();
		
		logger.debug("Creando objeto a guardar");
		indicesVO.setFecha(new Date());
		indicesVO.setIndice(indiceMCVO.getStrIndice());
		indicesVO.setUltimo(BigDecimal.valueOf(indiceMCVO.getDblUltimo()==null?0d:indiceMCVO.getDblUltimo()));
		indicesVO.setHora(indiceMCVO.getTimeHora());
		indicesVO.setAnterior(BigDecimal.valueOf(indiceMCVO.getDblAnterior()==null?0d:indiceMCVO.getDblAnterior()));
		indicesVO.setPorcentaje(BigDecimal.valueOf(indiceMCVO.getDblPct()==null?0d:indiceMCVO.getDblPct()));
		indicesVO.setPuntos(BigDecimal.valueOf(indiceMCVO.getDblPuntos()==null?0d:indiceMCVO.getDblPuntos()));
		
		indicesVO.setMaximo(BigDecimal.valueOf(indiceMCVO.getDblMaximo()==null?0d:indiceMCVO.getDblMaximo()));
		indicesVO.setHoraMaximo(indiceMCVO.getTimeHoraMaximo());
		
		indicesVO.setMinimo(BigDecimal.valueOf(indiceMCVO.getDblMinimo()==null?0d:indiceMCVO.getDblMinimo()));
		indicesVO.setHoraMinimo(indiceMCVO.getTimeHoraMinimo());
		
		indicesVO.setDiaHabil('S');
		indicesVO.setCveTipoCarga('H');
	
		if(calHoy.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY ||
		   calHoy.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
			indicesVO.setDiaHabil('N');	
		}else{//no es sabado ni domingo checamos si es un dia feriado.
			lstDiasFestivos = diasFestivosSrv.getListaDiasFestivos();
			if(lstDiasFestivos!=null){
				for(DiasFestivos diasFestivosVO:lstDiasFestivos){
					diasFestivos.setTime(diasFestivosVO.getFecha());
					if(diasFestivos.get(Calendar.DAY_OF_MONTH)==calHoy.get(Calendar.DAY_OF_MONTH) &&
							diasFestivos.get(Calendar.MONTH)==calHoy.get(Calendar.MONTH) &&
							diasFestivos.get(Calendar.YEAR)==calHoy.get(Calendar.YEAR)){
						indicesVO.setDiaHabil('N');
						break;
					}
				}
			}
		}
		/*Buscamos si existe el indice en una carga previa* /
		voTmp = indicesSrv.getIndiceAnterior(indicesVO.getFecha(), indicesVO.getIndice());
		
		logger.debug("Calculamos la varicacion porcentual");
		builder.calculoRendimiento(indicesVO, voTmp);
		
		return indicesVO;
	}
	
	private SeriesOperadasVO creaSeriesOperadasVO(SeriesOperadasMercadoCapitalesVO seriesVO) throws BusinessException{
		SeriesOperadasVO seriesOperadasVO = new SeriesOperadasVO();
		List<DiasFestivos> lstDiasFestivos;
		Calendar calHoy = Calendar.getInstance();
		Calendar diasFestivos = Calendar.getInstance();
		SeriesOperadas voTmp;
		ConfiguracionSplit confTmp;
		SeriesOperadasVOBuilder builder = new SeriesOperadasVOBuilder();
		
		logger.debug("Creando objeto a guardar");
		seriesOperadasVO.setFecha(new Date());
		seriesOperadasVO.setEmisora(seriesVO.getStrEmisora());
		seriesOperadasVO.setSerie(seriesVO.getStrSerie()==null?"":seriesVO.getStrSerie());
		seriesOperadasVO.setHora(seriesVO.getTimeHora());
		seriesOperadasVO.setUltimo(BigDecimal.valueOf(seriesVO.getDblUltimo()==null?0d:seriesVO.getDblUltimo()));
		seriesOperadasVO.setPpp(BigDecimal.valueOf(seriesVO.getDblPPP()==null?0d:seriesVO.getDblPPP()));
		seriesOperadasVO.setAnterior(BigDecimal.valueOf(seriesVO.getDblAnterior()==null?0d:seriesVO.getDblAnterior()));
		seriesOperadasVO.setMaximo(BigDecimal.valueOf(seriesVO.getDblMaximo()==null?0d:seriesVO.getDblMaximo()));
		seriesOperadasVO.setMinimo(BigDecimal.valueOf(seriesVO.getDblMinimo()==null?0d:seriesVO.getDblMinimo()));
		seriesOperadasVO.setVolumen(Long.valueOf(seriesVO.getDblVolumen()==null?0L:seriesVO.getDblVolumen().longValue()));
		seriesOperadasVO.setImporte(BigDecimal.valueOf(seriesVO.getDblImporte()==null?0d:seriesVO.getDblImporte()));
		seriesOperadasVO.setOperaciones(Integer.valueOf(seriesVO.getDblOps()==null?0:seriesVO.getDblOps().intValue()));
		seriesOperadasVO.setPuntos(BigDecimal.valueOf(seriesVO.getDblPuntos()==null?0d:seriesVO.getDblPuntos()));
		seriesOperadasVO.setPorcentaje(BigDecimal.valueOf(seriesVO.getDblPct()==null?0d:seriesVO.getDblPct()));
		seriesOperadasVO.setDiaHabil('S');
		seriesOperadasVO.setCveTipoCarga('H');
	
		if(calHoy.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY ||
		   calHoy.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
			seriesOperadasVO.setDiaHabil('N');	
		}else{//no es sabado ni domingo checamos si es un dia feriado.
			lstDiasFestivos = diasFestivosSrv.getListaDiasFestivos();
			if(lstDiasFestivos!=null){
				for(DiasFestivos diasFestivosVO:lstDiasFestivos){
					diasFestivos.setTime(diasFestivosVO.getFecha());
					if(diasFestivos.get(Calendar.DAY_OF_MONTH)==calHoy.get(Calendar.DAY_OF_MONTH) &&
							diasFestivos.get(Calendar.MONTH)==calHoy.get(Calendar.MONTH) &&
							diasFestivos.get(Calendar.YEAR)==calHoy.get(Calendar.YEAR)){
						seriesOperadasVO.setDiaHabil('N');
						break;
					}
				}
			}
		}
		/*Buscamos si existe la serie en una carga previa,
		en caso de existir cargamos el split anterior, si no existe se busca de la configuracion
		y si no existe de la configuracion se le coloca 1* /
		voTmp = seriesOperadasSrv.getSerieOperadaAnterior(seriesOperadasVO.getEmisora(), seriesOperadasVO.getSerie(),seriesOperadasVO.getFecha());
		if(voTmp!=null){//Existe serie, se aplica el Split anterior
			seriesOperadasVO.setSplit(voTmp.getSplit());
		}else{//No existe la serie enterior se saca de la configuracion
			//obtenenemos la configuracion
			confTmp = configuracionSplitSrv.getConfiguracionSplit(seriesOperadasVO.getEmisora(), seriesOperadasVO.getSerie());
			if(confTmp!=null){
				seriesOperadasVO.setSplit(confTmp.getSplit());
				confTmp.setFechaAplicacion(new Date());
				//Se aplico se actualiza
				configuracionSplitSrv.txActualizaConfiguracionSplit(confTmp);
			}else{//No existe configuracion se coloca por default
				seriesOperadasVO.setSplit(1);
			}
		}
		logger.debug("Calculamos la varicacion porcentual");
		builder.calculoRendimiento(seriesOperadasVO, voTmp);
		
		return seriesOperadasVO;
	}*/
	
	public ServicioParseoTabla getServicioParseoTabla() {
		return servicioParseoTabla;
	}

	public void setServicioParseoTabla(ServicioParseoTabla servicioParseoTabla) {
		this.servicioParseoTabla = servicioParseoTabla;
	}

	public CiConfLinksSrv getCiConfLinksSrv() {
		return ciConfLinksSrv;
	}

	public void setCiConfLinksSrv(CiConfLinksSrv ciConfLinksSrv) {
		this.ciConfLinksSrv = ciConfLinksSrv;
	}

	public SeriesOperadasSrv getSeriesOperadasSrv() {
		return seriesOperadasSrv;
	}

	public void setSeriesOperadasSrv(SeriesOperadasSrv seriesOperadasSrv) {
		this.seriesOperadasSrv = seriesOperadasSrv;
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public SimpleMailMessage getTemplateMessage() {
		return templateMessage;
	}

	public void setTemplateMessage(SimpleMailMessage templateMessage) {
		this.templateMessage = templateMessage;
	}

	public DiasFestivosSrv getDiasFestivosSrv() {
		return diasFestivosSrv;
	}

	public void setDiasFestivosSrv(DiasFestivosSrv diasFestivosSrv) {
		this.diasFestivosSrv = diasFestivosSrv;
	}

	public ConfiguracionSplitSrv getConfiguracionSplitSrv() {
		return configuracionSplitSrv;
	}

	public void setConfiguracionSplitSrv(ConfiguracionSplitSrv configuracionSplitSrv) {
		this.configuracionSplitSrv = configuracionSplitSrv;
	}

	public IndicesSrv getIndicesSrv() {
		return indicesSrv;
	}

	public void setIndicesSrv(IndicesSrv indicesSrv) {
		this.indicesSrv = indicesSrv;
	}
	
}
