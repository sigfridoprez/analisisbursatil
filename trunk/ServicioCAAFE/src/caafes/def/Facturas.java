/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package caafes.def;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 *
 * @author Edgar
 */
@Entity
@Table(name = "FACTURAS")
@NamedQueries({@NamedQuery(name = "Facturas.findAll", query = "SELECT f FROM Facturas f"), @NamedQuery(name = "Facturas.findByIdCliente", query = "SELECT f FROM Facturas f WHERE f.facturasPK.idCliente = :idCliente"), @NamedQuery(name = "Facturas.findByIdFolioFactura", query = "SELECT f FROM Facturas f WHERE f.facturasPK.idFolioFactura = :idFolioFactura"), @NamedQuery(name = "Facturas.findByFechaCreacion", query = "SELECT f FROM Facturas f WHERE f.fechaCreacion = :fechaCreacion")})
public class Facturas implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FacturasPK facturasPK;
    @Column(name = "EXPORTADO")
    private Character exportado;
    @Basic(optional = false)
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Clientes clientes;

    public Facturas() {
    }

    public Facturas(FacturasPK facturasPK) {
        this.facturasPK = facturasPK;
    }

    public Facturas(FacturasPK facturasPK,  Date fechaCreacion) {
        this.facturasPK = facturasPK;
        this.fechaCreacion = fechaCreacion;
    }

    public Facturas(BigDecimal idCliente, BigDecimal idFolioFactura) {
        this.facturasPK = new FacturasPK(idCliente, idFolioFactura);
    }

    public FacturasPK getFacturasPK() {
        return facturasPK;
    }

    public void setFacturasPK(FacturasPK facturasPK) {
        this.facturasPK = facturasPK;
    }


    public Character getExportado() {
        return exportado;
    }

    public void setExportado(Character exportado) {
        this.exportado = exportado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facturasPK != null ? facturasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facturas)) {
            return false;
        }
        Facturas other = (Facturas) object;
        if ((this.facturasPK == null && other.facturasPK != null) || (this.facturasPK != null && !this.facturasPK.equals(other.facturasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sbReturn = new StringBuilder("caafes.def.Facturas[facturasPK=" + facturasPK.toString() + "]");
        sbReturn.append("caafes.def.Facturas[");
        sbReturn.append("fechaCreacion="+fechaCreacion);
        sbReturn.append("]");
        return sbReturn.toString();
    }
}
