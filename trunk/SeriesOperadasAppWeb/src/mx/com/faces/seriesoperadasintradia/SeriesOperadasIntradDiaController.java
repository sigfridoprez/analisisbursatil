package mx.com.faces.seriesoperadasintradia;

import java.util.List;

import org.apache.log4j.Logger;

import mx.com.business.mainservice.seriesoperadasintradia.SeriesOperadasIntradiaMainSrv;
import mx.com.faces.seriesoperadas.util.SeriesOperadasList;
import mx.com.infra.SimpleController;
import mx.com.infraestructura.exceptions.BusinessException;

public class SeriesOperadasIntradDiaController  extends SimpleController{
	private Logger logger = Logger.getLogger(this.getClass());
	private List<SeriesOperadasList> lstSeriesOperadas;
	private SeriesOperadasIntradiaMainSrv seriesOperadasIntradiaMainSrv;
	
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

	private void setListaSeriesOperadasGrid() throws BusinessException{
		this.setLstSeriesOperadas(seriesOperadasIntradiaMainSrv.getListaSeriesOperadasIntradiaHoy());
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
}
