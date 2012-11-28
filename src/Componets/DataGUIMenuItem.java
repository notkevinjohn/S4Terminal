package Componets;

import javax.swing.JMenuItem;

import GUI.Terminal;

public class DataGUIMenuItem extends JMenuItem 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6313022524480593076L;
	public Terminal termianl;
	boolean actionListenerSet = false;

	public int sensorNubmer;
	
	public DataGUIMenuItem(Terminal termianl, String name)
	{
		super(name);	
		this.setName(name);
		this.termianl = termianl;
	}	
	
	public void setActionListener()
	{
		if(!actionListenerSet)
		{
			this.addActionListener( new DataGUIMenuItemActionListener(termianl));
			actionListenerSet = true;
		}
	}

}