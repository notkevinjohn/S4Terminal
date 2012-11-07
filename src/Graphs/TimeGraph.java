package Graphs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import Data.PayloadData;

import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

public class TimeGraph extends JFrame {

	private JPanel contentPane;
	public XYSeries series;
	public ChartPanel CP;
	public JFreeChart chart;
	public JFreeChart jFreeChart;
	public String deviceName;
	public int time;
	public double y;
	private JMenuBar menuBar;
	private JMenu mnData;
	private JMenu mnDatapoints;
	public DataTimeRadial time15;
	public DataTimeRadial time30;
	public DataTimeRadial time60;
	public DataTimeRadial time120;
	public DataTimeRadial time300;
	public DataTimeRadial time600;
	public DataTimeRadial timeAll;
	public int dataPointSet = 15;
	public int dataPointCount = 0;
	public int hasDeleted = 0;
	public ButtonGroup dataTime;
	
	

	/**
	 * Create the frame.
	 */
	public TimeGraph() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 644, 410);
		
		
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnData = new JMenu("Data");
		menuBar.add(mnData);
		
		mnDatapoints = new JMenu("DataPoints");
		mnData.add(mnDatapoints);

		
		
		dataTime = new ButtonGroup();
		time15 = new DataTimeRadial(this,"15   (15 seconds)");
		time15.setActionListener();
		time15.setSelected(true);
		dataTime.add(time15);
		mnDatapoints.add(time15);
		
		time30 = new DataTimeRadial(this,"30   (30 seconds)");
		time30.setActionListener();
		dataTime.add(time30);
		mnDatapoints.add(time30);
		
		time60 = new DataTimeRadial(this,"60   (1 minute)");
		time60.setActionListener();
		dataTime.add(time60);
		mnDatapoints.add(time60);
		
		time120 = new DataTimeRadial(this,"120 (2 minutes)");
		time120.setActionListener();
		dataTime.add(time120);
		mnDatapoints.add(time120);
		
		time300 = new DataTimeRadial(this,"300 (5 minutes)");
		time300.setActionListener();
		dataTime.add(time300);
		mnDatapoints.add(time300);
		
		time600 = new DataTimeRadial(this,"600 (10 minutes)");
		time600.setActionListener();
		dataTime.add(time600);
		mnDatapoints.add(time300);
		
		timeAll = new DataTimeRadial(this,"All");
		timeAll.setActionListener();
		dataTime.add(timeAll);
		mnDatapoints.add(timeAll);
		
		
	
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setVisible(true);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		
		series = new XYSeries("Data");
		

	
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
				
		ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());
		
		chart = ChartFactory.createXYLineChart(
				   "XY Chart", // Title
				   "x-axis", // x-axis Label
				   "y-axis", // y-axis Label
				   dataset, // Dataset
				   PlotOrientation.VERTICAL, // Plot Orientation
				   false, // Show Legend
				   false, // Use tooltips
				   false // Configure chart to generate URLs?
				);
		
		chart.setBackgroundPaint(Color.white);
		
//		JPanel panel = new JPanel();
//		contentPane.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
	
		CP = new ChartPanel(chart);
		CP.setBorder(null);
		CP.setZoomAroundAnchor(true);
		CP.setHorizontalAxisTrace(true);
		CP.setMouseZoomable(false);
		CP.setRefreshBuffer(true);
		CP.setVerticalAxisTrace(true);
		CP.setBounds(0, 0, 618, 362);
		
		panel.add(CP);
		panel.validate();
		
	}
	
	public void updatePayloadData(PayloadData payloadData)
	{
		
		time = time + 1;
		y = payloadData.Sen_1_Value;
		series.add(time, y);
		dataPointCount++;
		
		while(dataPointCount >= dataPointSet && dataPointSet >0)
		{	
			
			series.remove(0);
		}
		
			
		CP.validate();	
	}
	
	

}
