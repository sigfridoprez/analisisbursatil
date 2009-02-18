/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package foo;

import javax.naming.*;
import javax.sql.*;
import java.sql.*;

public class DBTest {

    String foo = "Not Connected";
    int bar = -1;

    public void init() {
        try {
            Context ctx = new InitialContext();
            if (ctx == null) {
                throw new Exception("Boom - No Context");
            }

            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/JNDI_DERBY");
            
            if (ds != null) {
                Connection conn = ds.getConnection();

                if (conn != null) {
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
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getFoo() {
        return foo;
    }

    public int getBar() {
        return bar;
    }
}


