/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caafe.table.tablemodel;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author Edgar
 */
public class DetalleAutorizacion {
    private BigDecimal idAutorizacion;
    private BigInteger folioInicio;
    private BigInteger folioFinal;
    private String solicitante;
    private String autorizo;
    private Date fechaCreacion;
    private Date caducidad;

   
    public DetalleAutorizacion() {
//        idAutorizacion = new BigDecimal("0");
    }

    public DetalleAutorizacion(BigDecimal idAutorizacion, BigInteger folioInicio, BigInteger folioFinal, Date caducidad, String solicitante, String autorizo, Date fechaCreacion) {
        this.idAutorizacion = idAutorizacion;
        this.folioInicio = folioInicio;
        this.folioFinal = folioFinal;
        this.caducidad = caducidad;
        this.solicitante = solicitante;
        this.autorizo = autorizo;
        this.fechaCreacion = fechaCreacion;
    }

    public String getAutorizo() {
        return autorizo;
    }

    public void setAutorizo(String autorizo) {
        this.autorizo = autorizo;
    }

    public Date getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(Date caducidad) {
        this.caducidad = caducidad;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public BigInteger getFolioFinal() {
        return folioFinal;
    }

    public void setFolioFinal(BigInteger folioFinal) {
        this.folioFinal = folioFinal;
    }

    public BigInteger getFolioInicio() {
        return folioInicio;
    }

    public void setFolioInicio(BigInteger folioInicio) {
        this.folioInicio = folioInicio;
    }

    public BigDecimal getIdAutorizacion() {
        return idAutorizacion;
    }

    public void setIdAutorizacion(BigDecimal idAutorizacion) {
        this.idAutorizacion = idAutorizacion;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }


}
