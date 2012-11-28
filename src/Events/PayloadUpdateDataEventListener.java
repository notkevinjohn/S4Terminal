package Events;

import Data.PayloadData;
import GUI.DataGUI;
import GUI.Terminal;

public class PayloadUpdateDataEventListener implements IPayloadUpdateDataEventListener
{
	public DataGUI dataGUI;
	public Terminal terminal;
	public PayloadData payloadData;

	
	public PayloadUpdateDataEventListener (Terminal terminal, DataGUI dataGUI)
	{
		this.terminal = terminal;
		this.dataGUI = dataGUI;
	}

	public void PayloadUpdateDataEventHandeler(PayloadUpdataDataEvent event) 
	{
		payloadData = event.payloadData;
		dataGUI.updatePayloadData(payloadData);
		
		
	}



}

	