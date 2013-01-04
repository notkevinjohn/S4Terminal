package Data;

import java.io.Serializable;

public class Command implements Serializable
{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6747567172135677536L;
	public long timeStamp = 0;
	public String payloadName = null;
	public String terminalName = null;
	public boolean commandOne = false;
	public boolean commandTwo = false;
	public boolean commandThree = false;
	public boolean commandFour = false;
	public boolean getPayloadList = false;
}
