package mx.com.business.service.seriesoperadas;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import mx.com.analisispreciosmercado.conf.SeriesOperadas;
import mx.com.business.dao.seriesoperadas.SeriesOperadasDAO;
import mx.com.infraestructura.exceptions.BusinessException;
import mx.com.infraestructura.exceptions.DataBaseException;

public class SeriesOperadasSrvImpl implements SeriesOperadasSrv {
	private Logger logger = Logger.getLogger(this.getClass());
	private SeriesOperadasDAO seriesOperadasDAO;

	public List<SeriesOperadas> getListaSeriesOperadas(String emisora,
			String serie, Date fechaInicio, Date fechaFin,char diaHabil,boolean blnGrafica)
			throws BusinessException {
		logger.debug("in getListaSeriesOperadas");

		try {
			return seriesOperadasDAO.getSeriesOperadas(emisora, serie,
					fechaInicio, fechaFin,diaHabil,blnGrafica);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("Error::" + e.getMessage());
		}
	}

	public SeriesOperadasDAO getSeriesOperadasDAO() {
		return seriesOperadasDAO;
	}

	public void setSeriesOperadasDAO(SeriesOperadasDAO seriesOperadasDAO) {
		this.seriesOperadasDAO = seriesOperadasDAO;
	}

	public List<String> getListEmisora() throws BusinessException {
		logger.debug("in getListEmisora");

		try {
			return seriesOperadasDAO.getListEmisora();
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("Error::" + e.getMessage());
		}
	}

	public List<String> getListSerie(String emisora) throws BusinessException {
		logger.debug("in getListEmisora");

		try {
			return seriesOperadasDAO.getListSerie(emisora);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("Error::" + e.getMessage());
		}
	}

	public List<String> getListaJList() throws BusinessException {
		try {
			return seriesOperadasDAO.getListaJList();
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("Error:::" + e.getMessage());
		}
	}

	public void txInsertSeriesOperadas(SeriesOperadas seriesOperadas)
			throws BusinessException {
		try {
			if (seriesOperadas != null) {
				seriesOperadasDAO.txInsertSeriesOperadas(seriesOperadas);
			}
		} catch (DataBaseException e) {
			e.printStackTrace();
			logger.error("Error al insertar la serie operada", e);
			throw new BusinessException(
					"Error al insertar la serie operada ERROR:: "
							+ e.getMessage());
		}
	}

	public void txInsertLstSeriesOperadas(List<SeriesOperadas> lstSeriesOperadas)
			throws BusinessException {
		if (lstSeriesOperadas != null) {
			for (SeriesOperadas vo : lstSeriesOperadas) {
				txInsertSeriesOperadas(vo);
			}
		}
	}

	public SeriesOperadas getSerieOperada(String emisora, String serie,
			Date d) throws BusinessException {
		try {
			return seriesOperadasDAO.getSerieOperada(emisora, serie, d);
		} catch (DataBaseException e) {
			e.printStackTrace();
			throw new BusinessException("Error::" + e.getMessage());
		}
	}

	public void txUpdateLstSeriesOperadas(List<SeriesOperadas> lstSeriesOperadas)
			throws BusinessException {
		for(SeriesOperadas ob:lstSeriesOperadas){
			try {
				seriesOperadasDAO.txUpdateSeriesOperadas(ob);
			} catch (DataBaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new BusinessException("ERROR::::" + e.getMessage());
			}
		}
	}

	public void txUpdateSeriesOperadas(SeriesOperadas seriesOperadas)
			throws BusinessException {
		try {
			seriesOperadasDAO.txUpdateSeriesOperadas(seriesOperadas);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
