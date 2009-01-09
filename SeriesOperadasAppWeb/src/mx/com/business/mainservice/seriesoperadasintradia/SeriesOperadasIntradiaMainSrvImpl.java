package mx.com.business.mainservice.seriesoperadasintradia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import mx.com.analisispreciosmercado.conf.SeriesOperadasIntraDia;
import mx.com.business.service.seriesoperadas.SeriesOperadasIntraDiaSrv;
import mx.com.business.service.util.UtilSrv;
import mx.com.faces.seriesoperadas.util.SerieGraficarVO;
import mx.com.faces.seriesoperadas.util.SeriesOperadasList;
import mx.com.faces.seriesoperadasintradia.util.SeriesIntraDiaBuilder;
import mx.com.infraestructura.exceptions.BusinessException;
import mx.com.util.Constantes;
import mx.com.util.UtilsGrafica;

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

	/**
	 * Metodo que ontiene la grafica por Emisora-Serie de los datos de Intradia
	 */
	public HashMap<String, Object> getGrafica(String emisora, String serie)throws BusinessException {
		logger.debug("En getGrafica");
		HashMap<String, Object> hsmReturn = new HashMap<String, Object>();
		StringBuilder sbGrafica = null;
		List<SeriesOperadasIntraDia> lstGraficar = null;
		int intNoCols = 0;
		UtilsGrafica util = new UtilsGrafica();
		
		//Obtenemos los datos a graficar
		lstGraficar = this.seriesOperadasIntraDiaSrv.getListaSeriesOperadasIntraDia(emisora, serie);
		if(lstGraficar!=null && !lstGraficar.isEmpty()){
			sbGrafica = new StringBuilder("{");
			intNoCols = lstGraficar.size();
			
			sbGrafica.append(util.getSeries(getSeriesGraficar(lstGraficar)));
			
			sbGrafica.append(",");
			sbGrafica.append(util.getOpciones(intNoCols));
			sbGrafica.append("}");
			hsmReturn.put(Constantes.SERIE_GRAFICAR, sbGrafica.toString());
		}
		
		return hsmReturn;
	}
	
	private List<SerieGraficarVO> getSeriesGraficar(List<SeriesOperadasIntraDia> lstGraficar){
		logger.debug("En getSeriesGraficar");
		List<SerieGraficarVO> lstReturn = new ArrayList<SerieGraficarVO>();
		SerieGraficarVO sbPrecio;
		SerieGraficarVO sbCierre;
		
		logger.debug("Recorremos la lista de series a graficar para obtener los valores");
		sbPrecio = new SerieGraficarVO();
		sbCierre = new SerieGraficarVO();
		
		sbPrecio.setStrNombreSerie("Precio");
		sbCierre.setStrNombreSerie("Cierre");
		
		for(SeriesOperadasIntraDia serieOBJ:lstGraficar){
			sbCierre.setValores(serieOBJ.getPpp().doubleValue(), serieOBJ.getId().getIdCarga());
			sbPrecio.setValores(serieOBJ.getUltimo().doubleValue(), serieOBJ.getId().getIdCarga());
		}
		
		lstReturn.add(sbCierre);
		lstReturn.add(sbPrecio);
		
		return lstReturn;
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
