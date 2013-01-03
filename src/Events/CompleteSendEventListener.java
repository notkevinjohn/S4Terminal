package Events;

import java.awt.Color;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import Main.DataController;

public class CompleteSendEventListener implements ICompleteSendEventListener
{
	private SimpleAttributeSet red = new SimpleAttributeSet();
	private DataController dataController;
	
	public CompleteSendEventListener (DataController dataController)
	{
		this.dataController = dataController;
		StyleConstants.setForeground(red, Color.RED);
	}
	
	public void completeSendEventHandler(CompleteSendEvent event) 
	{
		dataController.sendCommandRX(event.command);
//		dataController.updateText(event.send, red); // need to change this if you want to see what is sent
	}
}

	

