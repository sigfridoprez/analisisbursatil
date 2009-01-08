package mx.com.infraparseohtml.conftablas.servicio;

import mx.com.analisispreciosmercado.conf.IpConfTablasHtmlParsear;
import mx.com.infraestructura.exceptions.BusinessException;

public interface IpConfTablasHTMLParsearSrv {
	public IpConfTablasHtmlParsear getIpConfTablasHtmlParsear(Integer idTabla)throws BusinessException; 
}
