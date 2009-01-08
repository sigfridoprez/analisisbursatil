package mx.com.cargainformacionipc.util;

import java.util.ArrayList;
import java.util.List;

import mx.com.analisispreciosmercado.conf.Indices;
import mx.com.analisispreciosmercado.conf.IndicesId;
import mx.com.cargainformacionipc.vo.IndicesVO;

public class IndicesVOBuilder {
	
	public List<Indices> getLstIndices(List<IndicesVO> lstInt){
		List<Indices> lstReturn = null;
		Indices voOut;
		
		if(lstInt!=null){
			lstReturn = new ArrayList<Indices>();
			for(IndicesVO vo:lstInt){
				voOut = new Indices();
				
				voOut.setId(getIndicesId(vo.getFecha(), vo.getIndice()));
				
				voOut.setUltimo(vo.getUltimo());
				voOut.setHora(vo.getHora());
				voOut.setAnterior(vo.getAnterior());
				voOut.setPorcentaje(vo.getPorcentaje());
				voOut.setPuntos(vo.getPuntos());
				voOut.setMaximo(vo.getMaximo());
				voOut.setHoraMaximo(vo.getHoraMaximo());
				voOut.setMinimo(vo.getMinimo());
				voOut.setHoraMinimo(vo.getHoraMinimo());
				voOut.setDiaHabil(vo.getDiaHabil());
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
	
	
	private IndicesId getIndicesId(java.util.Date dtFecha,String strIndice){
		IndicesId id = new IndicesId();
		
		id.setIndice(strIndice);
		id.setFecha(dtFecha);
		
		return id;
	}
}
