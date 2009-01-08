package mx.com.infraparseohtml.conftablas.dao;

import mx.com.analisispreciosmercado.conf.IpConfTablasHtmlParsear;
import mx.com.infraestructura.exceptions.DataBaseException;

public interface IpConfTablasHTMLParsearDAO {
	public IpConfTablasHtmlParsear getIpConfTablasHtmlParsear(Integer idTabla)throws DataBaseException;
}
