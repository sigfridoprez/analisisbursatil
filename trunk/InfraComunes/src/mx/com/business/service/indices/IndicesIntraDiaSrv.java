package mx.com.business.service.indices;

import java.util.Date;
import java.util.List;

import mx.com.analisispreciosmercado.conf.IndicesIntraDia;
import mx.com.infraestructura.exceptions.BusinessException;

public interface IndicesIntraDiaSrv {
	public void txInsertLstIndicesIntraDia(List<IndicesIntraDia> lstIndices)throws BusinessException;
	public int txBorrarIndicesIntraDia(Date fecha)throws BusinessException;
	
	public IndicesIntraDia getIndiceIntraDia(int idCarga,Date fecha,String indice)throws BusinessException;
	
	public List<IndicesIntraDia> getIndicesIntraDia(String indice, Date fechaInicio,Date fechaFin, char diaHabil)throws BusinessException;
}
