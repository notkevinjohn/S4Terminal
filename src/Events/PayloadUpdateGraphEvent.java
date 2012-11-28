package Events;

import java.util.EventObject;

import Data.PayloadData;

public class PayloadUpdateGraphEvent  extends EventObject
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8526732845393991655L;
	public PayloadData payloadData;
	
	public  PayloadUpdateGraphEvent (Object source, PayloadData payloadData)
	{
		super(source);
		this.payloadData =payloadData;
		
	}
}



