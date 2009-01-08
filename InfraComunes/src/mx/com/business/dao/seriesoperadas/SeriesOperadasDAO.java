package mx.com.business.dao.seriesoperadas;

import java.util.Date;
import java.util.List;

import mx.com.analisispreciosmercado.conf.SeriesOperadas;
import mx.com.infraestructura.exceptions.DataBaseException;

public interface SeriesOperadasDAO {
	public List<SeriesOperadas> getSeriesOperadas(String emisora,String serie,Date fechaInicio,Date fechaFin,char diaHabil,boolean blnGrafica)throws DataBaseException;
	public List<String> getListEmisora() throws DataBaseException;
	public List<String> getListSerie(String emisora) throws DataBaseException;
	public void txInsertSeriesOperadas(SeriesOperadas seriesOperadas)throws DataBaseException;
	public void txUpdateSeriesOperadas(SeriesOperadas seriesOperadas)throws DataBaseException;
	public List<String> getListaJList()throws DataBaseException;
	
	public SeriesOperadas getSerieOperada(String emisora,String serie,Date date)throws DataBaseException;
}