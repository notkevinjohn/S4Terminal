package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Data.PayloadData;
import javax.swing.JTextField;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.DialShape;
import org.jfree.chart.plot.MeterInterval;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.dial.DialBackground;
import org.jfree.chart.plot.dial.DialCap;
import org.jfree.chart.plot.dial.DialFrame;
import org.jfree.chart.plot.dial.DialPlot;
import org.jfree.chart.plot.dial.DialPointer;
import org.jfree.chart.plot.dial.DialScale;
import org.jfree.chart.plot.dial.DialTextAnnotation;
import org.jfree.chart.plot.dial.DialValueIndicator;
import org.jfree.chart.plot.dial.StandardDialFrame;
import org.jfree.chart.plot.dial.StandardDialRange;
import org.jfree.chart.plot.dial.StandardDialScale;
import org.jfree.data.Range;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.ValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;

public class DataGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2551954609539999072L;
	private JPanel contentPane;
	private JTextField altitude;
	public double baseAlt = 0;
	public double baseLon;
	public double baseLat;
	public boolean baseSet;
	public PayloadData payloadData;
	public int arrowValue = 10;
	public int dialMinimum = 0;
	public int dialMaximum = 1000;
	public int chartWidth = 500;
	public int charthight = 500;
	private static DefaultValueDataset dataset;
	
	public DataGUI() 
	{
		JPanel panel = createPanel();
		panel.setPreferredSize(new Dimension(500, 350));
		setContentPane(panel);
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 518, 435);
		this.setVisible(true);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnData = new JMenu("Data");
		menuBar.add(mnData);
		
		JMenuItem mntmSetBase = new JMenuItem("Set Base");
		mnData.add(mntmSetBase);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
//		
//		altitude = new JTextField();
//		altitude.setBounds(10, 25, 86, 20);
//		contentPane.add(altitude);
//		altitude.setColumns(10);
//		
//		JLabel lblAltitude = DefaultComponentFactory.getInstance().createTitle("Altitude");
//		lblAltitude.setBounds(32, 11, 88, 14);
//		contentPane.add(lblAltitude);
//		
//		JLabel lblmAboveSea = DefaultComponentFactory.getInstance().createLabel("(m) Above Sea Level");
//		lblmAboveSea.setBounds(106, 28, 116, 14);
//		contentPane.add(lblmAboveSea);
		
		

		contentPane.add(createPanel());
	
		contentPane.validate();
         
		}

	public void updatePayloadData(PayloadData payloadData)
	{
		this.payloadData = payloadData;
		//altitude.setText(Double.toString(payloadData.alt));
		
		dataset.setValue(payloadData.alt);
	}
	
	
	private static JFreeChart createChart(ValueDataset valuedataset) {
		MeterPlot meterplot = new MeterPlot(valuedataset);
	  //set minimum and maximum value
		meterplot.setRange(new Range(0.0D, 10000D));
 
	  //assume  Loss range 0 - 6500
//		meterplot.addInterval(new MeterInterval("Loss", new Range(0.0D, 6500D), 
//				Color.red, new BasicStroke(2.0F), new Color(255, 0, 0, 128)));
// 
	  //assume break even range from 6500 to 7000
//		meterplot.addInterval(new MeterInterval("break even", new Range(6501D, 7000D), 
//				Color.yellow, new BasicStroke(2.0F), new Color(255, 255, 0, 64)));
// 
		 //assume Profit range from 7000 - 10000
		meterplot.addInterval(new MeterInterval("", new Range(0D, 10000D),
				Color.green, new BasicStroke(2.0F), new Color(0, 255, 0, 64)));
		meterplot.setNeedlePaint(Color.darkGray);
		meterplot.setDialBackgroundPaint(Color.white);
		meterplot.setDialOutlinePaint(Color.black);
		meterplot.setDialShape(DialShape.CHORD);
		meterplot.setMeterAngle(180);
		meterplot.setTickLabelsVisible(true);
		meterplot.setTickLabelFont(new Font("Arial", 1, 12));
		meterplot.setTickLabelPaint(Color.black);
		meterplot.setTickSize(5D);
		meterplot.setTickPaint(Color.gray);
		meterplot.setValuePaint(Color.black);
		meterplot.setValueFont(new Font("Arial", 1, 14));
		JFreeChart jfreechart = new JFreeChart("Altitude",
				JFreeChart.DEFAULT_TITLE_FONT, meterplot, true);
		return jfreechart;
	
	}
	public static JPanel createPanel() {
		 
		  //lets assume sales achievement is  7500 units
			dataset = new DefaultValueDataset(0);
			JFreeChart chart = createChart(dataset);
			ChartPanel chartpanel = new ChartPanel(chart);
			chartpanel.setBounds(0, 0,400, 400);
			return chartpanel;
		}
}
