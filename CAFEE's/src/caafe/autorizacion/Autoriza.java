/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * OtroFrame.java
 *
 * Created on 21-abr-2009, 22:36:42
 */
package caafe.autorizacion;

import autorizacion.servicio.ServicioAutorizacion;
import caafes.def.Autorizaciones;
import caafes.def.Facturas;
import factura.servicio.ServicioFactura;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import otro.MiFrame;
import otro.Pass;
import utils.vo.UtilVO;

/**
 *
 * @author Edgar
 */
public class Autoriza extends MiFrame {

    public boolean bEstado;
    private int iEstatus = 0;
    public final static int NUEVO = 100;
    public final static int CONSULTAR = 101;
    public final static int EDITAR = 102;
    public Facturas facturaVo;
    public Autorizaciones autorizaVO;
    private Pass frmPass;

    /** Creates new form OtroFrame */
    public Autoriza() {
        initComponents();

    }

    public void creaModelo() {
        List<UtilVO> listvVo;
        DefaultComboBoxModel cmbModel = new DefaultComboBoxModel();
        ServicioFactura srvFactura = new ServicioFactura();
        listvVo = srvFactura.obtieneListaFacturasSinAutorizacion();

        if (listvVo != null) {
            for (UtilVO utilVO : listvVo) {
                cmbModel.addElement(utilVO.getLngId());
            }
            jcbFactura.setModel(cmbModel);
        }
    }

    public void actualizaForm(int iEstatus, long idAutorizacion) {
        this.iEstatus = iEstatus;

        if (iEstatus == CONSULTAR) {

            jtxAutorizo.setEnabled(false);
            jcCaduca.setEnabled(false);
            jtxFolioFin.setEnabled(false);
            jtxFolioIncia.setEnabled(false);
            jtxNumAutoriza.setEnabled(false);
            jtxSolocitante.setEnabled(false);
            jcbFactura.setEnabled(false);


            ServicioAutorizacion srvAutorizacion = new ServicioAutorizacion();
            ServicioFactura srvFactura = new ServicioFactura();
            Facturas facturaVO = srvFactura.obtieneFacturaFolioAutorizacion(idAutorizacion);

            autorizaVO = srvAutorizacion.obtieneAutorizacion(idAutorizacion);

            jtxNumAutoriza.setText(String.valueOf(autorizaVO.getIdAutorizacion()));
            jtxFolioIncia.setText(String.valueOf(autorizaVO.getFolioInicio()));
            jtxFolioFin.setText(String.valueOf(autorizaVO.getFolioFinal()));
            jtxAutorizo.setText(autorizaVO.getAutorizo());
            jtxSolocitante.setText(autorizaVO.getSolicitante());
            jcCaduca.setDate(autorizaVO.getCaducidad());

            DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();

            defaultComboBoxModel.addElement(facturaVO.getId().getIdFolio());
            jcbFactura.setModel(defaultComboBoxModel);

            jbAceptar.setText("Modificar");

            iEstatus = CONSULTAR;
        }
        if (iEstatus == NUEVO) {
            jtxAutorizo.setEnabled(true);
            jcCaduca.setEnabled(true);
            jtxFolioFin.setEnabled(true);
            jtxFolioIncia.setEnabled(true);
            jtxNumAutoriza.setEnabled(true);
            jtxSolocitante.setEnabled(true);
            jcbFactura.setEnabled(true);

            limpiaForma();
            jbAceptar.setText("Agregar");
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jbAceptar = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();
        jtxNumAutoriza = new javax.swing.JTextField();
        jtxSolocitante = new javax.swing.JTextField();
        jtxAutorizo = new javax.swing.JTextField();
        jtxFolioIncia = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jtxFolioFin = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jcbFactura = new javax.swing.JComboBox();
        jcCaduca = new com.toedter.calendar.JDateChooser("dd/MM/yyyy", "##/##/####", '_');
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Autorización");
        setAlwaysOnTop(true);

        jLabel1.setText("Num Autorizacion");

        jLabel3.setText("Folios");

        jLabel4.setText("Solicitante");

        jLabel5.setText("Autorizo");

        jbAceptar.setText("Agregar");
        jbAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAceptarActionPerformed(evt);
            }
        });

        jbCancelar.setText("Salir");
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        jtxNumAutoriza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxNumAutorizaActionPerformed(evt);
            }
        });
        jtxNumAutoriza.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxNumAutorizaFocusLost(evt);
            }
        });

        jtxSolocitante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxSolocitanteActionPerformed(evt);
            }
        });

        jtxAutorizo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxAutorizoActionPerformed(evt);
            }
        });

        try {
            jtxFolioIncia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel8.setText(" a");

        try {
            jtxFolioFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel9.setText("Caduca dd/mm/AA");

        jLabel10.setText("Folio Factura");

        jcCaduca.setDateFormatString("dd/MM/yyyy");

        jMenu1.setText("Archivo");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Agregar");
        jMenu1.add(jMenuItem1);

        jMenuItem3.setText("Salir");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Acerca");

        jMenuItem2.setText("Ayuda");
        jMenu2.add(jMenuItem2);

        jMenuItem4.setText("Acerca...");
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(jbAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jbCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jcbFactura, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtxSolocitante, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                        .addComponent(jtxAutorizo, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jtxFolioIncia, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel8)
                            .addGap(18, 18, 18)
                            .addComponent(jtxFolioFin, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jtxNumAutoriza, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jcCaduca, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxNumAutoriza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxFolioIncia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jtxFolioFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxSolocitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxAutorizo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jcCaduca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jcbFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregaAutorizacion() {
        ServicioAutorizacion srvAutoriza = new ServicioAutorizacion();
        ServicioFactura srvFactura = new ServicioFactura();
        String strAutoriza;
        List<Autorizaciones> lista;

        if (bEstado == true) {
            strAutoriza = jtxNumAutoriza.getText();
            lista = srvAutoriza.obtieneListaAutorizacionFolio(Long.valueOf(strAutoriza));
            if (lista != null) {
                if (lista.isEmpty()) {
                    creaAutoriza();
                } else {
                    JOptionPane.showMessageDialog(this, "El Numero de Autorizacion ya exciste", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                creaAutoriza();
            }
        }
    }



    private void creaAutoriza() {
        ServicioAutorizacion srvAutoriza = new ServicioAutorizacion();
        ServicioFactura srvfFactura = new ServicioFactura();
        Autorizaciones autoriza = null;
        Facturas factura=null;
        String strFac;

        if (bEstado == true) {
            srvAutoriza = new ServicioAutorizacion();
            autoriza = new Autorizaciones();

            autoriza.setAutorizo(jtxAutorizo.getText());
            autoriza.setFolioInicio(jtxFolioIncia.getText());
            autoriza.setFolioFinal(jtxFolioFin.getText());
            autoriza.setCaducidad(jcCaduca.getDate());
            autoriza.setFechaCreacion(Calendar.getInstance().getTime());
            autoriza.setIdAutorizacion(Long.valueOf(jtxNumAutoriza.getText()));
            autoriza.setSolicitante(jtxSolocitante.getText());
            autoriza.setValido('S');
            autoriza.setExportado('N');
            strFac = jcbFactura.getSelectedItem().toString();

            srvAutoriza.insertaAutorizacion(autoriza);

            factura= srvfFactura.obtieneFactura(Long.valueOf(strFac));
            strFac = jtxNumAutoriza.getText();
            factura.setIdAutorizacion(Long.valueOf(strFac));
            srvfFactura.modificaFactura(factura);

            JOptionPane.showMessageDialog(this, "Datos agregados Correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
    }

     private void modificaAutoriza() {
        ServicioAutorizacion srvAutoriza = new ServicioAutorizacion();
        Autorizaciones autoriza = null;

            srvAutoriza = new ServicioAutorizacion();

            autoriza = srvAutoriza.obtieneAutorizacion(Long.valueOf(jtxNumAutoriza.getText()));

            autoriza.setAutorizo(jtxAutorizo.getText());
            autoriza.setFolioInicio(jtxFolioIncia.getText());
            autoriza.setFolioFinal(jtxFolioFin.getText());
            autoriza.setCaducidad(jcCaduca.getDate());
            autoriza.setFechaCreacion(Calendar.getInstance().getTime());
            autoriza.setIdAutorizacion(Long.valueOf(jtxNumAutoriza.getText()));
            autoriza.setSolicitante(jtxSolocitante.getText());
            autoriza.setValido('S');
            autoriza.setExportado('N');
            srvAutoriza.modificaAutorizacion(autoriza);
    }

    private void jbAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAceptarActionPerformed
        switch (iEstatus) {
            case NUEVO:
                desborde();
                estado();
                folios();
                if (bEstado != false) {
                    agregaAutorizacion();
                }
                break;

            case CONSULTAR:
                if (frmPass == null) {
                    frmPass = new Pass(this, 1);
                }
                frmPass.limpiaFrame();
                frmPass.setVisible(true);
                frmPass.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                frmPass.setAlwaysOnTop(true);
                frmPass.setLocationRelativeTo(this);
                break;
            case EDITAR:
                modificaAutoriza();
                JOptionPane.showMessageDialog(this, "Datos Editados con éxito", "", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                break;
        }


}//GEN-LAST:event_jbAceptarActionPerformed

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        this.dispose();

    // TODO add your handling code here:
}//GEN-LAST:event_jbCancelarActionPerformed

    private void jtxSolocitanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxSolocitanteActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jtxSolocitanteActionPerformed

    private void jtxAutorizoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxAutorizoActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jtxAutorizoActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        this.dispose();    // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jtxNumAutorizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxNumAutorizaActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jtxNumAutorizaActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jtxNumAutorizaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxNumAutorizaFocusLost
        BigDecimal bdNumeroAutorizacion;
        if (!jtxNumAutoriza.getText().equals("")) {
            try {
                bdNumeroAutorizacion = new BigDecimal(jtxNumAutoriza.getText());
            } catch (Exception exception) {
                jtxNumAutoriza.setText("");
                JOptionPane.showMessageDialog(this, "El Numero de Autorizaicon debe de ser numerico", "Error", JOptionPane.ERROR_MESSAGE);

            }
        }       // TODO add your handling code here:
    }//GEN-LAST:event_jtxNumAutorizaFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Autoriza().setVisible(true);
            }
        });
    }

    public void estado() {
        String sNumAutoriza;
        String sFolioInicia;
        String sFolioFin;
        String sSolicitante;
        String sAutorizo;
        Date clCaduca = jcCaduca.getDate();


        StringBuilder sbFaltantes = new StringBuilder("");
        boolean bFaltan = false;

        sNumAutoriza = jtxNumAutoriza.getText();
        sFolioInicia = jtxFolioIncia.getText();
        sFolioFin = jtxFolioFin.getText();
        sSolicitante = jtxSolocitante.getText();
        sAutorizo = jtxAutorizo.getText();

        if (sNumAutoriza.equals("")) {
            sbFaltantes.append("Numero de Autorizacion \n");
            bFaltan = true;
        }
        if (sFolioInicia.equals("")) {
            sbFaltantes.append("Inicio de Folios\n");
            bFaltan = true;
        }
        if (sFolioFin.equals("")) {
            sbFaltantes.append("Fin de Folios\n");
            bFaltan = true;
        }
        if (sSolicitante.equals("")) {
            sbFaltantes.append("Solicitante\n");
            bFaltan = true;
        }
        if (sAutorizo.equals("")) {
            sbFaltantes.append("Autorizo\n");
            bFaltan = true;
        }
        if (clCaduca == null) {
            sbFaltantes.append("Fecha Caducidad\n");
            bFaltan = true;
        }
        if (bFaltan == true) {
            JOptionPane.showMessageDialog(this, "Los campos:\n" + sbFaltantes + "Son requeridos", "Campos faltantes", JOptionPane.ERROR_MESSAGE);
            bEstado = false;
        } else {

            bEstado = true;
        }
        bFaltan = false;
    }

    public void desborde() {
        StringBuilder sbError = new StringBuilder("");
        boolean bEntra = false;



        if (jtxNumAutoriza.getText().length() > 20) {
            sbError.append("El campo Numero de Autorizacion solo acepta 20 caracteres\n");
            bEntra = true;
        }
        if (jtxSolocitante.getText().length() > 20) {
            sbError.append("El campo Solicitante solo acepta 20 caracteres\n");
            bEntra = true;

        }
        if (jtxAutorizo.getText().length() > 20) {
            sbError.append("El campo Autorizo solo acepta 20 caracteres \n");
            bEntra = true;
        }


        if (bEntra == true) {

            JOptionPane.showMessageDialog(this, "" + sbError, "Demasiados caracteres", JOptionPane.ERROR_MESSAGE);
            bEstado = false;
        } else {
            bEstado = true;
        }

    }

    private void folios() {
        Long folioInicia = Long.valueOf(jtxFolioIncia.getText());
        Long folioFin = Long.valueOf(jtxFolioFin.getText());

        if (folioInicia >= folioFin) {
            JOptionPane.showMessageDialog(this, "EL Folio Final debe ser mas Grande que el de Inicio", "Error", JOptionPane.ERROR_MESSAGE);
            bEstado = false;
        }
    }

    private void limpiaForma() {
        jtxAutorizo.setText("");
        jtxFolioFin.setText("");
        jtxFolioIncia.setText("");
        jtxNumAutoriza.setText("");
        jtxSolocitante.setText("");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JButton jbAceptar;
    private javax.swing.JButton jbCancelar;
    private com.toedter.calendar.JDateChooser jcCaduca;
    private javax.swing.JComboBox jcbFactura;
    private javax.swing.JTextField jtxAutorizo;
    private javax.swing.JFormattedTextField jtxFolioFin;
    private javax.swing.JFormattedTextField jtxFolioIncia;
    private javax.swing.JTextField jtxNumAutoriza;
    private javax.swing.JTextField jtxSolocitante;
    // End of variables declaration//GEN-END:variables

    @Override
    public void cbPasswprd() {
        jtxAutorizo.setEnabled(true);
        jtxFolioFin.setEnabled(true);
        jtxFolioIncia.setEnabled(true);
        jtxSolocitante.setEnabled(true);
        jcCaduca.setEnabled(true);
        iEstatus = EDITAR;
        jbAceptar.setText("Guardar");
    }
}
