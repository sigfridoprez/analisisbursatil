package mx.com.faces.seriesoperadas;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

import mx.com.business.mainservice.seriesoperadas.SeriesOperadasMainSrv;
import mx.com.infra.SimpleController;
import mx.com.infraestructura.exceptions.BusinessException;
import mx.com.util.Constantes;

import org.apache.log4j.Logger;

public class SeriesOperadasGraficarController extends SimpleController{
	private Logger logger = Logger.getLogger(this.getClass());
	private SeriesOperadasMainSrv seriesOperadasMainSrv;
	private Date desde;
	private Date hasta;
	private String emisora;
	private String serie;
	private String strJSON;
	private List<String> lstBuscar;
	private boolean blnImprimeIPC;
	private String fechaInicio;
	
	public boolean isBlnImprimeIPC() {
		return blnImprimeIPC;
	}

	public void setBlnImprimeIPC(boolean blnImprimeIPC) {
		this.blnImprimeIPC = blnImprimeIPC;
	}

	public String cargaPagina(){
		logger.debug("Inicia doForm");
		return "exito";
	}
	
	public void doGraficar(){
		logger.debug("En doGraficar");
		HashMap<String, Object> hsmRespuesta;
		
		try {
			hsmRespuesta = seriesOperadasMainSrv.getGrafica(lstBuscar, desde, hasta, blnImprimeIPC);
			if(hsmRespuesta!=null){
				strJSON = (String)hsmRespuesta.get(Constantes.SERIE_GRAFICAR);
				fechaInicio = (String)hsmRespuesta.get(Constantes.FECHA_MINIMA);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("Error en doGraficar", e);
		}
	}
	
	public SeriesOperadasMainSrv getSeriesOperadasMainSrv() {
		return seriesOperadasMainSrv;
	}
	public void setSeriesOperadasMainSrv(SeriesOperadasMainSrv seriesOperadasMainSrv) {
		this.seriesOperadasMainSrv = seriesOperadasMainSrv;
	}

	public String getStrJSON() {
		return strJSON;
	}

	public void setStrJSON(String strJSON) {
		this.strJSON = strJSON;
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

	public List<String> getLstBuscar() {
		return lstBuscar;
	}

	public void setLstBuscar(List<String> lstBuscar) {
		this.lstBuscar = lstBuscar;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	
}
