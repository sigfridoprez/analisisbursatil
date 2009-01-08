package mx.com.infracomunes.impl.vo;

import java.sql.Time;

import mx.com.infracomunes.vo.ObjetoTablaVO;



public class SeriesOperadasMercadoCapitalesVO extends ObjetoTablaVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1934117851567649983L;
	
	private String strEmisora;
	private String strSerie;
	private Time timeHora;
	private Double dblUltimo;
	private Double dblPPP;
	private Double dblAnterior;
	private Double dblMaximo;
	private Double dblMinimo;
	private Double dblVolumen;
	private Double dblImporte;
	private Double dblOps;
	private Double dblPuntos;
	private Double dblPct;
	private Time timeHoraMaximo;
	private Time timeHoraMinimo;
	
	public String getStrEmisora() {
		return strEmisora;
	}
	public void setStrEmisora(String strEmisora) {
		this.strEmisora = strEmisora;
	}
	public String getStrSerie() {
		return strSerie;
	}
	public void setStrSerie(String strSerie) {
		this.strSerie = strSerie;
	}
	public Time getTimeHora() {
		return timeHora;
	}
	public void setTimeHora(Time timeHora) {
		this.timeHora = timeHora;
	}
	public Double getDblUltimo() {
		return dblUltimo;
	}
	public void setDblUltimo(Double dblUltimo) {
		this.dblUltimo = dblUltimo;
	}
	public Double getDblPPP() {
		return dblPPP;
	}
	public void setDblPPP(Double dblPPP) {
		this.dblPPP = dblPPP;
	}
	public Double getDblAnterior() {
		return dblAnterior;
	}
	public void setDblAnterior(Double dblAnterior) {
		this.dblAnterior = dblAnterior;
	}
	public Double getDblMaximo() {
		return dblMaximo;
	}
	public void setDblMaximo(Double dblMaximo) {
		this.dblMaximo = dblMaximo;
	}
	public Double getDblMinimo() {
		return dblMinimo;
	}
	public void setDblMinimo(Double dblMinimo) {
		this.dblMinimo = dblMinimo;
	}
	public Double getDblVolumen() {
		return dblVolumen;
	}
	public void setDblVolumen(Double dblVolumen) {
		this.dblVolumen = dblVolumen;
	}
	public Double getDblImporte() {
		return dblImporte;
	}
	public void setDblImporte(Double dblImporte) {
		this.dblImporte = dblImporte;
	}
	public Double getDblOps() {
		return dblOps;
	}
	public void setDblOps(Double dblOps) {
		this.dblOps = dblOps;
	}
	public Double getDblPuntos() {
		return dblPuntos;
	}
	public void setDblPuntos(Double dblPuntos) {
		this.dblPuntos = dblPuntos;
	}
	public Double getDblPct() {
		return dblPct;
	}
	public void setDblPct(Double dblPct) {
		this.dblPct = dblPct;
	}
	public Time getTimeHoraMaximo() {
		return timeHoraMaximo;
	}
	public void setTimeHoraMaximo(Time timeHoraMaximo) {
		this.timeHoraMaximo = timeHoraMaximo;
	}
	public Time getTimeHoraMinimo() {
		return timeHoraMinimo;
	}
	public void setTimeHoraMinimo(Time timeHoraMinimo) {
		this.timeHoraMinimo = timeHoraMinimo;
	}
}
