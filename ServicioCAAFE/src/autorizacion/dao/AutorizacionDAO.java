/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autorizacion.dao;

import caafes.def.Autorizaciones;
import infraestructura.dao.DAOGenerico;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Edgar
 */
public class AutorizacionDAO extends DAOGenerico {

    public AutorizacionDAO() {
    }

    //@Override
    public void ejecuta() {
    }

    public Autorizaciones obtienAutorizacion(long idAutorizacion) {
        Session session = getSessionFactory().openSession();
        Autorizaciones AutorizacioneVO = (Autorizaciones) session.createCriteria(Autorizaciones.class).add(Restrictions.eq("idAutorizacion", idAutorizacion)).uniqueResult();
        session.close();

        return AutorizacioneVO;
    }
    
    public List<Autorizaciones> obtienAutorizacionFechacrea() {
        Session session = getSessionFactory().openSession();
        Criteria criteria= session.createCriteria(Autorizaciones.class);
        List<Autorizaciones> lista=null;
        lista = criteria.addOrder(Order.asc("fechaCreacion")).list();
        session.close();
        return lista;
    }
       public List<Autorizaciones> obtienAutorizacionFechaCaduca() {
        Session session = getSessionFactory().openSession();
        Criteria criteria= session.createCriteria(Autorizaciones.class);
        List<Autorizaciones> lista=null;
        lista =  criteria.addOrder(Order.desc("caducidad")).list();
        session.close();
        return lista;
    }


    public void insertaAutorizacion(Autorizaciones AutorizacioneVO) {
        Session session = getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(AutorizacioneVO);
        //session.delete(facturasVO);
        tx.commit();
        session.close();
    }

    public void eliminaAutorizacion(Autorizaciones AutorizacioneVO) {
        Session session = getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(AutorizacioneVO);
        //session.delete(facturasVO);
        tx.commit();
        session.close();
    }
     public List<Autorizaciones> obtieneListaAutorizacion(long iDAutoriza) {
        Session session = getSessionFactory().openSession();
        List<Autorizaciones> listaFacturas = null;
        Criteria criteria = null;

        criteria = session.createCriteria(Autorizaciones.class);

        Criterion buscaIdFolio = Restrictions.eq("idAutorizacion",iDAutoriza);
        criteria.add(buscaIdFolio);

        listaFacturas = criteria.list();
        return listaFacturas;
    }

    public void modificaAutorizacion(Autorizaciones AutorizacioneVO) {
        Session session = getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(AutorizacioneVO);
        //session.delete(facturasVO);
        tx.commit();
        session.close();
    }
    
}
