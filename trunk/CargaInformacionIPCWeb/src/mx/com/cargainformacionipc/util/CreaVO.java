package mx.com.cargainformacionipc.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import mx.com.analisispreciosmercado.conf.ConfiguracionSplit;
import mx.com.analisispreciosmercado.conf.DiasFestivos;
import mx.com.analisispreciosmercado.conf.Indices;
import mx.com.analisispreciosmercado.conf.SeriesOperadas;
import mx.com.business.service.indices.IndicesSrv;
import mx.com.business.service.seriesoperadas.SeriesOperadasSrv;
import mx.com.cargainformacionipc.persistencia.service.ConfiguracionSplitSrv;
import mx.com.cargainformacionipc.persistencia.service.DiasFestivosSrv;
import mx.com.cargainformacionipc.vo.IndicesVO;
import mx.com.cargainformacionipc.vo.SeriesOperadasVO;
import mx.com.infracomunes.impl.vo.IndicesMercadoCapitalesVO;
import mx.com.infracomunes.impl.vo.SeriesOperadasMercadoCapitalesVO;
import mx.com.infraestructura.exceptions.BusinessException;
import mx.com.util.Utils;

public class CreaVO {
	private Logger logger = Logger.getLogger(this.getClass());
	private DiasFestivosSrv diasFestivosSrv;
	private IndicesSrv indicesSrv;
	private SeriesOperadasSrv seriesOperadasSrv;
	private ConfiguracionSplitSrv configuracionSplitSrv;
	
	public CreaVO(DiasFestivosSrv diasFestivosSrv,IndicesSrv indicesSrv,SeriesOperadasSrv seriesOperadasSrv,ConfiguracionSplitSrv configuracionSplitSrv){
		this.diasFestivosSrv = diasFestivosSrv;
		this.indicesSrv = indicesSrv;
		this.seriesOperadasSrv = seriesOperadasSrv;
		this.configuracionSplitSrv = configuracionSplitSrv;
	}
	
	public IndicesVO creaIndiceVO(Date fecha,IndicesMercadoCapitalesVO indiceMCVO) throws BusinessException{
		IndicesVO indicesVO = new IndicesVO();
		List<DiasFestivos> lstDiasFestivos;
		Calendar calHoy = Calendar.getInstance();
		Calendar diasFestivos = Calendar.getInstance();
		Indices voTmp;
		
		logger.debug("Creando objeto a guardar");
		indicesVO.setFecha(fecha);
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
		
		lstDiasFestivos = diasFestivosSrv.getListaDiasFestivos();	
		if(calHoy.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY ||
		   calHoy.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
			indicesVO.setDiaHabil('N');	
		}else{//no es sabado ni domingo checamos si es un dia feriado.
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
		/*Buscamos si existe el indice en una carga previa*/
		Utils utils = new Utils();
		voTmp = indicesSrv.getIndice(utils.getDiaHabilAnterior(lstDiasFestivos,indicesVO.getFecha()), indicesVO.getIndice());
		logger.debug("Calculamos la varicacion porcentual");
		calculoRendimientoIndice(indicesVO,voTmp);
	
		return indicesVO;
	}
	
	public SeriesOperadasVO creaSeriesOperadasVO(Date fecha,SeriesOperadasMercadoCapitalesVO seriesVO) throws BusinessException{
		SeriesOperadasVO seriesOperadasVO = new SeriesOperadasVO();
		List<DiasFestivos> lstDiasFestivos;
		Calendar calHoy = Calendar.getInstance();
		Calendar diasFestivos = Calendar.getInstance();
		SeriesOperadas voTmp;
		ConfiguracionSplit confTmp;
		Utils utils = new Utils();
		
		logger.debug("Creando objeto a guardar");
		seriesOperadasVO.setFecha(fecha);
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

		lstDiasFestivos = diasFestivosSrv.getListaDiasFestivos();
		if(calHoy.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY ||
		   calHoy.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
			seriesOperadasVO.setDiaHabil('N');	
		}else{//no es sabado ni domingo checamos si es un dia feriado.
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
		y si no existe de la configuracion se le coloca 1*/
		voTmp = seriesOperadasSrv.getSerieOperada(seriesOperadasVO.getEmisora(), 
				seriesOperadasVO.getSerie(),
				utils.getDiaHabilAnterior(lstDiasFestivos,seriesOperadasVO.getFecha()));
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
		calculoRendimiento(seriesOperadasVO, voTmp);
		
		return seriesOperadasVO;
	}

	private void calculoRendimiento(SeriesOperadas seriesOperadasVO,SeriesOperadas voTmp){
		BigDecimal tmp=null;
		BigDecimal bdPctprecio=null;

		seriesOperadasVO.setVarPctMaximo(new BigDecimal("0"));
		seriesOperadasVO.setVarPctMinimo(new BigDecimal("0"));
		seriesOperadasVO.setVarPct(new BigDecimal("0"));
		seriesOperadasVO.setVarPctPrecio(new BigDecimal("0"));
		seriesOperadasVO.setRendimiento(new BigDecimal("1"));
		if (voTmp != null) {// Calculamos la variacion y rendimiento
			// Calculamos la variacion del precio
			if (voTmp.getPpp().doubleValue() != 0) {
				bdPctprecio = seriesOperadasVO.getPpp().divide(voTmp.getPpp(), 6,
						BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal("1"));
				
				tmp = bdPctprecio; 
				if(Math.abs(bdPctprecio.intValue())<1){
					tmp = tmp.multiply(new BigDecimal("100"));	
				}
				
				seriesOperadasVO.setVarPctPrecio(tmp);
			}
			// Calculamos la variacion de Maximo
			if (voTmp.getPpp().doubleValue() != 0) {
				tmp = seriesOperadasVO.getMaximo().divide(voTmp.getPpp(), 6, BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal("1"));
				
				if(Math.abs(tmp.intValue())<1){
					tmp = tmp.multiply(new BigDecimal("100"));	
				}
				
				seriesOperadasVO.setVarPctMaximo(tmp);
			}
			// Calculamos la variacion de Minimo
			if (voTmp.getPpp().doubleValue() != 0) {
				tmp = seriesOperadasVO.getMinimo().divide(voTmp.getPpp(), 6, BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal("1"));
				
				if(Math.abs(tmp.intValue())<1){
					tmp = tmp.multiply(new BigDecimal("100"));	
				}
				
				seriesOperadasVO.setVarPctMinimo(tmp);
			}
			// Calculamos el Porcentaje
			seriesOperadasVO.setVarPct(seriesOperadasVO.getVarPctMaximo().subtract(seriesOperadasVO.getVarPctMinimo()));
			// calculamos el rendimiento
			if (voTmp.getRendimiento() != null && bdPctprecio!=null) {
				seriesOperadasVO.setRendimiento(voTmp.getRendimiento().multiply(bdPctprecio).add(voTmp.getRendimiento()));
			}
		}
	}
	
	private void calculoRendimientoIndice(IndicesVO indicesVO,Indices voTmp){
		BigDecimal tmp=null;
		BigDecimal bdPctprecio=null;

		indicesVO.setVarPctMaximo(new BigDecimal("0"));
		indicesVO.setVarPctMinimo(new BigDecimal("0"));
		indicesVO.setVarPct(new BigDecimal("0"));
		indicesVO.setVarPctUltimo(new BigDecimal("0"));
		indicesVO.setRendimiento(new BigDecimal("1"));
		
		if (voTmp != null) {// Calculamos la variacion y rendimiento
			// Calculamos la variacion del precio
			if (voTmp.getUltimo().doubleValue() != 0) {
				bdPctprecio = indicesVO.getUltimo().divide(voTmp.getUltimo(), 6,
						BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal("1"));
				
				tmp = bdPctprecio; 
				if(Math.abs(bdPctprecio.intValue())<1){
					tmp = tmp.multiply(new BigDecimal("100"));	
				}
				
				indicesVO.setVarPctUltimo(tmp);
			}
			// Calculamos la variacion de Maximo
			if (voTmp.getUltimo().doubleValue() != 0) {
				tmp = indicesVO.getMaximo().divide(voTmp.getUltimo(), 6, BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal("1"));
				
				if(Math.abs(tmp.intValue())<1){
					tmp = tmp.multiply(new BigDecimal("100"));	
				}
				
				indicesVO.setVarPctMaximo(tmp);
			}
			// Calculamos la variacion de Minimo
			if (voTmp.getUltimo().doubleValue() != 0) {
				tmp = indicesVO.getMinimo().divide(voTmp.getUltimo(), 6, BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal("1"));
				
				if(Math.abs(tmp.intValue())<1){
					tmp = tmp.multiply(new BigDecimal("100"));	
				}
				
				indicesVO.setVarPctMinimo(tmp);
			}
			// Calculamos el Porcentaje
			indicesVO.setVarPct(indicesVO.getVarPctMaximo().subtract(indicesVO.getVarPctMinimo()));
			// calculamos el rendimiento
			if (voTmp.getRendimiento() != null && bdPctprecio!=null) {
				indicesVO.setRendimiento(voTmp.getRendimiento().multiply(bdPctprecio).add(voTmp.getRendimiento()));
			}
		}
	}
}
