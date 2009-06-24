/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package usuarios.servicio;

import caafes.def.Usuarios;
import java.math.BigDecimal;
import usuarios.dao.usuarioDAO;

/**
 *
 * @author Edgar
 */
public class ServicioUsuario {

    public void modificaUsuario(Usuarios nuevo) {
        usuarioDAO usuarioDAO = new usuarioDAO();
        usuarioDAO.modificaUsuario(nuevo);
    }
    public Usuarios ObtieneUsuarios(BigDecimal idUsuario) {
        usuarioDAO dao = new usuarioDAO();
        return dao.obtienUsuario(idUsuario);
    }

}
