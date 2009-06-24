/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.servicios.explorador;

/**
 *
 * @author sigfrido
 */
public class DommyClass {
    private String descripcion;
    private long id;

    public DommyClass(String descripcion, long id) {
        this.descripcion = descripcion;
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "[descripcion::"+descripcion+ " id::"+ id + " ]";
    }


}
