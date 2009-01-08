package mx.com.faces.seriesoperadasintradia.util;

import java.util.ArrayList;
import java.util.List;

import mx.com.analisispreciosmercado.conf.SeriesOperadasIntraDia;
import mx.com.faces.seriesoperadas.util.SeriesOperadasList;
import mx.com.infraestructura.exceptions.BusinessException;
import mx.com.util.Utils;

public class SeriesIntraDiaBuilder {
	public List<SeriesOperadasList> getListaSeriesOperadasList(List<SeriesOperadasIntraDia> lstSeries)throws BusinessException{
		List<SeriesOperadasList> lstReturn = null;
		SeriesOperadasList vo;
		Utils util = new Utils();
		
		if(lstSeries!=null){
			lstReturn = new ArrayList<SeriesOperadasList>();
			for(SeriesOperadasIntraDia serie:lstSeries){
				vo = new SeriesOperadasList();
				
				vo.setEmisora(serie.getId().getEmisora());
				vo.setSerie(serie.getId().getSerie());
				vo.setPpp(serie.getPpp()==null?0:serie.getPpp().doubleValue());
				vo.setUltimo(serie.getUltimo().doubleValue());
				vo.setPct((serie.getPorcentaje()==null?0:serie.getPorcentaje().doubleValue()));
				vo.setHora(util.getHora(serie.getHora()));
				
				vo.setMaximo(serie.getMaximo().doubleValue());
				vo.setHoraMaximo(util.getHora(serie.getHoraMaximo()));
				vo.setMinimo(serie.getMinimo().doubleValue());
				vo.setHoraMinimo(util.getHora(serie.getHoraMinimo()));
				
				vo.setVolumen(Double.valueOf(serie.getVolumen()));
				vo.setOps(Double.valueOf(serie.getOperaciones()));
					
				lstReturn.add(vo);	
			}
		}
		
		return lstReturn;
	}
}
