package IOStream;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import Data.Command;

public class CommandObjectTX {
	
	public ObjectOutputStream objectOutputStream;
	public Socket socket;
	
	public CommandObjectTX(Socket socket)
	{
		this.socket = socket;
		try 
		{
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void sendObject(Command command)
	{

		try 
		{
			objectOutputStream.writeObject(command);
			objectOutputStream.flush();
			objectOutputStream.reset();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

