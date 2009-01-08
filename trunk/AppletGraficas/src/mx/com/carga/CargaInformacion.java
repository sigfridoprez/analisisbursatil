package mx.com.carga;

import java.awt.BorderLayout;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.Rectangle;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JLabel;
import javax.swing.JButton;

public class CargaInformacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JButton jButton = null;
	private JLabel jLabel1 = null;
	private JButton jButton1 = null;
	private JFileChooser jfile = null;
	private CargaArchivo carga;
	/**
	 * This is the default constructor
	 */
	public CargaInformacion() {
		super();
		carga = new CargaArchivo();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(839, 200);
		jfile = new JFileChooser();
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(7, 65, 820, 16));
			jLabel1.setText("");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(12, 11, 97, 25));
			jLabel.setText("Archivo");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getJButton1(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(267, 10, 128, 26));
			jButton.setEnabled(true);
			jButton.setText("Cargar");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					carga.cargaArchivo(jfile.getSelectedFile().getAbsolutePath());
					jLabel1.setText(jfile.getSelectedFile().getAbsolutePath());
					jButton.setEnabled(false);
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(131, 9, 127, 29));
			jButton1.setText("Selecciona");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					jfile.showOpenDialog(CargaInformacion.this);
					jButton.setEnabled(true);
				}
			});
		}
		return jButton1;
	}

	public static void main(String[] arg){
		CargaInformacion car=new CargaInformacion();
		car.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		car.setLocationRelativeTo(null);
		car.setVisible(true);
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
