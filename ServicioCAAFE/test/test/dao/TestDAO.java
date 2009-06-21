/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.dao;

import caafes.def.Clientes;
import clientes.servicio.ServicioCliente;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import caafes.def.Facturas;
import caafes.def.FacturasPK;
import factura.servicio.ServicioFactura;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Edgar
 */
public class TestDAO {

    public TestDAO() {
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

    //@Test
    public void testDAOInsertaClientes() {
        System.out.println("INICIO :::::::::::: testDAOClientesJDBC ::::::::::::::: ");
        ServicioCliente servicioCliente = new ServicioCliente();
        Clientes clienteVO = new Clientes(new BigDecimal("0"), "Edgar", "Perez", "EDPE122222111", " Calle", "Colonia", 635, "delegacionMunicipio", "ciudad", 5591876316l);

//        Clientes clienteVO = servicioCliente.obtieneCliente(new BigDecimal("1"), null);
        System.out.println("clienteVO:::" + clienteVO);

        try {
            servicioCliente.insertaCliente(clienteVO);
        } catch (Exception e) {
            System.out.println("e::" + e.getMessage());
        }
        System.out.println("FIN :::::::::::: testDAOClientesJDBC ::::::::::::::: ");
    }

    @Test
    public void testListaClientes() {
        System.out.println("INICIO :::::::::::: testDAOClientesJDBC ::::::::::::::: ");

        ServicioCliente servicioCliente = new ServicioCliente();
        List<Clientes> lstClientes = servicioCliente.obtieneClientes("PE");
        System.out.println("lstClientes:::" + lstClientes);


        for (Clientes cliVO : lstClientes) {
            System.out.println("NOMBRE::" + cliVO.getNombre() + "  RFC::" + cliVO.getRfc());
        }

        System.out.println("FIN :::::::::::: testDAOClientesJDBC ::::::::::::::: ");
    }

    @Test
    public void testDAOFacturaDAO() {
        Facturas facturas;
        FacturasPK id;
        List<Facturas> as;

        System.out.println("INICIO :::::::::::: testDAOClientesDAO ::::::::::::::: ");

        ServicioFactura srvFactrua = new ServicioFactura();

        id = new FacturasPK(new BigDecimal("0"), new BigDecimal("1"));
        facturas = new Facturas(id, 1, "desc", new Date());
        srvFactrua.insertaFactura(facturas);


        as = srvFactrua.obtieneListaFacturas(new BigDecimal("0"));

        for (Facturas next : as) {
            System.out.println(next.getCantidad());
        }

        System.out.println("FIN :::::::::::: testDAOClientesDAO ::::::::::::::: ");
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}