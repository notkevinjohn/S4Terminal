package Componets;

import javax.swing.JMenuItem;

import GUI.DataGUI;

public class SetDataMenuItem extends JMenuItem 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5570296424264800396L;
	public DataGUI dataGUI;
	boolean actionListenerSet = false;

	public int sensorNubmer;
	
	public SetDataMenuItem(DataGUI dataGUI, String name)
	{
		super(name);	
		this.setName(name);
		this.dataGUI = dataGUI;
	}	
	
	public void setActionListener()
	{
		if(!actionListenerSet)
		{
			this.addActionListener( new SetDataMenuActionListener(dataGUI));
			actionListenerSet = true;
			
		}
	}

}