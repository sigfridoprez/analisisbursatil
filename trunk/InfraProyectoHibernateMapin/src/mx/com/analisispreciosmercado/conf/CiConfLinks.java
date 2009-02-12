package mx.com.analisispreciosmercado.conf;

// Generated 12-feb-2009 0:14:27 by Hibernate Tools 3.2.1.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CiConfLinks generated by hbm2java
 */
@Entity
@Table(name = "ci_conf_links", schema = "public")
public class CiConfLinks implements java.io.Serializable {

	private Integer idConf;
	private IpConfTablasHtmlParsear ipConfTablasHtmlParsear;
	private String link;

	public CiConfLinks() {
	}

	public CiConfLinks(Integer idConf,
			IpConfTablasHtmlParsear ipConfTablasHtmlParsear, String link) {
		this.idConf = idConf;
		this.ipConfTablasHtmlParsear = ipConfTablasHtmlParsear;
		this.link = link;
	}

	@Id
	@Column(name = "id_conf", unique = true, nullable = false)
	public Integer getIdConf() {
		return this.idConf;
	}

	public void setIdConf(Integer idConf) {
		this.idConf = idConf;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tabla", nullable = false)
	public IpConfTablasHtmlParsear getIpConfTablasHtmlParsear() {
		return this.ipConfTablasHtmlParsear;
	}

	public void setIpConfTablasHtmlParsear(
			IpConfTablasHtmlParsear ipConfTablasHtmlParsear) {
		this.ipConfTablasHtmlParsear = ipConfTablasHtmlParsear;
	}

	@Column(name = "link", nullable = false, length = 200)
	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
