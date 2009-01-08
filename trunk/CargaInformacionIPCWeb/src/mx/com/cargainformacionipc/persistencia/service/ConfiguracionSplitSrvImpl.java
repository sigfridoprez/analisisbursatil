package mx.com.cargainformacionipc.persistencia.service;

import mx.com.analisispreciosmercado.conf.ConfiguracionSplit;
import mx.com.cargainformacionipc.persistencia.dao.ConfiguracionSplitDAO;
import mx.com.infraestructura.exceptions.BusinessException;
import mx.com.infraestructura.exceptions.DataBaseException;

public class ConfiguracionSplitSrvImpl implements ConfiguracionSplitSrv {
	private ConfiguracionSplitDAO configuracionSplitDAO;
	
	public ConfiguracionSplit getConfiguracionSplit(String emisora,
			String serie) throws BusinessException {
		// TODO Auto-generated method stub
		try {
			return configuracionSplitDAO.getConfiguracion(emisora, serie);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("Error:::" + e.getMessage());
		}
	}

	public void txActualizaConfiguracionSplit(ConfiguracionSplit conf)
			throws BusinessException {
		// TODO Auto-generated method stub
		try {
			configuracionSplitDAO.txActualizaConfiguracionSplit(conf);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("Error::"+e.getMessage());
		}
	}

	public ConfiguracionSplitDAO getConfiguracionSplitDAO() {
		return configuracionSplitDAO;
	}

	public void setConfiguracionSplitDAO(ConfiguracionSplitDAO configuracionSplitDAO) {
		this.configuracionSplitDAO = configuracionSplitDAO;
	}

}
