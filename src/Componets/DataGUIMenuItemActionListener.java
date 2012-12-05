package Componets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Events.PayloadUpdateDataEventListener;
import GUI.DataGUI;
import GUI.Terminal;

public class DataGUIMenuItemActionListener implements ActionListener
{
	public Terminal terminal;
	public int sensorNumber;
	public String graphName;
	
	
	public DataGUIMenuItemActionListener(Terminal terminal)
	{
		this.terminal = terminal;

	}
	
	public void actionPerformed(ActionEvent arg0) 
	{
		DataGUI dataGUI = new DataGUI();
		
		Terminal.addPayloadUpdateDataEvent(new PayloadUpdateDataEventListener(terminal, dataGUI));
	}
}