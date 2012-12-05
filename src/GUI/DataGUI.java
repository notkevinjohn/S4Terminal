package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Data.PayloadData;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.dial.DialBackground;
import org.jfree.chart.plot.dial.DialCap;
import org.jfree.chart.plot.dial.DialPlot;
import org.jfree.chart.plot.dial.DialTextAnnotation;
import org.jfree.chart.plot.dial.DialValueIndicator;
import org.jfree.chart.plot.dial.StandardDialFrame;
import org.jfree.chart.plot.dial.StandardDialScale;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.StandardGradientPaintTransformer;
import java.text.NumberFormat;
import java.util.TimerTask;

import java.util.Timer;

public class DataGUI extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2551954609539999072L;
	private JPanel contentPane;
	public double baseAlt = 0;
	public double baseLon;
	public double baseLat;
	public boolean baseSet;
	public PayloadData payloadData;
	public int arrowValue = 10;
	public int dialMinimum = 0;
	public static int dialMaximum = 5000;
	public int chartWidth = 500;
	public int charthight = 500;
	public static DialPlot plot;
	public static DefaultValueDataset dataset1;
	public static DefaultValueDataset dataset2;
	public double changeInAlt;
	public double totalChangeInAlt;
	public int i=0;
	public double altitide;
	public Timer timer;
	public double priorAlt = 0;
	public double alt;
	
	public DataGUI() 
	{
		JPanel panel = createPanel();
		panel.setPreferredSize(new Dimension(500, 350));
		setContentPane(panel);
		panel.setBackground(Color.WHITE);
		panel.setForeground(Color.WHITE);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 500, 500);
		this.setVisible(true);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnData = new JMenu("Data");
		menuBar.add(mnData);
		
		JMenuItem mntmSetBase = new JMenuItem("Set Base");
		mnData.add(mntmSetBase);
		
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
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
		contentPane.setLayout(null);
		
	
		contentPane.validate();
         
		}

	public void updatePayloadData(PayloadData payloadData)
	{
		
		this.payloadData = payloadData;
		alt = payloadData.alt;
		totalChangeInAlt = alt - priorAlt ;
		final int delay = 50;
		
		changeInAlt = totalChangeInAlt/31;
		i = 0;
		Reminder(delay);
	    priorAlt = alt;
	}
	 public void Reminder(int miliseconds) {
	        timer = new Timer();
	        timer.schedule(new RemindTask(), 0, miliseconds);
		}

	    class RemindTask extends TimerTask 
	    {
	        public void run() {
	        	if(i <= 31)
	        	{
	            
	        		dataset1.setValue(priorAlt + changeInAlt*i);
	        		dataset2.setValue(dataset1.getValue().doubleValue()/100);
	        		System.out.println(i);
	        		System.out.println(dataset1.getValue().doubleValue());
	        		i++;
	        	}
	        	else
	        	{
	            timer.cancel(); //Terminate the timer thread
	        	}
	        }
	    }
	     
	
	public void updatePayloadData2(PayloadData payloadData)
	{
		this.payloadData = payloadData;
		
		
	}

	public static JPanel createPanel() 
	{


			JFreeChart chart = DemoPanelB();
			ChartPanel chartpanel = new ChartPanel(chart);
			chartpanel.setLayout(null);
			chartpanel.setBounds(0, 0,480, 480);
			chart.setBackgroundPaint(Color.WHITE);
			
			
			chartpanel.repaint();
			return chartpanel;
		}
	public static JFreeChart DemoPanelB()
	{

		dataset1 = new DefaultValueDataset(10);
		dataset2 = new DefaultValueDataset(50);
		DialPlot dialplot = new DialPlot();
		dialplot.setView(0.0D, 0.0D, 1.0D, 1.0D);
		dialplot.setDataset(0, dataset1);
		dialplot.setDataset(1, dataset2);
		StandardDialFrame standarddialframe = new StandardDialFrame();
		standarddialframe.setBackgroundPaint(Color.lightGray);
		standarddialframe.setForegroundPaint(Color.darkGray);
		dialplot.setDialFrame(standarddialframe);
		GradientPaint gradientpaint = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(170, 170, 220));
		DialBackground dialbackground = new DialBackground(gradientpaint);
		dialbackground.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
		dialplot.setBackground(dialbackground);
		DialTextAnnotation dialtextannotation = new DialTextAnnotation("meters");
		dialtextannotation.setFont(new Font("Dialog", 1, 12));
		dialtextannotation.setRadius(0.575);
		dialtextannotation.setAngle(-78D);
		dialplot.addLayer(dialtextannotation);
		
		DialValueIndicator dialvalueindicator = new DialValueIndicator(0);
		dialvalueindicator.setFont(new Font("Dialog", 0, 12));
		dialvalueindicator.setOutlinePaint(Color.darkGray);
		dialvalueindicator.setRadius(0.59999999999999998D);
		dialvalueindicator.setAngle(-92D);
		dialvalueindicator.setNumberFormat(NumberFormat.getIntegerInstance());
		dialvalueindicator.setFrameAnchor(RectangleAnchor.RIGHT);
		System.out.println(dialvalueindicator.getNumberFormat());
		dialplot.addLayer(dialvalueindicator);

		StandardDialScale standarddialscale = new StandardDialScale(0, 99.999, 90, -360D, 10D, 4);
		standarddialscale.setTickRadius(0.88D);
		standarddialscale.setTickLabelOffset(0.14999999999999999D);
		standarddialscale.setTickLabelFont(new Font("Dialog", 0, 14));
		dialplot.addScale(0, standarddialscale);
		StandardDialScale standarddialscale1 = new StandardDialScale(0, 9.9999, 90, -360D, 1D, 4);
		standarddialscale1.setTickRadius(0.5D);
		standarddialscale1.setTickLabelOffset(0.14999999999999999D);
		standarddialscale1.setTickLabelFont(new Font("Dialog", 0, 10));
		standarddialscale1.setMajorTickPaint(Color.BLACK);
		standarddialscale1.setMinorTickPaint(Color.BLACK);
		dialplot.addScale(1, standarddialscale1);
		dialplot.mapDatasetToScale(1, 1);
		org.jfree.chart.plot.dial.DialPointer.Pin pin = new org.jfree.chart.plot.dial.DialPointer.Pin(1);
		pin.setPaint(Color.black);
		pin.setRadius(0.55000000000000004D);
		dialplot.addPointer(pin);
		org.jfree.chart.plot.dial.DialPointer.Pointer pointer = new org.jfree.chart.plot.dial.DialPointer.Pointer(0);
		dialplot.addPointer(pointer);
		DialCap dialcap = new DialCap();
		dialcap.setRadius(0.10000000000000001D);
		dialplot.setCap(dialcap);
		JFreeChart jfreechart = new JFreeChart(dialplot);
		jfreechart.setTitle("Altitude");
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setPreferredSize(new Dimension(400, 400));
		
		return jfreechart;
	}
}

