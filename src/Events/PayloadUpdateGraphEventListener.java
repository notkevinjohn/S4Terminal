package Events;

import Data.PayloadData;
import GUI.Terminal;
import Graphs.TimeGraph;


public class PayloadUpdateGraphEventListener implements IPayloadUpdateUpdateGraphEventListener
{
	public  TimeGraph timeGraph;
	public Terminal terminal;
	public PayloadData payloadData;

	
	public PayloadUpdateGraphEventListener (Terminal terminal, TimeGraph timeGraph)
	{
		this.terminal = terminal;
		this.timeGraph = timeGraph;
	}

	public void PayloadUpdateUpdateEventHandeler(PayloadUpdateGraphEvent event) 
	{
		payloadData = event.payloadData;
		timeGraph.updatePayloadData(payloadData);
		
		
	}

}

	