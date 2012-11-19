package Componets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Events.PayloadUpdateEventListener;
import GUI.Terminal;
import Graphs.TimeGraph;


public class GraphMenuItemActionListener implements ActionListener
{
	public Terminal terminal;
	public int sensorNumber;
	public String graphName;
	
	
	public GraphMenuItemActionListener(Terminal terminal, String graphName, int sensorNumber)
	{
		this.terminal = terminal;
		this.graphName = graphName;
		this.sensorNumber = sensorNumber;
	}
	
	public void actionPerformed(ActionEvent arg0) 
	{
		String TempString = graphName + " vs. Time";
		TimeGraph timeGraph = new TimeGraph(TempString, "Time (seconds)", graphName, sensorNumber);
		
		Terminal.addPayloadUpdateEvent(new PayloadUpdateEventListener(terminal, timeGraph));
		
		
	}
}