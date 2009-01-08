package mx.com.faces.seriesoperadas.util;

import java.util.ArrayList;
import java.util.List;

import mx.com.analisispreciosmercado.conf.SeriesOperadas;
import mx.com.infraestructura.exceptions.BusinessException;
import mx.com.util.Utils;

public class SeriesOperadasBuilder {
	
	public List<SeriesOperadasList> getListaSeriesOperadasList(List<SeriesOperadas> lstSeries)throws BusinessException{
		List<SeriesOperadasList> lstReturn = null;
		SeriesOperadasList vo;
		Utils util = new Utils();
		
		if(lstSeries!=null){
			lstReturn = new ArrayList<SeriesOperadasList>();
			for(SeriesOperadas serie:lstSeries){
				vo = new SeriesOperadasList();
				
				vo.setFecha(util.getFecha(serie.getId().getFecha(), "dd/MM/yyyy"));
				vo.setEmisora(serie.getId().getEmisora());
				vo.setSerie(serie.getId().getSerie());
				vo.setHora(util.getHora(serie.getHora()));
				vo.setUltimo(serie.getUltimo().doubleValue());
				vo.setPpp(serie.getPpp()==null?0:serie.getPpp().doubleValue());
				vo.setAnterior(serie.getAnterior().doubleValue());
				vo.setMaximo(serie.getMaximo().doubleValue());
				vo.setMinimo(serie.getMinimo().doubleValue());
				vo.setVolumen((serie.getVolumen()==null?0:serie.getVolumen().doubleValue()));
				vo.setImporte((serie.getImporte()==null?0:serie.getImporte().doubleValue()));
				vo.setOps((serie.getOperaciones()==null?0:serie.getOperaciones().doubleValue()));
				vo.setPuntos((serie.getPuntos()==null?0:serie.getPuntos().doubleValue()));
				//vo.setPct((serie.getPorcentaje()==null?0:serie.getPorcentaje().divide(new BigDecimal("100"),8,BigDecimal.ROUND_HALF_DOWN)).doubleValue());
				vo.setPct((serie.getPorcentaje()==null?0:serie.getPorcentaje().doubleValue()));
				
				vo.setHabil(String.valueOf(serie.getDiaHabil()));
				
				lstReturn.add(vo);	
			}
		}
		
		return lstReturn;
	}
}
