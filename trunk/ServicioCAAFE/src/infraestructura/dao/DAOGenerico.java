/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infraestructura.dao;

import java.sql.Connection;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

/**
 *
 * @author Edgar
 */
public abstract class DAOGenerico {

    protected Connection conexion = null;

    /*public DAOGenerico()throws ClassNotFoundException, SQLException{
    Class.forName("org.apache.derby.jdbc.ClientDriver");
    conexion = DriverManager.getConnection("jdbc:derby://localhost:1527/C:\\CAFESDB\\CAFESDB", "sdm", "adm");
    }*/

    /*protected void creaConexion() throws ClassNotFoundException, SQLException{
    Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
    conexion = DriverManager.getConnection("jdbc:derby:C:\\CAFESDB\\CAFESDB", "sdm", "adm");
    }*/
    public SessionFactory getSessionFactory() {
        return HibernateUtil.getSessionFactory();
    }
    //X public abstract void ejecuta();
}
