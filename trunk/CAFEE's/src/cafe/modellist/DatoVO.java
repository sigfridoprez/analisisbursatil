/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cafe.modellist;

import java.math.BigDecimal;

/**
 *
 * @author Edgar
 */
public class DatoVO {
    private BigDecimal bdId;
    private String strDesc;

    public DatoVO(){
        bdId = new BigDecimal("0");
        strDesc = "";
    }

    public DatoVO(String strDesc){
        this.bdId = new BigDecimal("0");
        this.strDesc = strDesc;
    }

    public DatoVO(BigDecimal bdId,String strDesc){
        this.bdId = bdId;
        this.strDesc = strDesc;
    }

    public DatoVO(String bdId,String strDesc){
        this.bdId = new BigDecimal(bdId);
        this.strDesc = strDesc;
    }

     public DatoVO(int bdId,String strDesc){
        this.bdId = new BigDecimal(bdId);
        this.strDesc = strDesc;
    }

    public BigDecimal getBdId() {
        return bdId;
    }

    public void setBdId(BigDecimal bdId) {
        this.bdId = bdId;
    }

    public String getStrDesc() {
        return strDesc;
    }

    public void setStrDesc(String strDesc) {
        this.strDesc = strDesc;
    }

}
