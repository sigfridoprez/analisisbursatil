package mx.com.business.dao.util;

import mx.com.infraestructura.exceptions.DataBaseException;

public interface UtilDAO {
	public int getMaximaSerieIntraDiaCarga()throws DataBaseException;
	
	public int getMaximoIndiceIntraDiaCarga()throws DataBaseException;
}
