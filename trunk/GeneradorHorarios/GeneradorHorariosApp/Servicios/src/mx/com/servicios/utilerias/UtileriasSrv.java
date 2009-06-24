/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.servicios.utilerias;

import java.security.NoSuchAlgorithmException;

/**
 *
 * @author sigfrido
 */
public interface UtileriasSrv {
    public String getSH1(String password)throws NoSuchAlgorithmException;
    public String getM5(String password)throws NoSuchAlgorithmException;
    public String getTituloApp();
}
