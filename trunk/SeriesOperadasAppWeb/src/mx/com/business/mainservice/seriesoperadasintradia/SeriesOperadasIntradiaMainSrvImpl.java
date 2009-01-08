package mx.com.business.mainservice.seriesoperadasintradia;

import java.util.List;

import org.apache.log4j.Logger;

import mx.com.analisispreciosmercado.conf.SeriesOperadasIntraDia;
import mx.com.business.service.seriesoperadas.SeriesOperadasIntraDiaSrv;
import mx.com.business.service.util.UtilSrv;
import mx.com.faces.seriesoperadas.util.SeriesOperadasList;
import mx.com.faces.seriesoperadasintradia.util.SeriesIntraDiaBuilder;
import mx.com.infraestructura.exceptions.BusinessException;

public class SeriesOperadasIntradiaMainSrvImpl implements
		SeriesOperadasIntradiaMainSrv {
	private Logger logger = Logger.getLogger(this.getClass());
	private SeriesOperadasIntraDiaSrv seriesOperadasIntraDiaSrv;
	private UtilSrv utilSrv;
	
	public List<SeriesOperadasList> getListaSeriesOperadasIntradiaHoy()
			throws BusinessException {
		logger.debug("En getListaSeriesOperadasIntradiaHoy");
		int intIdCarga = 0;
		List<SeriesOperadasIntraDia> lstSeriesOperadas;
		SeriesIntraDiaBuilder builder = new SeriesIntraDiaBuilder();
		
		//Primero recuperamos el Id de la ultima carga
		intIdCarga = utilSrv.getMaximaSerieIntraDiaCarga();
		//Obtenemos esta carga
		lstSeriesOperadas = seriesOperadasIntraDiaSrv.getListaSeriesOperadasIntraDia(intIdCarga);
		//Lo transformamos de List<SeriesOperadasList> a List<SeriesOperadasList>
		
		return builder.getListaSeriesOperadasList(lstSeriesOperadas);
	}

	public UtilSrv getUtilSrv() {
		return utilSrv;
	}

	public void setUtilSrv(UtilSrv utilSrv) {
		this.utilSrv = utilSrv;
	}

	public SeriesOperadasIntraDiaSrv getSeriesOperadasIntraDiaSrv() {
		return seriesOperadasIntraDiaSrv;
	}

	public void setSeriesOperadasIntraDiaSrv(
			SeriesOperadasIntraDiaSrv seriesOperadasIntraDiaSrv) {
		this.seriesOperadasIntraDiaSrv = seriesOperadasIntraDiaSrv;
	}

}
