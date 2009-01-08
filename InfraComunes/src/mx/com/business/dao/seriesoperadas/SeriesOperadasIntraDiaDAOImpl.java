package mx.com.business.dao.seriesoperadas;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import mx.com.analisispreciosmercado.conf.SeriesOperadasIntraDia;
import mx.com.infraestructura.dao.BaseDAO;
import mx.com.infraestructura.exceptions.DataBaseException;

/**
 * 
 * @author sigfrido
 *
 * DAO para el listado, insercion, borrado y actualizaci˜n de las series operadas intra d“a.
 *
 * @version 1.0
 */
public class SeriesOperadasIntraDiaDAOImpl extends BaseDAO implements
		SeriesOperadasIntraDiaDAO {

	@SuppressWarnings("unchecked")
	public List<String> getListEmisora() throws DataBaseException {
		List<String> lstEmisoras = null;
		StringBuffer sbQuery = new StringBuffer();
		
		sbQuery.append(" select distinct so.id.emisora "); 
		sbQuery.append(" from mx.com.analisispreciosmercado.conf.SeriesOperadasIntraDia so ");
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
		sbQuery.append(" from mx.com.analisispreciosmercado.conf.SeriesOperadasIntraDia so ");
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
				       " from mx.com.analisispreciosmercado.conf.SeriesOperadasIntraDia so ");
		
		try{
			lstEmisoras = (List<String>) this.getSession().createQuery(sbQuery.toString()).list();	
		}catch(Exception e){
			logger.error("Error:" + e.getMessage());
			e.printStackTrace();
		}
		
		return lstEmisoras;
	}

	public SeriesOperadasIntraDia getSerieOperadaIntraDia(int idCarga,String emisora,
			String serie, Date date) throws DataBaseException {
		SeriesOperadasIntraDia ob = null;

		Criteria criteria =  this.getSession().createCriteria(SeriesOperadasIntraDia.class);
		
		criteria.add(Restrictions.eq("id.fecha", date));
		criteria.add(Restrictions.eq("id.idCarga", idCarga));
		criteria.add(Restrictions.eq("id.emisora", emisora));
		criteria.add(Restrictions.eq("id.serie", serie));
		
		ob=(SeriesOperadasIntraDia)criteria.uniqueResult();
		
		return ob;
	}

	@SuppressWarnings("unchecked")
	public List<SeriesOperadasIntraDia> getSeriesOperadasIntraDia(
			String emisora, String serie, Date fechaInicio, Date fechaFin,
			char diaHabil, boolean blnGrafica) throws DataBaseException {
		Criteria criteria = this.getSession().createCriteria(SeriesOperadasIntraDia.class);
		List<SeriesOperadasIntraDia> lstSeries = null;
		
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
			lstSeries = (List<SeriesOperadasIntraDia>) criteria.list();	
		}catch(Exception e){
			logger.error("Error:" + e.getMessage());
			e.printStackTrace();
		}
		
		return lstSeries;
	}
	
	/**
	 * 
	 * @param int idCarga
	 * @throws DataBaseException
	 */
	@SuppressWarnings("unchecked")
	public List<SeriesOperadasIntraDia> getListaSeriesOperadasIntraDia(int idCarga)throws DataBaseException {
		Criteria criteria = this.getSession().createCriteria(SeriesOperadasIntraDia.class);
		criteria.add(Restrictions.eq("id.fecha", new Date()));
		criteria.add(Restrictions.eq("id.idCarga", idCarga));
		
		return (List<SeriesOperadasIntraDia>)criteria.list();
	}

	public void txInsertSeriesOperadasIntraDia(
			SeriesOperadasIntraDia seriesOperadas) throws DataBaseException {
		this.insert(seriesOperadas);
	}

	public void txUpdateSeriesOperadasIntraDia(
			SeriesOperadasIntraDia seriesOperadas) throws DataBaseException {
		this.update(seriesOperadas);
	}

	public int txBorrarSeriesOperadasIntraDia(Date fecha)throws DataBaseException{
		StringBuilder sbQuery = new StringBuilder("delete from SeriesOperadasIntraDia p where p.id.fecha <= :fecha");
		Query query = this.getSession().createQuery(sbQuery.toString());
		
		query.setDate("fecha", fecha);
		
		return query.executeUpdate();
	}
}
