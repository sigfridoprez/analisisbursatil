/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caafe.table.celleditor;

import caafe.autorizacion.Autoriza;
import caafe.factura.Factura;
import caafe.table.tablemodel.DetalleAutorizacion;
import caafe.table.tablemodel.DetalleFacturaVO;
import caafe.table.tablemodel.TableModelDetalle;
import java.awt.Component;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import otro.Constantes;

/**
 *
 * @author Edgar
 */
public class ButtonCellEditor extends AbstractCellEditor
        implements TableCellEditor, ActionListener {

    private JButton button;
    private Object value;
    private boolean isSelected = false;
    private int row = 0;
    private int column = 0;
    protected static final String EDIT = "EDIT";
    private JTable table;
    public final static int NUEVO = 100;
    private Autoriza jpAutoriza;

    public ButtonCellEditor() {
        button = new JButton();
        button.setActionCommand(EDIT);
        button.addActionListener(this);
    }

    public void colocaAutorizacioVO(DetalleAutorizacion autorizacionesVO) {
        TableModelDetalle dtm = (TableModelDetalle) table.getModel();
        dtm.actualizaAutorizacion(row, autorizacionesVO);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (EDIT.equals(e.getActionCommand())) {
            if (revisaRenglon()) {
                if (!table.getModel().getValueAt(row, 1).equals(Constantes.OTRO)) {
                    if (jpAutoriza == null) {
                        jpAutoriza = new Autoriza(this);
                    }
                    jpAutoriza.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    jpAutoriza.setAlwaysOnTop(true);
                    jpAutoriza.setLocationRelativeTo(null);
                    jpAutoriza.setModal(true);
                    jpAutoriza.actualizaForm(NUEVO, null);
                    jpAutoriza.setVisible(true);
                    fireEditingStopped();
                }
            }
        }
    }

    private boolean revisaRenglon() {
        boolean bReturn = true;
        StringBuilder sbMensaje = new StringBuilder("");

        TableModelDetalle modelo = (TableModelDetalle) table.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            DetalleFacturaVO object = modelo.getDetalleFacturaVO(i);

            if (object.getCostoTrabajo() != null && object.getCostoTrabajo().intValue()==0) {
                sbMensaje.append("Costo del Trabajo es Requerido.\n");
                bReturn = false;
            } else if (object.getCostoTrabajo() == null) {
                sbMensaje.append("Costo del Trabajo es Requerido.\n");
                bReturn = false;
            }
            if (object.getCantidad()==0) {
                sbMensaje.append("La Cantidad es Requerida.\n");
                bReturn = false;
            }
//             else if (object.getCantidad() == null) {
//                sbMensaje.append("La Cantidad es Requerida.\n");
//                bReturn = false;
//            }
        }

        if (!bReturn) {
            JOptionPane.showMessageDialog(null, sbMensaje.toString(), "Mensaje", JOptionPane.WARNING_MESSAGE);
        }

        return bReturn;
    }

    //Implement the one CellEditor method that AbstractCellEditor doesn't.
    @Override
    public Object getCellEditorValue() {
        return "";
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.table = table;
        this.value = value;
        this.isSelected = isSelected;
        this.row = row;
        this.column = column;
        return button;
    }

    public void setEnable(boolean bBolean) {
        this.button.setEnabled(bBolean);
    }
}
