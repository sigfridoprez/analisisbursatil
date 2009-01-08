package mx.com.infracomunes.impl.vo;

import java.sql.Time;

import mx.com.infracomunes.vo.ObjetoTablaVO;

public class IndicesMercadoCapitalesVO extends ObjetoTablaVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strIndice;
	private Double dblUltimo;
	private Time timeHora;
	private Double dblAnterior;
	private Double dblPct;
	private Double dblPuntos;
	private Double dblMaximo;
	private Time timeHoraMaximo;
	private Double dblMinimo;
	private Time timeHoraMinimo;
	
	public String getStrIndice() {
		return strIndice;
	}
	public void setStrIndice(String strIndice) {
		this.strIndice = strIndice;
	}
	public Double getDblUltimo() {
		return dblUltimo;
	}
	public void setDblUltimo(Double dblUltimo) {
		this.dblUltimo = dblUltimo;
	}
	public Time getTimeHora() {
		return timeHora;
	}
	public void setTimeHora(Time timeHora) {
		this.timeHora = timeHora;
	}
	public Double getDblAnterior() {
		return dblAnterior;
	}
	public void setDblAnterior(Double dblAnterior) {
		this.dblAnterior = dblAnterior;
	}
	public Double getDblPct() {
		return dblPct;
	}
	public void setDblPct(Double dblPct) {
		this.dblPct = dblPct;
	}
	public Double getDblPuntos() {
		return dblPuntos;
	}
	public void setDblPuntos(Double dblPuntos) {
		this.dblPuntos = dblPuntos;
	}
	public Double getDblMaximo() {
		return dblMaximo;
	}
	public void setDblMaximo(Double dblMaximo) {
		this.dblMaximo = dblMaximo;
	}
	public Time getTimeHoraMaximo() {
		return timeHoraMaximo;
	}
	public void setTimeHoraMaximo(Time timeHoraMaximo) {
		this.timeHoraMaximo = timeHoraMaximo;
	}
	public Double getDblMinimo() {
		return dblMinimo;
	}
	public void setDblMinimo(Double dblMinimo) {
		this.dblMinimo = dblMinimo;
	}
	public Time getTimeHoraMinimo() {
		return timeHoraMinimo;
	}
	public void setTimeHoraMinimo(Time timeHoraMinimo) {
		this.timeHoraMinimo = timeHoraMinimo;
	}
}
