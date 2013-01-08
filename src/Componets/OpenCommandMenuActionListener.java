package Componets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GUI.CommandWindow;
import GUI.Terminal;
import Main.DataController;

public class OpenCommandMenuActionListener implements ActionListener
{

	public Terminal terminal;
	public int sensorNumber;
	public String graphName;
	public DataController dataController;
	
	public OpenCommandMenuActionListener(Terminal terminal, DataController dataController)
	{
		this.terminal = terminal;
		this.dataController = dataController;
	}
	
	public void actionPerformed(ActionEvent arg0) 
	{
		new CommandWindow(dataController);
	}

}
