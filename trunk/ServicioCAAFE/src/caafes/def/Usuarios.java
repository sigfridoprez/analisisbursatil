/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package caafes.def;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Edgar
 */
@Entity
@Table(name = "USUARIOS")
@NamedQueries({@NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"), @NamedQuery(name = "Usuarios.findByIdUsuario", query = "SELECT u FROM Usuarios u WHERE u.idUsuario = :idUsuario"), @NamedQuery(name = "Usuarios.findByPassword", query = "SELECT u FROM Usuarios u WHERE u.password = :password"), @NamedQuery(name = "Usuarios.findByExportado", query = "SELECT u FROM Usuarios u WHERE u.exportado = :exportado")})
public class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_USUARIO")
    private BigDecimal idUsuario;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "EXPORTADO")
    private Character exportado;

    public Usuarios() {
    }

    public Usuarios(BigDecimal idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuarios(BigDecimal idUsuario, String password) {
        this.idUsuario = idUsuario;
        this.password = password;
    }

    public BigDecimal getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(BigDecimal idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Character getExportado() {
        return exportado;
    }

    public void setExportado(Character exportado) {
        this.exportado = exportado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caafes.def.Usuarios[idUsuario=" + idUsuario + "]";
    }

}
