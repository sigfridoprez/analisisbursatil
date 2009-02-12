package mx.com.analisispreciosmercado.conf;

// Generated 12-feb-2009 0:14:27 by Hibernate Tools 3.2.1.GA

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SeriesOperadas generated by hbm2java
 */
@Entity
@Table(name = "series_operadas", schema = "public")
public class SeriesOperadas implements java.io.Serializable {

	private SeriesOperadasId id;
	private Date hora;
	private BigDecimal ultimo;
	private BigDecimal ppp;
	private BigDecimal anterior;
	private BigDecimal maximo;
	private BigDecimal minimo;
	private Long volumen;
	private BigDecimal importe;
	private Integer operaciones;
	private BigDecimal puntos;
	private BigDecimal porcentaje;
	private char diaHabil;
	private Integer split;
	private BigDecimal varPctPrecio;
	private BigDecimal varPctMaximo;
	private BigDecimal varPctMinimo;
	private BigDecimal varPct;
	private BigDecimal rendimiento;
	private BigDecimal indBursatilidad;
	private Character tipoIndBursatilidad;

	public SeriesOperadas() {
	}

	public SeriesOperadas(SeriesOperadasId id, BigDecimal ultimo,
			BigDecimal anterior, BigDecimal maximo, BigDecimal minimo,
			char diaHabil, Integer split, BigDecimal varPctMaximo,
			BigDecimal varPctMinimo, BigDecimal varPct) {
		this.id = id;
		this.ultimo = ultimo;
		this.anterior = anterior;
		this.maximo = maximo;
		this.minimo = minimo;
		this.diaHabil = diaHabil;
		this.split = split;
		this.varPctMaximo = varPctMaximo;
		this.varPctMinimo = varPctMinimo;
		this.varPct = varPct;
	}

	public SeriesOperadas(SeriesOperadasId id, Date hora, BigDecimal ultimo,
			BigDecimal ppp, BigDecimal anterior, BigDecimal maximo,
			BigDecimal minimo, Long volumen, BigDecimal importe,
			Integer operaciones, BigDecimal puntos, BigDecimal porcentaje,
			char diaHabil, Integer split, BigDecimal varPctPrecio,
			BigDecimal varPctMaximo, BigDecimal varPctMinimo,
			BigDecimal varPct, BigDecimal rendimiento,
			BigDecimal indBursatilidad, Character tipoIndBursatilidad) {
		this.id = id;
		this.hora = hora;
		this.ultimo = ultimo;
		this.ppp = ppp;
		this.anterior = anterior;
		this.maximo = maximo;
		this.minimo = minimo;
		this.volumen = volumen;
		this.importe = importe;
		this.operaciones = operaciones;
		this.puntos = puntos;
		this.porcentaje = porcentaje;
		this.diaHabil = diaHabil;
		this.split = split;
		this.varPctPrecio = varPctPrecio;
		this.varPctMaximo = varPctMaximo;
		this.varPctMinimo = varPctMinimo;
		this.varPct = varPct;
		this.rendimiento = rendimiento;
		this.indBursatilidad = indBursatilidad;
		this.tipoIndBursatilidad = tipoIndBursatilidad;
	}

	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "fecha", column = @Column(name = "fecha", nullable = false, length = 13)),
			@AttributeOverride(name = "emisora", column = @Column(name = "emisora", nullable = false, length = 25)),
			@AttributeOverride(name = "serie", column = @Column(name = "serie", nullable = false, length = 10)) })
	public SeriesOperadasId getId() {
		return this.id;
	}

	public void setId(SeriesOperadasId id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "hora", length = 15)
	public Date getHora() {
		return this.hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	@Column(name = "ultimo", nullable = false, precision = 16)
	public BigDecimal getUltimo() {
		return this.ultimo;
	}

	public void setUltimo(BigDecimal ultimo) {
		this.ultimo = ultimo;
	}

	@Column(name = "ppp", precision = 16)
	public BigDecimal getPpp() {
		return this.ppp;
	}

	public void setPpp(BigDecimal ppp) {
		this.ppp = ppp;
	}

	@Column(name = "anterior", nullable = false, precision = 16)
	public BigDecimal getAnterior() {
		return this.anterior;
	}

	public void setAnterior(BigDecimal anterior) {
		this.anterior = anterior;
	}

	@Column(name = "maximo", nullable = false, precision = 16)
	public BigDecimal getMaximo() {
		return this.maximo;
	}

	public void setMaximo(BigDecimal maximo) {
		this.maximo = maximo;
	}

	@Column(name = "minimo", nullable = false, precision = 16)
	public BigDecimal getMinimo() {
		return this.minimo;
	}

	public void setMinimo(BigDecimal minimo) {
		this.minimo = minimo;
	}

	@Column(name = "volumen", precision = 12, scale = 0)
	public Long getVolumen() {
		return this.volumen;
	}

	public void setVolumen(Long volumen) {
		this.volumen = volumen;
	}

	@Column(name = "importe", precision = 16)
	public BigDecimal getImporte() {
		return this.importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	@Column(name = "operaciones", precision = 6, scale = 0)
	public Integer getOperaciones() {
		return this.operaciones;
	}

	public void setOperaciones(Integer operaciones) {
		this.operaciones = operaciones;
	}

	@Column(name = "puntos", precision = 8)
	public BigDecimal getPuntos() {
		return this.puntos;
	}

	public void setPuntos(BigDecimal puntos) {
		this.puntos = puntos;
	}

	@Column(name = "porcentaje", precision = 4)
	public BigDecimal getPorcentaje() {
		return this.porcentaje;
	}

	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}

	@Column(name = "dia_habil", nullable = false, length = 1)
	public char getDiaHabil() {
		return this.diaHabil;
	}

	public void setDiaHabil(char diaHabil) {
		this.diaHabil = diaHabil;
	}

	@Column(name = "split", nullable = false)
	public Integer getSplit() {
		return this.split;
	}

	public void setSplit(Integer split) {
		this.split = split;
	}

	@Column(name = "var_pct_precio", precision = 6, scale = 4)
	public BigDecimal getVarPctPrecio() {
		return this.varPctPrecio;
	}

	public void setVarPctPrecio(BigDecimal varPctPrecio) {
		this.varPctPrecio = varPctPrecio;
	}

	@Column(name = "var_pct_maximo", nullable = false, precision = 6, scale = 4)
	public BigDecimal getVarPctMaximo() {
		return this.varPctMaximo;
	}

	public void setVarPctMaximo(BigDecimal varPctMaximo) {
		this.varPctMaximo = varPctMaximo;
	}

	@Column(name = "var_pct_minimo", nullable = false, precision = 6, scale = 4)
	public BigDecimal getVarPctMinimo() {
		return this.varPctMinimo;
	}

	public void setVarPctMinimo(BigDecimal varPctMinimo) {
		this.varPctMinimo = varPctMinimo;
	}

	@Column(name = "var_pct", nullable = false, precision = 6, scale = 4)
	public BigDecimal getVarPct() {
		return this.varPct;
	}

	public void setVarPct(BigDecimal varPct) {
		this.varPct = varPct;
	}

	@Column(name = "rendimiento", precision = 4, scale = 3)
	public BigDecimal getRendimiento() {
		return this.rendimiento;
	}

	public void setRendimiento(BigDecimal rendimiento) {
		this.rendimiento = rendimiento;
	}

	@Column(name = "ind_bursatilidad", precision = 9, scale = 6)
	public BigDecimal getIndBursatilidad() {
		return this.indBursatilidad;
	}

	public void setIndBursatilidad(BigDecimal indBursatilidad) {
		this.indBursatilidad = indBursatilidad;
	}

	@Column(name = "tipo_ind_bursatilidad", length = 1)
	public Character getTipoIndBursatilidad() {
		return this.tipoIndBursatilidad;
	}

	public void setTipoIndBursatilidad(Character tipoIndBursatilidad) {
		this.tipoIndBursatilidad = tipoIndBursatilidad;
	}

}
