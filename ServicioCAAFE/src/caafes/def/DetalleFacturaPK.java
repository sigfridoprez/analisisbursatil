/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package caafes.def;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Edgar
 */
@Embeddable
public class DetalleFacturaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_CLIENTE")
    private BigInteger idCliente;
    @Basic(optional = false)
    @Column(name = "ID_FOLIO_FACTURA")
    private BigInteger idFolioFactura;
    @Basic(optional = false)
    @Column(name = "ID_DETALLE_FACTURA")
    private BigInteger idDetalleFactura;

    public DetalleFacturaPK() {
    }

    public DetalleFacturaPK(BigInteger idCliente, BigInteger idFolioFactura, BigInteger idDetalleFactura) {
        this.idCliente = idCliente;
        this.idFolioFactura = idFolioFactura;
        this.idDetalleFactura = idDetalleFactura;
    }

    public BigInteger getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(BigInteger idCliente) {
        this.idCliente = idCliente;
    }

    public BigInteger getIdFolioFactura() {
        return idFolioFactura;
    }

    public void setIdFolioFactura(BigInteger idFolioFactura) {
        this.idFolioFactura = idFolioFactura;
    }

    public BigInteger getIdDetalleFactura() {
        return idDetalleFactura;
    }

    public void setIdDetalleFactura(BigInteger idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        hash += (idFolioFactura != null ? idFolioFactura.hashCode() : 0);
        hash += (idDetalleFactura != null ? idDetalleFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleFacturaPK)) {
            return false;
        }
        DetalleFacturaPK other = (DetalleFacturaPK) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        if ((this.idFolioFactura == null && other.idFolioFactura != null) || (this.idFolioFactura != null && !this.idFolioFactura.equals(other.idFolioFactura))) {
            return false;
        }
        if ((this.idDetalleFactura == null && other.idDetalleFactura != null) || (this.idDetalleFactura != null && !this.idDetalleFactura.equals(other.idDetalleFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caafes.def.DetalleFacturaPK[idCliente=" + idCliente + ", idFolioFactura=" + idFolioFactura + ", idDetalleFactura=" + idDetalleFactura + "]";
    }

}
