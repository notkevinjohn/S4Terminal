package GUI;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import javax.swing.JScrollPane;
import Componets.AutoscrollCheckBox;
import Componets.DataGUIMenuItem;
import Componets.GraphButtonMenuItem;
import Componets.SendButton;
import Componets.SendLineTextField;
import Componets.SetDataMenuItem;
import Componets.StartStopButton;
import Componets.TerminalText;
import Data.PayloadData;
import Events.IPayloadUpdateDataEventListener;
import Events.IPayloadUpdateUpdateGraphEventListener;
import Events.PayloadUpdataDataEvent;
import Events.PayloadUpdateGraphEvent;
import Graphs.TimeGraph;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;

import javax.swing.BorderFactory;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;


public class Terminal 
{
	public static javax.swing.event.EventListenerList listenerList = new javax.swing.event.EventListenerList();
	private JScrollPane scrollPane;
	private JPanel panel;
	private JPanel contentPane;
	private StartStopButton btnStartStop;
	private AutoscrollCheckBox chckbxAutoscroll;
	private JFrame frame;
	public TerminalText terminalText;
	public SendButton btnSend;
	public String textSend;
	public StyledDocument doc;
	public SendLineTextField sendLine;
	public String deviceName;
	public JFreeChart jFreeChart;
	public double x=0.0;
	public double y=0.0;
	public XYSeries series;
	public ChartPanel CP;
	public JFreeChart chart;
	private SimpleAttributeSet green = new SimpleAttributeSet();
	private SimpleAttributeSet blue = new SimpleAttributeSet();
	public boolean enableGraph = true;
	public TimeGraph timeGraph;
	private JMenuBar menuBar;
	private JMenu mnGraph;
	private boolean graphSet = false;
	private ButtonGroup graph;
	public boolean dataSet = false;
	public JMenuItem mntmOpenGui;
	public SetDataMenuItem setDataMenuItem;
	public DataGUIMenuItem dataGUIMenuItem;
    public long lastRecivedTime = 0;
    public boolean goodData = true;    
	public JEditorPane goodDataButton;
	public JProgressBar signalStrength;
	private JProgressBar batteryStatus;
	private JLabel lblSignal;
	private JLabel lblBattery;
	
	public Terminal(String deviceName)
	{
		this.deviceName = deviceName;
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					try 
					{
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				    } 
				    catch (UnsupportedLookAndFeelException e) { }
				    catch (ClassNotFoundException e) {}
				    catch (InstantiationException e) {}
				    catch (IllegalAccessException e) {}
					
					terminal();
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});	
	}

	public void terminal() 
	{
		String terminalName = "Terminal Payload -- " + deviceName;
		frame = new JFrame(terminalName);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 640, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		frame.setMinimumSize(new Dimension(400,300));
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setSize(632,603);
		panel.setLocation(0,0);
		contentPane.add(panel);
		panel.setLayout(null);
		
			
		scrollPane = new JScrollPane();
		scrollPane.setSize(494,483);
		scrollPane.setLocation(0,21);
		panel.add(scrollPane);
		
		final int scrollPaneX = panel.getWidth()-scrollPane.getWidth();
		final int scrollPaneY = panel.getHeight()-scrollPane.getHeight();
		
		terminalText = new TerminalText(terminalText);
		terminalText.setEditable(false);
		terminalText.setBackground(Color.WHITE);
		scrollPane.setViewportView(terminalText);
		doc = terminalText.getStyledDocument();
		

		chckbxAutoscroll = new AutoscrollCheckBox(terminalText);
		chckbxAutoscroll.setBackground(Color.WHITE);
		chckbxAutoscroll.setSelected(true);
		chckbxAutoscroll.setSize(92,23);
		chckbxAutoscroll.setLocation(434,556);
		panel.add(chckbxAutoscroll);
		chckbxAutoscroll.setActionListener();
		terminalText.setActionListener(terminalText,chckbxAutoscroll);
		

		
		

		sendLine = new SendLineTextField();
		sendLine.setSize(248,23);
		sendLine.setLocation(10,556);
		panel.add(sendLine);
		sendLine.setActionListener();
		
		final int sendLineX = sendLine.getX();
		final int sendLineXresize = panel.getWidth()-sendLine.getWidth();
		final int sendLineY =  panel.getHeight()-sendLine.getY();

		
		btnSend = new SendButton(sendLine);
		btnSend.setSize(75,23);
		btnSend.setLocation(268,556);
		panel.add(btnSend);
		btnSend.setActionListener();
		
		final int btnSendX = panel.getWidth()-btnSend.getX();
		final int btnSendY = panel.getHeight()-btnSend.getY();
		
		btnStartStop = new StartStopButton();
		btnStartStop.setSize(75,23);
		btnStartStop.setLocation(353,556);
		panel.add(btnStartStop);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 622, 21);
		panel.add(menuBar);
		
		mnGraph = new JMenu("Graph");
		menuBar.add(mnGraph);
		
		JMenu mnData = new JMenu("Data");
		menuBar.add(mnData);
		
		
		dataGUIMenuItem = new DataGUIMenuItem(this,"Open GUI");
		mnData.add(dataGUIMenuItem);
		dataGUIMenuItem.setActionListener();
		
		
		Border raisedetched;
		raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED,Color.DARK_GRAY, Color.GRAY); 
		
		goodDataButton = new JEditorPane();
		goodDataButton.setBackground(Color.GREEN);
		goodDataButton.setBorder(raisedetched);
		goodDataButton.setBounds(587, 32, 24, 20);
		panel.add(goodDataButton);
		
		
		JLabel lblDataStatus = new JLabel("Data Status");
		lblDataStatus.setBounds(512, 38, 65, 14);
		panel.add(lblDataStatus);
		
		signalStrength = new JProgressBar();
		signalStrength.setOrientation(SwingConstants.VERTICAL);
		signalStrength.setBounds(515, 63, 11, 127);
		panel.add(signalStrength);
		
		batteryStatus = new JProgressBar();
		batteryStatus.setMinimum(2800);
		batteryStatus.setMaximum(3065);
		batteryStatus.setOrientation(SwingConstants.VERTICAL);
		batteryStatus.setBounds(570, 63, 11, 127);
		panel.add(batteryStatus);
		
		lblSignal = new JLabel("Signal");
		lblSignal.setBounds(504, 201, 46, 14);
		panel.add(lblSignal);
		
		lblBattery = new JLabel("Battery");
		lblBattery.setBounds(560, 201, 46, 14);
		panel.add(lblBattery);
	   

		
		btnStartStop.setActionListener();
		
		
		
		
		final int btnStartStopX = panel.getWidth()-btnStartStop.getX();
		final int btnStartStopY = panel.getHeight()-btnStartStop.getY();
		

		final int chckbxAutoscrollX = panel.getWidth()-chckbxAutoscroll.getX();
		final int chckbxAutoscrollY = panel.getHeight()-chckbxAutoscroll.getY();
		
		frame.getContentPane().addComponentListener(new ComponentAdapter() 
		{
			public void componentResized(ComponentEvent arg0) 
			{
				int frameWidth= panel.getWidth();
				int frameHeight = panel.getHeight();
				
				panel.setSize(frame.getContentPane().getSize());
				scrollPane.setSize(frameWidth-scrollPaneX, frameHeight-scrollPaneY);
				sendLine.setSize(frameWidth-sendLineXresize,sendLine.getHeight());
				
				sendLine.setLocation(sendLineX, frameHeight-sendLineY);
				btnSend.setLocation(frameWidth-btnSendX, frameHeight-btnSendY);
				btnStartStop.setLocation(frameWidth-btnStartStopX, frameHeight-btnStartStopY);
				chckbxAutoscroll.setLocation(frameWidth-chckbxAutoscrollX, frameHeight-chckbxAutoscrollY);
			}
		});
		
		frame.addWindowListener(new WindowListener() 
		{
			@Override
			public void windowActivated(WindowEvent arg0) {}
			@Override
			public void windowClosed(WindowEvent arg0)
			{
			}
			@Override
			public void windowDeactivated(WindowEvent arg0) {}
			@Override
			public void windowDeiconified(WindowEvent arg0) {}
			@Override
			public void windowIconified(WindowEvent arg0) {}
			@Override
			public void windowOpened(WindowEvent arg0) {}
			@Override
			public void windowClosing(WindowEvent e) 
			{
				System.exit(0);
			}
		});	
		
		
		//timeGraph = new TimeGraph();
}

	public void updateText(String _StreamInString, SimpleAttributeSet type)
	{
		
		
		try
		{
			doc.insertString(doc.getLength(),_StreamInString, type);
			//System.out.print(updateString);
		}
		catch(Exception e) 
		{ 
			System.out.println(e);
		}	
	}
	
	public void updatePayloadData(PayloadData payloadData)
	{
		
		if(!graphSet)
		{
			int commas = 0;
			for(int i = 0; i < payloadData.scienceData.length(); i++)
			{
				if(payloadData.scienceData.charAt(i) == ',')
					commas++;
			}
			
			graph = new ButtonGroup();
			int start = 2;
			int end = payloadData.scienceData.indexOf(',',2);
			
			for(int j = 0; j < commas/2; j++)
			{
				
				String tempString = payloadData.scienceData.substring(start, end);
				GraphButtonMenuItem graphType = new GraphButtonMenuItem(this, tempString,j);
				
				start = payloadData.scienceData.indexOf(',', start)+1;
				end = payloadData.scienceData.indexOf(',',start);
				start = payloadData.scienceData.indexOf(',', start)+1;
				end = payloadData.scienceData.indexOf(',',start);
				
				if(end <0)
				{
					end = payloadData.scienceData.length();
				}
				graphType.setActionListener();
				graph.add(graphType);
				mnGraph.add(graphType);
				
			}
			
			graphSet = true;
			//timeGraph = new TimeGraph(deviceName, deviceName, deviceName, 1);
			
		}
		
		

		
		if(payloadData.timeStamp >= lastRecivedTime && !goodData)
		{
			goodDataButton.setForeground(Color.GREEN);
			goodDataButton.setBackground(Color.GREEN);
			goodData = true;
		}
		if(payloadData.timeStamp < lastRecivedTime && goodData)
		{
			goodDataButton.setForeground(Color.RED);
			goodDataButton.setBackground(Color.RED);
			goodData = false;
		}
		lastRecivedTime = payloadData.timeStamp;
		
		if(goodData)
		{
			try
			{
				String tempGPS = payloadData.gpsData;
				String tempSensor = payloadData.scienceData + "\n";
				doc.insertString(doc.getLength(),tempGPS, blue);
				doc.insertString(doc.getLength(), tempSensor, green);
				
				//System.out.print(updateString);
			}
			catch(Exception e) 
			{ 
				System.out.println(e);
			}
		}
		
		
		if(enableGraph == true)
		{
			//timeGraph.updatePayloadData(payloadData);
	
		PayloadUpdateGraphEvent complete = new PayloadUpdateGraphEvent(this, payloadData);
		Object[] listeners = Terminal.listenerList.getListenerList(); 
   		for (int i=0; i<listeners.length; i+=2) 
   		{
             if (listeners[i]==IPayloadUpdateUpdateGraphEventListener.class)
             {
                 ((IPayloadUpdateUpdateGraphEventListener)listeners[i+1]).PayloadUpdateUpdateEventHandeler(complete);
             }
        } 		
   		
		PayloadUpdataDataEvent complete2 = new PayloadUpdataDataEvent(this, payloadData);
		Object[] listeners2 = Terminal.listenerList.getListenerList(); 
   		for (int i=0; i<listeners2.length; i+=2) 
   		{
             if (listeners[i]==IPayloadUpdateDataEventListener.class)
             {
                 ((IPayloadUpdateDataEventListener)listeners2[i+1]).PayloadUpdateDataEventHandeler(complete2);
             }
        } 		

 
		}
		
		if(payloadData.brodcastMessage != null)
		{
			long temp = Long.parseLong(payloadData.brodcastMessage.RSSI,16);
			int tempVal = (int) (100-temp); // needs to be addressed for min and max
			signalStrength.setValue(tempVal);
			
			long temp2 = Long.parseLong(payloadData.brodcastMessage.BatteryVoltage,16);
			int tempVal2 = (int) (temp2); // needs to be addressed for min and max
			batteryStatus.setValue(tempVal2);
		}
		
		
		
		
		
		
		
		
	}
	
	public static void addPayloadUpdateEvent (IPayloadUpdateUpdateGraphEventListener completePayloadUpdateEventListener)
	{
		listenerList.add(IPayloadUpdateUpdateGraphEventListener.class, completePayloadUpdateEventListener);
	}
	public static void addPayloadUpdateDataEvent (IPayloadUpdateDataEventListener completePayloadUpdateDataEventListener)
	{
		listenerList.add(IPayloadUpdateDataEventListener.class, completePayloadUpdateDataEventListener);
	}
}
