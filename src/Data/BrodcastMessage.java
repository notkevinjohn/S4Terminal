package Data;

import java.io.Serializable;

public class BrodcastMessage implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8735505746069529122L;
	
	public String macAddress;
	public String channel;
	public String RSSI = "0";
	public String localTCPPort;
	public String RTCvalue;
	public String BatteryVoltage = "0";
	public String valueofGPIO;
	public String ASCIITime;
	public String Version;
	public String DeviceID;
	public String BootTime;
	public String VoltageSensors;
	
}
