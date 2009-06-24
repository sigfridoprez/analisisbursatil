/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facturas.dao;

import caafes.def.DetalleFactura;
import infraestructura.dao.DAOGenerico;
import caafes.def.Facturas;
import facturas.util.DetalleFacturaUtil;
import facturas.util.FacturasUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import utils.vo.UtilVO;

/**
 *
 * @author Edgar
 */
public class FacturasDAO extends DAOGenerico {

    public FacturasDAO() {
    }

    //@Override
    public void ejecuta() {
    }

    public Facturas obtienFactura(BigDecimal idFolio, BigDecimal idCliente) {
        Session session = getSessionFactory().openSession();
        Facturas facturasVO = (Facturas) session.createCriteria(Facturas.class).add(Restrictions.eq("facturasPK.idFolioFactura", idFolio)).add(Restrictions.eq("facturasPK.idCliente", idCliente)).uniqueResult();
        session.close();

        return facturasVO;
    }

    public Facturas obtienFactura(BigDecimal idFolio) {
        Session session = getSessionFactory().openSession();
        Facturas facturasVO = (Facturas) session.createCriteria(Facturas.class).add(Restrictions.eq("facturasPK.idFolioFactura", idFolio)).uniqueResult();
        session.close();

        return facturasVO;
    }
    
    public Facturas obtienFacturaFolioAutorizacion(BigDecimal idAutorizacion) {
        Session session = getSessionFactory().openSession();
        Facturas facturasVO = (Facturas) session.createCriteria(Facturas.class).add(Restrictions.eq("idAutorizacion", idAutorizacion)).uniqueResult();
        session.close();

        return facturasVO;
    }

    public List<Facturas> obtieneListaFacturas(BigDecimal iDCliente) {
        Session session = getSessionFactory().openSession();
        List<Facturas> listaFacturas = null;
        Criteria criteria = null;

        criteria = session.createCriteria(Facturas.class);

        Criterion bucaIdCliente = Restrictions.eq("facturasPK.idCliente", iDCliente);
        criteria.add(bucaIdCliente);

        listaFacturas = criteria.list();
        return listaFacturas;
    }

    public List<Facturas> obtieneListaFacturasFolio(BigDecimal iDFolio) {
        Session session = getSessionFactory().openSession();
        List<Facturas> listaFacturas = null;
        Criteria criteria = null;

        criteria = session.createCriteria(Facturas.class);

        Criterion bucaIdCliente = Restrictions.eq("facturasPK.idFolioFactura", iDFolio);
        criteria.add(bucaIdCliente);

        listaFacturas = criteria.list();
        return listaFacturas;
    }


    public List<UtilVO> obtieneListaFacturasSinAutorizacion() {
        Session session = getSessionFactory().openSession();
        List<UtilVO> listaFacturas = new ArrayList<UtilVO>();
        UtilVO vo;
        List<Long> lstResutl = null;
        Query query = null;

        query = session.createQuery("select id.idFolio from Facturas " +
                " where idAutorizacion is null " +
                " and substr(descripcion,1,1) != '1' " );

        lstResutl = query.list();
        for(Long id:lstResutl){
            vo = new UtilVO();
            vo.setLngId(id);
            vo.setStrDescripcion(id.toString());

            listaFacturas.add(vo);
        }

        return listaFacturas;
    }

    public void insertaFactura(FacturasUtil facturasVO) {
        Session session = getSessionFactory().openSession();
        DetalleFactura detalle;

        Transaction tx = session.beginTransaction();
            session.save(facturasVO.getFacturas());
            if(facturasVO.getDetalleFactura()!=null){

                for (DetalleFacturaUtil detalleFactura : facturasVO.getDetalleFactura()) {
                    detalle = detalleFactura.getDetalleFactura();
                    session.save(detalle);
                    if(detalleFactura.getAutorizaciones()!=null){
                        session.save(detalleFactura.getAutorizaciones());
                    }
                }
            }
        tx.commit();
        session.close();
    }

    public void modificaFactura(Facturas facturasVO) {
        Session session = getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();
        session.update(facturasVO);
        //session.delete(facturasVO);
        tx.commit();
        session.close();
    }

    public void eliminaFactura(FacturasUtil facturasVO) {
        Session session = getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        facturasVO = (FacturasUtil) session.get(Facturas.class, facturasVO.getFacturas().getFacturasPK());
        session.delete(facturasVO);
        tx.commit();
        session.close();
    }
}
