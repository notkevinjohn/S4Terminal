package Events;

import java.util.EventObject;

import Data.PayloadData;

public class PayloadUpdataDataEvent extends EventObject
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7638825269192691075L;
	public PayloadData payloadData;
	
	public  PayloadUpdataDataEvent (Object source, PayloadData payloadData)
	{
		super(source);
		this.payloadData =payloadData;
		
	}
}


