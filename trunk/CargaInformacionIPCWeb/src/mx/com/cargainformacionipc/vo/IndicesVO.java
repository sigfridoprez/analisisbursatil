package mx.com.cargainformacionipc.vo;

import java.util.Date;

import mx.com.analisispreciosmercado.conf.Indices;

public class IndicesVO extends Indices{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date fecha;
	private String indice;
	
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
	
}
