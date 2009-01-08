package mx.com.infracomunes.impl.vo;

import mx.com.infracomunes.vo.ObjetoTablaVO;

public class ResumenEmisoraVO extends ObjetoTablaVO{
	private static final long serialVersionUID = 1L;
	
	private Double dblUltimaTransac;
	private Double dblCierrePrevio;
	private Double dblPorPagar;
	private Double dblOferta;
	private Double dblVenta;
	
	public Double getDblUltimaTransac() {
		return dblUltimaTransac;
	}
	public void setDblUltimaTransac(Double dblUltimaTransac) {
		this.dblUltimaTransac = dblUltimaTransac;
	}
	public Double getDblCierrePrevio() {
		return dblCierrePrevio;
	}
	public void setDblCierrePrevio(Double dblCierrePrevio) {
		this.dblCierrePrevio = dblCierrePrevio;
	}
	public Double getDblPorPagar() {
		return dblPorPagar;
	}
	public void setDblPorPagar(Double dblPorPagar) {
		this.dblPorPagar = dblPorPagar;
	}
	public Double getDblOferta() {
		return dblOferta;
	}
	public void setDblOferta(Double dblOferta) {
		this.dblOferta = dblOferta;
	}
	public Double getDblVenta() {
		return dblVenta;
	}
	public void setDblVenta(Double dblVenta) {
		this.dblVenta = dblVenta;
	}
	
}
