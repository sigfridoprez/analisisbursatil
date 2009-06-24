/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autorizacion.servicio;

import autorizacion.dao.AutorizacionDAO;
import caafes.def.Autorizaciones;
import caafes.def.Facturas;
import facturas.dao.FacturasDAO;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Edgar
 */
public class ServicioAutorizacion {

    public Autorizaciones obtieneAutorizacion(BigDecimal idAutorizacion) {
        AutorizacionDAO dao = new AutorizacionDAO();
        return dao.obtienAutorizacion(idAutorizacion);
    }
    public List<Autorizaciones> obtieneListaAutorizacionFolio(BigDecimal iDAutorizacion) {
        AutorizacionDAO dAoObtieneListaAutorizacion = new AutorizacionDAO();
        return dAoObtieneListaAutorizacion.obtieneListaAutorizacion(iDAutorizacion);
    }

    public List<Autorizaciones> obtieneListaAutorizaCreacion(){
        AutorizacionDAO dAobObtiene = new AutorizacionDAO();
        return dAobObtiene.obtienAutorizacionFechacrea();
    }

    public List<Autorizaciones> obtieneListaAutorizaCaduca(){
        AutorizacionDAO dAobObtiene = new AutorizacionDAO();
        return dAobObtiene.obtienAutorizacionFechaCaduca();
    }

    public BigDecimal obtieneIDmaximo(){
        AutorizacionDAO dAobObtieneIdMax = new AutorizacionDAO();
        return dAobObtieneIdMax.obtieneIDmaximo();
    }


    public void insertaAutorizacion(Autorizaciones nuevo) {
        AutorizacionDAO dAOInserta = new AutorizacionDAO();
        dAOInserta.insertaAutorizacion(nuevo);
    }
     public void modificaAutorizacion(Autorizaciones nuevo) {
        AutorizacionDAO dAoModifica = new AutorizacionDAO();
        dAoModifica.modificaAutorizacion(nuevo);
    }
}
