package mx.com.cargainformacion.scheduling;

import mx.com.cargainformacionipc.servicio.CargaInformacionIntraDiaSrv;
import mx.com.infraestructura.exceptions.BusinessException;
import mx.com.infraparseohtml.conftablas.exception.PaginaSinDatosException;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class CargaInformacionIntraDiasJob extends QuartzJobBean {
	private Logger logger = Logger.getLogger(this.getClass());
	private CargaInformacionIntraDiaSrv cargaInformacionIntraDiaSrv;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		try {
			logger.debug("En CargaInformacionIntraDiasJob");
			logger.debug("Creamos una bitacora con la fecha de inicio");
			cargaInformacionIntraDiaSrv.cargaInformacion();
			logger.debug("Actualizamos la bitacora con la fecha de fin");
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("Error al ejecutar la tarea",e);
			logger.debug("Se actualiza la bitacora indicando el error");
			logger.debug("Se notifica via correo del error");
			logger.debug("Si el error es distinto a no existe informacion en la tabla, se trata de reejecutar el servicio");
		} catch (PaginaSinDatosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public CargaInformacionIntraDiaSrv getCargaInformacionIntraDiaSrv() {
		return cargaInformacionIntraDiaSrv;
	}

	public void setCargaInformacionIntraDiaSrv(
			CargaInformacionIntraDiaSrv cargaInformacionIntraDiaSrv) {
		this.cargaInformacionIntraDiaSrv = cargaInformacionIntraDiaSrv;
	}

}
