package mx.com.business.service.seriesoperadas;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import mx.com.analisispreciosmercado.conf.SeriesOperadasIntraDia;
import mx.com.business.dao.seriesoperadas.SeriesOperadasIntraDiaDAO;
import mx.com.infraestructura.exceptions.BusinessException;
import mx.com.infraestructura.exceptions.DataBaseException;

public class SeriesOperadasIntraDiaSrvImpl implements SeriesOperadasIntraDiaSrv {
	private Logger logger = Logger.getLogger(this.getClass());
	private SeriesOperadasIntraDiaDAO seriesOperadasIntraDiaDAO;

	public List<String> getListEmisora() throws BusinessException {
		logger.debug("in getListEmisora");

		try {
			return seriesOperadasIntraDiaDAO.getListEmisora();
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("Error::" + e.getMessage());
		}
	}

	public List<String> getListSerie(String emisora) throws BusinessException {
		logger.debug("in getListEmisora");

		try {
			return seriesOperadasIntraDiaDAO.getListSerie(emisora);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("Error::" + e.getMessage());
		}
	}

	public List<String> getListaJList() throws BusinessException {
		try {
			return seriesOperadasIntraDiaDAO.getListaJList();
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("Error:::" + e.getMessage());
		}
	}

	public List<SeriesOperadasIntraDia> getListaSeriesOperadasIntraDia(
			String emisora, String serie, Date fechaInicio, Date fechaFin,
			char diaHabil, boolean blnGrafica) throws BusinessException {
		logger.debug("in getListaSeriesOperadasIntraDia");

		try {
			return seriesOperadasIntraDiaDAO.getSeriesOperadasIntraDia(emisora, serie,
					fechaInicio, fechaFin,diaHabil,blnGrafica);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("Error::" + e.getMessage());
		}
	}

	public SeriesOperadasIntraDia getSerieOperadaIntraDia(int idCarga,String emisora,
			String serie, Date date) throws BusinessException {
		try {
			return seriesOperadasIntraDiaDAO.getSerieOperadaIntraDia(idCarga,emisora, serie, date);
		} catch (DataBaseException e) {
			e.printStackTrace();
			throw new BusinessException("Error::" + e.getMessage());
		}
	}

	public void txInsertLstSeriesOperadasIntraDia(
			List<SeriesOperadasIntraDia> lstSeriesOperadas)
			throws BusinessException {
		if (lstSeriesOperadas != null) {
			for (SeriesOperadasIntraDia vo : lstSeriesOperadas) {
				txInsertSeriesOperadas(vo);
			}
		}
	}
	
	public void txInsertSeriesOperadas(SeriesOperadasIntraDia seriesOperadas)
		throws BusinessException {
		try {
			if (seriesOperadas != null) {
				seriesOperadasIntraDiaDAO.txInsertSeriesOperadasIntraDia(seriesOperadas);
			}
		} catch (DataBaseException e) {
			e.printStackTrace();
			logger.error("Error al insertar la serie operada", e);
			throw new BusinessException(
					"Error al insertar la serie operada ERROR:: "
							+ e.getMessage());
		}
	}

	public void txUpdateLstSeriesOperadasIntraDia(
			List<SeriesOperadasIntraDia> lstSeriesOperadas)
			throws BusinessException {
		for(SeriesOperadasIntraDia ob:lstSeriesOperadas){
			try {
				seriesOperadasIntraDiaDAO.txUpdateSeriesOperadasIntraDia(ob);
			} catch (DataBaseException e) {
				e.printStackTrace();
				throw new BusinessException("ERROR::::" + e.getMessage());
			}
		}
	}

	public void txUpdateSeriesOperadasIntraDia(
			SeriesOperadasIntraDia seriesOperadas) throws BusinessException {
		try {
			seriesOperadasIntraDiaDAO.txUpdateSeriesOperadasIntraDia(seriesOperadas);
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Borra la información intra dia que se encuentre a una fecha dada
	 * @param fecha Fecha a borrar la información
	 */
	public int txBorrarSerieIntraDia(Date fecha)throws BusinessException{
		int intResult = 0;
		
		try {
			intResult = seriesOperadasIntraDiaDAO.txBorrarSeriesOperadasIntraDia(fecha);
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		
		return intResult;
	}
	
	/**
	 * Recupera la ultima carga de la informaci˜n intradia
	 */
	public List<SeriesOperadasIntraDia> getListaSeriesOperadasIntraDia(
			int idCarga) throws BusinessException {
		logger.debug("getListaSeriesOperadasIntraDia:::" + "idCarga::" + idCarga);
		
		try {
			return seriesOperadasIntraDiaDAO.getListaSeriesOperadasIntraDia(idCarga);
		} catch (DataBaseException e) {
			e.printStackTrace();
			throw new BusinessException("ERROR::::" + e.getMessage());
		}
	}
	
	public SeriesOperadasIntraDiaDAO getSeriesOperadasIntraDiaDAO() {
		return seriesOperadasIntraDiaDAO;
	}

	public void setSeriesOperadasIntraDiaDAO(
			SeriesOperadasIntraDiaDAO seriesOperadasIntraDiaDAO) {
		this.seriesOperadasIntraDiaDAO = seriesOperadasIntraDiaDAO;
	}

}
