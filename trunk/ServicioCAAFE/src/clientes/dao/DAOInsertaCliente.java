/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clientes.dao;

import clientes.vo.ClienteVO;
import infraestructura.dao.DAOGenerico;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edgar
 */
public class DAOInsertaCliente extends DAOGenerico {
    private ClienteVO clienteVO;
/*
    public DAOInsertaCliente(ClienteVO clienteVO) throws ClassNotFoundException, SQLException{
        super();
        this.clienteVO = clienteVO;
    }

    @Override
    public void ejecuta() {
        BigDecimal idCliente = new BigDecimal("0");

        //Obtenemos el ID_CLIENTE
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("Select max(id_cliente) from clientes");
        Statement stm;
        try {
            stm = conexion.createStatement();
            ResultSet res =  stm.executeQuery(sbQuery.toString());
            if(res.next()){
                idCliente = res.getBigDecimal(1);
                if(idCliente==null){
                    idCliente = new BigDecimal("0");
                }
                idCliente = idCliente.add(new BigDecimal("1"));
            }
            stm.close();
            res.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOInsertaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            PreparedStatement ppstm = conexion.prepareStatement("insert into " +
                       "CLIENTES(ID_CLIENTE, NOMBRE, APELLIDOS, RFC, CALLE_NUMERO, COLONIA, CODIGO_POSTAL, " +
                       "DELEGACION_MUNICIPIO, CIUDAD, TELEFONO, VALIDO, EXPORTADO)" +
                       "values(?,?,?,?,?,?,?,?,?,?,?,?)");
            ppstm.setBigDecimal(1, idCliente);
            ppstm.setString(2,this.clienteVO.getNombre());
            ppstm.setString(3,this.clienteVO.getApellidos());
            ppstm.setString(4,this.clienteVO.getRfc());
            ppstm.setString(5,this.clienteVO.getCalleNumero());
            ppstm.setString(6,this.clienteVO.getColonia());
            ppstm.setBigDecimal(7, this.clienteVO.getCodigoPostal());
            ppstm.setString(8,this.clienteVO.getDelegacionMunicipio());
            ppstm.setString(9,this.clienteVO.getCiudad());
            ppstm.setBigDecimal(10, this.clienteVO.getTelefono());
            ppstm.setString(11,String.valueOf(this.clienteVO.getValido()));
            ppstm.setString(12,String.valueOf(this.clienteVO.getExportado()));
            ppstm.execute();

            ppstm.close();
            conexion.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOInsertaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

*/
}
