package mx.com.business.mainservice.seriesoperadasintradia;

import java.util.HashMap;
import java.util.List;

import mx.com.faces.seriesoperadas.util.SeriesOperadasList;
import mx.com.infraestructura.exceptions.BusinessException;

public interface SeriesOperadasIntradiaMainSrv {
	public List<SeriesOperadasList> getListaSeriesOperadasIntradiaHoy()throws BusinessException;
	public HashMap<String, Object> getGrafica(String emisora,String serie)throws BusinessException;
}
