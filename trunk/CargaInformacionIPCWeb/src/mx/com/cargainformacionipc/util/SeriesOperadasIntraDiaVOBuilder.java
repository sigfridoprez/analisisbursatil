package mx.com.cargainformacionipc.util;

import java.util.ArrayList;
import java.util.List;

import mx.com.analisispreciosmercado.conf.SeriesOperadasIntraDia;
import mx.com.analisispreciosmercado.conf.SeriesOperadasIntraDiaId;
import mx.com.cargainformacionipc.vo.SeriesOperadasIntraDiaVO;

public class SeriesOperadasIntraDiaVOBuilder {
	
	public List<SeriesOperadasIntraDia> getLstSeriesOperadas(List<SeriesOperadasIntraDiaVO> lstInt){
		List<SeriesOperadasIntraDia> lstReturn = null;
		SeriesOperadasIntraDia voOut;
		
		if(lstInt!=null){
			lstReturn = new ArrayList<SeriesOperadasIntraDia>();
			for(SeriesOperadasIntraDiaVO vo:lstInt){
				voOut = new SeriesOperadasIntraDia();
				
				voOut.setId(getSeriesOperadasIntraDiaId(vo.getIdCarga(),vo.getFecha(), vo.getEmisora(), vo.getSerie()));
				voOut.setHora(vo.getHora());
				voOut.setUltimo(vo.getUltimo());
				voOut.setPpp(vo.getPpp());
				voOut.setAnterior(vo.getAnterior());
				
				voOut.setMaximo(vo.getMaximo());
				voOut.setHoraMaximo(vo.getHoraMaximo());
				
				voOut.setMinimo(vo.getMinimo());
				voOut.setHoraMinimo(vo.getHoraMinimo());
				
				voOut.setVolumen(vo.getVolumen());
				voOut.setImporte(vo.getImporte());
				voOut.setOperaciones(vo.getOperaciones());
				voOut.setPuntos(vo.getPuntos());
				voOut.setPorcentaje(vo.getPorcentaje());
				voOut.setSplit(vo.getSplit());
				
				voOut.setVarPct(vo.getVarPct());
				voOut.setVarPctMaximo(vo.getVarPctMaximo());
				voOut.setVarPctMinimo(vo.getVarPctMinimo());
				voOut.setVarPctPrecio(vo.getVarPctPrecio());
				voOut.setRendimiento(vo.getRendimiento());
				
				lstReturn.add(voOut);
			}
		}
		
		return lstReturn;
	}
	
	private SeriesOperadasIntraDiaId getSeriesOperadasIntraDiaId(int idCarga,java.util.Date dtFecha,String strEmisora,String strSerie){
		SeriesOperadasIntraDiaId id = new SeriesOperadasIntraDiaId();
		
		id.setEmisora(strEmisora);
		id.setFecha(dtFecha);
		id.setIdCarga(idCarga);
		id.setSerie(strSerie);
		
		return id;
	}
}
