/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package caafes.def;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Edgar
 */
@Entity
@Table(name = "DETALLE_FACTURA")
@NamedQueries({@NamedQuery(name = "DetalleFactura.findAll", query = "SELECT d FROM DetalleFactura d"), @NamedQuery(name = "DetalleFactura.findByIdCliente", query = "SELECT d FROM DetalleFactura d WHERE d.detalleFacturaPK.idCliente = :idCliente"), @NamedQuery(name = "DetalleFactura.findByIdFolioFactura", query = "SELECT d FROM DetalleFactura d WHERE d.detalleFacturaPK.idFolioFactura = :idFolioFactura"), @NamedQuery(name = "DetalleFactura.findByIdDetalleFactura", query = "SELECT d FROM DetalleFactura d WHERE d.detalleFacturaPK.idDetalleFactura = :idDetalleFactura"), @NamedQuery(name = "DetalleFactura.findByCantidad", query = "SELECT d FROM DetalleFactura d WHERE d.cantidad = :cantidad"), @NamedQuery(name = "DetalleFactura.findByAnotaciones", query = "SELECT d FROM DetalleFactura d WHERE d.anotaciones = :anotaciones"), @NamedQuery(name = "DetalleFactura.findByTipoTrabajo", query = "SELECT d FROM DetalleFactura d WHERE d.tipoTrabajo = :tipoTrabajo"), @NamedQuery(name = "DetalleFactura.findByCostoTrabajo", query = "SELECT d FROM DetalleFactura d WHERE d.costoTrabajo = :costoTrabajo")})
public class DetalleFactura implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleFacturaPK detalleFacturaPK;
    @Basic(optional = false)
    @Column(name = "CANTIDAD")
    private long cantidad;
    @Basic(optional = false)
    @Column(name = "ANOTACIONES ")
    private String anotaciones;
    @Basic(optional = false)
    @Column(name = "TIPO_TRABAJO")
    private String tipoTrabajo;
    @Basic(optional = false)
    @Column(name = "COSTO_TRABAJO")
    private BigDecimal costoTrabajo;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detalleFactura")
//    private Collection<Autorizaciones> autorizacionesCollection;
//    @JoinColumns({@JoinColumn(name = "ID_FOLIO_FACTURA", referencedColumnName = "ID_FOLIO_FACTURA", insertable = false, updatable = false), @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE", insertable = false, updatable = false)})
//    @ManyToOne(optional = false)
    private Facturas facturas;

    public DetalleFactura() {
    }

    public DetalleFactura(DetalleFacturaPK detalleFacturaPK) {
        this.detalleFacturaPK = detalleFacturaPK;
    }

    public DetalleFactura(DetalleFacturaPK detalleFacturaPK, long cantidad, String anotaciones, String tipoTrabajo, BigDecimal costoTrabajo) {
        this.detalleFacturaPK = detalleFacturaPK;
        this.cantidad = cantidad;
        this.anotaciones = anotaciones;
        this.tipoTrabajo = tipoTrabajo;
        this.costoTrabajo = costoTrabajo;
    }

    public DetalleFactura(BigInteger idCliente, BigInteger idFolioFactura, BigInteger idDetalleFactura) {
        this.detalleFacturaPK = new DetalleFacturaPK(idCliente, idFolioFactura, idDetalleFactura);
    }

    public DetalleFacturaPK getDetalleFacturaPK() {
        return detalleFacturaPK;
    }

    public void setDetalleFacturaPK(DetalleFacturaPK detalleFacturaPK) {
        this.detalleFacturaPK = detalleFacturaPK;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public String getAnotaciones() {
        return anotaciones;
    }

    public void setAnotaciones(String anotaciones) {
        this.anotaciones = anotaciones;
    }

    public String getTipoTrabajo() {
        return tipoTrabajo;
    }

    public void setTipoTrabajo(String tipoTrabajo) {
        this.tipoTrabajo = tipoTrabajo;
    }

    public BigDecimal getCostoTrabajo() {
        return costoTrabajo;
    }

    public void setCostoTrabajo(BigDecimal costoTrabajo) {
        this.costoTrabajo = costoTrabajo;
    }

//    public Collection<Autorizaciones> getAutorizacionesCollection() {
//        return autorizacionesCollection;
//    }
//
//    public void setAutorizacionesCollection(Collection<Autorizaciones> autorizacionesCollection) {
//        this.autorizacionesCollection = autorizacionesCollection;
//    }

    public Facturas getFacturas() {
        return facturas;
    }

    public void setFacturas(Facturas facturas) {
        this.facturas = facturas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleFacturaPK != null ? detalleFacturaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleFactura)) {
            return false;
        }
        DetalleFactura other = (DetalleFactura) object;
        if ((this.detalleFacturaPK == null && other.detalleFacturaPK != null) || (this.detalleFacturaPK != null && !this.detalleFacturaPK.equals(other.detalleFacturaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caafes.def.DetalleFactura[detalleFacturaPK=" + detalleFacturaPK + "]";
    }

}
