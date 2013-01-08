package Componets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Data.Command;
import GUI.CommandWindow;

public class SendActionListener implements ActionListener
{
	public Command command;
	public CommandWindow commandWindow;
	
	public SendActionListener(String name, CommandWindow commandWindow)
	{
		this.commandWindow = commandWindow;
		command = new Command();
		
		if(name.startsWith("Command 1"))
		{
			command.commandOne = true;
		}
		if(name.startsWith("Command 2"))
		{
			command.commandTwo = true;
		}
		if(name.startsWith("Command 3"))
		{
			command.commandThree = true;
		}
		if(name.startsWith("Command 4"))
		{
			command.commandFour = true;
		}
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		commandWindow.SendCommand(command);
    } 		 
}
	


