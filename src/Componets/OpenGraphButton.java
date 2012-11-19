package Componets;

import javax.swing.JRadioButtonMenuItem;

import GUI.Terminal;

public class OpenGraphButton extends JRadioButtonMenuItem 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4807259918154006470L;
	public Terminal terminal;
	boolean actionListenerSet = false;
	public String nameOfGraph;
	
	public OpenGraphButton(Terminal terminal, String nameOfGraph)
	{
		super(nameOfGraph);	
		this.setName(nameOfGraph);
		this.terminal = terminal;
		this.nameOfGraph = nameOfGraph;
	}	
	
	public void setActionListener()
	{
		if(!actionListenerSet)
		{
			this.addActionListener( new GraphMenuItemActionListener(terminal, nameOfGraph, 1));
			actionListenerSet = true;
		}
	}

}
