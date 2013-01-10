package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Componets.SendButton;
import Data.Command;
import Main.DataController;

public class CommandWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 560840414023413780L;
	private JPanel contentPane;
	public SendButton command1;
	public SendButton command2;
	public SendButton command3;
	public SendButton command4;
	public JFrame frame;
	public DataController dataController;
	public CommandWindow(DataController dataController) 
	{
		
		this.dataController = dataController;
		
		this.setVisible(true);
		
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 169, 194);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		command1 = new SendButton("Command 1");
		command1.setBounds(17, 15, 121, 23);
		contentPane.add(command1);
		command1.setActionListener(this);
		
		
		command2 = new SendButton("Command 2");
		command2.setBounds(17, 46, 121, 23);
		contentPane.add(command2);
		command2.setActionListener(this);
		
		command3 = new SendButton("Command 3");
		command3.setBounds(17, 80, 121, 23);
		contentPane.add(command3);
		command3.setActionListener(this);
		
		command4 = new SendButton("Command 4");
		command4.setBounds(17, 115, 121, 23);
		contentPane.add(command4);
		command4.setActionListener(this);
	
	}
	
	public void SendCommand(Command command)
	{
		dataController.sendCommandToPayload(command);
	}
}
