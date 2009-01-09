package mx.com.faces.seriesoperadasintradia;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import mx.com.business.mainservice.seriesoperadasintradia.SeriesOperadasIntradiaMainSrv;
import mx.com.faces.seriesoperadas.util.SeriesOperadasList;
import mx.com.infra.SimpleController;
import mx.com.infraestructura.exceptions.BusinessException;
import mx.com.util.Constantes;
import mx.com.util.Utils;

public class SeriesOperadasIntradDiaController  extends SimpleController{
	private Logger logger = Logger.getLogger(this.getClass());
	private List<SeriesOperadasList> lstSeriesOperadas;
	private List<SeriesOperadasList> lstSeriesOperadasSeleccionadas;
	private SeriesOperadasIntradiaMainSrv seriesOperadasIntradiaMainSrv;
	private String emisora;
	private String serie;
	private boolean pollEnabled;
	private String strJSON;
	private Utils utils;
	
	public SeriesOperadasIntradDiaController(){
		utils = new Utils();
	}
	
	@Override
	public String cargaPagina() {
		logger.debug("Inicia doForm");
		
		try {
			logger.debug("Cargamos la lista de series operadas");
			setListaSeriesOperadasGrid();			
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.debug("Error:::" + e.getMessage());
		}
		logger.debug("Fin de doForm");
		return "exito";
	}
	
	public void doGraficar(){
		logger.debug("En doGraficar");
		HashMap<String, Object> hsmRespuesta;
		
		try {
			hsmRespuesta = seriesOperadasIntradiaMainSrv.getGrafica(this.emisora,this.serie);
			if(hsmRespuesta!=null){
				strJSON = (String)hsmRespuesta.get(Constantes.SERIE_GRAFICAR);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("Error en doGraficar", e);
		}
	}
	
	private void setListaSeriesOperadasGrid() throws BusinessException{
		this.setLstSeriesOperadas(seriesOperadasIntradiaMainSrv.getListaSeriesOperadasIntradiaHoy());
		this.setLstSeriesOperadasSeleccionadas(this.getLstSeriesOperadas());
	}

	public List<SeriesOperadasList> getLstSeriesOperadas() {
		return lstSeriesOperadas;
	}

	public void setLstSeriesOperadas(List<SeriesOperadasList> lstSeriesOperadas) {
		this.lstSeriesOperadas = lstSeriesOperadas;
	}

	public SeriesOperadasIntradiaMainSrv getSeriesOperadasIntradiaMainSrv() {
		return seriesOperadasIntradiaMainSrv;
	}

	public void setSeriesOperadasIntradiaMainSrv(
			SeriesOperadasIntradiaMainSrv seriesOperadasIntradiaMainSrv) {
		this.seriesOperadasIntradiaMainSrv = seriesOperadasIntradiaMainSrv;
	}

	public List<SeriesOperadasList> getLstSeriesOperadasSeleccionadas() {
		return lstSeriesOperadasSeleccionadas;
	}

	public void setLstSeriesOperadasSeleccionadas(
			List<SeriesOperadasList> lstSeriesOperadasSeleccionadas) {
		this.lstSeriesOperadasSeleccionadas = lstSeriesOperadasSeleccionadas;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getEmisora() {
		return emisora;
	}

	public void setEmisora(String emisora) {
		this.emisora = emisora;
	}

	public boolean isPollEnabled() {
		return pollEnabled;
	}

	public void setPollEnabled(boolean pollEnabled) {
		this.pollEnabled = pollEnabled;
	}
	
	public String getStrJSON() {
		return strJSON;
	}

	public void setStrJSON(String strJSON) {
		this.strJSON = strJSON;
	}

	public String getFecha() {
		return utils.getFecha(new Date(), "dd/MM/yyyy hh:mm:ss");
	}
}