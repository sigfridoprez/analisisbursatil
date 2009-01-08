package mx.com.cargainformacionipc.persistencia.service;

import java.util.List;

import org.apache.log4j.Logger;

import mx.com.analisispreciosmercado.conf.DiasFestivos;
import mx.com.cargainformacionipc.persistencia.dao.DiasFestivosDAO;
import mx.com.infraestructura.exceptions.BusinessException;
import mx.com.infraestructura.exceptions.DataBaseException;

public class DiasFestivosSrvImpl implements DiasFestivosSrv {
	private Logger logger = Logger.getLogger(this.getClass());
	private DiasFestivosDAO diasFestivosDAO;
	
	public List<DiasFestivos> getListaDiasFestivos() throws BusinessException {
		// TODO Auto-generated method stub
		try {
			logger.debug("En getListaDiasFestivos");
			return diasFestivosDAO.getListaDiasFestivos();
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("Error:::" + e.getMessage());
		}
	}

	public DiasFestivosDAO getDiasFestivosDAO() {
		return diasFestivosDAO;
	}

	public void setDiasFestivosDAO(DiasFestivosDAO diasFestivosDAO) {
		this.diasFestivosDAO = diasFestivosDAO;
	}

}
