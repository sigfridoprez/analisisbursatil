package mx.com.cargainformacionipc.util;

import java.util.ArrayList;
import java.util.List;

import mx.com.analisispreciosmercado.conf.SeriesOperadas;
import mx.com.analisispreciosmercado.conf.SeriesOperadasId;
import mx.com.cargainformacionipc.vo.SeriesOperadasVO;

public class SeriesOperadasVOBuilder {
	
	public List<SeriesOperadas> getLstSeriesOperadas(List<SeriesOperadasVO> lstInt){
		List<SeriesOperadas> lstReturn = null;
		SeriesOperadas voOut;
		
		if(lstInt!=null){
			lstReturn = new ArrayList<SeriesOperadas>();
			for(SeriesOperadasVO vo:lstInt){
				voOut = new SeriesOperadas();
				
				voOut.setId(getSeriesOperadasId(vo.getFecha(), vo.getEmisora(), vo.getSerie()));
				voOut.setHora(vo.getHora());
				voOut.setUltimo(vo.getUltimo());
				voOut.setPpp(vo.getPpp());
				voOut.setAnterior(vo.getAnterior());
				voOut.setMaximo(vo.getMaximo());
				voOut.setMinimo(vo.getMinimo());
				voOut.setVolumen(vo.getVolumen());
				voOut.setImporte(vo.getImporte());
				voOut.setOperaciones(vo.getOperaciones());
				voOut.setPuntos(vo.getPuntos());
				voOut.setPorcentaje(vo.getPorcentaje());
				voOut.setDiaHabil(vo.getDiaHabil());
				voOut.setSplit(vo.getSplit());
				
				voOut.setVarPct(vo.getVarPct());
				voOut.setVarPctMaximo(vo.getVarPctMaximo());
				voOut.setVarPctMinimo(vo.getVarPctMinimo());
				voOut.setVarPctPrecio(vo.getVarPctPrecio());
				
				voOut.setRendimiento(vo.getRendimiento());
				voOut.setIndBursatilidad(vo.getIndBursatilidad());
				voOut.setTipoIndBursatilidad(vo.getTipoIndBursatilidad());
				
				lstReturn.add(voOut);
			}
		}
		
		return lstReturn;
	}
	
	private SeriesOperadasId getSeriesOperadasId(java.util.Date dtFecha,String strEmisora,String strSerie){
		SeriesOperadasId id = new SeriesOperadasId();
		
		id.setEmisora(strEmisora);
		id.setFecha(dtFecha);
		id.setSerie(strSerie);
		
		return id;
	}
}
