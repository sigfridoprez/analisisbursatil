/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clientes.dao;

import caafes.def.Clientes;
import infraestructura.dao.DAOGenerico;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Edgar
 */
public class DAOClientes extends DAOGenerico {


    /* POR JBDC
    public void ejecuta() {
    StringBuilder sbQuery = new StringBuilder("select * from CLIENTES where ");
    vo = new ClienteVO();

    try {
    if(strRFC != null){
    sbQuery.append(" RFC = ? ");
    }else if(idCliente != null){
    sbQuery.append(" ID_CLIENTE = ? ");
    }

    PreparedStatement stm = conexion.prepareStatement(sbQuery.toString());

    if(strRFC != null){
    stm.setString(1,this.strRFC);
    }else if(idCliente != null){
    stm.setBigDecimal(1, this.idCliente);
    }

    ResultSet res = stm.executeQuery();

    if(res.next()){
    vo.setIdCliente(res.getBigDecimal("ID_CLIENTE"));
    vo.setNombre(res.getString("NOMBRE"));
    vo.setApellidos(res.getString("Apellidos"));
    vo.setRfc(res.getString("RFC"));
    vo.setCalleNumero(res.getString("calle_numero"));
    vo.setColonia(res.getString("colonia"));
    vo.setCodigoPostal(res.getBigDecimal("codigo_postal"));
    vo.setDelegacionMunicipio(res.getString("DELEGACION_MUNICIPIO"));
    vo.setCiudad(res.getString("CIUDAD"));
    vo.setTelefono(res.getBigDecimal("Telefono"));
    vo.setValido((char)res.getString("valido").getBytes()[0]);
    vo.setExportado((char)res.getString("exportado").getBytes()[0]);

    }

    res.close();
    stm.close();
    conexion.close();
    } catch (SQLException ex) {
    System.out.println("Error::" + ex.getMessage());
    }
    }
     */
    public Clientes obtieneCliente(BigDecimal idCliente, String Rfc) {
        Session session = getSessionFactory().openSession();
        Clientes clientesVO = null;

        if (idCliente != null) {
            clientesVO = (Clientes) session.createCriteria(Clientes.class).add(Restrictions.eq("idCliente", idCliente)).uniqueResult();
        } else if (Rfc != null) {
            clientesVO = (Clientes) session.createCriteria(Clientes.class).add(Restrictions.eq("rfc", Rfc)).uniqueResult();
        }

        session.close();
        return clientesVO;
    }

    public List<Clientes> obtieneListaClientes(String consulta) {
        Session session = getSessionFactory().openSession();
        List<Clientes> listaCliente = null;
        Criteria criteria = null;

        criteria = session.createCriteria(Clientes.class);

        Criterion nombre = Restrictions.ilike("nombre", consulta, MatchMode.START);
        Criterion rfc  = Restrictions.ilike("rfc", consulta, MatchMode.START);
        LogicalExpression orExp = Restrictions.or(nombre,rfc);
        criteria.add(orExp);
        //criteria.add(Restrictions.eq("valido", 'S'));
        
        listaCliente = criteria.list();
        return listaCliente;
    }

    public void insertaCliente(Clientes clientesVO) {
        Session session = getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();
        session.save(clientesVO);
        tx.commit();
        session.close();
    }

    public void modificaCliente(Clientes clientesVO) {
        Session session = getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();
        session.update(clientesVO);
        tx.commit();
        session.close();
    }
    public void eliminaCliente(Clientes clientesVO) {
        Session session = getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();
        session.delete(clientesVO);
        tx.commit();
        session.close();
    }
}
