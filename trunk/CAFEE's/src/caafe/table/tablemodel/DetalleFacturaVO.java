/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caafe.table.tablemodel;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Edgar
 */
public class DetalleFacturaVO implements Serializable {

    private BigDecimal idCliente;
    private BigDecimal idFolioFactura;
    private BigDecimal idDetalleFactura;
    private int cantidad;
    private String anotaciones;
    private String tipoTrabajo;
    private BigDecimal costoTrabajo;
    private DetalleAutorizacion autorizacionVO=null;

    public DetalleFacturaVO() {
    }

    public DetalleFacturaVO(int cantidad, String anotaciones, BigDecimal costoTrabajo , String tipoTrabajo) {
        this.cantidad = cantidad;
        this.anotaciones = anotaciones;
        this.tipoTrabajo = tipoTrabajo;
        this.costoTrabajo = costoTrabajo;
    }

    public DetalleFacturaVO(BigDecimal idCliente, BigDecimal idFolioFactura, BigDecimal idDetalleFactura) {
        this.idCliente = idCliente;
        this.idFolioFactura = idFolioFactura;
        this.idDetalleFactura = idDetalleFactura;

        this.cantidad = 0;
        this.anotaciones = "";
        this.tipoTrabajo = "";
        this.costoTrabajo = new BigDecimal("0");
    }

    public DetalleFacturaVO(BigDecimal idCliente, BigDecimal idFolioFactura, BigDecimal idDetalleFactura, int cantidad, String anotaciones, String tipoTrabajo, BigDecimal costoTrabajo) {
        this.idCliente = idCliente;
        this.idFolioFactura = idFolioFactura;
        this.idDetalleFactura = idDetalleFactura;
        this.cantidad = cantidad;
        this.anotaciones = anotaciones;
        this.tipoTrabajo = tipoTrabajo;
        this.costoTrabajo = costoTrabajo;
    }

    public String getAnotaciones() {
        return anotaciones;
    }

    public void setAnotaciones(String anotaciones) {
        this.anotaciones = anotaciones;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getCostoTrabajo() {
        return costoTrabajo;
    }

    public void setCostoTrabajo(BigDecimal costoTrabajo) {
        this.costoTrabajo = costoTrabajo;
    }

    public BigDecimal getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(BigDecimal idCliente) {
        this.idCliente = idCliente;
    }

    public BigDecimal getIdDetalleFactura() {
        return idDetalleFactura;
    }

    public void setIdDetalleFactura(BigDecimal idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    public BigDecimal getIdFolioFactura() {
        return idFolioFactura;
    }

    public void setIdFolioFactura(BigDecimal idFolioFactura) {
        this.idFolioFactura = idFolioFactura;
    }

    public String getTipoTrabajo() {
        return tipoTrabajo;
    }

    public void setTipoTrabajo(String tipoTrabajo) {
        this.tipoTrabajo = tipoTrabajo;
    }
    public DetalleAutorizacion getAutorizacionVO() {
        return autorizacionVO;
    }

    public void setAutorizacionVO(DetalleAutorizacion autorizacionVO) {
        this.autorizacionVO = autorizacionVO;
    }
}
