package IOStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import Data.PayloadRX;

public class PayloadObjectRX 
{
	public PayloadRX payloadRX;
	public Socket socket;
	public ObjectInputStream objectInputStream;
	
	public PayloadObjectRX(Socket _socket)
	{
		socket = _socket;
		try 
		{
			objectInputStream = new ObjectInputStream(socket.getInputStream());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public PayloadRX getPayloadObject()	
	{	
		try 
		{
			payloadRX = (PayloadRX)objectInputStream.readObject();
		} 
		catch (ClassNotFoundException e) 
		{	
		}
		catch(IOException e)
		{
		}
			
		return payloadRX;
	}
}
