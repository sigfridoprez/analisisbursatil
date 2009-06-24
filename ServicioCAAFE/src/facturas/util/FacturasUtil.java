/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facturas.util;

import caafes.def.Facturas;
import java.util.Collection;

/**
 *
 * @author Edgar
 */
public class FacturasUtil {

    private Facturas facturas;
    private Collection<DetalleFacturaUtil> detalleFactura;

    public Collection<DetalleFacturaUtil> getDetalleFactura() {
        return detalleFactura;
    }

    public Facturas getFacturas() {
        return facturas;
    }

    public void setFacturas(Facturas facturas) {
        this.facturas = facturas;
    }

    public void setDetalleFactura(Collection<DetalleFacturaUtil> detalleFactura) {
        this.detalleFactura = detalleFactura;
    }
}
