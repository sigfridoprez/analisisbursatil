package mx.com.graficas;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;

import mx.com.business.service.seriesoperadas.SeriesOperadasSrv;
import mx.com.infraestructura.exceptions.BusinessException;

public class SeriesGraficas extends InfraApplet {
	private SeriesOperadasSrv seriesOperadasSrv;
	private static final long serialVersionUID = 1L;
	private JPanel jpnlFiltros = null;
	private JLabel jLabel = null;
	private JComboBox jcbEmisora = null;
	private JLabel jlblSerie = null;
	private JComboBox jcbSerie = null;
	private JLabel jlblDesde = null;
	private JDateChooser jcalDesde = null;
	private JLabel jlblHasta = null;
	private JDateChooser jcalHasta = null;
	private JButton jButton = null;

	/**
	 * This is the default constructor
	 */
	public SeriesGraficas() {
		super();
		seriesOperadasSrv = (SeriesOperadasSrv) this.factory.getBean("seriesOperadasSrv");
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	public void init() {
		this.setSize(900, 500);
		this.setLayout(null);
		this.add(getJpnlFiltros());
	}

	/**
	 * This method initializes jpnlFiltros	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJpnlFiltros() {
		if (jpnlFiltros == null) {
			jlblHasta = new JLabel();
			jlblHasta.setBounds(new Rectangle(360, 57, 67, 25));
			jlblHasta.setText("Hasta");
			jlblDesde = new JLabel();
			jlblDesde.setBounds(new Rectangle(17, 55, 67, 23));
			jlblDesde.setText("Desde");
			jcalDesde = new JDateChooser(new Date(),"dd/MM/yyyy");
			jcalDesde.setLocale(new Locale("es","MX"));
			jcalDesde.setBounds(new Rectangle(98, 56, 170, 23));
			
			
			jlblSerie = new JLabel();
			jlblSerie.setBounds(new Rectangle(359, 23, 67, 23));
			jlblSerie.setText("Serie");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(16, 23, 67, 23));
			jLabel.setText("Emisora");
			jpnlFiltros = new JPanel();
			jpnlFiltros.setSize(709, 113);
			jpnlFiltros.setLayout(null);
			jpnlFiltros.setLocation(new Point(79, 0));
			jpnlFiltros.setBorder(BorderFactory.createTitledBorder(null, "Filtros", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
			jpnlFiltros.add(jLabel, null);
			jpnlFiltros.add(getJcbEmisora(), null);
			jpnlFiltros.add(jlblSerie, null);
			jpnlFiltros.add(getJcbSerie(), null);
			jpnlFiltros.add(jlblDesde, null);
			jpnlFiltros.add(jcalDesde, null);
			jpnlFiltros.add(jlblHasta, null);
			jpnlFiltros.add(getJcalHasta(), null);
			jpnlFiltros.add(getJButton(), null);
		}
		return jpnlFiltros;
	}

	/**
	 * This method initializes jcbEmisora	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJcbEmisora() {
		List<String> lstEmisora;
		DefaultComboBoxModel model;
		if (jcbEmisora == null) {
			jcbEmisora = new JComboBox();
			jcbEmisora.setBounds(new Rectangle(92, 23, 253, 27));
			jcbEmisora.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JComboBox cb;
					DefaultComboBoxModel model;
					System.out.println("actionPerformed()  " + e.getSource());
					List<String> lstSerie;
					if(e.getSource() instanceof JComboBox){
						cb = (JComboBox)e.getSource();
						System.out.println(cb.getModel().getSelectedItem());
						try {
							lstSerie = seriesOperadasSrv.getListSerie((String)cb.getModel().getSelectedItem());
							model = new DefaultComboBoxModel();
							model.addElement("Seleccione Serie");
							for(String val:lstSerie){
								model.addElement(val);
							}
							jcbSerie.setModel(model);
						} catch (BusinessException e1) {
							e1.printStackTrace();
						}
					}
				}
			});
			if(seriesOperadasSrv!=null){
				try {
					lstEmisora = seriesOperadasSrv.getListEmisora();
					model = new DefaultComboBoxModel();
					model.addElement("Seleccione una Emisora");
					for(String val:lstEmisora){
						model.addElement(val);
					}
					jcbEmisora.setModel(model);
				} catch (BusinessException e) {
					e.printStackTrace();
				}
			}
		}
		return jcbEmisora;
	}

	/**
	 * This method initializes jcbSerie	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJcbSerie() {
		if (jcbSerie == null) {
			jcbSerie = new JComboBox();
			jcbSerie.setBounds(new Rectangle(435, 23, 251, 27));
		}
		return jcbSerie;
	}

	/**
	 * This method initializes jcalHasta1	
	 * 	
	 * @return de.wannawork.jcalendar.JCalendarComboBox	
	 */
	private JDateChooser getJcalHasta() {
		if (jcalHasta == null) {
			jcalHasta = new JDateChooser();
			jcalHasta.setDateFormatString("dd/MM/yyyy");
			jcalHasta.setLocale(new Locale("es", "MX"));
			jcalHasta.setBounds(new Rectangle(444, 58, 197, 25));
		}
		return jcalHasta;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(13, 84, 98, 29));
			jButton.setText("Graficar");
		}
		return jButton;
	}

}
