package mx.com.cargainformacionipc.persistencia.service;

import java.util.List;

import org.apache.log4j.Logger;


import mx.com.analisispreciosmercado.conf.CiConfLinks;
import mx.com.cargainformacionipc.persistencia.dao.CiConfLinksDAO;
import mx.com.infraestructura.exceptions.BusinessException;
import mx.com.infraestructura.exceptions.DataBaseException;

public class CiConfLinksSrvImpl implements CiConfLinksSrv {
	private Logger logger = Logger.getLogger(this.getClass());
	private CiConfLinksDAO ciConfLinksDAO;
	
	public List<CiConfLinks> getListCiConfLinks()throws BusinessException{
		logger.debug("En getListCiConfLinks");
		try {
			return ciConfLinksDAO.getLstCiConfLinks();
		} catch (DataBaseException e) {
			e.printStackTrace();
			logger.error("getListCiConfLinks Error al cargar la lista de configuraciones",e);
			throw new BusinessException("getListCiConfLinks Error al cargar la lista de configuraciones ERROR::" + e.getMessage());
		}
	}

	public CiConfLinks getCiConfLinks(Integer id)throws BusinessException{
		logger.debug("En getCiConfLinks");
		try {
			return ciConfLinksDAO.getCiConfLinks(id);
		} catch (DataBaseException e) {
			e.printStackTrace();
			logger.error("En getCiConfLinks error al cargar el VO de configuraciones",e);
			throw new BusinessException("En getCiConfLinks error al cargar el VO de configuraciones ERROR::" + e.getMessage());
		}
	}
	
	public CiConfLinksDAO getCiConfLinksDAO() {
		return ciConfLinksDAO;
	}

	public void setCiConfLinksDAO(CiConfLinksDAO ciConfLinksDAO) {
		this.ciConfLinksDAO = ciConfLinksDAO;
	}
}
