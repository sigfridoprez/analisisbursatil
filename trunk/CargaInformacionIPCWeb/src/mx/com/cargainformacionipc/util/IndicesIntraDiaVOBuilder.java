package mx.com.cargainformacionipc.util;

import java.util.ArrayList;
import java.util.List;

import mx.com.analisispreciosmercado.conf.IndicesIntraDia;
import mx.com.analisispreciosmercado.conf.IndicesIntraDiaId;
import mx.com.cargainformacionipc.vo.IndicesIntraDiaVO;

public class IndicesIntraDiaVOBuilder {
	
	public List<IndicesIntraDia> getLstIndices(List<IndicesIntraDiaVO> lstInt){
		List<IndicesIntraDia> lstReturn = null;
		IndicesIntraDia voOut;
		
		if(lstInt!=null){
			lstReturn = new ArrayList<IndicesIntraDia>();
			for(IndicesIntraDiaVO vo:lstInt){
				voOut = new IndicesIntraDia();
				
				voOut.setId(getIndicesIntraDiaId(vo.getIdCarga(),vo.getFecha(), vo.getIndice()));
				
				voOut.setUltimo(vo.getUltimo());
				voOut.setHora(vo.getHora());
				voOut.setAnterior(vo.getAnterior());
				voOut.setPorcentaje(vo.getPorcentaje());
				voOut.setPuntos(vo.getPuntos());
				voOut.setMaximo(vo.getMaximo());
				voOut.setHoraMaximo(vo.getHoraMaximo());
				voOut.setMinimo(vo.getMinimo());
				voOut.setHoraMinimo(vo.getHoraMinimo());
				
				voOut.setVarPctUltimo(vo.getVarPctUltimo());
				voOut.setVarPctMaximo(vo.getVarPctMaximo());
				voOut.setVarPctMinimo(vo.getVarPctMinimo());
				voOut.setVarPct(vo.getVarPct());
				voOut.setRendimiento(vo.getRendimiento());
				
				lstReturn.add(voOut);
			}
		}
		
		return lstReturn;
	}
	
	
	private IndicesIntraDiaId getIndicesIntraDiaId(int idCarga,java.util.Date dtFecha,String strIndice){
		IndicesIntraDiaId id = new IndicesIntraDiaId();
		
		id.setIndice(strIndice);
		id.setFecha(dtFecha);
		id.setIdCarga(idCarga);
		
		return id;
	}
}
