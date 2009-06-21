/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package caafes.def;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Edgar
 */
@Embeddable
public class FacturasPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_CLIENTE")
    private BigDecimal idCliente;
    @Basic(optional = false)
    @Column(name = "ID_FOLIO_FACTURA")
    private BigDecimal idFolioFactura;

    public FacturasPK() {
    }

    public FacturasPK(BigDecimal idCliente, BigDecimal idFolioFactura) {
        this.idCliente = idCliente;
        this.idFolioFactura = idFolioFactura;
    }

    public BigDecimal getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(BigDecimal idCliente) {
        this.idCliente = idCliente;
    }

    public BigDecimal getIdFolioFactura() {
        return idFolioFactura;
    }

    public void setIdFolioFactura(BigDecimal idFolioFactura) {
        this.idFolioFactura = idFolioFactura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        hash += (idFolioFactura != null ? idFolioFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacturasPK)) {
            return false;
        }
        FacturasPK other = (FacturasPK) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        if ((this.idFolioFactura == null && other.idFolioFactura != null) || (this.idFolioFactura != null && !this.idFolioFactura.equals(other.idFolioFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caafes.def.FacturasPK[idCliente=" + idCliente + ", idFolioFactura=" + idFolioFactura + "]";
    }

}
