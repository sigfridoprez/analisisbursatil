package mx.com.cargainformacionipc.vo;

import java.util.Date;

public class SeriesOperadasIntraDiaVO extends mx.com.analisispreciosmercado.conf.SeriesOperadasIntraDia{
	private static final long serialVersionUID = 1L;
	private Date fecha;
	private int idCarga;
	private String emisora;
	private String serie;
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
	public int getIdCarga() {
		return idCarga;
	}
	public void setIdCarga(int idCarga) {
		this.idCarga = idCarga;
	}
	
	
}
