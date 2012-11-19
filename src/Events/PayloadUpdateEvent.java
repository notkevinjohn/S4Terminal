package Events;

import java.util.EventObject;

import Data.PayloadData;

public class PayloadUpdateEvent  extends EventObject
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8526732845393991655L;
	public PayloadData payloadData;
	
	public  PayloadUpdateEvent (Object source, PayloadData payloadData)
	{
		super(source);
		this.payloadData =payloadData;
		
	}
}



