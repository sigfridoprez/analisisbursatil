/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.exploradorhorarios.visual;

import java.awt.Image;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;

/**
 *
 * @author sigfrido
 */
public class HorariosNode extends AbstractNode {

    public HorariosNode(Children hijo, Lookup lookup) {
        super(hijo, lookup);
    }

    public HorariosNode(Children hijo) {
        super(hijo);
    }

    @Override
    public Image getIcon(int arg0) {
        return ImageUtilities.loadImage("mx/com/exploradorhorarios/visual/horario.png");
    }
}
