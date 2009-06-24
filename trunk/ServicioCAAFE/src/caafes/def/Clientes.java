/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caafes.def;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Edgar
 */
@Entity
@Table(name = "CLIENTES")
@NamedQueries({@NamedQuery(name = "Clientes.findAll", query = "SELECT c FROM Clientes c"), @NamedQuery(name = "Clientes.findByIdCliente", query = "SELECT c FROM Clientes c WHERE c.idCliente = :idCliente"), @NamedQuery(name = "Clientes.findByNombre", query = "SELECT c FROM Clientes c WHERE c.nombre = :nombre"), @NamedQuery(name = "Clientes.findByApellidos", query = "SELECT c FROM Clientes c WHERE c.apellidos = :apellidos"), @NamedQuery(name = "Clientes.findByRfc", query = "SELECT c FROM Clientes c WHERE c.rfc = :rfc"), @NamedQuery(name = "Clientes.findByCalleNumero", query = "SELECT c FROM Clientes c WHERE c.calleNumero = :calleNumero"), @NamedQuery(name = "Clientes.findByColonia", query = "SELECT c FROM Clientes c WHERE c.colonia = :colonia"), @NamedQuery(name = "Clientes.findByCodigoPostal", query = "SELECT c FROM Clientes c WHERE c.codigoPostal = :codigoPostal"), @NamedQuery(name = "Clientes.findByDelegacionMunicipio", query = "SELECT c FROM Clientes c WHERE c.delegacionMunicipio = :delegacionMunicipio"), @NamedQuery(name = "Clientes.findByCiudad", query = "SELECT c FROM Clientes c WHERE c.ciudad = :ciudad"), @NamedQuery(name = "Clientes.findByTelefono", query = "SELECT c FROM Clientes c WHERE c.telefono = :telefono"), @NamedQuery(name = "Clientes.findByExportado", query = "SELECT c FROM Clientes c WHERE c.exportado = :exportado")})
public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CLIENTE")
    private BigDecimal idCliente;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "RFC")
    private String rfc;
    @Basic(optional = false)
    @Column(name = "CALLE_NUMERO")
    private String calleNumero;
    @Basic(optional = false)
    @Column(name = "COLONIA")
    private String colonia;
    @Basic(optional = false)
    @Column(name = "CODIGO_POSTAL")
    private String codigoPostal;
    @Basic(optional = false)
    @Column(name = "DELEGACION_MUNICIPIO")
    private String delegacionMunicipio;
    @Basic(optional = false)
    @Column(name = "CIUDAD")
    private String ciudad;
    @Basic(optional = false)
    @Column(name = "TELEFONO")
    private String telefono;
    @Basic(optional = false)
    @Column(name = "NUMERO_ARCHIVO")
    private String numeroArchivo;
    @Column(name = "EXPORTADO")
    private Character exportado;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientes")
//    private Collection<Facturas> facturasCollection;
    public Clientes() {
    }

    public Clientes(BigDecimal idCliente) {
        this.idCliente = idCliente;
    }

    public Clientes( String nombre, String apellidos, String rfc, String calleNumero, String colonia, String codigoPostal, String delegacionMunicipio, String ciudad, String telefono, String numeroArchivo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.rfc = rfc;
        this.calleNumero = calleNumero;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
        this.delegacionMunicipio = delegacionMunicipio;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.numeroArchivo = numeroArchivo;
    }

    public BigDecimal getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(BigDecimal idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCalleNumero() {
        return calleNumero;
    }

    public void setCalleNumero(String calleNumero) {
        this.calleNumero = calleNumero;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getDelegacionMunicipio() {
        return delegacionMunicipio;
    }

    public void setDelegacionMunicipio(String delegacionMunicipio) {
        this.delegacionMunicipio = delegacionMunicipio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Character getExportado() {
        return exportado;
    }

    public void setExportado(Character exportado) {
        this.exportado = exportado;
    }

    public String getNumeroArchivo() {
        return numeroArchivo;
    }

    public void setNumeroArchivo(String numeroArchivo) {
        this.numeroArchivo = numeroArchivo;
    }

//    public Collection<Facturas> getFacturasCollection() {
//        return facturasCollection;
//    }
//
//    public void setFacturasCollection(Collection<Facturas> facturasCollection) {
//        this.facturasCollection = facturasCollection;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caafes.def.Clientes[idCliente=" + idCliente + "]";
    }
}
