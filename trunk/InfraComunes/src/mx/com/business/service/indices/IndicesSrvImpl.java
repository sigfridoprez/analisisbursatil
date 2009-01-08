package mx.com.business.service.indices;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import mx.com.analisispreciosmercado.conf.Indices;
import mx.com.business.dao.indices.IndicesDAO;
import mx.com.infraestructura.exceptions.BusinessException;
import mx.com.infraestructura.exceptions.DataBaseException;

public class IndicesSrvImpl implements IndicesSrv {
	private Logger logger = Logger.getLogger(this.getClass());
	IndicesDAO indicesDAO;

	public void txInsertLstIndices(List<Indices> lstIndices)
			throws BusinessException {
		if (lstIndices != null) {
			for (Indices vo : lstIndices) {
				txInsertIndices(vo);
			}
		}
	}

	public void txInsertIndices(Indices indices) throws BusinessException {
		try {
			if (indices != null) {
				indicesDAO.txInsertIndices(indices);
			}
		} catch (DataBaseException e) {
			e.printStackTrace();
			logger.error("Error al insertar el Indice", e);
			throw new BusinessException("Error al insertar el Indice ERROR:: "
					+ e.getMessage());
		}
	}
	
	public Indices getIndice(Date fecha, String indice)
			throws BusinessException {
		try {
			return indicesDAO.getIndice(fecha, indice);
		} catch (DataBaseException e) {
			e.printStackTrace();
			throw new BusinessException("Error::" + e.getMessage());
		}
	}
	
	public IndicesDAO getIndicesDAO() {
		return indicesDAO;
	}

	public void setIndicesDAO(IndicesDAO indicesDAO) {
		this.indicesDAO = indicesDAO;
	}

	public List<Indices> getIndices(String indice, Date fechaInicio,
			Date fechaFin, char diaHabil) throws BusinessException {
		try {
			return indicesDAO.getIndices(indice, fechaInicio, fechaFin, diaHabil);
		} catch (DataBaseException e) {
			e.printStackTrace();
			throw new BusinessException("ERROR:::" + e.getMessage());
		}
	}

}
