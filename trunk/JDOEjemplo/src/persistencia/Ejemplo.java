package persistencia;

import java.math.BigDecimal;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.NullValue;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(table="EJEMPLO",schema="ADM")
public class Ejemplo {
	
	public Ejemplo(){}
	
	@Persistent(primaryKey="true",nullValue=NullValue.NONE)
	@Column(name="ID_CONSECUTIVO",length=18,jdbcType="NUMERIC")
	private BigDecimal idConsecutivo;
	
	@Persistent
	@Column(name="ID_UNO",length=18,jdbcType="NUMERIC")
	private BigDecimal idUno;
	
	@Persistent
	@Column(name="ID_DOS",length=18,jdbcType="NUMERIC")
	private BigDecimal idDos;
	
	private BigDecimal idTres;
	
	private BigDecimal idCuatro;
	
	private BigDecimal idCinco;
	
	private BigDecimal idSeis;
	
	private BigDecimal idSiete;
	
	private BigDecimal idOcho;
	
	private String uno;
	
	private String dos;
	
	private String tres;
	
	private String cuatro;
	
	private String cinco;
	
	private String seis;
	
	private String siete;
	
	private String ocho;
	
	private String nueve;
	
	public Ejemplo(BigDecimal idConsecutivo){
		this.idConsecutivo=idConsecutivo;
		this.idUno = idConsecutivo;
		this.idDos = idConsecutivo;
		this.idTres = idConsecutivo;
		this.idCuatro = idConsecutivo;
		this.idCinco = idConsecutivo;
		this.idSeis = idConsecutivo;
		this.idSiete = idConsecutivo;
		this.idOcho = idConsecutivo;
		this.uno = "rrr";
		this.dos = "rrr";
		this.tres = "rrr";
		this.cuatro = "rrr";
		this.cinco = "rrr";
		this.seis = "rrr";
		this.siete = "rrr";
		this.ocho = "rrr";
		this.nueve = "rrr";
	}
			
	public Ejemplo(BigDecimal idConsecutivo, BigDecimal idUno,
			BigDecimal idDos, BigDecimal idTres, BigDecimal idCuatro,
			BigDecimal idCinco, BigDecimal idSeis, BigDecimal idSiete,
			BigDecimal idOcho, String uno, String dos, String tres,
			String cuatro, String cinco, String seis, String siete,
			String ocho, String nueve) {
		super();
		this.idConsecutivo = idConsecutivo;
		this.idUno = idUno;
		this.idDos = idDos;
		this.idTres = idTres;
		this.idCuatro = idCuatro;
		this.idCinco = idCinco;
		this.idSeis = idSeis;
		this.idSiete = idSiete;
		this.idOcho = idOcho;
		this.uno = uno;
		this.dos = dos;
		this.tres = tres;
		this.cuatro = cuatro;
		this.cinco = cinco;
		this.seis = seis;
		this.siete = siete;
		this.ocho = ocho;
		this.nueve = nueve;
	}
	
	public BigDecimal getIdConsecutivo() {
		return idConsecutivo;
	}
	public void setIdConsecutivo(BigDecimal idConsecutivo) {
		this.idConsecutivo = idConsecutivo;
	}
	public BigDecimal getIdUno() {
		return idUno;
	}
	public void setIdUno(BigDecimal idUno) {
		this.idUno = idUno;
	}
	public BigDecimal getIdDos() {
		return idDos;
	}
	public void setIdDos(BigDecimal idDos) {
		this.idDos = idDos;
	}
	public BigDecimal getIdTres() {
		return idTres;
	}
	public void setIdTres(BigDecimal idTres) {
		this.idTres = idTres;
	}
	public BigDecimal getIdCuatro() {
		return idCuatro;
	}
	public void setIdCuatro(BigDecimal idCuatro) {
		this.idCuatro = idCuatro;
	}
	public BigDecimal getIdCinco() {
		return idCinco;
	}
	public void setIdCinco(BigDecimal idCinco) {
		this.idCinco = idCinco;
	}
	public BigDecimal getIdSeis() {
		return idSeis;
	}
	public void setIdSeis(BigDecimal idSeis) {
		this.idSeis = idSeis;
	}
	public BigDecimal getIdSiete() {
		return idSiete;
	}
	public void setIdSiete(BigDecimal idSiete) {
		this.idSiete = idSiete;
	}
	public BigDecimal getIdOcho() {
		return idOcho;
	}
	public void setIdOcho(BigDecimal idOcho) {
		this.idOcho = idOcho;
	}
	public String getUno() {
		return uno;
	}
	public void setUno(String uno) {
		this.uno = uno;
	}
	public String getDos() {
		return dos;
	}
	public void setDos(String dos) {
		this.dos = dos;
	}
	public String getTres() {
		return tres;
	}
	public void setTres(String tres) {
		this.tres = tres;
	}
	public String getCuatro() {
		return cuatro;
	}
	public void setCuatro(String cuatro) {
		this.cuatro = cuatro;
	}
	public String getCinco() {
		return cinco;
	}
	public void setCinco(String cinco) {
		this.cinco = cinco;
	}
	public String getSeis() {
		return seis;
	}
	public void setSeis(String seis) {
		this.seis = seis;
	}
	public String getSiete() {
		return siete;
	}
	public void setSiete(String siete) {
		this.siete = siete;
	}
	public String getOcho() {
		return ocho;
	}
	public void setOcho(String ocho) {
		this.ocho = ocho;
	}
	public String getNueve() {
		return nueve;
	}
	public void setNueve(String nueve) {
		this.nueve = nueve;
	}
	
}
