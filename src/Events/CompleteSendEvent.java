package Events;

import java.util.EventObject;

import Data.Command;

public class CompleteSendEvent  extends EventObject{
	private static final long serialVersionUID = 1893392283841623289L;
	
	public Command command;
	
	public  CompleteSendEvent (Object source, Command command)
	{
		super(source);
		this.command = command;
	}
}



