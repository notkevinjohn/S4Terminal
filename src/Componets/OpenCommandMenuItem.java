package Componets;

import javax.swing.JMenuItem;

import GUI.Terminal;
import Main.DataController;

public class OpenCommandMenuItem extends JMenuItem 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6725801655463685162L;
	
	public Terminal termianl;
	boolean actionListenerSet = false;
	
	public OpenCommandMenuItem(Terminal termianl, String name)
	{
		super(name);	
		this.setName(name);
		this.termianl = termianl;
		
	}	
	
	public void setActionListener(DataController dataController)
	{
		if(!actionListenerSet)
		{
			this.addActionListener( new OpenCommandMenuActionListener(termianl, dataController));
			actionListenerSet = true;
		}
	}

}
