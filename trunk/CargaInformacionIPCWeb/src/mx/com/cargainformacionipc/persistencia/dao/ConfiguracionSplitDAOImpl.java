package mx.com.cargainformacionipc.persistencia.dao;

import java.util.Date;

import mx.com.analisispreciosmercado.conf.ConfiguracionSplit;
import mx.com.analisispreciosmercado.conf.ConfiguracionSplitId;
import mx.com.infraestructura.dao.BaseDAO;
import mx.com.infraestructura.exceptions.DataBaseException;

public class ConfiguracionSplitDAOImpl extends BaseDAO implements ConfiguracionSplitDAO {

	public ConfiguracionSplit getConfiguracion(String emisora, String serie)
			throws DataBaseException {
		ConfiguracionSplit vo = null;
		ConfiguracionSplitId id = null;
		
		Object[] ob = (Object[])this.getSession().createSQLQuery("select * from configuracion_split " +
		"where emisora=:emi and serie=:se and fecha_aplicacion is null").setString("emi", emisora).setString("se",serie).uniqueResult();
		
		if(ob!=null){
			vo = new ConfiguracionSplit();
			id = new ConfiguracionSplitId();
			
			id.setFechaAlta((Date)ob[0]);
			id.setEmisora((String)ob[1]);
			id.setSerie((String)ob[2]);
			
			vo.setId(id);
			vo.setFechaAplicacion((Date)ob[3]);
			vo.setSplit((Integer)ob[4]);			
		}
		
		return vo;
	}

	public void txActualizaConfiguracionSplit(ConfiguracionSplit conf)
			throws DataBaseException {
		// TODO Auto-generated method stub
		this.update(conf);
	}

}
