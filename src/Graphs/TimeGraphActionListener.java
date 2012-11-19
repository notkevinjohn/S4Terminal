package Graphs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
			int end = timeGraph.seriesCount-timeGraph.seriesStart-15;
			for(int i = 0; i < end; i++)
			{
				timeGraph.series.remove(0);
				timeGraph.seriesStart++;
			}
		}
		
		if(timeGraph.time30.isSelected())
		{
			timeGraph.dataPointSet = 30;
			int end = timeGraph.seriesCount-timeGraph.seriesStart-30;
			for(int i = 0; i < end; i++)
			{
				timeGraph.series.remove(0);
				timeGraph.seriesStart++;
			}
		}
		
		if(timeGraph.time60.isSelected())
		{
			timeGraph.dataPointSet = 60;
			int end = timeGraph.seriesCount-timeGraph.seriesStart-60;
			for(int i = 0; i < end; i++)
			{
				timeGraph.series.remove(0);
				timeGraph.seriesStart++;
			}
		}
		
		if(timeGraph.time120.isSelected())
		{
			timeGraph.dataPointSet = 120;
			int end = timeGraph.seriesCount-timeGraph.seriesStart-120;
			for(int i = 0; i < end; i++)
			{
				timeGraph.series.remove(0);
				timeGraph.seriesStart++;
			}
		}
		
		if(timeGraph.time300.isSelected())
		{
			timeGraph.dataPointSet = 300;
			int end = timeGraph.seriesCount-timeGraph.seriesStart-300;
			for(int i = 0; i < end; i++)
			{
				timeGraph.series.remove(0);
				timeGraph.seriesStart++;
			}
		}
		
		if(timeGraph.time600.isSelected())
		{
			timeGraph.dataPointSet = 600;
			int end = timeGraph.seriesCount-timeGraph.seriesStart-60;
			for(int i = 0; i < end; i++)
			{
				timeGraph.series.remove(0);
				timeGraph.seriesStart++;
			}
		}
		if(timeGraph.timeAll.isSelected())
		{
			timeGraph.dataPointSet = -1;
		}
		
		
    } 		 


}
