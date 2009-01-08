package mx.com.cargainformacionipc.persistencia.dao;

import java.util.List;

import mx.com.analisispreciosmercado.conf.CiConfLinks;
import mx.com.infraestructura.exceptions.DataBaseException;

public interface CiConfLinksDAO {

	public CiConfLinks getCiConfLinks(Integer idConf)throws DataBaseException;
	public List<CiConfLinks> getLstCiConfLinks()throws DataBaseException;
	
}
