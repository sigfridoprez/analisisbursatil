/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.proveedorservicios.explorador;

import java.util.ArrayList;
import java.util.List;
import mx.com.servicios.explorador.DommyClass;
import mx.com.servicios.explorador.ExploradorSrv;

/**
 *
 * @author sigfrido
 */
public class ExploradorSrvImpl implements ExploradorSrv{

    public List<DommyClass> getListaHorarios() {
        List<DommyClass> lstReturn = new ArrayList<DommyClass>();

        for (int i = 0; i < 20; i++) {
            lstReturn.add(new DommyClass("Desc:"+i,i));
        }
        return lstReturn;
    }

}
