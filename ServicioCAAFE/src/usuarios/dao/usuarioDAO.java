/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios.dao;

import caafes.def.Usuarios;
import org.hibernate.Session;
import infraestructura.dao.DAOGenerico;
import java.math.BigDecimal;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Edgar
 */
public class usuarioDAO extends DAOGenerico {

    public Usuarios obtienUsuario(BigDecimal idUsuario) {
        Session session = getSessionFactory().openSession();

        Usuarios usuarioVO = (Usuarios) session.createCriteria(Usuarios.class).add(Restrictions.eq("idUsuario", idUsuario)).uniqueResult();
        session.close();

        return usuarioVO;
    }

    public void modificaUsuario(Usuarios usuariosVO) {
        Session session = getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(usuariosVO);
        //session.delete(facturasVO);
        tx.commit();
        session.close();
    }
}
