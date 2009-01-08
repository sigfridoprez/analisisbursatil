package mx.com.cargainformacionipc.persistencia.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;


import mx.com.analisispreciosmercado.conf.CiConfLinks;
import mx.com.infraestructura.dao.BaseDAO;
import mx.com.infraestructura.exceptions.DataBaseException;

public class CiConfLinksDAOImpl extends BaseDAO implements CiConfLinksDAO {
	private Logger logger = Logger.getLogger(this.getClass());

	public CiConfLinks getCiConfLinks(Integer idConf) throws DataBaseException {
		logger.debug("En getCiConfLinks");
		return (CiConfLinks) getSession().createCriteria(CiConfLinks.class).add(Restrictions.eq("idConf", idConf)).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<CiConfLinks> getLstCiConfLinks()throws DataBaseException{
		logger.debug("En getLstCiConfLinks");
		
		return getSession().createCriteria(CiConfLinks.class).list();
	}

}
