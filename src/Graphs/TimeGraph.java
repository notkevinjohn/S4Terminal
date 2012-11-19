package Graphs;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JMenu;

public class TimeGraph extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3054937374800825011L;
	private JPanel contentPane;
	public XYSeries series;
	public ChartPanel CP;
	public JFreeChart chart;
	public JFreeChart jFreeChart;
	public String deviceName;
	
	public int time;
	public int seriesCount;
	public int seriesStart;
	
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
	public String title="";
	public String xAxisLable="";
	public String yAxisLable="";
	public int sen_Value = 0;

	/**
	 * Create the frame.
	 */
	public TimeGraph(String title, String xAxisLable, String yAxisLable, int sen_Value) 
	{
		this.title = title;
		this.xAxisLable = xAxisLable;
		this.yAxisLable = yAxisLable;
		this.sen_Value = sen_Value;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 420);
		this.setVisible(true);
		
		
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
		
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		series = new XYSeries("Data");

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		
	
		ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());
		
		chart = ChartFactory.createXYLineChart(
					title, // Title
					xAxisLable, // x-axis Label
					yAxisLable, // y-axis Label
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
		CP.setBounds(0, 0, 600, 350);
		
		panel.add(CP);
		panel.validate();
		
	}
	
	
	
	public void updatePayloadData(PayloadData payloadData)
	{
		
		seriesCount = seriesCount + 1;
		y = sensorValue(payloadData);
		series.add(seriesCount, y);
		System.out.println(sen_Value);
		
		if(seriesCount-seriesStart > dataPointSet && dataPointSet>0)
		{
			series.remove(0);
			seriesStart++;
		}
		CP.validate();	
	
	}
	public double sensorValue(PayloadData payloadData)
	{
		double data = 0;
		switch (sen_Value)
		{
		case 0: data = payloadData.Sen_1_Value;
				break;
		case 1: data =  payloadData.Sen_2_Value;
				break;
		case 2: data =  payloadData.Sen_3_Value;
				break;
		case 3: data =  payloadData.Sen_4_Value;
				break;
		case 4: data =  payloadData.Sen_5_Value;
				break;
		case 5: data =  payloadData.Sen_6_Value;
				break;
		case 6: data =  payloadData.Sen_7_Value;
				break;
		case 7: data =  payloadData.Sen_8_Value;
				break;
		case 8: data =  payloadData.Sen_9_Value;
				break;
		case 9: data =  payloadData.Sen_10_Value;
	    		break;
		}
		return data;
		
		
	}
	
	

}
