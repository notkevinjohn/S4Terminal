// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 



import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.dial.*;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.ValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;

public class DialDemo1 extends JFrame
{
	static class DemoPanel extends JPanel
		implements ChangeListener
	{

		JSlider slider;
		DefaultValueDataset dataset;
		DefaultValueDataset dataset2;

		public static JFreeChart createStandardDialChart(String s, String s1, ValueDataset valuedataset, double d, double d1, double d2, int i)
		{
			DialPlot dialplot = new DialPlot();
			dialplot.setDataset(0,valuedataset);
			dialplot.setDataset(1,valuedataset);
			
			
			
			dialplot.setDialFrame(new StandardDialFrame());
			dialplot.setBackground(new DialBackground());
			DialTextAnnotation dialtextannotation = new DialTextAnnotation(s1);
			dialtextannotation.setFont(new Font("Dialog", 1, 14));
			dialtextannotation.setRadius(0.69999999999999996D);
			dialplot.addLayer(dialtextannotation);
			
			DialValueIndicator dialvalueindicator = new DialValueIndicator(0);
			dialplot.addLayer(dialvalueindicator);
			
			DialValueIndicator dialvalueindicator2 = new DialValueIndicator(0);
			dialplot.addLayer(dialvalueindicator2);
			
			StandardDialScale standarddialscale = new StandardDialScale(0, 99, 90, -360D, 10D, 4);
			standarddialscale.setMajorTickIncrement(d2);
			standarddialscale.setMinorTickCount(i);
			standarddialscale.setTickRadius(0.88D);
			standarddialscale.setTickLabelOffset(0.14999999999999999D);
			standarddialscale.setTickLabelFont(new Font("Dialog", 0, 14));
			dialplot.addScale(0, standarddialscale);
			dialplot.addPointer(new org.jfree.chart.plot.dial.DialPointer.Pin());
			dialplot.addPointer(new org.jfree.chart.plot.dial.DialPointer.Pointer());
			DialCap dialcap = new DialCap();
			dialplot.setCap(dialcap);
			return new JFreeChart(s, dialplot);
		}

		public void stateChanged(ChangeEvent changeevent)
		{
			dataset.setValue(new Integer(slider.getValue()));
		}

		public DemoPanel()
		{
			super(new BorderLayout());
			dataset = new DefaultValueDataset(10D);
			dataset2 = new DefaultValueDataset(2D);
			JFreeChart jfreechart = createStandardDialChart("Dial Demo 1", "", dataset, -0D, -9D, 10D, 2);
			DialPlot dialplot = (DialPlot)jfreechart.getPlot();
//			StandardDialRange standarddialrange = new StandardDialRange(40D, 60D, Color.red);
//			standarddialrange.setInnerRadius(0.52000000000000002D);
//			standarddialrange.setOuterRadius(0.55000000000000004D);
//			dialplot.addLayer(standarddialrange);
//			StandardDialRange standarddialrange1 = new StandardDialRange(10D, 40D, Color.orange);
//			standarddialrange1.setInnerRadius(0.52000000000000002D);
//			standarddialrange1.setOuterRadius(0.55000000000000004D);
//			dialplot.addLayer(standarddialrange1);
//			StandardDialRange standarddialrange2 = new StandardDialRange(-40D, 10D, Color.green);
//			standarddialrange2.setInnerRadius(0.52000000000000002D);
//			standarddialrange2.setOuterRadius(0.55000000000000004D);
//			dialplot.addLayer(standarddialrange2);
			GradientPaint gradientpaint = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(170, 170, 220));
			DialBackground dialbackground = new DialBackground(gradientpaint);
			dialbackground.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
			dialplot.setBackground(dialbackground);
			dialplot.removePointer(0);
			org.jfree.chart.plot.dial.DialPointer.Pointer pointer = new org.jfree.chart.plot.dial.DialPointer.Pointer();
			pointer.setWidthRadius(.01);
			pointer.setFillPaint(Color.black);
			dialplot.addPointer(pointer);
			
			org.jfree.chart.plot.dial.DialPointer.Pointer pointer2 = new org.jfree.chart.plot.dial.DialPointer.Pointer();
			pointer2.setWidthRadius(.05);
			pointer2.setFillPaint(Color.GRAY);
			dialplot.addPointer(pointer2);
			
			ChartPanel chartpanel = new ChartPanel(jfreechart);
			chartpanel.setPreferredSize(new Dimension(400, 400));
			
			slider = new JSlider(0, 100);
			slider.setMajorTickSpacing(10);
			slider.setPaintLabels(true);
			slider.addChangeListener(this);
			add(chartpanel);
			add(slider, "South");
		}
	}


	public DialDemo1(String s)
	{
		super(s);
		setDefaultCloseOperation(3);
		setContentPane(createDemoPanel());
	}

	public static JPanel createDemoPanel()
	{
		return new DemoPanel();
	}

	public static void main(String args[])
	{
		DialDemo1 dialdemo1 = new DialDemo1("JFreeChart - Demo Dial 1");
		dialdemo1.pack();
		dialdemo1.setVisible(true);
	}
}