/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package caafes.def;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Edgar
 */
@Entity
@Table(name = "AUTORIZACIONES")
@NamedQueries({@NamedQuery(name = "Autorizaciones.findAll", query = "SELECT a FROM Autorizaciones a"), @NamedQuery(name = "Autorizaciones.findByIdCliente", query = "SELECT a FROM Autorizaciones a WHERE a.autorizacionesPK.idCliente = :idCliente"), @NamedQuery(name = "Autorizaciones.findByIdFolioFactura", query = "SELECT a FROM Autorizaciones a WHERE a.autorizacionesPK.idFolioFactura = :idFolioFactura"), @NamedQuery(name = "Autorizaciones.findByIdDetalleFactura", query = "SELECT a FROM Autorizaciones a WHERE a.autorizacionesPK.idDetalleFactura = :idDetalleFactura"), @NamedQuery(name = "Autorizaciones.findByIdAutorizacion", query = "SELECT a FROM Autorizaciones a WHERE a.autorizacionesPK.idAutorizacion = :idAutorizacion"), @NamedQuery(name = "Autorizaciones.findByFolioInicio", query = "SELECT a FROM Autorizaciones a WHERE a.folioInicio = :folioInicio"), @NamedQuery(name = "Autorizaciones.findByFolioFinal", query = "SELECT a FROM Autorizaciones a WHERE a.folioFinal = :folioFinal"), @NamedQuery(name = "Autorizaciones.findByCaducidad", query = "SELECT a FROM Autorizaciones a WHERE a.caducidad = :caducidad"), @NamedQuery(name = "Autorizaciones.findBySolicitante", query = "SELECT a FROM Autorizaciones a WHERE a.solicitante = :solicitante"), @NamedQuery(name = "Autorizaciones.findByAutorizo", query = "SELECT a FROM Autorizaciones a WHERE a.autorizo = :autorizo"), @NamedQuery(name = "Autorizaciones.findByFechaCreacion", query = "SELECT a FROM Autorizaciones a WHERE a.fechaCreacion = :fechaCreacion"), @NamedQuery(name = "Autorizaciones.findByExportado", query = "SELECT a FROM Autorizaciones a WHERE a.exportado = :exportado")})
public class Autorizaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AutorizacionesPK autorizacionesPK;
    @Basic(optional = false)
    @Column(name = "FOLIO_INICIO")
    private BigInteger folioInicio;
    @Basic(optional = false)
    @Column(name = "FOLIO_FINAL")
    private BigInteger folioFinal;
    @Basic(optional = false)
    @Column(name = "CADUCIDAD")
    @Temporal(TemporalType.DATE)
    private Date caducidad;
    @Column(name = "SOLICITANTE")
    private String solicitante;
    @Basic(optional = false)
    @Column(name = "AUTORIZO")
    private String autorizo;
    @Basic(optional = false)
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = "EXPORTADO")
    private Character exportado;
//    @JoinColumns({@JoinColumn(name = "ID_FOLIO_FACTURA", referencedColumnName = "ID_FOLIO_FACTURA", insertable = false, updatable = false), @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE", insertable = false, updatable = false), @JoinColumn(name = "ID_DETALLE_FACTURA", referencedColumnName = "ID_DETALLE_FACTURA", insertable = false, updatable = false)})
//    @ManyToOne(optional = false)
//    private DetalleFactura detalleFactura;

    public Autorizaciones() {
    }

    public Autorizaciones(AutorizacionesPK autorizacionesPK) {
        this.autorizacionesPK = autorizacionesPK;
    }

    public Autorizaciones(AutorizacionesPK autorizacionesPK, BigInteger folioInicio, BigInteger folioFinal, Date caducidad, String autorizo, Date fechaCreacion) {
        this.autorizacionesPK = autorizacionesPK;
        this.folioInicio = folioInicio;
        this.folioFinal = folioFinal;
        this.caducidad = caducidad;
        this.autorizo = autorizo;
        this.fechaCreacion = fechaCreacion;
    }

    public Autorizaciones(BigInteger idCliente, BigInteger idFolioFactura, BigInteger idDetalleFactura, BigInteger idAutorizacion) {
        this.autorizacionesPK = new AutorizacionesPK(idCliente, idFolioFactura, idDetalleFactura, idAutorizacion);
    }

    public AutorizacionesPK getAutorizacionesPK() {
        return autorizacionesPK;
    }

    public void setAutorizacionesPK(AutorizacionesPK autorizacionesPK) {
        this.autorizacionesPK = autorizacionesPK;
    }

    public BigInteger getFolioInicio() {
        return folioInicio;
    }

    public void setFolioInicio(BigInteger folioInicio) {
        this.folioInicio = folioInicio;
    }

    public BigInteger getFolioFinal() {
        return folioFinal;
    }

    public void setFolioFinal(BigInteger folioFinal) {
        this.folioFinal = folioFinal;
    }

    public Date getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(Date caducidad) {
        this.caducidad = caducidad;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getAutorizo() {
        return autorizo;
    }

    public void setAutorizo(String autorizo) {
        this.autorizo = autorizo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Character getExportado() {
        return exportado;
    }

    public void setExportado(Character exportado) {
        this.exportado = exportado;
    }

//    public DetalleFactura getDetalleFactura() {
//        return detalleFactura;
//    }
//
//    public void setDetalleFactura(DetalleFactura detalleFactura) {
//        this.detalleFactura = detalleFactura;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (autorizacionesPK != null ? autorizacionesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autorizaciones)) {
            return false;
        }
        Autorizaciones other = (Autorizaciones) object;
        if ((this.autorizacionesPK == null && other.autorizacionesPK != null) || (this.autorizacionesPK != null && !this.autorizacionesPK.equals(other.autorizacionesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caafes.def.Autorizaciones[autorizacionesPK=" + autorizacionesPK + "]";
    }

}
