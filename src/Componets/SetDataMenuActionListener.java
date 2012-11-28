package Componets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GUI.DataGUI;

public class SetDataMenuActionListener implements ActionListener
{
	public DataGUI dataGUI;
	
	public SetDataMenuActionListener(DataGUI dataGUI)
	{
		this.dataGUI = dataGUI;

	}
	
	public void actionPerformed(ActionEvent arg0) 
	{
		dataGUI.baseAlt = dataGUI.payloadData.alt;
		dataGUI.baseLon = dataGUI.payloadData.lon;
		dataGUI.baseLat = dataGUI.payloadData.lat;
		dataGUI.baseSet = true;
	}
}