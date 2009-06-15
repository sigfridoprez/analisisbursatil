/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.controlesvisuales.vista;

import javax.swing.JButton;

/**
 *
 * @author sigfrido
 */
public class Ficha2 extends JButton {

    public Ficha2() {
        initComponents();
    }

    private void initComponents() {

        setToolTipText("Ejemplo");
        addMouseListener(new java.awt.event.MouseAdapter() {

            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {

            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }

            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        setLayout(null);
    }// </editor-fold>

    private void formMouseClicked(java.awt.event.MouseEvent evt) {
        System.out.println("formMouseClicked");
    }

    private void formMouseEntered(java.awt.event.MouseEvent evt) {
        System.out.println("formMouseEntered");
    }

    private void formMouseExited(java.awt.event.MouseEvent evt) {
        System.out.println("formMouseClicked");
    }

    private void formMousePressed(java.awt.event.MouseEvent evt) {
        System.out.println("formMousePressed");
        System.out.println("Repintamos el boton pero mas brillante");

//        bBrilloso = true;
        this.repaint();
    }

    private void formMouseReleased(java.awt.event.MouseEvent evt) {
        System.out.println("formMouseReleased");
        System.out.println("Repintamos el boton pero opaco");

//        bBrilloso = false;
        this.repaint();
    }

    private void formMouseDragged(java.awt.event.MouseEvent evt) {
        System.out.println("formMouseDragged::" + evt);
//        iPosX = evt.getX();
//        iPosY = evt.getY();
//        this.repaint();
    }

    private void formMouseMoved(java.awt.event.MouseEvent evt) {
        System.out.println("formMouseMoved");
    }
}
