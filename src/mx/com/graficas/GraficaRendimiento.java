package mx.com.graficas;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import com.toedter.calendar.JDateChooser;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.LegendItemEntity;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import mx.com.analisispreciosmercado.conf.SeriesOperadas;
import mx.com.business.service.seriesoperadas.SeriesOperadasSrv;
import mx.com.infraestructura.exceptions.BusinessException;

import java.awt.BasicStroke;

public class GraficaRendimiento extends InfraApplet implements ChartMouseListener {
	private static final long serialVersionUID = 1L;
	private SeriesOperadasSrv seriesOperadasSrv;
	private JPanel jpnlFiltros = null;
	private JLabel jLabel = null;
	private JLabel jlblSerie = null;
	private JLabel jlblDesde = null;
	private JDateChooser jcalDesde = null;
	private JLabel jlblHasta = null;
	private JDateChooser jcalHasta = null;
	private JButton jButton = null;
	private JList jListEmisoraSerie = null;
	private JList jListGraficar = null;
	private JButton jButton1 = null;
	private JButton jButton11 = null;
	private JLabel jLabel1 = null;
	private JCheckBox jCheckBox = null;
	private JScrollPane jScrollPane = null;
	private JScrollPane jScrollPane1 = null;
	private JFreeChart chart;
	private ChartPanel panel;
	private JPanel jPanelContenido = null;

	/**
	 * This is the default constructor
	 */
	public GraficaRendimiento() {
		super();
		seriesOperadasSrv = (SeriesOperadasSrv) this.factory
				.getBean("seriesOperadasSrv");
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	public void init() {
		this.setSize(900, 500);
		this.setLayout(null);
		this.setContentPane(getJPanelContenido());
	}

	private static JFreeChart createChart() {
		NumberFormat format = DecimalFormat.getInstance(new Locale("es","MX"));
		JFreeChart chart = ChartFactory.createTimeSeriesChart(
				"GRAFICAS DE RENDIMIENTOS", "FECHA", "RENDIMIENTO", null, true,
				true, false);

		XYPlot plot = (XYPlot) chart.getPlot();
		/*NumberAxis rangeAxis1 = (NumberAxis) plot.getRangeAxis();
		rangeAxis1.setLowerMargin(0.50); // to leave room for volume bars
		rangeAxis1.setNumberFormatOverride(format);
*/
		
		XYItemRenderer renderer1 = plot.getRenderer();
		renderer1.setBaseToolTipGenerator(new StandardXYToolTipGenerator(
				StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
				new SimpleDateFormat("dd/MM/yyyy"), format));

		return chart;
	}

	/**
	 * This method initializes jpnlFiltros
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJpnlFiltros() {
		if (jpnlFiltros == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(583, 93, 62, 20));
			jLabel1.setText("IPC");
			jlblHasta = new JLabel();
			jlblHasta.setBounds(new Rectangle(580, 42, 67, 25));
			jlblHasta.setText("Hasta");
			jlblDesde = new JLabel();
			jlblDesde.setBounds(new Rectangle(578, 14, 67, 23));
			jlblDesde.setText("Desde");
			jcalDesde = new JDateChooser();
			jcalDesde.setDateFormatString("dd/MM/yyyy");
			jcalDesde.setLocale(new Locale("es", "MX"));
			jcalDesde.setBounds(new Rectangle(650, 17, 178, 23));

			jlblSerie = new JLabel();
			jlblSerie.setBounds(new Rectangle(414, 16, 67, 23));
			jlblSerie.setText("Graficar");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(89, 15, 67, 23));
			jLabel.setText("Emisoras");
			jpnlFiltros = new JPanel();
			jpnlFiltros.setLayout(null);
			jpnlFiltros.setBorder(BorderFactory.createTitledBorder(null,
					"Filtros", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, null, null));
			jpnlFiltros.setBounds(new Rectangle(13, 8, 877, 151));
			jpnlFiltros.add(jLabel, null);
			jpnlFiltros.add(jlblSerie, null);
			jpnlFiltros.add(jlblDesde, null);
			jpnlFiltros.add(jcalDesde, null);
			jpnlFiltros.add(jlblHasta, null);
			jpnlFiltros.add(getJcalHasta(), null);
			jpnlFiltros.add(getJButton(), null);
			jpnlFiltros.add(getJScrollPane(), null);
			jpnlFiltros.add(getJScrollPane1(), null);
			jpnlFiltros.add(getJButton1(), null);
			jpnlFiltros.add(getJButton11(), null);
			jpnlFiltros.add(jLabel1, null);
			jpnlFiltros.add(getJCheckBox(), null);
		}
		return jpnlFiltros;
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
			jcalHasta.setBounds(new Rectangle(652, 48, 173, 25));
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
			jButton.setBounds(new Rectangle(742, 101, 98, 29));
			jButton.setText("Graficar");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("graficamos");
					List<List<SeriesOperadas>> lstGraficar;
					List<String> lstLabel;
					List<SeriesOperadas> lstSeries;
					StringTokenizer strtksTokens;

					// Obtenemos la lista que se va a graficar
					DefaultListModel model = (DefaultListModel) jListGraficar
							.getModel();
					Object[] arrayOb = new Object[model.size()];
					for (int j = 0; j < model.size(); j++) {
						arrayOb[j] = model.elementAt(j);
					}
					if (arrayOb != null) {
						lstGraficar = new ArrayList<List<SeriesOperadas>>();
						lstLabel = new ArrayList<String>();
						for (Object ob : arrayOb) {
							lstLabel.add((String) ob);
							strtksTokens = new StringTokenizer((String) ob, "-");
							try {
								if(strtksTokens.countTokens()==2){
									lstSeries = seriesOperadasSrv
									.getListaSeriesOperadas(strtksTokens
											.nextToken(), strtksTokens
											.nextToken(), jcalDesde.getDate(), jcalHasta.getDate(),'S',true);	
								}else{
									lstSeries = seriesOperadasSrv
									.getListaSeriesOperadas(strtksTokens
											.nextToken(), "", jcalDesde.getDate(), jcalHasta.getDate(),'S',true);
								}
								
								lstGraficar.add(lstSeries);
							} catch (BusinessException e1) {
								e1.printStackTrace();
							}
						}
						// ya tenemos lo que se va a graficar
						XYPlot plot = (XYPlot) chart.getPlot();
						XYDataset dataSet = createRendimientoDataset(
								lstGraficar, lstLabel);
						System.out.println("Graficando::" + lstGraficar.size());

						if (dataSet != null) {
							plot.setDataset(dataSet);
						}
					}
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jListEmisoraSerie
	 * 
	 * @return javax.swing.JList
	 */
	private JList getJListEmisoraSerie() {
		List<String> lstEmisoras;
		DefaultListModel model;
		int intIndex = 0;
		if (jListEmisoraSerie == null) {
			jListEmisoraSerie = new JList();
			jListEmisoraSerie
					.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			jListEmisoraSerie.setAutoscrolls(true);

			try {
				lstEmisoras = seriesOperadasSrv.getListaJList();
				model = new DefaultListModel();
				for (String val : lstEmisoras) {
					model.add(intIndex, val);
					intIndex++;
				}
				jListEmisoraSerie.setModel(model);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
		return jListEmisoraSerie;
	}

	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(9, 42, 249, 70));
			jScrollPane.setViewportView(getJListEmisoraSerie());
		}
		return jScrollPane;
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(360, 42, 205, 77));
			jScrollPane1.setViewportView(getJListGraficar());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jListGraficar
	 * 
	 * @return javax.swing.JList
	 */
	private JList getJListGraficar() {
		if (jListGraficar == null) {
			jListGraficar = new JList(new DefaultListModel());
		}
		return jListGraficar;
	}

	/**
	 * This method initializes jButton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(271, 42, 75, 29));
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()");
					int[] indices = jListEmisoraSerie.getSelectedIndices();
					Object[] arraybject = jListEmisoraSerie.getSelectedValues();

					for (int i : indices) {
						((DefaultListModel) jListEmisoraSerie.getModel())
								.remove(i);
					}

					DefaultListModel model = (DefaultListModel) jListGraficar
							.getModel();
					int i = 0;
					for (Object ob : arraybject) {
						model.addElement(ob);
						i++;
					}
					jListGraficar.setModel(model);
					jListEmisoraSerie.requestFocus();
					jListEmisoraSerie.setSelectedIndex(0);
				}
			});
			jButton1.setText(">>");
		}
		return jButton1;
	}

	/**
	 * This method initializes jButton11
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton11() {
		if (jButton11 == null) {
			jButton11 = new JButton();
			jButton11.setBounds(new Rectangle(269, 76, 69, 29));
			jButton11.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()");
					int[] indices = jListGraficar.getSelectedIndices();
					Object[] arraybject = jListGraficar.getSelectedValues();

					for (int i : indices) {
						((DefaultListModel) jListGraficar.getModel()).remove(i);
					}

					DefaultListModel model = (DefaultListModel) jListEmisoraSerie
							.getModel();
					int i = 0;
					for (Object ob : arraybject) {
						model.addElement(ob);
						i++;
					}
					jListEmisoraSerie.setModel(model);
					jListGraficar.requestFocus();
					jListGraficar.setSelectedIndex(0);
				}
			});
			jButton11.setText("<<");
		}
		return jButton11;
	}

	/**
	 * This method initializes jCheckBox
	 * 
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBox() {
		if (jCheckBox == null) {
			jCheckBox = new JCheckBox();
			jCheckBox.setBounds(new Rectangle(660, 89, 28, 23));
		}
		return jCheckBox;
	}

	/**
	 * This method initializes jPanelContenido
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelContenido() {
		if (jPanelContenido == null) {
			jPanelContenido = new JPanel();
			jPanelContenido.setLayout(null);
			jPanelContenido.add(getJpnlFiltros(), null);
			jPanelContenido.add(getPanelChart(), null);
		}
		return jPanelContenido;
	}

	private ChartPanel getPanelChart() {
		chart = createChart();
		panel = new ChartPanel(chart, true, true, true, false, true);
		panel.setPreferredSize(new java.awt.Dimension(500, 270));
		panel.setBounds(new Rectangle(4, 163, 892, 329));
		panel.addChartMouseListener(this);
		return panel;
	}

	@SuppressWarnings("unused")
	private static XYDataset createRendimientoDataset(
			List<List<SeriesOperadas>> lstGraficar, List<String> lstlabel) {
		TimeSeries[] arraySeries = null;
		Day day = null;
		TimeSeriesCollection tscReturn = null;

		int i = 0;
		if (lstGraficar != null) {
			arraySeries = new TimeSeries[lstGraficar.size()];

			for (List<SeriesOperadas> lstDat : lstGraficar) {
				arraySeries[i] = new TimeSeries(lstlabel.get(i), Day.class);
				for (SeriesOperadas ob : lstDat) {
					day = new Day(ob.getId().getFecha());
					arraySeries[i].add(day, ob.getRendimiento() == null ? 0
							: ob.getRendimiento());
					arraySeries[i].setRangeDescription(ob.getId().getEmisora()
							+ " " + ob.getId().getSerie());
				}
				i++;
			}
		}
		if (arraySeries != null) {
			tscReturn = new TimeSeriesCollection();
			for (TimeSeries serie : arraySeries) {
				tscReturn.addSeries(serie);
			}
		}

		return tscReturn;
	}

	@SuppressWarnings("unchecked")
	public void chartMouseClicked(ChartMouseEvent event) {
		ChartEntity e = event.getEntity();

		if (e != null) {
			if (e instanceof LegendItemEntity) {
				LegendItemEntity entity = (LegendItemEntity) e;
				Comparable seriesKey = entity.getSeriesKey();
				XYPlot plot = (XYPlot) this.chart.getPlot();
				XYDataset dataset = plot.getDataset();
				XYItemRenderer renderer = plot.getRenderer();
				for (int i = 0; i < dataset.getSeriesCount(); i++) {
					renderer.setSeriesStroke(i, new BasicStroke(1.0f));
					if (dataset.getSeriesKey(i).equals(seriesKey)) {
						renderer.setSeriesStroke(i, new BasicStroke(2.0f));
					}
				}
			}
		}
	}

	public void chartMouseMoved(ChartMouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
