/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
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
        Properties pro = new Properties();
        String foo = "Not Connected";
        int bar = -1;


        pro.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
        pro.put(Context.PROVIDER_URL, "iiop://127.0.0.1:3700");

        //Context ctx = new InitialContext(pro);
        Context ctx = new InitialContext();

        System.out.println("ctx::" + ctx);
        DataSource ds = (DataSource) ctx.lookup("jdbc/JNDI_DERBY");
        System.out.println("datasource::"+ds);
        
        Connection conn = ds.getConnection();

        foo = "Got Connection " + conn.toString();
        Statement stmt = conn.createStatement();
        ResultSet rst =
                stmt.executeQuery(
                "select hotelid,hotelname from TRAVEL.HOTEL");
        if (rst.next()) {
            foo = rst.getString(2);
            bar = rst.getInt(1);
        }
        conn.close();
        System.out.println("foo:::"+foo);
        System.out.println("bar:::"+bar);
    }
}
