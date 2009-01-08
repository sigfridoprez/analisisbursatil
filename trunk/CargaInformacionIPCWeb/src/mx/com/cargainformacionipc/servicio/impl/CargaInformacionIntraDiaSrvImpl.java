package mx.com.cargainformacionipc.servicio.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import mx.com.analisispreciosmercado.conf.CiConfLinks;
import mx.com.business.service.indices.IndicesIntraDiaSrv;
import mx.com.business.service.seriesoperadas.SeriesOperadasIntraDiaSrv;
import mx.com.business.service.util.UtilSrv;
import mx.com.cargainformacionipc.persistencia.service.CiConfLinksSrv;
import mx.com.cargainformacionipc.persistencia.service.ConfiguracionSplitSrv;
import mx.com.cargainformacionipc.persistencia.service.DiasFestivosSrv;
import mx.com.cargainformacionipc.servicio.CargaInformacionIntraDiaSrv;
import mx.com.cargainformacionipc.util.CreaIntraDiaVO;
import mx.com.cargainformacionipc.util.IndicesIntraDiaVOBuilder;
import mx.com.cargainformacionipc.util.SeriesOperadasIntraDiaVOBuilder;
import mx.com.cargainformacionipc.vo.IndicesIntraDiaVO;
import mx.com.cargainformacionipc.vo.SeriesOperadasIntraDiaVO;
import mx.com.infracomunes.impl.vo.IndicesMercadoCapitalesVO;
import mx.com.infracomunes.impl.vo.SeriesOperadasMercadoCapitalesVO;
import mx.com.infracomunes.vo.ObjetoTablaVO;
import mx.com.infracomunes.vo.ObjetoTransportadorVO;
import mx.com.infraestructura.exceptions.BusinessException;
import mx.com.infraparseohtml.conftablas.exception.PaginaSinDatosException;
import mx.com.infraparseohtml.servicio.ServicioParseoTabla;
import mx.com.infraparseohtml.util.Constantes;

public class CargaInformacionIntraDiaSrvImpl implements
		CargaInformacionIntraDiaSrv {
	private Logger logger = Logger.getLogger(this.getClass());
	private ServicioParseoTabla servicioParseoTabla;
	private CiConfLinksSrv ciConfLinksSrv;
	private SeriesOperadasIntraDiaSrv seriesOperadasIntraDiaSrv;
	private IndicesIntraDiaSrv indicesIntraDiaSrv;
	private DiasFestivosSrv diasFestivosSrv;
	private MailSender mailSender;
    private SimpleMailMessage templateMessage;
    private ConfiguracionSplitSrv configuracionSplitSrv;
    private UtilSrv utilSrv;
    private static int SERIE_OPERADA = 1;
    private static int INDICES = 3;
    private static int DIAS_GUARDAR_INFORMACION = 30;
    
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
		CiConfLinks lstLinkBMVResumen;
		List<SeriesOperadasIntraDiaVO> lstSeriesOperadasVO;
		List<IndicesIntraDiaVO> lstIndicesVO;
		List<ObjetoTablaVO> lstObjetosTablaVO;
		SeriesOperadasMercadoCapitalesVO seriesVO;
		IndicesMercadoCapitalesVO indiceVO;
		SeriesOperadasIntraDiaVOBuilder voBuilder = new SeriesOperadasIntraDiaVOBuilder();
		IndicesIntraDiaVOBuilder indiceBuilder = new IndicesIntraDiaVOBuilder();
		Date fecha = new Date();
		int idCargaSerie = utilSrv.getMaximaSerieIntraDiaCarga()+1;
		int idCargaIndice= utilSrv.getMaximaIndiceIntraDiaCarga()+1;
		Calendar calFechaBorrar = Calendar.getInstance();
		
		CreaIntraDiaVO creaVO = new CreaIntraDiaVO(configuracionSplitSrv,utilSrv);
		
		logger.debug("Borramos la información intra dia que debe de borrarse");
		calFechaBorrar.add(Calendar.DAY_OF_MONTH, -DIAS_GUARDAR_INFORMACION);
		seriesOperadasIntraDiaSrv.txBorrarSerieIntraDia(calFechaBorrar.getTime());
		indicesIntraDiaSrv.txBorrarIndicesIntraDia(calFechaBorrar.getTime());
		
		logger.debug("obtenemos la configuracion de las series operadas del BMV");
		lstLinkBMV = ciConfLinksSrv.getCiConfLinks(Integer.valueOf(SERIE_OPERADA));
		
		if(lstLinkBMV!=null){
			logger.debug("Cargamos las series operadas del BMV");
			tablaVO = servicioParseoTabla.getTabla(lstLinkBMV.getIpConfTablasHtmlParsear().getIdTabla(), lstLinkBMV.getLink());
			if(tablaVO!=null){
				lstObjetosTablaVO = tablaVO.getLstObjetos(); 
				if(lstObjetosTablaVO!=null){
					logger.debug("Recuperada correctamente la lista de la bolsa mexicana de valores::" + lstObjetosTablaVO.size());
					lstSeriesOperadasVO = new ArrayList<SeriesOperadasIntraDiaVO>();
					for(ObjetoTablaVO vo:lstObjetosTablaVO){
						if(vo!=null){
							seriesVO = (SeriesOperadasMercadoCapitalesVO)vo;
							logger.debug("Creamos la lista que se guardara en la BD");
							lstSeriesOperadasVO.add(creaVO.creaSeriesOperadasVO(idCargaSerie,fecha,seriesVO));
						}
					}
					logger.debug("Guardamos la informacion: lstSeriesOperadasVO.size::::" + lstSeriesOperadasVO.size());
					seriesOperadasIntraDiaSrv.txInsertLstSeriesOperadasIntraDia(voBuilder.getLstSeriesOperadas(lstSeriesOperadasVO));
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
					logger.debug("Se carga el Resumen de Mercado");
					lstLinkBMVResumen = ciConfLinksSrv.getCiConfLinks(Integer.valueOf(INDICES));
					if(lstLinkBMVResumen!=null){
						logger.debug("Cargamos el Resumen del Mercado BMV");
						tablaVO = servicioParseoTabla.getTabla(lstLinkBMVResumen.getIpConfTablasHtmlParsear().getIdTabla(), lstLinkBMVResumen.getLink());
						if(tablaVO!=null){
							lstObjetosTablaVO = tablaVO.getLstObjetos(); 
							if(lstObjetosTablaVO!=null){
								logger.debug("Recuperada correctamente la lista de la bolsa mexicana de valores::" + lstObjetosTablaVO.size());
								logger.debug("Creamos la lista que se guardara en la BD");
								lstIndicesVO = new ArrayList<IndicesIntraDiaVO>();
								for(ObjetoTablaVO vo:lstObjetosTablaVO){
									if(vo!=null){
										indiceVO = (IndicesMercadoCapitalesVO)vo;
										lstIndicesVO.add(creaVO.creaIndiceVO(idCargaIndice,fecha,indiceVO));
									}
								}
								logger.debug("Guardamos la informacion: lstSeriesOperadasVO.size::::" + lstIndicesVO.size());
								indicesIntraDiaSrv.txInsertLstIndicesIntraDia(indiceBuilder.getLstIndices(lstIndicesVO));
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

	public SeriesOperadasIntraDiaSrv getSeriesOperadasIntraDiaSrv() {
		return seriesOperadasIntraDiaSrv;
	}

	public void setSeriesOperadasIntraDiaSrv(
			SeriesOperadasIntraDiaSrv seriesOperadasIntraDiaSrv) {
		this.seriesOperadasIntraDiaSrv = seriesOperadasIntraDiaSrv;
	}

	public IndicesIntraDiaSrv getIndicesIntraDiaSrv() {
		return indicesIntraDiaSrv;
	}

	public void setIndicesIntraDiaSrv(IndicesIntraDiaSrv indicesIntraDiaSrv) {
		this.indicesIntraDiaSrv = indicesIntraDiaSrv;
	}

	public DiasFestivosSrv getDiasFestivosSrv() {
		return diasFestivosSrv;
	}

	public void setDiasFestivosSrv(DiasFestivosSrv diasFestivosSrv) {
		this.diasFestivosSrv = diasFestivosSrv;
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

	public ConfiguracionSplitSrv getConfiguracionSplitSrv() {
		return configuracionSplitSrv;
	}

	public void setConfiguracionSplitSrv(ConfiguracionSplitSrv configuracionSplitSrv) {
		this.configuracionSplitSrv = configuracionSplitSrv;
	}

	public UtilSrv getUtilSrv() {
		return utilSrv;
	}

	public void setUtilSrv(UtilSrv utilSrv) {
		this.utilSrv = utilSrv;
	}
}
