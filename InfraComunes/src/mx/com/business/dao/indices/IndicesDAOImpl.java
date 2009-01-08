package mx.com.business.dao.indices;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import mx.com.analisispreciosmercado.conf.Indices;
import mx.com.infraestructura.dao.BaseDAO;
import mx.com.infraestructura.exceptions.DataBaseException;

public class IndicesDAOImpl extends BaseDAO implements IndicesDAO {

	public void txInsertIndices(Indices indices) throws DataBaseException {
		this.insert(indices);
	}

	public Indices getIndice(Date fecha, String indice)
			throws DataBaseException {
		Criteria criteria;
		Indices indiceReturn;
		
		criteria = this.getSession().createCriteria(Indices.class);
		criteria.add(Restrictions.eq("id.fecha", fecha));
		criteria.add(Restrictions.eq("id.indice", indice));
		indiceReturn = (Indices)criteria.uniqueResult();
		
		return indiceReturn;
	}

	@SuppressWarnings("unchecked")
	public List<Indices> getIndices(String indice, Date fechaInicio,
			Date fechaFin, char diaHabil) throws DataBaseException {
		List<Indices> lstIndices = null;
		Criteria criteria = this.getSession().createCriteria(Indices.class);
		
		if(indice!=null && !indice.equalsIgnoreCase("")){
			criteria.add(Restrictions.eq("id.indice", indice));
		}
		if(diaHabil=='S' || diaHabil=='N'){
			criteria.add(Restrictions.eq("diaHabil", diaHabil));
		}
		if(fechaInicio!=null && fechaFin!=null){
			criteria.add(Restrictions.between("id.fecha", fechaInicio, fechaFin));
			criteria.addOrder(Order.asc("id.indice"));
		}else if(fechaInicio!=null){
			criteria.add(Restrictions.ge("id.fecha", fechaInicio));
			criteria.addOrder(Order.asc("id.indice"));
		}
		criteria.addOrder(Order.asc("id.fecha"));
		try{
			lstIndices = (List<Indices>) criteria.list();	
		}catch(Exception e){
			logger.error("Error:" + e.getMessage());
			e.printStackTrace();
		}		
		return lstIndices;
	}

}
