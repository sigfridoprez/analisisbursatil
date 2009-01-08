package mx.com.cargainformacionipc.persistencia.dao;

import mx.com.analisispreciosmercado.conf.ConfiguracionSplit;
import mx.com.infraestructura.exceptions.DataBaseException;

public interface ConfiguracionSplitDAO {
	public ConfiguracionSplit getConfiguracion(String emisora,String serie)throws DataBaseException;
	public void txActualizaConfiguracionSplit(ConfiguracionSplit conf)throws DataBaseException;
}
