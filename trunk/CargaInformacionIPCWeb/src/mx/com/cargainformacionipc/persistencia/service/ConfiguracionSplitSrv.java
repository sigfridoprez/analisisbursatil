package mx.com.cargainformacionipc.persistencia.service;

import mx.com.analisispreciosmercado.conf.ConfiguracionSplit;
import mx.com.infraestructura.exceptions.BusinessException;

public interface ConfiguracionSplitSrv {
	public ConfiguracionSplit getConfiguracionSplit(String emmisora,String serie)throws BusinessException;
	public void txActualizaConfiguracionSplit(ConfiguracionSplit conf)throws BusinessException;
}
