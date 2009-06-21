/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clientes.servicio;

import clientes.dao.DAOClientes;
import clientes.dao.DAOInsertaCliente;
import clientes.vo.ClienteVO;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import caafes.def.Clientes;

/**
 *
 * @author Edgar
 */
public class ServicioCliente {

    public Clientes obtieneCliente(BigDecimal idCliente, String Rfc) {
        DAOClientes dao = new DAOClientes();
        //dao.ejecuta();
        return dao.obtieneCliente(idCliente, Rfc);
    }

    public List<Clientes> obtieneClientes(String consulta) {
        DAOClientes dAOInsertaCliente = new DAOClientes();
        return dAOInsertaCliente.obtieneListaClientes(consulta);
    }

    public void insertaCliente(Clientes nuevo) {
        //try {
            DAOClientes dAOInsertaCliente = new DAOClientes();
           dAOInsertaCliente.insertaCliente(nuevo);
            //dAOInsertaCliente.ejecuta();

        /*} catch (ClassNotFoundException ex) {
          //  Logger.getLogger(ServicioCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServicioCliente.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

      public void modificaCliente(Clientes nuevo) {
            DAOClientes dAOInsertaCliente = new DAOClientes();
           dAOInsertaCliente.modificaCliente(nuevo);
    }
      public void eliminaCliente(Clientes nuevo) {
            DAOClientes dAoEliminaClliente = new DAOClientes();
           dAoEliminaClliente.eliminaCliente(nuevo);
    }
}
