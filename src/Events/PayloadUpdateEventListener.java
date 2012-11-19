package Events;

import Data.PayloadData;
import GUI.Terminal;
import Graphs.TimeGraph;


public class PayloadUpdateEventListener implements IPayloadUpdateUpdateEventListener
{
	public  TimeGraph timeGraph;
	public Terminal terminal;
	public PayloadData payloadData;
	
	public PayloadUpdateEventListener (Terminal terminal, TimeGraph timeGraph)
	{
		this.terminal = terminal;
		this.timeGraph = timeGraph;

	}

	public void PayloadUpdateUpdateEventHandeler(PayloadUpdateEvent event) 
	{
		payloadData = event.payloadData;
		timeGraph.updatePayloadData(payloadData);
		
	}

}

	