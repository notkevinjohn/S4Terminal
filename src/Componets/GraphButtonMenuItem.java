package Componets;

import javax.swing.JMenuItem;

import GUI.Terminal;

public class GraphButtonMenuItem extends JMenuItem 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4757404618347565907L;
	
	public Terminal termianl;
	boolean actionListenerSet = false;
	public String graphName;
	public int sensorNubmer;
	
	public GraphButtonMenuItem(Terminal termianl, String graphName, int sensorNumber)
	{
		super(graphName);	
		this.setName(graphName);
		this.termianl = termianl;
		this.sensorNubmer = sensorNumber;
		this.graphName = graphName;
	}	
	
	public void setActionListener()
	{
		if(!actionListenerSet)
		{
			this.addActionListener( new GraphMenuItemActionListener(termianl,graphName,sensorNubmer));
			actionListenerSet = true;
		}
	}

}
