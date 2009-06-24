/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.exploradorhorarios.visual;

import mx.com.exploradorhorarios.ExploradorHorariosTopComponent;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.NbBundle;

/**
 *
 * @author sigfrido
 */
public class HorariosRootNode extends AbstractNode{

    public HorariosRootNode(Children hijos) {
        super(hijos);
        setDisplayName(NbBundle.getMessage(ExploradorHorariosTopComponent.class, "CTL_Root_Node"));
        this.setIconBaseWithExtension("mx/com/exploradorhorarios/visual/root.png");
    }

}
