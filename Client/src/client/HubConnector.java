package client;

import java.io.IOException;
import java.util.Vector;

import javax.bluetooth.*;
import javax.microedition.io.Connection;
import javax.microedition.io.Connector;
import javax.microedition.lcdui.AlertType;
import javax.obex.ClientSession;

import shared.discovery.DeviceDiscoverer;
import shared.discovery.ServiceDiscoverer;
import shared.gui.AlertDisplayer;

public class HubConnector {
	
	public static String HUB_BLUETOOTH_ADDRESS = "0000000DECAF";
	public static final String HUB_UUID = "00FF00FF00FF00FF00FF00FF00FF00FF";
	
	
	public BTClient client;
	public DeviceDiscoverer deviceDiscoverer;
	public ServiceDiscoverer serviceDiscoverer;
	public ClientSession conn;
		
	public HubConnector ( BTClient client )
	{
		this.client = client;
	}
	
	public ClientSession connect ()
	{
		System.out.println("[client:] Trying to Connect to Hub");
		//try {
		String[] str = new String[1];
		str[0] = HUB_BLUETOOTH_ADDRESS;
		deviceDiscoverer = new DeviceDiscoverer(str, client.localDevice);
                
		Vector devices = deviceDiscoverer.getDiscoveredDevices();
		if(!devices.isEmpty())
		{
                    client.gui.showAlert("DeviceFound", "A Device was Found", this.client.gui.getWelcome());
               
                    System.out.println("[client:] Few devices found that may be Hub with BT " + ((RemoteDevice) devices.elementAt(0)).getBluetoothAddress());
                        
			UUID[] uuidSet = new UUID[1];
			uuidSet[0] = new UUID(HubConnector.HUB_UUID, false);
			serviceDiscoverer = new ServiceDiscoverer(uuidSet,(RemoteDevice)devices.elementAt(0), client.localDevice);
			Vector rec = serviceDiscoverer.getServiceRecords();
			if(!rec.isEmpty())
			{
				//System.out.println("[client:] HubService Found, Connecting");
				
                                
                                
				String connectionURL = ((ServiceRecord)rec.elementAt(0)).getConnectionURL(
										ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
				
				//System.out.println("[client:] Connection URL: " + connectionURL);
				try {
					conn = (ClientSession) Connector.open(connectionURL);
                                        client.gui.showAlert("Success", "Connection Established",
                                                    client.gui.getWelcome());
					return conn;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//System.out.println("[client:] Error Occured");
					e.printStackTrace();
					return null;
				}
			}

			else
			{
				//System.out.println("[client:] HubService Not Found");
				return null;
			}
                }
 

                    
                return null;
        }
}
