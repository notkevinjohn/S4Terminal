package Graphs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;

public class TimeGraphActionListener implements ActionListener
{
	public TimeGraph timeGraph;
	
	public TimeGraphActionListener(TimeGraph timeGraph)
	{
		this.timeGraph = timeGraph;
	}
	
	public void actionPerformed(ActionEvent arg0) 
	{
		if(timeGraph.time15.isSelected())
		{
			timeGraph.dataPointSet = 15;
		}
		
		if(timeGraph.time30.isSelected())
		{
			timeGraph.dataPointSet = 30;
		}
		
		if(timeGraph.time60.isSelected())
		{
			timeGraph.dataPointSet = 60;
		}
		
		if(timeGraph.time120.isSelected())
		{
			timeGraph.dataPointSet = 120;
		}
		
		if(timeGraph.time300.isSelected())
		{
			timeGraph.dataPointSet = 300;
		}
		
		if(timeGraph.time600.isSelected())
		{
			timeGraph.dataPointSet = 600;
		}
		if(timeGraph.timeAll.isSelected())
		{
			timeGraph.dataPointSet = -1;
		}
		
		
    } 		 


}
