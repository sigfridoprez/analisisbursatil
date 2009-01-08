package mx.com.business.service.indices;

import java.util.Date;
import java.util.List;

import mx.com.analisispreciosmercado.conf.Indices;
import mx.com.infraestructura.exceptions.BusinessException;

public interface IndicesSrv {
	public void txInsertLstIndices(List<Indices> lstIndices)throws BusinessException;
	public Indices getIndice(Date fecha,String indice)throws BusinessException;
	
	public List<Indices> getIndices(String indice, Date fechaInicio,Date fechaFin, char diaHabil)throws BusinessException;
}
