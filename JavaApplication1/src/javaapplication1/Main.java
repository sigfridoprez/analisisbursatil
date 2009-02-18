/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author sperezc
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Hola mundo");
        Properties  pro = new Properties();
        
        pro.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory");
        Context ctx = new InitialContext(pro);
        
        DataSource ds  = (DataSource)ctx.lookup("java:comp/env/jdbc/JNDI_DERBY");
        System.out.println("ds::"+ds);
    }
}
