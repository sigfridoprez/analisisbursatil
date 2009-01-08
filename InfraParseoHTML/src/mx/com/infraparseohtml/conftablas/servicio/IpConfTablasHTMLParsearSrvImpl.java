package mx.com.infraparseohtml.conftablas.servicio;

import org.apache.log4j.Logger;

import mx.com.analisispreciosmercado.conf.IpConfTablasHtmlParsear;
import mx.com.infraestructura.exceptions.BusinessException;
import mx.com.infraestructura.exceptions.DataBaseException;
import mx.com.infraparseohtml.conftablas.dao.IpConfTablasHTMLParsearDAO;

public class IpConfTablasHTMLParsearSrvImpl implements
		IpConfTablasHTMLParsearSrv {
	private Logger logger = Logger.getLogger(this.getClass());
	private IpConfTablasHTMLParsearDAO ipConfTablasHTMLParsearDAO;
	
	public IpConfTablasHtmlParsear getIpConfTablasHtmlParsear(Integer idTabla)
			throws BusinessException {
		try {
			logger.debug("En getIpConfTablasHtmlParsear idTabla:::" + idTabla);
			return ipConfTablasHTMLParsearDAO.getIpConfTablasHtmlParsear(idTabla);
		} catch (DataBaseException e) {
			e.printStackTrace();
			logger.debug("Error en getIpConfTablasHtmlParsear ERROR::" + e.getMessage());
			throw new BusinessException("Error en getIpConfTablasHtmlParsear ERROR::" + e.getMessage());
		}
	}

	public IpConfTablasHTMLParsearDAO getIpConfTablasHTMLParsearDAO() {
		return ipConfTablasHTMLParsearDAO;
	}

	public void setIpConfTablasHTMLParsearDAO(
			IpConfTablasHTMLParsearDAO ipConfTablasHTMLParsearDAO) {
		this.ipConfTablasHTMLParsearDAO = ipConfTablasHTMLParsearDAO;
	}
}
