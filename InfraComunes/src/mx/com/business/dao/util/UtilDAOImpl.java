package mx.com.business.dao.util;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import mx.com.analisispreciosmercado.conf.IndicesIntraDia;
import mx.com.analisispreciosmercado.conf.SeriesOperadasIntraDia;
import mx.com.infraestructura.dao.BaseDAO;
import mx.com.infraestructura.exceptions.DataBaseException;

public class UtilDAOImpl extends BaseDAO implements UtilDAO {

	public int getMaximaSerieIntraDiaCarga()
			throws DataBaseException {
		Criteria criteria = this.getSession().createCriteria(SeriesOperadasIntraDia.class);
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.max("id.idCarga"));
		Integer idMax = null;
		
		criteria.add(Restrictions.eq("id.fecha", new Date()));
		criteria.setProjection(projList);
		
		idMax = (Integer)criteria.uniqueResult();
		
		if(idMax!=null){
			return idMax.intValue();
		}else{
			return 0;
		}
	}

	public int getMaximoIndiceIntraDiaCarga()
			throws DataBaseException {
		Criteria criteria = this.getSession().createCriteria(IndicesIntraDia.class);
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.max("id.idCarga"));
		Integer idMax = null;
		
		criteria.add(Restrictions.eq("id.fecha", new Date()));
		criteria.setProjection(projList);
		
		idMax = (Integer)criteria.uniqueResult();
		
		if(idMax!=null){
			return idMax.intValue();
		}else{
			return 0;
		}
	}
}
