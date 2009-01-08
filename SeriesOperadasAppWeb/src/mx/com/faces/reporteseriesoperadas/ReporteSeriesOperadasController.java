package mx.com.faces.reporteseriesoperadas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ajax4jsf.event.AjaxEvent;
import org.apache.log4j.Logger;
import org.richfaces.component.AjaxSuggestionEvent;
import org.richfaces.component.html.HtmlSuggestionBox;

import mx.com.business.mainservice.seriesoperadas.SeriesOperadasMainSrv;
import mx.com.faces.reporteseriesoperadas.util.DataSuggestionBox;
import mx.com.faces.seriesoperadas.util.SeriesOperadasList;
import mx.com.infra.SimpleController;
import mx.com.infraestructura.exceptions.BusinessException;

public class ReporteSeriesOperadasController  extends SimpleController implements org.ajax4jsf.event.AjaxListener{
	private Logger logger = Logger.getLogger(this.getClass());
	private List<SeriesOperadasList> lstSeriesOperadas;
	private static SeriesOperadasMainSrv seriesOperadasMainSrv;
	private String emisora;
	private String serie;
	private Date desde;
	private Date hasta;
	
	//Para el rich suggestionbox
	private List<DataSuggestionBox> listEmisora;
	private List<DataSuggestionBox> listSerie;
	private String rules;
    
    public ReporteSeriesOperadasController(){
    	logger.debug("Constructor");
        this.rules = "none";
        logger.debug("Fuera del constructor");
    }
    
    public List<DataSuggestionBox> autocompleteEmisora(Object suggest) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage("SuggestionBox.autocomplete(" + suggest + ")");
        context.addMessage(null, message);
        
        String pref = (String)suggest;
        ArrayList<DataSuggestionBox> result = new ArrayList<DataSuggestionBox>();
        
        for(DataSuggestionBox elem : getListEmisora()){
            if ((elem != null && elem.getTexto().toLowerCase().indexOf(pref.toLowerCase()) == 0) || "".equals(pref)){
                result.add(elem);
            }        	
        }
        
        return result;
    }
    
    public List<DataSuggestionBox> autocompleteSerie(Object suggest) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage("SuggestionBox.autocomplete(" + suggest + ")");
        context.addMessage(null, message);
        String pref = "";
        ArrayList<DataSuggestionBox> result = new ArrayList<DataSuggestionBox>();
        
        if(suggest!=null){
        	pref = (String)suggest;
            
            if(getListSerie()!=null){
            	for(DataSuggestionBox elem : getListSerie()){
                    if ((elem != null && elem.getTexto().toLowerCase().indexOf(pref.toLowerCase()) == 0) || "".equals(pref)){
                        result.add(elem);
                    }        	
                }	
            }	
        }
        
        return result;
    }
    
    public void listaSerie(){
    	this.setListSerie(this.getAllData(this.emisora));
    	this.serie="";
    }
    
    public List<DataSuggestionBox> getAllData() {
        try {
			return this.getSeriesOperadasMainSrv().getListEmisora();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
    }
    
    public List<DataSuggestionBox> getAllData(String emisora) {
        try {
			return this.getSeriesOperadasMainSrv().getListSerie(emisora);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
    }
    
    public void processAjax(AjaxEvent event) {
		System.out.println("AjaxListener.processAjax()");
		if (event instanceof AjaxSuggestionEvent) {
			AjaxSuggestionEvent ajaxSuggestionEvent = (AjaxSuggestionEvent) event;
			
			if(ajaxSuggestionEvent.getSource() instanceof HtmlSuggestionBox){
				HtmlSuggestionBox sb = (HtmlSuggestionBox)ajaxSuggestionEvent.getSource();
				if(sb.getId().equals("suggestionBoxEmisora")){
					this.setListSerie(getAllData(ajaxSuggestionEvent.getSubmittedValue().toString()));
				}
			}
			System.out.println("Submitted value: " + ajaxSuggestionEvent.getSubmittedValue());
		}
	}
    
	@Override
	public String cargaPagina() {
		logger.debug("Inicia Carga Pagina");
		this.emisora="";
		this.serie="";
		setListEmisora(this.getAllData());
		
		this.listSerie=null;
		this.lstSeriesOperadas=null;
		return "";
	}
	
	public void doBuscar(){
		logger.debug("Buscando");
		try {
			logger.debug("Cargamos la lista de series operadas");
			setListaSeriesOperadasGrid();
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.debug("Error:::" + e.getMessage());
		}
		logger.debug("Fin Buscar");
	}

	private void setListaSeriesOperadasGrid() throws BusinessException{
		this.setLstSeriesOperadas(seriesOperadasMainSrv.getListaSeriesOperadas(this.emisora,this.serie,this.desde,this.hasta));
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

	@SuppressWarnings("static-access")
	public void setSeriesOperadasMainSrv(SeriesOperadasMainSrv seriesOperadasMainSrv) {
		this.seriesOperadasMainSrv = seriesOperadasMainSrv;
	}

	
	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public String getEmisora() {
		return emisora;
	}

	public void setEmisora(String emisora) {
		this.emisora = emisora;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public List<DataSuggestionBox> getListEmisora() {
		return listEmisora;
	}

	public void setListEmisora(List<DataSuggestionBox> listEmisora) {
		this.listEmisora = listEmisora;
	}

	public List<DataSuggestionBox> getListSerie() {
		return listSerie;
	}

	public void setListSerie(List<DataSuggestionBox> listSerie) {
		this.listSerie = listSerie;
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
}
