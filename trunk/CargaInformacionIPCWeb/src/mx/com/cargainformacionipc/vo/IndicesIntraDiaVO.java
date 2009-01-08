package mx.com.cargainformacionipc.vo;

import java.util.Date;

import mx.com.analisispreciosmercado.conf.IndicesIntraDia;

public class IndicesIntraDiaVO extends IndicesIntraDia{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date fecha;
	private String indice;
	private int idCarga;
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getIndice() {
		return indice;
	}
	public void setIndice(String indice) {
		this.indice = indice;
	}
	public int getIdCarga() {
		return idCarga;
	}
	public void setIdCarga(int idCarga) {
		this.idCarga = idCarga;
	}
	
}
