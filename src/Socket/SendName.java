package Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import Data.Command;
import Data.PayloadData;
import Data.PayloadRX;
import Data.TerminalPayloadList;
import GUI.SendConnectionName;
import IOStream.CommandObjectTX;
import IOStream.PayloadObjectRX;

public class SendName
{
//	public SendStreamOut sendStreamOut;
	private int available = 0;
	public String streamInString;
//	public GetStreamIn getStreamIn;
	public String payloadName;
	private boolean deviceNameSet = false;
//	public GetObjectStream getObjectStream;
	public Vector<TerminalPayloadList> payloadListVector;
	public SendConnectionName sendConnectionName;
	public ObjectInputStream objectInputStream;
	public ObjectOutputStream objectOutputStrem;
	public CommandObjectTX commandObjectTX;
	public PayloadObjectRX payloadObjectRX;
	public Command command;
	public String terminalName;
	public PayloadRX payloadRX;
	
	public boolean sendName(Socket socket, String terminalName)
	{
		this.terminalName = terminalName;
//		getStreamIn = new GetStreamIn();
//		sendStreamOut = new SendStreamOut();
//		sendStreamOut.attachSocket(socket);
//		getObjectStream = new GetObjectStream();
//		getObjectStream.getObjectStream(socket);
//		sendStreamOut.streamOut("Refresh");
		
		
		
		commandObjectTX = new CommandObjectTX(socket);
		payloadObjectRX = new PayloadObjectRX(socket);
		
		command = new Command();
		command.terminalName = terminalName;
		command.getPayloadList = true;
		
		commandObjectTX.sendObject(command);
		
		sendConnectionName = new SendConnectionName(this);
		
		
		
		while(!deviceNameSet)
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
				payloadRX = new PayloadRX();
				payloadRX.payloadRX = new Vector<PayloadData>();
				payloadRX = payloadObjectRX.getPayloadObject();
				
				if(payloadRX != null && payloadRX.payloadRX.get(0).PayloadList != null && payloadRX.payloadRX.get(0).payloadName == null)
				{
					sendConnectionName.refreshPayloadList(payloadRX.payloadRX.get(0).PayloadList);
				}
//				else
//				{
//					commandObjectTX.sendObject(command);
//				}
				
				if(payloadRX.payloadRX.get(0).payloadName != null)
				{
					deviceNameSet = true;
				}
		
			}
		}	
				
				
//				 streamInString = getStreamIn.StreamIn(socket);
//				 if(streamInString.equals("#"))
//				 {
//					 sendStreamOut.streamOut("Pong");
//				 }
//				 if (streamInString.equals("@"))
//				 {
//					 deviceNameSet = true;
//					 
//				 }
//				 else if(streamInString.equals("Refresh"))
//				 { 
//					 sendConnectionName.refreshPayloadList(getObjectStream.getObject());
//					 objectInputStream = getObjectStream.objectInputStream;
//				 }
			
		//	try { Thread.sleep(10); } catch(InterruptedException e) { /* we tried */} This makes it mess up need to find out why
		
		return true;
	}
	
	public void RefreshPayloadList()
	{
		commandObjectTX.sendObject(command);
	}
	
	public void SelectPayload(String payloadName)
	{
		this.payloadName = payloadName;
		
		Command command = new Command();
		
		command.payloadName = payloadName;
		command.getPayloadList = true;
		commandObjectTX.sendObject(command);
	}
}



