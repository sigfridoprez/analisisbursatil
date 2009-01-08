package test;

import mx.com.infraestructura.exceptions.BusinessException;
import mx.com.infraparseohtml.conftablas.exception.PaginaSinDatosException;

public interface TestSrv {
	public void testCarga()throws BusinessException, PaginaSinDatosException;
}
