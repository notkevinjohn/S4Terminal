package IOStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Vector;
import Data.PayloadData;
import Main.DataController;

public class PayloadObjectRX 
{
	public Vector<PayloadData> payloadDataVector;
	public Socket socket;
	public ObjectInputStream objectInputStream;
	
	public PayloadObjectRX(Socket _socket, ObjectInputStream objectInputStream)
	{
		socket = _socket;
		this.objectInputStream = objectInputStream;
	}
	
	
	public   Vector<PayloadData> getPayloadObject(Vector<PayloadData> payloadDataVector, DataController dataController)	
	{	
			try 
			{
				
				PayloadData updatePayloadData = (PayloadData)objectInputStream.readObject();

				payloadDataVector.addElement((PayloadData) updatePayloadData);
			
			} 
			catch (ClassNotFoundException e) 
			{

			}
			catch(IOException e)
			{
			}
			
	     

		return payloadDataVector;
	}
}
