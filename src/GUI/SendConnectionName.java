package GUI;

import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import java.awt.CheckboxGroup;
import Socket.SendName;
import Componets.Connection.DeviceConnectButton;
import Componets.Connection.RefreshButton;
import Data.PayloadDeviceNameList;
import javax.swing.JComboBox;

public class SendConnectionName extends JFrame
{

	private static final long serialVersionUID = -5571237008779560428L;
	private JPanel contentPane;
	private SendName sendName;
	public ArrayList<JCheckBox> selectArray = new ArrayList<JCheckBox>();
	public ArrayList<JTextField> deviceNames = new ArrayList<JTextField>();
	public ArrayList<String> deviceStringNames = new ArrayList<String>();
	public JFrame frame;
	public RefreshButton refreshButton;
	public DeviceConnectButton deviceConnectButton;
	public CheckboxGroup checkBoxGroup;
	public PayloadDeviceNameList payloadDeviceNameList;
	public JComboBox<String> payloadListComboBox;
	
	public SendConnectionName(SendName sendName) 
	{
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException e) { }
	    catch (ClassNotFoundException e) {}
	    catch (InstantiationException e) {}
	    catch (IllegalAccessException e) {}
		
		this.sendName = sendName;
		frame = new JFrame("Select Payload");
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(454, 77);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		refreshButton = new RefreshButton();
		refreshButton.setBounds(251, 11, 89, 23);
		contentPane.add(refreshButton);
		refreshButton.setActionListener(sendName);
		
		deviceConnectButton = new DeviceConnectButton();
		deviceConnectButton.setBounds(350, 11, 89, 23);
		contentPane.add(deviceConnectButton);
		
		payloadListComboBox = new JComboBox<String>();
		payloadListComboBox.setBounds(10, 12, 231, 20);
		contentPane.add(payloadListComboBox);
		
		payloadDeviceNameList = new PayloadDeviceNameList();
		
		frame.repaint();
		
		if(payloadDeviceNameList.payloadDeviceNameList != null)
		{
			refreshPayloadList(payloadDeviceNameList);
		}
		else
		{
			deviceConnectButton.setEnabled(false);
			payloadListComboBox.addItem("No Payloads Available");
			
		}
	}

	public void redrawDeviceNames(ArrayList<String> deviceStringNames)
	{
		new JPanel();
		payloadListComboBox.removeAllItems();
		
		if(payloadDeviceNameList.payloadDeviceNameList.isEmpty())// || payloadDeviceNameList.payloadDeviceNameList.size() == 0)
		{
			deviceConnectButton.setEnabled(false);
			payloadListComboBox.addItem("No Payloads Available");
		}
		else
		{
			deviceConnectButton.setEnabled(true);
			int deviceSize = payloadDeviceNameList.payloadDeviceNameList.size();
			for(int i =0; i< deviceSize; i++)
			{
				String name = payloadDeviceNameList.payloadDeviceNameList.get(i).deviceName;
				payloadListComboBox.addItem(name);
			}
			deviceConnectButton.setActionListener(sendName, payloadListComboBox,this);	
		}
	}
	
	public void refreshPayloadList(PayloadDeviceNameList payloadDeviceNameList)
	{
		this.payloadDeviceNameList = payloadDeviceNameList;
		redrawDeviceNames(deviceStringNames);
	}
}
