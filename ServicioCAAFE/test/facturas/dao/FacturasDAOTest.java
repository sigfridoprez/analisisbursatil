/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturas.dao;

import caafes.def.Facturas;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.vo.UtilVO;

/**
 *
 * @author Edgar
 */
public class FacturasDAOTest {

    public FacturasDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of ejecuta method, of class FacturasDAO.
     */
   // @Test
    public void testEjecuta() {
        System.out.println("ejecuta");
        FacturasDAO instance = new FacturasDAO();
        instance.ejecuta();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtienFactura method, of class FacturasDAO.
     */
    //@Test
    public void testObtienFactura_long_long() {
        System.out.println("obtienFactura");
        BigDecimal idFolio = new BigDecimal("0");
        BigDecimal idCliente = new BigDecimal("0");
        
        FacturasDAO instance = new FacturasDAO();
        Facturas result = instance.obtienFactura(idFolio, idCliente);

        System.out.println("result::"+result.toString());
    }

    /**
     * Test of obtienFactura method, of class FacturasDAO.
     */
   // @Test
    public void testObtienFactura_long() {
        System.out.println("obtienFactura");
        BigDecimal idFolio = new BigDecimal("1");
        FacturasDAO instance = new FacturasDAO();

        Facturas result = instance.obtienFactura(idFolio);
       System.out.println("result::"+result);
    }

    /**
     * Test of obtieneListaFacturas method, of class FacturasDAO.
     */
    //@Test
    public void testObtieneListaFacturas() {
        System.out.println("obtieneListaFacturas");
        BigDecimal iDCliente = new BigDecimal("0");

        FacturasDAO instance = new FacturasDAO();
        List<Facturas> result = instance.obtieneListaFacturas(iDCliente);
        
        for (Facturas facturas : result) {
            System.out.println("facturas::"+facturas);    
        }
    }

    /**
     * Test of obtieneListaFacturasFolio method, of class FacturasDAO.
     */
    //@Test
    public void testObtieneListaFacturasFolio() {
        System.out.println("obtieneListaFacturasFolio");
        BigDecimal iDFolio = new BigDecimal("0");

        FacturasDAO instance = new FacturasDAO();
        List<Facturas> result = instance.obtieneListaFacturasFolio(iDFolio);
        for (Facturas facturas : result) {
            System.out.println("facturas::"+facturas);
        }
    }

    /**
     * Test of obtieneListaFacturasSinAutorizacion method, of class FacturasDAO.
     */
    //@Test
    public void testObtieneListaFacturasSinAutorizacion() {
        System.out.println("obtieneListaFacturasSinAutorizacion");
        FacturasDAO instance = new FacturasDAO();
        
        List<UtilVO> result = instance.obtieneListaFacturasSinAutorizacion();
        for(UtilVO vo:result){
            System.out.println("vo.id:::"+vo.getLngId());
            System.out.println("vo.d:::"+vo.getStrDescripcion());
        }

    }

    /**
     * Test of modificaFactura method, of class FacturasDAO.
     */
    //@Test
    public void testModificaFactura() {
        System.out.println("modificaFactura");
        Facturas facturasVO = null;
        FacturasDAO instance = new FacturasDAO();
        BigDecimal idFolio = new BigDecimal("0");

        facturasVO = instance.obtienFactura(idFolio);
        facturasVO.setCantidad(500);
        instance.modificaFactura(facturasVO);
    }

    /**
     * Test of eliminaFactura method, of class FacturasDAO.
     */
    //@Test
    public void testEliminaFactura() {
        System.out.println("eliminaFactura");
        Facturas facturasVO = null;
        BigDecimal idCliente = new BigDecimal("0");
        BigDecimal idFolioFactura = new BigDecimal("0");
        FacturasDAO instance = new FacturasDAO();

        facturasVO = new Facturas(idCliente, idFolioFactura);

        instance.eliminaFactura(facturasVO);
    }

}