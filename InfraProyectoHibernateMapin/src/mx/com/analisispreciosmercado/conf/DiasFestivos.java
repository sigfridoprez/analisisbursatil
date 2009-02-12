package mx.com.analisispreciosmercado.conf;

// Generated 12-feb-2009 0:14:27 by Hibernate Tools 3.2.1.GA

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * DiasFestivos generated by hbm2java
 */
@Entity
@Table(name = "dias_festivos", schema = "ADMIN")
public class DiasFestivos implements java.io.Serializable {

	private Date fecha;
	private String descripcion;

	public DiasFestivos() {
	}

	public DiasFestivos(Date fecha) {
		this.fecha = fecha;
	}

	public DiasFestivos(Date fecha, String descripcion) {
		this.fecha = fecha;
		this.descripcion = descripcion;
	}

	@Id
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha", unique = true, nullable = false, length = 13)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column(name = "descripcion", length = 200)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
