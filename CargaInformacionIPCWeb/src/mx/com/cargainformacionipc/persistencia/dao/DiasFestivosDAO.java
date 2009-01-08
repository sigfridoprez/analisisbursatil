package mx.com.cargainformacionipc.persistencia.dao;

import java.util.List;

import mx.com.analisispreciosmercado.conf.DiasFestivos;

public interface DiasFestivosDAO {
	public List<DiasFestivos> getListaDiasFestivos()throws mx.com.infraestructura.exceptions.DataBaseException;
}
