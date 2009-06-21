/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factura.servicio;

import caafes.def.Facturas;
import facturas.dao.FacturasDAO;
import java.math.BigDecimal;
import java.util.List;
import utils.vo.UtilVO;

/**
 *
 * @author Edgar
 */
public class ServicioFactura {

    public Facturas obtieneFactura(BigDecimal idFolio, BigDecimal idCliente) {
        FacturasDAO dao = new FacturasDAO();
        return dao.obtienFactura(idFolio, idCliente);
    }

    public Facturas obtieneFactura(BigDecimal idFolio) {
        FacturasDAO dao = new FacturasDAO();
        return dao.obtienFactura(idFolio);
    }
    public Facturas obtieneFacturaFolioAutorizacion(BigDecimal idAutorizacion) {
        FacturasDAO dao = new FacturasDAO();
        return dao.obtienFacturaFolioAutorizacion(idAutorizacion);
    }

    public List<Facturas> obtieneListaFacturas(BigDecimal iDCliente) {
        FacturasDAO dAoObtieneListaFactura = new FacturasDAO();
        return dAoObtieneListaFactura.obtieneListaFacturas(iDCliente);
    }

    public List<Facturas> obtieneListaFacturasFolio(BigDecimal iDFolio) {
        FacturasDAO dAoObtieneListaFactura = new FacturasDAO();
        return dAoObtieneListaFactura.obtieneListaFacturasFolio(iDFolio);
    }
    public List<UtilVO> obtieneListaFacturasSinAutorizacion() {
        FacturasDAO dAoObtieneListaFactura = new FacturasDAO();
        return dAoObtieneListaFactura.obtieneListaFacturasSinAutorizacion();
    }

    public void insertaFactura(Facturas nuevo) {
        FacturasDAO dAOInsertaFactura = new FacturasDAO();
        dAOInsertaFactura.insertaFactura(nuevo);
    }
    public void modificaFactura(Facturas nuevo) {
        FacturasDAO dAOInsertaFactura = new FacturasDAO();
        dAOInsertaFactura.modificaFactura(nuevo);
    }
    public void eliminaFactura(Facturas nuevo) {
        FacturasDAO dAOeliminaFactura = new FacturasDAO();
        dAOeliminaFactura.eliminaFactura(nuevo);
    }
}
