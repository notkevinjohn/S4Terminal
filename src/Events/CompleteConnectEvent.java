package Events;

import java.util.EventObject;

public class CompleteConnectEvent extends EventObject 
{
	private static final long serialVersionUID = -1298252356347935141L;
	public String ip;
	public int port;
	public String terminalName;

	public CompleteConnectEvent (Object source, String _ip, int _port, String _terminalName)
	{
		super(source);
		ip = _ip;
		port = _port;
		terminalName = _terminalName;
	}
}
