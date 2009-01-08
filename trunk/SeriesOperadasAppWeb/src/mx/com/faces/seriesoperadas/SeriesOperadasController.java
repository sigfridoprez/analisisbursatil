package mx.com.faces.seriesoperadas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.com.business.mainservice.seriesoperadas.SeriesOperadasMainSrv;
import mx.com.faces.seriesoperadas.util.SeriesOperadasList;
import mx.com.infra.SimpleController;
import mx.com.infraestructura.exceptions.BusinessException;

import org.apache.log4j.Logger;

public class SeriesOperadasController extends SimpleController{
	private Logger logger = Logger.getLogger(this.getClass());
	private List<SeriesOperadasList> lstSeriesOperadas;
	private SeriesOperadasMainSrv seriesOperadasMainSrv;
	private Date desde;
	private Date hasta;
	private ArrayList<SelectItem> lstOrigen = null;
	
	public SeriesOperadasController(){
		lstOrigen = new ArrayList<SelectItem>();
	}
	
	public String cargaPagina(){
		logger.debug("Inicia doForm");
		List<String> lstServicio;
		
		try {
			logger.debug("Cargamos la lista de series operadas");
			this.desde=new Date();
			setListaSeriesOperadasGrid(this.desde,null);
			lstServicio =  seriesOperadasMainSrv.getListaJList();
			
			if(lstServicio!=null){
				lstOrigen.clear();
				for(String val:lstServicio){
					lstOrigen.add(new SelectItem(val,val));
				}
			}
			
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.debug("Error:::" + e.getMessage());
		}
		logger.debug("Fin de doForm");
		return "exito";
	}

	public void doBuscar(){
		logger.debug("Buscando");
		try {
			logger.debug("Cargamos la lista de series operadas");
			setListaSeriesOperadasGrid(this.desde,this.hasta);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.debug("Error:::" + e.getMessage());
		}
		logger.debug("Fin Buscar");
	}
	
	private void setListaSeriesOperadasGrid(Date desde,Date hasta) throws BusinessException{
		this.setLstSeriesOperadas(seriesOperadasMainSrv.getListaSeriesOperadas(null,null,desde,hasta));
	}
	public List<SeriesOperadasList> getLstSeriesOperadas() {
		return lstSeriesOperadas;
	}
	public void setLstSeriesOperadas(List<SeriesOperadasList> lstSeriesOperadas) {
		this.lstSeriesOperadas = lstSeriesOperadas;
	}
	public SeriesOperadasMainSrv getSeriesOperadasMainSrv() {
		return seriesOperadasMainSrv;
	}
	public void setSeriesOperadasMainSrv(SeriesOperadasMainSrv seriesOperadasMainSrv) {
		this.seriesOperadasMainSrv = seriesOperadasMainSrv;
	}

	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Date getHasta() {
		return hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}

	public ArrayList<SelectItem> getLstOrigen() {
		return lstOrigen;
	}

	public void setLstOrigen(ArrayList<SelectItem> lstOrigen) {
		this.lstOrigen = lstOrigen;
	}
}
