package mx.com.business.dao.seriesoperadas;

import java.util.Date;
import java.util.List;

import mx.com.analisispreciosmercado.conf.SeriesOperadasIntraDia;
import mx.com.infraestructura.exceptions.DataBaseException;

public interface SeriesOperadasIntraDiaDAO {
	public List<SeriesOperadasIntraDia> getSeriesOperadasIntraDia(String emisora,String serie,Date fechaInicio,Date fechaFin,char diaHabil,boolean blnGrafica)throws DataBaseException;
	public List<String> getListEmisora() throws DataBaseException;
	public List<String> getListSerie(String emisora) throws DataBaseException;
	public void txInsertSeriesOperadasIntraDia(SeriesOperadasIntraDia seriesOperadas)throws DataBaseException;
	public void txUpdateSeriesOperadasIntraDia(SeriesOperadasIntraDia seriesOperadas)throws DataBaseException;
	public int  txBorrarSeriesOperadasIntraDia(Date fecha)throws DataBaseException;
	
	public List<String> getListaJList()throws DataBaseException;
	
	public SeriesOperadasIntraDia getSerieOperadaIntraDia(int idCarga,String emisora,String serie,Date date)throws DataBaseException;
	
	//Pantalla
	public List<SeriesOperadasIntraDia> getListaSeriesOperadasIntraDia(int idCarga)throws DataBaseException;
}
