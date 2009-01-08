package mx.com.business.dao.indices;

import java.util.Date;
import java.util.List;

import mx.com.analisispreciosmercado.conf.IndicesIntraDia;
import mx.com.infraestructura.exceptions.DataBaseException;

public interface IndicesIntraDiaDAO {
	public void txInsertIndicesIntraDia(IndicesIntraDia indicesIntraDia)throws DataBaseException;
	public int txBorrarIndicesIntraDia(Date date)throws DataBaseException;
	
	public IndicesIntraDia getIndiceIntraDia(int idCarga,Date fecha, String indice)throws DataBaseException;
	public List<IndicesIntraDia> getIndicesIntraDias(String indice,Date fechaInicio,Date fechaFin,char diaHabil)throws DataBaseException;
}
