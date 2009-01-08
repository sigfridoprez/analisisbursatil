package mx.com.cargainformacionipc.persistencia.service;

import java.util.List;

import mx.com.analisispreciosmercado.conf.CiConfLinks;
import mx.com.infraestructura.exceptions.BusinessException;

public interface CiConfLinksSrv {
	
	public List<CiConfLinks> getListCiConfLinks()throws BusinessException;
	public CiConfLinks getCiConfLinks(Integer id)throws BusinessException;
	
}
