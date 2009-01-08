package mx.com.business.dao.indices;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import mx.com.analisispreciosmercado.conf.IndicesIntraDia;
import mx.com.infraestructura.dao.BaseDAO;
import mx.com.infraestructura.exceptions.DataBaseException;

public class IndicesIntraDiaDAOImpl extends BaseDAO implements
		IndicesIntraDiaDAO {

	public IndicesIntraDia getIndiceIntraDia(int idCarga,Date fecha, String indice)
			throws DataBaseException {
		Criteria criteria;
		IndicesIntraDia indiceReturn;
		
		criteria = this.getSession().createCriteria(IndicesIntraDia.class);
		criteria.add(Restrictions.eq("id.fecha", fecha));
		criteria.add(Restrictions.eq("id.idCarga", idCarga));
		criteria.add(Restrictions.eq("id.indice", indice));
		indiceReturn = (IndicesIntraDia)criteria.uniqueResult();
		
		return indiceReturn;
	}

	@SuppressWarnings("unchecked")
	public List<IndicesIntraDia> getIndicesIntraDias(String indice,
			Date fechaInicio, Date fechaFin, char diaHabil)
			throws DataBaseException {
		List<IndicesIntraDia> lstIndices = null;
		Criteria criteria = this.getSession().createCriteria(IndicesIntraDia.class);
		
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
			lstIndices = (List<IndicesIntraDia>) criteria.list();	
		}catch(Exception e){
			logger.error("Error:" + e.getMessage());
			e.printStackTrace();
		}		
		return lstIndices;
	}

	public void txInsertIndicesIntraDia(IndicesIntraDia indicesIntraDia)
			throws DataBaseException {
		this.insert(indicesIntraDia);
	}

	public int txBorrarIndicesIntraDia(Date fecha)throws DataBaseException{
		StringBuilder sbQuery = new StringBuilder("delete from IndicesIntraDia p where p.id.fecha <= :fecha");
		Query query = this.getSession().createQuery(sbQuery.toString());
		
		query.setDate("fecha", fecha);
		
		return query.executeUpdate();
	}
}
