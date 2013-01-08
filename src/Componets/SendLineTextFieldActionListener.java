package Componets;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

public class SendLineTextFieldActionListener implements KeyListener
{

	
	public SendLineTextFieldActionListener(JTextField sendLine)
	{

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
//			sendString = sendLine.getText() + "\n";
//			sendLine.setText("");
//			CompleteSendEvent complete = new CompleteSendEvent(this);
//			Object[] listeners = DataController.listenerList.getListenerList(); 
//	   		for (int i=0; i<listeners.length; i+=2) 
//	   		{
//	             if (listeners[i]==ICompleteSendEventListener.class)
//	             {
//	                 ((ICompleteSendEventListener)listeners[i+1]).completeSendEventHandler(complete);
//	             }
//	        } 
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) 
	{	
	}
	@Override
	public void keyReleased(KeyEvent e) 
	{
	}
}


	
