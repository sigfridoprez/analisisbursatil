package mx.com.business.service.seriesoperadas;

import java.util.Date;
import java.util.List;

import mx.com.analisispreciosmercado.conf.SeriesOperadasIntraDia;
import mx.com.infraestructura.exceptions.BusinessException;

public interface SeriesOperadasIntraDiaSrv {
	public void txInsertLstSeriesOperadasIntraDia(List<SeriesOperadasIntraDia> lstSeriesOperadas)throws BusinessException;
	public void txUpdateLstSeriesOperadasIntraDia(List<SeriesOperadasIntraDia> lstSeriesOperadas)throws BusinessException;
	public void txUpdateSeriesOperadasIntraDia(SeriesOperadasIntraDia seriesOperadas)throws BusinessException;
	public int  txBorrarSerieIntraDia(Date fecha)throws BusinessException;
	
	public List<SeriesOperadasIntraDia> getListaSeriesOperadasIntraDia(String emisora,String serie,Date fechaInicio,Date fechaFin,char diaHabil,boolean blnGrafica)throws BusinessException;
	public List<String> getListEmisora() throws BusinessException;
	public List<String> getListSerie(String emisora) throws BusinessException;
	public List<String> getListaJList()throws BusinessException;
	
	public SeriesOperadasIntraDia getSerieOperadaIntraDia(int idCarga,String emisora, String serie,Date date)throws BusinessException;
	
	//Pantalla
	public List<SeriesOperadasIntraDia> getListaSeriesOperadasIntraDia(int idCarga)throws BusinessException;
}
