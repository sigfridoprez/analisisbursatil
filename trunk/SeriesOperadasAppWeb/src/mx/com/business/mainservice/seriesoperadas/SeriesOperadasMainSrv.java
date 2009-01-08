package mx.com.business.mainservice.seriesoperadas;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import mx.com.faces.reporteseriesoperadas.util.DataSuggestionBox;
import mx.com.faces.seriesoperadas.util.SeriesOperadasList;
import mx.com.infraestructura.exceptions.BusinessException;

public interface SeriesOperadasMainSrv {
	public List<SeriesOperadasList> getListaSeriesOperadasHoy()throws BusinessException;
	public List<SeriesOperadasList> getListaSeriesOperadas(String emisora,String serie,Date fechaInicio,Date fechaFin)throws BusinessException;
	
	public List<DataSuggestionBox> getListEmisora() throws BusinessException;
	public List<DataSuggestionBox> getListSerie(String emisora) throws BusinessException;
	
	
	public List<String> getListaJList()throws BusinessException;
	public HashMap<String, Object> getGrafica(List<String> lstSeriesGraficar,Date desde,Date hasta,boolean blnImprimeIPC)throws BusinessException;
	
}
