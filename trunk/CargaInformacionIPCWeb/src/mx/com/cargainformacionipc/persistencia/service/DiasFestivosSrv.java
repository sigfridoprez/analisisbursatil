package mx.com.cargainformacionipc.persistencia.service;

import java.util.List;

import mx.com.analisispreciosmercado.conf.DiasFestivos;
import mx.com.infraestructura.exceptions.BusinessException;

public interface DiasFestivosSrv {
	List<DiasFestivos> getListaDiasFestivos()throws BusinessException;
}
