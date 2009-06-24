/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caafe.table.tablemodel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import otro.Constantes;

/**
 *
 * @author Edgar
 */
public class TableModelDetalle extends AbstractTableModel implements Serializable {

    private Vector<DetalleFacturaVO> lstModelo = new Vector<DetalleFacturaVO>();
    private List<String> identificadoresColumna = new ArrayList<String>();

  
    public TableModelDetalle() {
        identificadoresColumna.add("Cantidad");
        identificadoresColumna.add("Tipo de Trabajo");
        identificadoresColumna.add("Costo");
        identificadoresColumna.add("Anotaciones");
        identificadoresColumna.add("Autorizacion");
        identificadoresColumna.add("Folio Aurizacion");
    }

    public void addRow(int cantidad, String anotaciones, BigDecimal costoTrabajo, String tipoTrabajo) {
        addRow(new DetalleFacturaVO(cantidad, anotaciones, costoTrabajo, tipoTrabajo));
    }

    public void addRow(BigDecimal idCliente, BigDecimal idFolioFactura, BigDecimal idDetalleFactura, int cantidad, String anotaciones, String tipoTrabajo, BigDecimal costoTrabajo) {
        addRow(new DetalleFacturaVO(idCliente, idFolioFactura, idDetalleFactura, cantidad, anotaciones, tipoTrabajo, costoTrabajo));
    }

    public void addRow(DetalleFacturaVO detalle) {
        lstModelo.add(detalle);
        fireTableRowsInserted(getRowCount(), getRowCount());
    }

    public void clearListModel() {
        lstModelo.clear();
    }

    @Override
    public int getRowCount() {
        return lstModelo.size();
    }

    @Override
    public int getColumnCount() {
        return identificadoresColumna.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return lstModelo.get(rowIndex).getCantidad();
            case 1:
                return lstModelo.get(rowIndex).getTipoTrabajo();
            case 2:
                return lstModelo.get(rowIndex).getCostoTrabajo();
            case 3:

                return lstModelo.get(rowIndex).getAnotaciones();
            case 4:
                if (lstModelo.get(rowIndex).getAutorizacionVO() != null) {
                    return "Autorizacion numero";
                } else {
                    return "Sin Autorizacion";
                }
                
            case 5:
                if (lstModelo.get(rowIndex).getAutorizacionVO() != null) {
                    return lstModelo.get(rowIndex).getAutorizacionVO().getIdAutorizacion();
                } else {
                    return "";
                }
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        Object id = null;

        if (column < identificadoresColumna.size() && (column >= 0)) {
            id = identificadoresColumna.get(column);
        }
        return (id == null) ? super.getColumnName(column) : id.toString();
    }

    public void removeRow(int indexRow) {
        lstModelo.remove(indexRow);
        fireTableRowsDeleted(getRowCount(), getRowCount());
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return JComboBox.class;
            case 2:
                return BigDecimal.class;
            case 3:
                return String.class;
            case 4:
                return JButton.class;
        }
        return super.getColumnClass(columnIndex);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if (column == 5) {
            return false;
        }
        return true;
    }

    public DetalleFacturaVO getDetalleFacturaVO(int row) {
        return lstModelo.elementAt(row);
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        DetalleFacturaVO vo = lstModelo.get(row);
        System.out.println("aValue::"+aValue);
        
        switch (column) {
            case 0:
                vo.setCantidad(Integer.parseInt(aValue.toString()));
                break;
            case 1:
                vo.setTipoTrabajo(aValue.toString());
                break;
            case 2:
                vo.setCostoTrabajo(new BigDecimal(aValue.toString()));
                if(vo.getCostoTrabajo().compareTo(Constantes.BD_MAXIMO_VALOR)==1){
                    JOptionPane.showMessageDialog(null, "El costo es muy grande, favor de verificar.", "Error", JOptionPane.ERROR_MESSAGE);
                    vo.setCostoTrabajo(new BigDecimal("0"));
                }
                break;
            case 3:
                vo.setAnotaciones(aValue.toString());
                break;
        }
        lstModelo.setElementAt(vo, row);
        fireTableCellUpdated(row, column);
    }
    
    public void actualizaAutorizacion(int row,DetalleAutorizacion autorizacionVO){
        getDetalleFacturaVO(row).setAutorizacionVO(autorizacionVO);
        fireTableDataChanged();
    }
}
