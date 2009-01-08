package mx.com.business.dao.seriesoperadas;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import mx.com.analisispreciosmercado.conf.SeriesOperadas;
import mx.com.infraestructura.dao.BaseDAO;
import mx.com.infraestructura.exceptions.DataBaseException;

public class SeriesOperadasDAOImpl extends BaseDAO implements SeriesOperadasDAO {

	@SuppressWarnings("unchecked")
	public List<SeriesOperadas> getSeriesOperadas(String emisora,String serie,Date fechaInicio,Date fechaFin,char diaHabil,boolean blnGrafica) throws DataBaseException {
		Criteria criteria = this.getSession().createCriteria(SeriesOperadas.class);
		List<SeriesOperadas> lstSeries = null;
		
		if(emisora!=null && !emisora.equalsIgnoreCase("")){
			criteria.add(Restrictions.eq("id.emisora", emisora));
		}
		if(serie!=null && !serie.equalsIgnoreCase("")){
			criteria.add(Restrictions.eq("id.serie", serie));
		}
		if(diaHabil=='S' || diaHabil=='N'){
			criteria.add(Restrictions.eq("diaHabil", diaHabil));
		}
		if(fechaInicio!=null && fechaFin!=null){
			criteria.add(Restrictions.between("id.fecha", fechaInicio, fechaFin));
			criteria.addOrder(Order.asc("id.emisora"));
			criteria.addOrder(Order.asc("id.serie"));
		}else if(fechaInicio!=null){
			if(blnGrafica){
				criteria.add(Restrictions.ge("id.fecha", fechaInicio));
			}else{
				criteria.add(Restrictions.eq("id.fecha", fechaInicio));
			}
			criteria.addOrder(Order.asc("id.emisora"));
			criteria.addOrder(Order.asc("id.serie"));
		}
		criteria.addOrder(Order.asc("id.fecha"));
		try{
			lstSeries = (List<SeriesOperadas>) criteria.list();	
		}catch(Exception e){
			logger.error("Error:" + e.getMessage());
			e.printStackTrace();
		}
		
		return lstSeries;
	}

	@SuppressWarnings("unchecked")
	public List<String> getListEmisora() throws DataBaseException {
		List<String> lstEmisoras = null;
		StringBuffer sbQuery = new StringBuffer();
		
		sbQuery.append(" select distinct so.id.emisora "); 
		sbQuery.append(" from mx.com.analisispreciosmercado.conf.SeriesOperadas so ");
		sbQuery.append(" order by so.id.emisora ");
		
		try{
			lstEmisoras = (List<String>) this.getSession().createQuery(sbQuery.toString()).list();	
		}catch(Exception e){
			logger.error("Error:" + e.getMessage());
			e.printStackTrace();
		}
		
		return lstEmisoras;
	}

	@SuppressWarnings("unchecked")
	public List<String> getListSerie(String emisora) throws DataBaseException {
		List<String> lstSeries = null;
		StringBuffer sbQuery = new StringBuffer();
		
		sbQuery.append(" select distinct so.id.serie "); 
		sbQuery.append(" from mx.com.analisispreciosmercado.conf.SeriesOperadas so ");
		sbQuery.append(" where so.id.emisora = :emisora ");
		sbQuery.append(" order by so.id.serie ");
		
		try{
			lstSeries = (List<String>) this.getSession().createQuery(sbQuery.toString()).setString("emisora", emisora).list();	
		}catch(Exception e){
			logger.error("Error:" + e.getMessage());
			e.printStackTrace();
		}
		
		return lstSeries;
	}

	@SuppressWarnings("unchecked")
	public List<String> getListaJList() throws DataBaseException {
		List<String> lstEmisoras = null;
		StringBuffer sbQuery = new StringBuffer();
		
		sbQuery.append(" select distinct concat(so.id.emisora||'-'||so.id.serie) " +
				       " from mx.com.analisispreciosmercado.conf.SeriesOperadas so ");
		
		try{
			lstEmisoras = (List<String>) this.getSession().createQuery(sbQuery.toString()).list();	
		}catch(Exception e){
			logger.error("Error:" + e.getMessage());
			e.printStackTrace();
		}
		
		return lstEmisoras;
	}
	
	public void txInsertSeriesOperadas(SeriesOperadas seriesOperadas)throws DataBaseException {
		this.insert(seriesOperadas);
	}
	
	public SeriesOperadas getSerieOperada(String emisora, String serie,Date fecha)
		throws DataBaseException {
		SeriesOperadas ob = null;

		Criteria criteria =  this.getSession().createCriteria(SeriesOperadas.class);
		
		criteria.add(Restrictions.eq("id.fecha", fecha));
		criteria.add(Restrictions.eq("id.emisora", emisora));
		criteria.add(Restrictions.eq("id.serie", serie));
		
		ob=(SeriesOperadas)criteria.uniqueResult();
		
		return ob;
	}

	public void txUpdateSeriesOperadas(SeriesOperadas seriesOperadas)
			throws DataBaseException {
		this.update(seriesOperadas);
	}
}
