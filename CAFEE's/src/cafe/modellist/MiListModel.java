/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cafe.modellist;

import java.util.Vector;
import javax.swing.AbstractListModel;

/**
 *
 * @author Edgar
 */
public class MiListModel extends AbstractListModel{
    private Vector<DatoVO> vLista = new Vector<DatoVO>();

    public MiListModel(){
    }

    public MiListModel(String cad){
        addElement(cad);
    }

    public void limpiaModelo(){
        vLista.clear();
    }

    @Override
    public int getSize() {
        return vLista.size();
    }

    @Override
    public Object getElementAt(int index) {
        return (vLista.elementAt(index)).getStrDesc();
    }

    public DatoVO getElementVO(int index) {
        return (vLista.elementAt(index));
    }

    public boolean isEmpty() {
        return vLista.isEmpty();
    }

    public void addElement(String cad){
        DatoVO vo = new DatoVO(cad);
        vLista.add(vo);
    }

    public void addElement(long bdId,String cad){
        DatoVO vo = new DatoVO(bdId,cad);
        vLista.add(vo);
    }
}
