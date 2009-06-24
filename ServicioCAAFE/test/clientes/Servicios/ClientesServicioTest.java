/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clientes.Servicios;

import caafes.def.Clientes;
import facturas.dao.*;
import caafes.def.Facturas;
import clientes.dao.DAOClientes;
import clientes.servicio.ServicioCliente;
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
public class ClientesServicioTest {

    public ClientesServicioTest() {
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
        DAOClientes instance = new DAOClientes();
        instance.ejecuta();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtienFactura method, of class FacturasDAO.
     */
   // @Test
       public void testInsertaClienteDAO() {
        System.out.println("Incerta Factura");
        Clientes nuevo = new Clientes("Hola", "Estoy", "d", "que", "Funicone", "El", "DAO", "de", "clientes","12");

        DAOClientes instance = new DAOClientes();
        instance.insertaCliente(nuevo);

    }
       //@Test
       public void testInsertaClienteServicio() {
        System.out.println("Incerta Factura");
        Clientes nuevo = new Clientes("Hola", "Estoy", "dwads", "que", "Funicone", "El", "DAO", "de", "clientes","12");

        ServicioCliente instance = new ServicioCliente();
        instance.insertaCliente(nuevo);

    }
       @Test
       public void testObtieneListaUsuarios() {
        System.out.println("Obtiene Lista clientes");

        DAOClientes clientesDao = new DAOClientes();
        List<Clientes> listaClientes=clientesDao.obtieneListaClientes("p");
        
        
        
           for (Clientes as : listaClientes) {
               System.out.println(as.getRfc()+"/n");
           }
                 
             
         }

    }



    /**
     * Test of obtienFactura method, of class FacturasDAO.
     */
 