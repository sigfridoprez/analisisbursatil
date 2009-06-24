/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.proveedorservicios.utilerias;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author sigfrido
 */
public class UtileriasSrvImplTestCase {
    private String password = "1";

    public UtileriasSrvImplTestCase() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of getSH1 method, of class RegistroSrvImpl.
     */
    @Test
    public void testGetSH1() throws Exception {
        System.out.println("getSH1");
        UtileriasSrvImpl instance = new UtileriasSrvImpl();
        String result = instance.getSH1(password);

        System.out.println("password::"+password + "  result::"+result);
    }

    /**
     * Test of getM5 method, of class RegistroSrvImpl.
     */
    @Test
    public void testGetM5() throws Exception {
        System.out.println("getM5");
        UtileriasSrvImpl instance = new UtileriasSrvImpl();

        String result = instance.getM5(password);

        System.out.println("password::"+password + "  result::"+result);
    }

}