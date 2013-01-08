package Componets;

import javax.swing.JButton;
import GUI.CommandWindow;

public class SendButton extends JButton
{
	private static final long serialVersionUID = -5039977757360860835L;
	private boolean actionListenerSet = false;
	private String name;
	
	public SendButton(String name)
	{
		super(name);	
		this.setName(name);
		this.name = name;
	}
	
	public void setActionListener (CommandWindow commandWindow)
	{
		if(!actionListenerSet)
		{
			this.addActionListener( new SendActionListener(name,commandWindow));
			actionListenerSet = true;
		}
	}
}
