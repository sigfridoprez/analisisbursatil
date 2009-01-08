package mx.com.business.service.util;

import java.util.Date;

import org.apache.log4j.Logger;

import mx.com.analisispreciosmercado.conf.IndicesIntraDia;
import mx.com.analisispreciosmercado.conf.SeriesOperadasIntraDia;
import mx.com.business.dao.util.UtilDAO;
import mx.com.business.service.indices.IndicesIntraDiaSrv;
import mx.com.business.service.seriesoperadas.SeriesOperadasIntraDiaSrv;
import mx.com.infraestructura.exceptions.BusinessException;
import mx.com.infraestructura.exceptions.DataBaseException;

public class UtilSrvImpl implements UtilSrv {
	private Logger logger = Logger.getLogger(this.getClass());
	private UtilDAO utilDAO;
	private SeriesOperadasIntraDiaSrv seriesOperadasIntraDiaSrv;
	private IndicesIntraDiaSrv indicesIntraDiaSrv;

	public SeriesOperadasIntraDia getSerieIntradiaAnterior(int idCargaActual,String emisora,
			String serie) throws BusinessException {
		int intMaxIdCarga = 0;
		SeriesOperadasIntraDia tmp = null;
		
		try {
			//Obtenemos el DE LA CARGA DE HOY ANTERIOR
			if(idCargaActual > 0){
				intMaxIdCarga = idCargaActual-1;	
			}else{
				intMaxIdCarga = 0;
			}			 
			tmp = seriesOperadasIntraDiaSrv.getSerieOperadaIntraDia(intMaxIdCarga,emisora, serie, new Date());
			
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("Error al obtener el MAX ID_CARGA", e);
			logger.debug("Error al obtener el MAX ID_CARGA:::" + e.getMessage());
			throw new BusinessException("Error al obtener el MAX ID_CARGA:::" + e.getMessage());
		}
		return tmp;
	}

	public int getMaximaSerieIntraDiaCarga()
			throws BusinessException {
		try {
			return utilDAO.getMaximaSerieIntraDiaCarga();
		} catch (DataBaseException e) {
			e.printStackTrace();
			logger.error("Error al obtener el MAX ID_CARGA", e);
			throw new BusinessException("Error al obtener el MAX ID_CARGA:::" + e.getMessage());
		}
	}

	
	public IndicesIntraDia getIndiceIntradiaAnterior(int idCargaActual,
			String indice) throws BusinessException {
		int intMaxIdCarga = 0;
		IndicesIntraDia tmp = null;
		
		try {
			//Obtenemos el DE LA CARGA DE HOY ANTERIOR
			if(idCargaActual > 0){
				intMaxIdCarga = idCargaActual-1;	
			}else{
				intMaxIdCarga = 0;
			}			 
			tmp = indicesIntraDiaSrv.getIndiceIntraDia(intMaxIdCarga,new Date(),indice);
			
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("Error al obtener el MAX ID_CARGA", e);
			logger.debug("Error al obtener el MAX ID_CARGA:::" + e.getMessage());
			throw new BusinessException("Error al obtener el MAX ID_CARGA:::" + e.getMessage());
		}
		return tmp;
	}

	public int getMaximaIndiceIntraDiaCarga()
			throws BusinessException {
		try {
			return utilDAO.getMaximoIndiceIntraDiaCarga();
		} catch (DataBaseException e) {
			e.printStackTrace();
			logger.error("Error al obtener el MAX ID_CARGA", e);
			throw new BusinessException("Error al obtener el MAX ID_CARGA:::" + e.getMessage());
		}
	}
	
	public SeriesOperadasIntraDiaSrv getSeriesOperadasIntraDiaSrv() {
		return seriesOperadasIntraDiaSrv;
	}

	public void setSeriesOperadasIntraDiaSrv(
			SeriesOperadasIntraDiaSrv seriesOperadasIntraDiaSrv) {
		this.seriesOperadasIntraDiaSrv = seriesOperadasIntraDiaSrv;
	}

	public IndicesIntraDiaSrv getIndicesIntraDiaSrv() {
		return indicesIntraDiaSrv;
	}

	public void setIndicesIntraDiaSrv(IndicesIntraDiaSrv indicesIntraDiaSrv) {
		this.indicesIntraDiaSrv = indicesIntraDiaSrv;
	}

	public UtilDAO getUtilDAO() {
		return utilDAO;
	}

	public void setUtilDAO(UtilDAO utilDAO) {
		this.utilDAO = utilDAO;
	}
}
