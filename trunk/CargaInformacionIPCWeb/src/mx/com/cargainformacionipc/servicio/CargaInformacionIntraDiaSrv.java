package mx.com.cargainformacionipc.servicio;

import mx.com.infraestructura.exceptions.BusinessException;
import mx.com.infraparseohtml.conftablas.exception.PaginaSinDatosException;

public interface CargaInformacionIntraDiaSrv {
	public void cargaInformacion()throws BusinessException, PaginaSinDatosException;
}
