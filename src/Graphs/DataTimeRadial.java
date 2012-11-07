package Graphs;

import javax.swing.JRadioButtonMenuItem;

public class DataTimeRadial extends JRadioButtonMenuItem 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4757404618347565907L;
	
	public TimeGraph timeGraph;
	boolean actionListenerSet = false;
	
	public DataTimeRadial(TimeGraph timeGraph, String time)
	{
		super(time);	
		this.setName(time);
		this.timeGraph = timeGraph;
	}	
	
	public void setActionListener()
	{
		if(!actionListenerSet)
		{
			this.addActionListener( new TimeGraphActionListener(timeGraph));
			actionListenerSet = true;
		}
	}

}
