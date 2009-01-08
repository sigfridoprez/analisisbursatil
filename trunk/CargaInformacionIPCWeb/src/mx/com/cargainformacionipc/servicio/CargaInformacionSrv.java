package mx.com.cargainformacionipc.servicio;

import mx.com.infraestructura.exceptions.BusinessException;
import mx.com.infraparseohtml.conftablas.exception.PaginaSinDatosException;

public interface CargaInformacionSrv {
	public void cargaInformacion()throws BusinessException, PaginaSinDatosException;
}
