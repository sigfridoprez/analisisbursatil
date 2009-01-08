package mx.com.cargainformacionipc.persistencia.dao;

import java.util.List;

import org.apache.log4j.Logger;

import mx.com.analisispreciosmercado.conf.DiasFestivos;
import mx.com.infraestructura.dao.BaseDAO;
import mx.com.infraestructura.exceptions.DataBaseException;

public class DiasFestivosDAOImpl extends BaseDAO implements DiasFestivosDAO{ 
	private Logger logger = Logger.getLogger(this.getClass());

	@SuppressWarnings("unchecked")
	public List<DiasFestivos> getListaDiasFestivos() throws DataBaseException {
		logger.debug("En getListaDiasFestivos");
		return getSession().createCriteria(DiasFestivos.class).list();
	}

}
