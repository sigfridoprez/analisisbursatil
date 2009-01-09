package mx.com.business.mainservice.seriesoperadas;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import mx.com.analisispreciosmercado.conf.Indices;
import mx.com.analisispreciosmercado.conf.SeriesOperadas;
import mx.com.business.service.indices.IndicesSrv;
import mx.com.business.service.seriesoperadas.SeriesOperadasSrv;
import mx.com.faces.reporteseriesoperadas.util.DataSuggestionBox;
import mx.com.faces.reporteseriesoperadas.util.DataSuggestionBoxBuilder;
import mx.com.faces.seriesoperadas.util.SerieGraficarVO;
import mx.com.faces.seriesoperadas.util.SeriesOperadasBuilder;
import mx.com.faces.seriesoperadas.util.SeriesOperadasList;
import mx.com.infraestructura.exceptions.BusinessException;
import mx.com.util.Constantes;
import mx.com.util.UtilsGrafica;

public class SeriesOperadasMainSrvImpl implements SeriesOperadasMainSrv {
	private Logger logger = Logger.getLogger(this.getClass());
	private SeriesOperadasSrv seriesOperadasSrv;
	private IndicesSrv indicesSrv;
	private int intMaximo = 0;
	private Date dateMinima;
	
	public List<SeriesOperadasList> getListaSeriesOperadasHoy()
			throws BusinessException {
		SeriesOperadasBuilder builder = new SeriesOperadasBuilder();
		return builder.getListaSeriesOperadasList(seriesOperadasSrv.getListaSeriesOperadas(null,null,new Date(),null,'S',false));
	}

	public SeriesOperadasSrv getSeriesOperadasSrv() {
		return seriesOperadasSrv;
	}

	public void setSeriesOperadasSrv(SeriesOperadasSrv seriesOperadasSrv) {
		this.seriesOperadasSrv = seriesOperadasSrv;
	}

	public List<DataSuggestionBox> getListEmisora() throws BusinessException {
		DataSuggestionBoxBuilder builder = new DataSuggestionBoxBuilder(); 
		return builder.getListData(this.seriesOperadasSrv.getListEmisora());
	}

	public List<DataSuggestionBox> getListSerie(String emisora) throws BusinessException {
		DataSuggestionBoxBuilder builder = new DataSuggestionBoxBuilder(); 
		return builder.getListData(this.seriesOperadasSrv.getListSerie(emisora));
	}

	public List<SeriesOperadasList> getListaSeriesOperadas(String emisora,
			String serie, Date fechaInicio, Date fechaFin)
			throws BusinessException {
		SeriesOperadasBuilder builder = new SeriesOperadasBuilder();
		return builder.getListaSeriesOperadasList(seriesOperadasSrv.getListaSeriesOperadas(emisora,serie,fechaInicio,fechaFin,'S',false));
	}

	public List<String> getListaJList() throws BusinessException {
		return this.seriesOperadasSrv.getListaJList();
	}

	/**
	 * Metodo para graficar el Rendimiento
	 */
	public HashMap<String, Object> getGrafica(List<String> lstSeriesGraficar,
			Date desde, Date hasta, boolean blnImprimeIPC)
			throws BusinessException {
		logger.debug("En getGrafica");
		HashMap<String, Object> hsmReturn = new HashMap<String, Object>();
		StringBuilder sbGrafica = null;
		StringTokenizer strtksTokens = null;
		List<SeriesOperadas> lstSeries = null;
		List<List<SeriesOperadas>> lstTodasSeriesGraficar = null;
		int intNoCols = 0;
		SimpleDateFormat sdfFecha = new SimpleDateFormat("dd/MM/yyyy");
		UtilsGrafica util = new UtilsGrafica();
		
		if(lstSeriesGraficar!=null && !lstSeriesGraficar.isEmpty()){
			sbGrafica = new StringBuilder("{");
			
			logger.debug("Primero recuperamos todas las series");
			lstTodasSeriesGraficar = new ArrayList<List<SeriesOperadas>>();
			for(String strEmisoraSerie:lstSeriesGraficar){
				strtksTokens = new StringTokenizer(strEmisoraSerie, "-");
				lstSeries = seriesOperadasSrv.getListaSeriesOperadas(strtksTokens.nextToken(),strtksTokens.nextToken(), desde, hasta,'S',true);
				lstTodasSeriesGraficar.add(lstSeries);
			}
			logger.debug("Ya tenemos todas las series que se van a graficar");
			if(!lstTodasSeriesGraficar.isEmpty()){
				intNoCols = lstTodasSeriesGraficar.size();
				sbGrafica.append(getSeries(getSeriesGraficar(lstTodasSeriesGraficar,desde),blnImprimeIPC,desde, hasta, 'S',util));
			}
			sbGrafica.append(",");
			sbGrafica.append(util.getOpciones(intNoCols));
			sbGrafica.append("}");
			hsmReturn.put(Constantes.SERIE_GRAFICAR, sbGrafica.toString());
			if(dateMinima!=null){
				hsmReturn.put(Constantes.FECHA_MINIMA,sdfFecha.format(dateMinima));
			}
		}
		
		util=null;
		return hsmReturn;
	}
	
		
	private String getSeries(List<SerieGraficarVO> lstSeries,boolean blnImprimeIPC,Date fechaInicio,Date fechaFin,char diaHabil,UtilsGrafica util){
		StringBuilder sbSeries = new StringBuilder("series:[");
		int intContador = 0;
		
		for(SerieGraficarVO vo:lstSeries){
			sbSeries.append(util.getSerie(vo));
			if(intContador < lstSeries.size()-1){
				sbSeries.append(",");
			}
			intContador++;
		}
		if(blnImprimeIPC){
			sbSeries.append(",");
			sbSeries.append(getIndice("IPC",fechaInicio, fechaFin, diaHabil));
		}
		sbSeries.append("]");
		return sbSeries.toString();
	}
	
	private String getIndice(String indice,Date fechaInicio,Date fechaFin,char diaHabil){
		StringBuilder sbIndice = new StringBuilder();
		List<Indices> lstIndice;
		int intContador=0;
		
		try {
			lstIndice = indicesSrv.getIndices(indice, fechaInicio, fechaFin, diaHabil);
			if(lstIndice!=null && !lstIndice.isEmpty()){
				if(lstIndice.size()>0){
					sbIndice.append("{data:[");
				}
				for(Indices ind:lstIndice){
					sbIndice.append("["+getValorX(ind.getId().getFecha())+","+ind.getRendimiento()+"]");
					if(intContador<lstIndice.size()-1){
						sbIndice.append(",");
					}
					intContador++;
				}
				if(lstIndice.size()>0){
					sbIndice.append("],");
					sbIndice.append("label: '"+indice+"',color:'black' ");		
					sbIndice.append("}");
				}
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("Error:: en getIndice:::", e);
		}
		return sbIndice.toString();
	}
	
	private List<SerieGraficarVO> getSeriesGraficar(List<List<SeriesOperadas>> lstTodasSeriesGraficar,Date desde){
		logger.debug("En getSeriesGraficar");
		List<SerieGraficarVO> lstReturn = new ArrayList<SerieGraficarVO>();
		SerieGraficarVO sgOb;
		int intContador = 0;
		
		logger.debug("Recorremos la lista de series a graficar para obtener los valores");
		for(List<SeriesOperadas> lstSerie:lstTodasSeriesGraficar){
			sgOb = new SerieGraficarVO();
			intContador = 0;
			
			if(lstSerie.size()>intMaximo){
				intMaximo = lstSerie.size(); 
			}
			
			for(SeriesOperadas serie:lstSerie){
				if(intContador==0){
					sgOb.setStrNombreSerie(serie.getId().getEmisora()+"-" + serie.getId().getSerie());
					if(desde==null){
						if(dateMinima==null){
							dateMinima = serie.getId().getFecha();
						}
						if(serie.getId().getFecha().before(dateMinima)){
							dateMinima = serie.getId().getFecha();
						}
					}else{
						dateMinima = desde;
					}
				}
				sgOb.setValores(serie.getRendimiento().doubleValue(), getValorX(serie.getId().getFecha()));
				intContador++;	
			}
			lstReturn.add(sgOb);
		}
		
		logger.debug("El numero m‡ximo de valores en X es:" + intMaximo);
		
		return lstReturn;
	}
	
	private int getValorX(Date dtFecha){
		long fechaInicialMs = dateMinima.getTime();
		long fechaFinalMs = dtFecha.getTime();
		long diferencia = fechaFinalMs - fechaInicialMs;
		
		double dias = Math.floor(diferencia / (double)(1000 * 60 * 60 * 24));
		
		return ( (int) dias);
	}
	
	public IndicesSrv getIndicesSrv() {
		return indicesSrv;
	}

	public void setIndicesSrv(IndicesSrv indicesSrv) {
		this.indicesSrv = indicesSrv;
	}

}
