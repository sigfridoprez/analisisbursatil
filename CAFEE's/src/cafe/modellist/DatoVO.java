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
    private long bdId;
    private String strDesc;

    public DatoVO(){
        bdId = 0;
        strDesc = "";
    }

    public DatoVO(String strDesc){
        this.bdId = 0;
        this.strDesc = strDesc;
    }

    public DatoVO(long bdId,String strDesc){
        this.bdId = bdId;
        this.strDesc = strDesc;
    }

    public DatoVO(String bdId,String strDesc){
        this.bdId = Long.parseLong(bdId);
        this.strDesc = strDesc;
    }

     public DatoVO(int bdId,String strDesc){
        this.bdId = bdId;
        this.strDesc = strDesc;
    }

    public long getBdId() {
        return bdId;
    }

    public void setBdId(long bdId) {
        this.bdId = bdId;
    }

    public String getStrDesc() {
        return strDesc;
    }

    public void setStrDesc(String strDesc) {
        this.strDesc = strDesc;
    }

}
