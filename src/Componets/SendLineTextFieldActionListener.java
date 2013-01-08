package Componets;

import Data.Command;
import Events.CompleteSendEvent;
import Events.ICompleteSendEventListener;
import Main.DataController;

public class SendLineTextFieldActionListener
{

	public SendLineTextFieldActionListener(Command command)
	{
		

			CompleteSendEvent complete = new CompleteSendEvent(this, null);
			
			Object[] listeners = DataController.listenerList.getListenerList(); 
	   		for (int i=0; i<listeners.length; i+=2) 
	   		{
	             if (listeners[i]==ICompleteSendEventListener.class)
	             {
	                 ((ICompleteSendEventListener)listeners[i+1]).completeSendEventHandler(complete);
	             }
	        } 
	}
}


	
