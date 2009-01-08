package mx.com.analisispreciosmercado.conf;

// Generated 10/12/2008 06:03:50 PM by Hibernate Tools 3.2.1.GA

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * IpConfPropiedadesTablaId generated by hbm2java
 */
@Embeddable
public class IpConfPropiedadesTablaId implements java.io.Serializable {

	private Integer idTabla;
	private Integer idPropiedadTabla;

	public IpConfPropiedadesTablaId() {
	}

	public IpConfPropiedadesTablaId(Integer idTabla, Integer idPropiedadTabla) {
		this.idTabla = idTabla;
		this.idPropiedadTabla = idPropiedadTabla;
	}

	@Column(name = "id_tabla", nullable = false)
	public Integer getIdTabla() {
		return this.idTabla;
	}

	public void setIdTabla(Integer idTabla) {
		this.idTabla = idTabla;
	}

	@Column(name = "id_propiedad_tabla", nullable = false)
	public Integer getIdPropiedadTabla() {
		return this.idPropiedadTabla;
	}

	public void setIdPropiedadTabla(Integer idPropiedadTabla) {
		this.idPropiedadTabla = idPropiedadTabla;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof IpConfPropiedadesTablaId))
			return false;
		IpConfPropiedadesTablaId castOther = (IpConfPropiedadesTablaId) other;

		return ((this.getIdTabla() == castOther.getIdTabla()) || (this
				.getIdTabla() != null
				&& castOther.getIdTabla() != null && this.getIdTabla().equals(
				castOther.getIdTabla())))
				&& ((this.getIdPropiedadTabla() == castOther
						.getIdPropiedadTabla()) || (this.getIdPropiedadTabla() != null
						&& castOther.getIdPropiedadTabla() != null && this
						.getIdPropiedadTabla().equals(
								castOther.getIdPropiedadTabla())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getIdTabla() == null ? 0 : this.getIdTabla().hashCode());
		result = 37
				* result
				+ (getIdPropiedadTabla() == null ? 0 : this
						.getIdPropiedadTabla().hashCode());
		return result;
	}

}
