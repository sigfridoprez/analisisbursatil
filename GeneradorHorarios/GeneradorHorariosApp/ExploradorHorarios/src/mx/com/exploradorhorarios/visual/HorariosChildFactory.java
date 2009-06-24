/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.exploradorhorarios.visual;

import java.util.List;
import mx.com.servicios.explorador.DommyClass;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Node;
import org.openide.nodes.Children;
import org.openide.util.lookup.Lookups;


/**
 *
 * @author sigfrido
 */
public class HorariosChildFactory extends org.openide.nodes.ChildFactory<DommyClass> {

    private List<DommyClass> resultList;

    public HorariosChildFactory(List<DommyClass> resultList) {
        this.resultList = resultList;
    }

        @Override
    protected boolean createKeys(List<DommyClass> list) {
        for (DommyClass obj : resultList) {
            list.add(obj);
        }
        return true;
    }

    @Override
    protected Node createNodeForKey(DommyClass obj) {
        Node node = new HorariosNode(Children.LEAF,Lookups.singleton(obj));
        node.setDisplayName(obj.getDescripcion());
        node.setShortDescription(obj.getId()+"");
        return node;
    }
}
