/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.proveedorservicios.utilerias;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import mx.com.servicios.utilerias.UtileriasSrv;

/**
 *
 * @author sigfrido
 */
public class UtileriasSrvImpl implements UtileriasSrv{

    public String getSH1(String password)throws NoSuchAlgorithmException {
        StringBuilder strHash = new StringBuilder("");
        byte[] buffer = password.getBytes();
        byte[] digest;
        MessageDigest messageDigest = MessageDigest.getInstance("SHA1");  // Inicializa SHA-512


        messageDigest.update(buffer);
        digest = messageDigest.digest();

        for(byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1){
                strHash.append("0");
            }
            strHash.append(Integer.toHexString(b));
        }

        return strHash.toString();
    }

    public String getM5(String password)throws NoSuchAlgorithmException{
        StringBuilder strHash = new StringBuilder("");
        byte[] buffer = password.getBytes();
        byte[] digest;
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");  // Inicializa MD5

        messageDigest.update(buffer);
        digest = messageDigest.digest();

        for(byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1){
                strHash.append("0");
            }
            strHash.append(Integer.toHexString(b));
        }

        return strHash.toString();
    }

    public String getTituloApp() {
        return "Generador de Horarios de Secundaria";
    }

}
