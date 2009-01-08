package mx.com.business.dao.indices;

import java.util.Date;
import java.util.List;

import mx.com.analisispreciosmercado.conf.Indices;
import mx.com.infraestructura.exceptions.DataBaseException;

public interface IndicesDAO {
	public void txInsertIndices(Indices indices)throws DataBaseException;
	public Indices getIndice(Date fecha, String indice)throws DataBaseException;
	public List<Indices> getIndices(String indice,Date fechaInicio,Date fechaFin,char diaHabil)throws DataBaseException;
}
