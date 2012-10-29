package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import Data.PayloadData;

public class TimeGraph extends JFrame {

	private JPanel contentPane;
	public XYSeries series;
	public ChartPanel CP;
	public JFreeChart chart;
	public JFreeChart jFreeChart;
	public String deviceName;
	public int time;
	public double y;

	/**
	 * Create the frame.
	 */
	public TimeGraph() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 644, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setVisible(true);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		
		series = new XYSeries("Data");
		
		series.add(0.0,0.0);
		series.add(0.0,0.0);
		series.add(0.0,0.0);
		series.add(0.0,0.0);
		series.add(0.0,0.0);
		series.add(0.0,0.0);
		series.add(0.0,0.0);
		series.add(0.0,0.0);
		series.add(0.0,0.0);
		series.add(0.0,0.0);
		series.add(0.0,0.0);
		series.add(0.0,0.0);
		series.add(0.0,0.0);
		series.add(0.0,0.0);
		series.add(0.0,0.0);
		series.add(0.0,0.0);
		series.add(0.0,0.0);
		series.add(0.0,0.0);
		series.add(0.0,0.0);
		series.add(0.0,0.0);
		series.add(0.0,0.0);
		series.add(0.0,0.0);
		series.add(0.0,0.0);
		series.add(0.0,0.0);
		series.add(0.0,0.0);
		series.add(0.0,0.0);
		series.add(0.0,0.0);
		series.add(0.0,0.0);
		series.add(0.0,0.0);
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
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
		series.delete(0, 0);
		CP.validate();	
	}
	
	

}
