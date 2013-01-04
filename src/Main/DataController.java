package Main;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.SwingUtilities;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import Data.Command;
import Data.PayloadData;
import Data.PayloadRX;
import Events.CompleteSendEventListener;
import Events.StartStop.ICompleteStartEventListener;
import Events.StartStop.ICompleteStopEventListener;
import Events.StartStop.CompleteStartEventListener;
import Events.StartStop.CompleteStopEventListener;
import Events.ICompleteSendEventListener;
import FileWriters.WiFiWriter;
import GUI.Terminal;
import IOStream.CommandObjectTX;
//import IOStream.GetStreamIn;
import IOStream.PayloadObjectRX;
//import IOStream.SendStreamOut;

public class DataController extends Thread 
{
	private Terminal terminal;
	public Socket socket;
	private int updateRate = 1000;
//	private SendStreamOut streamOut;
//	private String streamInString;
//	private GetStreamIn getStreamIn;
	private int available = 0;
	private SimpleAttributeSet blue = new SimpleAttributeSet();
	private SimpleAttributeSet green = new SimpleAttributeSet();
	public Vector<PayloadData> payloadDataVector;
	public boolean boolStream = true;
	public long lastReadTime = System.currentTimeMillis();
	public long lastUpdateTime = System.currentTimeMillis();;
	public String ip;
	public int port;
	public WiFiWriter wiFiWriter;
	public static javax.swing.event.EventListenerList listenerList = new javax.swing.event.EventListenerList();
	public String payloadName;
	public PayloadObjectRX payloadObjectRX;
	public  ObjectInputStream objectInputStream;
	public CommandObjectTX commandObjectTX;
	public Command command;
	public long lastTimeStampFromPayload;
	private Timer timer;
	public void Initilize(Socket socket, String payloadName, CommandObjectTX commandObjectTX, PayloadObjectRX payloadObjectRX)
	{
		this.socket = socket;
		this.payloadName = payloadName;
		this.commandObjectTX = commandObjectTX;
		this.payloadObjectRX = payloadObjectRX;
		
//		this.objectInputStream = objectInputStream;
//		this.socket = socket;
//		this.ip = ip;
//		this.port = port;
//		this.deviceName = deviceName;
		
		StyleConstants.setForeground(blue, Color.BLUE);
		StyleConstants.setForeground(green, new Color(0,64,0));
		
		terminal = new Terminal(payloadName);
		wiFiWriter = new WiFiWriter();
		
//		getStreamIn = new GetStreamIn();
//		streamOut = new SendStreamOut();
//		streamOut.attachSocket(socket);
		
		payloadDataVector = new Vector<PayloadData>();
		CommandRXController();
		Start();
		Stop();
		UpdatePayload(updateRate);
		this.start();
		
	}
	
	public void run() 
	{
		while(true)
		{
			if(boolStream)
			{
				try 
				{
					available = socket.getInputStream().available();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				
				if(available > 0)
				{
					PayloadRX payloadRX = new PayloadRX();
					payloadRX = payloadObjectRX.getPayloadObject();
					
					if(payloadRX != null)
					{
						for(int i = 0; i < payloadRX.payloadRX.size(); i++)
						{
							PayloadData payloadData = payloadRX.payloadRX.get(i);
							updatePayloadData(payloadData);
							lastTimeStampFromPayload = payloadData.timeStamp;
						}
					}
					
//					  lastReadTime = System.currentTimeMillis();
//					  streamInString = getStreamIn.StreamIn(socket);
//					 // System.out.println(streamInString);
//					   
//					  if(streamInString.startsWith("PayloadUpdate"))
//					  {
//							payloadDataVector = payloadObjectRX.getPayloadObject(payloadDataVector,this);
//							
//							if(payloadDataVector != null)
//							{
////								updateText(payloadDataVector.lastElement().gpsData , blue);
////								String tempString = payloadDataVector.lastElement().scienceData;
////								tempString += '\n';
////								
////								updateText(tempString, blue);
//								
//								
//								updatePayloadData(payloadDataVector.lastElement());
//								
//							}
//					  }
		//try { Thread.sleep(10); } catch(InterruptedException e) { /* we tried */}
					}
			}
		}
	}
				 
	public void UpdatePayload(int updateRate) 
	{
		timer = new Timer();
		timer.schedule(new RemindTask(), 0, updateRate);
	}
	

	class RemindTask extends TimerTask 
	{
		public void run() 
		{
			Command command = new Command();
			command.timeStamp = lastTimeStampFromPayload;
			command.payloadName = payloadName;
			sendCommandRX(command);
			
		}
	}
			
	
	public void sendCommandRX(final Command command)
	{
		 SwingUtilities.invokeLater(new Runnable() {
			    public void run() 
			    {
			    	commandObjectTX.sendObject(command);
			    }
			  });
	}

//	public void updateText(final String _StreamInString, final SimpleAttributeSet type) {
//		  SwingUtilities.invokeLater(new Runnable() {
//		    public void run() {
//		    	 terminal.updateText(_StreamInString,type);
//		    	// wiFiWriter.recieveText(_streamInString);
//		    }
//		  });
//		}
	
	public void updatePayloadData(final PayloadData payloadData) {
		  SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	 terminal.updatePayloadData(payloadData);
		    	 //wiFiWriter.recieveText(_streamInString);
		    }
		  });
		}
	
	public  void CommandRXController()
	{
		addCompleteSendEventListener(new CompleteSendEventListener(this));
	}
	
	public void Start()
	{
		addCompleteStartEventListener(new CompleteStartEventListener(this));
	}
	
	public void Stop()
	{
		addCompleteStopEventListener(new CompleteStopEventListener(this));
	}
	public void SendButtonDisable()
	{
		terminal.btnSend.setEnabled(false);
	}
	
	public void SendButtonEnable()
	{
		terminal.btnSend.setEnabled(true);
	}
	
	public boolean isConnectionAlive()
	{
		return true; //(System.currentTimeMillis() - lastReadTime) < timeout;
	}

	public static void addCompleteSendEventListener (ICompleteSendEventListener completeSendEventListener)
	{
		listenerList.add(ICompleteSendEventListener.class, completeSendEventListener);
	}
	public static void removeCompleteSendEventListener (ICompleteSendEventListener completeSendEventListener)
	{
		listenerList.remove(ICompleteSendEventListener.class, completeSendEventListener);
	}
	public static void addCompleteStartEventListener (ICompleteStartEventListener completeStartEventListener)
	{
		listenerList.add(ICompleteStartEventListener.class, completeStartEventListener);
	}
	public static void addCompleteStopEventListener (ICompleteStopEventListener completeStopEventListener)
	{
		listenerList.add(ICompleteStopEventListener.class, completeStopEventListener);
	}

}
