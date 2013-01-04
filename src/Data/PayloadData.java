package Data;

import java.io.Serializable;

public class PayloadData implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5658928332982123269L;
	
	public String payloadName;
	public String gpsData;
	public String scienceData;
	public long timeStamp;
	public double alt;
	public double lon;
	public double lat;
	public String Sen_1_Key;
	public double Sen_1_Value;
	public String Sen_2_Key;
	public double Sen_2_Value;
	public String Sen_3_Key;
	public double Sen_3_Value;
	public String Sen_4_Key;
	public double Sen_4_Value;
	public String Sen_5_Key;
	public double Sen_5_Value;
	public String Sen_6_Key;
	public double Sen_6_Value;
	public String Sen_7_Key;
	public double Sen_7_Value;
	public String Sen_8_Key;
	public double Sen_8_Value;
	public String Sen_9_Key;
	public double Sen_9_Value;
	public String Sen_10_Key;
	public double Sen_10_Value;
	public BrodcastMessage brodcastMessage;
	public PayloadDeviceNameList PayloadList;
	
}
