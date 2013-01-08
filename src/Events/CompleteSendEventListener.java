package Events;

import Main.DataController;

public class CompleteSendEventListener implements ICompleteSendEventListener
{
	private DataController dataController;
	
	public CompleteSendEventListener (DataController dataController)
	{
		this.dataController = dataController;
	}
	
	public void completeSendEventHandler(CompleteSendEvent event) 
	{
		dataController.sendCommandTX(event.command);
	}
}

	

