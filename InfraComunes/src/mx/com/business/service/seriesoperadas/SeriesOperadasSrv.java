package mx.com.business.service.seriesoperadas;

import java.util.Date;
import java.util.List;

import mx.com.analisispreciosmercado.conf.SeriesOperadas;
import mx.com.infraestructura.exceptions.BusinessException;

public interface SeriesOperadasSrv {
	public void txInsertLstSeriesOperadas(List<SeriesOperadas> lstSeriesOperadas)throws BusinessException;
	public void txUpdateLstSeriesOperadas(List<SeriesOperadas> lstSeriesOperadas)throws BusinessException;
	public void txUpdateSeriesOperadas(SeriesOperadas seriesOperadas)throws BusinessException;
	
	public List<SeriesOperadas> getListaSeriesOperadas(String emisora,String serie,Date fechaInicio,Date fechaFin,char diaHabil,boolean blnGrafica)throws BusinessException;
	public List<String> getListEmisora() throws BusinessException;
	public List<String> getListSerie(String emisora) throws BusinessException;
	public List<String> getListaJList()throws BusinessException;
	public SeriesOperadas getSerieOperada(String emisora, String serie,Date date)throws BusinessException;
}
