package Componets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

import Data.Command;
import Events.CompleteSendEvent;
import Events.ICompleteSendEventListener;
import Main.DataController;

public class SendActionListener implements ActionListener
{
	private  JTextField sendLine;
	private String sendString;
	private Command command;
	public SendActionListener(JTextField sendLine)
	{
		this.sendLine = sendLine;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		sendString = sendLine.getText() + "\n";
		sendLine.setText("");
		Command command = null;
		CompleteSendEvent complete = new CompleteSendEvent(this, command);
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
	


