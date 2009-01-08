package mx.com.infraparseohtml.conftablas.dao;

import mx.com.analisispreciosmercado.conf.IpConfTablasHtmlParsear;
import mx.com.infraestructura.dao.BaseDAO;
import mx.com.infraestructura.exceptions.DataBaseException;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;

public class IpConfTablasHTMLParsearDAOImpl extends BaseDAO implements
		IpConfTablasHTMLParsearDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	public IpConfTablasHtmlParsear getIpConfTablasHtmlParsear(Integer idTabla)
			throws DataBaseException {
		logger.debug("En getIpConfTablasHtmlParsear idTabla::" + idTabla);
		return (IpConfTablasHtmlParsear)getSession().createCriteria(IpConfTablasHtmlParsear.class).add(Restrictions.eq("idTabla", idTabla)).uniqueResult();
	}

}
