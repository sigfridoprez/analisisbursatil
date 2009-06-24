/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturas.util;

import caafes.def.Autorizaciones;
import caafes.def.DetalleFactura;

/**
 *
 * @author Edgar
 */
public class DetalleFacturaUtil {
    private DetalleFactura detalleFactura;
    private Autorizaciones autorizaciones;

    public Autorizaciones getAutorizaciones() {
        return autorizaciones;
    }

    public void setAutorizaciones(Autorizaciones autorizaciones) {
        this.autorizaciones = autorizaciones;
    }

    public DetalleFactura getDetalleFactura() {
        return detalleFactura;
    }

    public void setDetalleFactura(DetalleFactura detalleFactura) {
        this.detalleFactura = detalleFactura;
    }
}
