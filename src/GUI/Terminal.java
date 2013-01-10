package GUI;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import javax.swing.JScrollPane;
import Componets.AutoscrollCheckBox;
import Componets.DataGUIMenuItem;
import Componets.GraphButtonMenuItem;
import Componets.OpenCommandMenuItem;
import Componets.SendButton;
import Componets.SetDataMenuItem;
import Componets.TerminalText;
import Data.PayloadData;
import Events.IPayloadUpdateDataEventListener;
import Events.IPayloadUpdateUpdateGraphEventListener;
import Events.PayloadUpdataDataEvent;
import Events.PayloadUpdateGraphEvent;
import Graphs.TimeGraph;
import Main.DataController;
import NoLongerUsed.SendLineTextField;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.BorderLayout;


public class Terminal 
{
	private JScrollPane scrollPane;
	private JPanel contentPane;
	private AutoscrollCheckBox chckbxAutoscroll;
	private JFrame frame;
	private SimpleAttributeSet green = new SimpleAttributeSet();
	private SimpleAttributeSet blue = new SimpleAttributeSet();
	private OpenCommandMenuItem openCommand;
	private JMenuBar menuBar;
	private JMenu mnGraph;
	private ButtonGroup graph;
	private JProgressBar batteryStatus;
	private JLabel lblSignal;
	private JLabel lblBattery;
	private DataController dataController;
	private JPanel infoPanel;
	private JPanel DataContainer;
	private boolean graphSet = false;
	
	public static javax.swing.event.EventListenerList listenerList = new javax.swing.event.EventListenerList();
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
	public boolean enableGraph = true;
	public TimeGraph timeGraph;
	
	public boolean dataSet = false;
	public JMenuItem mntmOpenGui;
	public SetDataMenuItem setDataMenuItem;
	public DataGUIMenuItem dataGUIMenuItem;
    public long lastRecivedTime = 0;
    public boolean goodData = true;    
	public JProgressBar signalStrength;
	
	
	public Terminal(String deviceName, DataController dataController)
	{
		this.deviceName = deviceName;
		this.dataController = dataController;
		
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
		frame.setBackground(Color.WHITE);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 764, 736);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		frame.setContentPane(contentPane);
		frame.setMinimumSize(new Dimension(400,500));
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{742, 0};
		gbl_contentPane.rowHeights = new int[]{27, 665, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 100.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
								
		JPanel MenuContainer = new JPanel();
		MenuContainer.setBorder(null);
		MenuContainer.setLayout(new BorderLayout(0, 0));
				
		menuBar = new JMenuBar();
		MenuContainer.add(menuBar);
										
		mnGraph = new JMenu("Graph");
		menuBar.add(mnGraph);
										
		JMenu mnData = new JMenu("Data");
		menuBar.add(mnData);
										
										
		dataGUIMenuItem = new DataGUIMenuItem(this,"Open GUI");
		mnData.add(dataGUIMenuItem);
		dataGUIMenuItem.setActionListener();
										
		JMenu mnCommand = new JMenu("Command");
		menuBar.add(mnCommand);
										
		openCommand = new OpenCommandMenuItem(this,"Open Command Window");
		mnCommand.add(openCommand);
		openCommand.setActionListener(dataController);
		
		GridBagConstraints gbc_MenuContainer = new GridBagConstraints();
		gbc_MenuContainer.fill = GridBagConstraints.BOTH;
		gbc_MenuContainer.insets = new Insets(0, 0, 5, 0);
		gbc_MenuContainer.gridx = 0;
		gbc_MenuContainer.gridy = 0;
		contentPane.add(MenuContainer, gbc_MenuContainer);
								
		DataContainer = new JPanel();
		DataContainer.setBackground(Color.WHITE);
		DataContainer.setForeground(Color.WHITE);
		DataContainer.setLayout(new FormLayout(new ColumnSpec[]{ColumnSpec.decode("max(100dlu;pref):grow"), ColumnSpec.decode("max(79dlu;default)"),},
		new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC,RowSpec.decode("max(250dlu;pref):grow"),}));
								
									
		scrollPane = new JScrollPane();
		DataContainer.add(scrollPane, "1, 2, fill, fill");
								
		terminalText = new TerminalText(terminalText);
		scrollPane.setViewportView(terminalText);
		terminalText.setEditable(false);
		terminalText.setBackground(Color.WHITE);
								
		infoPanel = new JPanel();
		DataContainer.add(infoPanel, "2, 2, right, fill");
		infoPanel.setForeground(Color.WHITE);
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setLayout(new FormLayout(new ColumnSpec[] 
		{
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("86px"),},
				new RowSpec[] 
					{
						RowSpec.decode("fill:10dlu"),
						RowSpec.decode("150px"),
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(13dlu;default)"),
						RowSpec.decode("max(150px;default)"),
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("10dlu"),
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(21dlu;default)"),
					}));
								
								
		signalStrength = new JProgressBar();
		infoPanel.add(signalStrength, "2, 2, center, bottom");
		signalStrength.setOrientation(SwingConstants.VERTICAL);
								
		lblSignal = new JLabel("Signal");
		infoPanel.add(lblSignal, "2, 4, center, top");
								
		batteryStatus = new JProgressBar();
		infoPanel.add(batteryStatus, "2, 5, center, bottom");
		batteryStatus.setMinimum(2800);
		batteryStatus.setMaximum(3065);
		batteryStatus.setOrientation(SwingConstants.VERTICAL);
								
		lblBattery = new JLabel("Battery");
		infoPanel.add(lblBattery, "2, 7, center, top");
								

		chckbxAutoscroll = new AutoscrollCheckBox(terminalText);
		infoPanel.add(chckbxAutoscroll, "2, 11");
		terminalText.setActionListener(terminalText,chckbxAutoscroll);
		chckbxAutoscroll.setActionListener();
		chckbxAutoscroll.setBackground(Color.WHITE);
		chckbxAutoscroll.setSelected(true);
		
		GridBagConstraints gbc_DataContainer = new GridBagConstraints();
		gbc_DataContainer.fill = GridBagConstraints.BOTH;
		gbc_DataContainer.gridx = 0;
		gbc_DataContainer.gridy = 1;
		contentPane.add(DataContainer, gbc_DataContainer);
		
		doc = terminalText.getStyledDocument();
		
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
}

	public void updateText(String _StreamInString, SimpleAttributeSet type)
	{
		try
		{
			doc.insertString(doc.getLength(),_StreamInString, type);
		}
		catch(Exception e) 
		{ 
			e.printStackTrace();
		}	
	}
	
	public void updatePayloadData(PayloadData payloadData)
	{
		lastRecivedTime = payloadData.timeStamp;
		
		if(!graphSet)
		{
			CreateGraphList(payloadData);
		}

		try
		{
			
			String tempGPS = payloadData.gpsData;
			String tempSensor = payloadData.scienceData + "\n";
			doc.insertString(doc.getLength(),tempGPS, blue);
			doc.insertString(doc.getLength(), tempSensor, green);
			System.out.println(tempGPS);
		}
		catch(Exception e) 
		{ 
			e.printStackTrace();
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
	
	public void CreateGraphList (PayloadData payloadData)
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
