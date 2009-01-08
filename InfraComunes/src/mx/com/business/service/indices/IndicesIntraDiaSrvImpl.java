package mx.com.business.service.indices;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import mx.com.analisispreciosmercado.conf.IndicesIntraDia;
import mx.com.business.dao.indices.IndicesIntraDiaDAO;
import mx.com.infraestructura.exceptions.BusinessException;
import mx.com.infraestructura.exceptions.DataBaseException;

public class IndicesIntraDiaSrvImpl implements IndicesIntraDiaSrv{
	private Logger logger = Logger.getLogger(this.getClass());
	IndicesIntraDiaDAO indicesIntraDiaDAO;
	
	public IndicesIntraDia getIndiceIntraDia(int idCarga,Date fecha, String indice)
			throws BusinessException {
		try {
			return indicesIntraDiaDAO.getIndiceIntraDia(idCarga,fecha, indice);
		} catch (DataBaseException e) {
			e.printStackTrace();
			throw new BusinessException("Error::" + e.getMessage());
		}
	}

	public List<IndicesIntraDia> getIndicesIntraDia(String indice,
			Date fechaInicio, Date fechaFin, char diaHabil)
			throws BusinessException {
		try {
			return indicesIntraDiaDAO.getIndicesIntraDias(indice, fechaInicio, fechaFin, diaHabil);
		} catch (DataBaseException e) {
			e.printStackTrace();
			throw new BusinessException("ERROR:::" + e.getMessage());
		}
	}

	public void txInsertLstIndicesIntraDia(List<IndicesIntraDia> lstIndices)
			throws BusinessException {
		if (lstIndices != null) {
			for (IndicesIntraDia vo : lstIndices) {
				txInsertIndicesIntraDia(vo);
			}
		}
	}
	
	public int txBorrarIndicesIntraDia(Date fecha)throws BusinessException{
		int intResult = 0;
		
		try {
			intResult = indicesIntraDiaDAO.txBorrarIndicesIntraDia(fecha);
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		
		return intResult;
	}
	

	public void txInsertIndicesIntraDia(IndicesIntraDia indices) throws BusinessException {
		try {
			if (indices != null) {
				indicesIntraDiaDAO.txInsertIndicesIntraDia(indices);
			}
		} catch (DataBaseException e) {
			e.printStackTrace();
			logger.error("Error al insertar el Indice", e);
			throw new BusinessException("Error al insertar el Indice ERROR:: "
					+ e.getMessage());
		}
	}

	public IndicesIntraDiaDAO getIndicesIntraDiaDAO() {
		return indicesIntraDiaDAO;
	}

	public void setIndicesIntraDiaDAO(IndicesIntraDiaDAO indicesIntraDiaDAO) {
		this.indicesIntraDiaDAO = indicesIntraDiaDAO;
	}
}
