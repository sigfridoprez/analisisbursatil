/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clientes.vo;

import java.math.BigDecimal;

/**
 *
 * @author Edgar
 */
public class ClienteVO {

    private BigDecimal idCliente;
    private String nombre;
    private String apellidos;
    private String rfc;
    private String calleNumero;
    private String colonia;
    private BigDecimal codigoPostal;
    private String DelegacionMunicipio;
    private String ciudad;
    private BigDecimal telefono;
    private char valido;

    public String getDelegacionMunicipio() {
        return DelegacionMunicipio;
    }

    public void setDelegacionMunicipio(String DelegacionMunicipio) {
        this.DelegacionMunicipio = DelegacionMunicipio;
    }

    public String getCalleNumero() {
        return calleNumero;
    }

    public void setCalleNumero(String calleNumero) {
        this.calleNumero = calleNumero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public BigDecimal getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(BigDecimal codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public char getExportado() {
        return exportado;
    }

    public void setExportado(char exportado) {
        this.exportado = exportado;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public BigDecimal getTelefono() {
        return telefono;
    }

    public void setTelefono(BigDecimal telefono) {
        this.telefono = telefono;
    }

    public char getValido() {
        return valido;
    }

    public void setValido(char valido) {
        this.valido = valido;
    }
    private char exportado;

    public void setIdCliente(BigDecimal i) {
        idCliente = i;
    }

    public BigDecimal getIdCliente() {
        return idCliente;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
