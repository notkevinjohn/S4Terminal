import java.awt.*;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.dial.*;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.ValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.StandardGradientPaintTransformer;

public class DialDemo2a extends JFrame
{
	static class DemoPanelB extends JPanel
		implements ChangeListener
	{

		DefaultValueDataset dataset1;
		DefaultValueDataset dataset2;
		JSlider slider1;

		public void stateChanged(ChangeEvent changeevent)
		{
			dataset1.setValue(new Integer(slider1.getValue()));
			dataset2.setValue(dataset1.getValue().doubleValue()/100);

			
		}

		public DemoPanelB()
		{
			super(new BorderLayout());
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
			JPanel jpanel = new JPanel(new GridLayout(1, 2));
			slider1 = new JSlider(0, 1000);
			slider1.setMajorTickSpacing(10);
			slider1.setPaintTicks(false);
			slider1.setPaintLabels(true);
			slider1.addChangeListener(this);
			jpanel.add(slider1);
			jpanel.add(slider1);
			add(chartpanel);
			add(jpanel, "South");
		}
	}


	public DialDemo2a(String s)
	{
		super(s);
		setDefaultCloseOperation(3);
		setContentPane(createDemoPanel());
	}

	public static JPanel createDemoPanel()
	{
		JPanel jpanel = new JPanel(new GridLayout(1, 2));
		jpanel.add(new DemoPanelB());
		return jpanel;
	}

	public static void main(String args[])
	{
		DialDemo2a dialdemo2a = new DialDemo2a("JFreeChart - Demo Dial 2a");
		dialdemo2a.pack();
		dialdemo2a.setVisible(true);
	}
}