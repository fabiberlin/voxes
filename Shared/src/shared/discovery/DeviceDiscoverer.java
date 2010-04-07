package shared.discovery;

import java.util.*;
import javax.bluetooth.*;

import shared.helpers.StringOp;





public class DeviceDiscoverer implements DiscoveryListener{
	
	/*public client.PeerListRequester parent;*/
	public Vector remoteDevices = new Vector();
	public String[] btAddresses;
	public DiscoveryAgent discoveryAgent;
	public LocalDevice localDevice;
	
	public DeviceDiscoverer(String[] btAddresses, LocalDevice localDevice/*, client.PeerListRequester parent*/){
		this.btAddresses = btAddresses;
		this.localDevice = localDevice;
		try {
			discoveryAgent = localDevice.getDiscoveryAgent();
		//	client.updateStatus("[client:] LocalDevice properties: " + localDevice.getFriendlyName() + " (" + localDevice.getBluetoothAddress() + ")");
		//	client.updateStatus("[client:] Searching for Bluetooth devices in the vicinity...");

		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public Vector getDiscoveredDevices()
	{
		synchronized(this)
		{
			
			try {
				discoveryAgent.startInquiry(DiscoveryAgent.GIAC, this);
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return remoteDevices;
	}
	public void deviceDiscovered(RemoteDevice remoteDevice, DeviceClass cod) {

		try{
			if(StringOp.find(btAddresses, remoteDevice.getBluetoothAddress()) != -1) {
	            remoteDevices.addElement(remoteDevice);
			}
//			client.updateStatus("[client:] New device discovered : "  + remoteDevice.getFriendlyName(true)+ " (" + remoteDevice.getBluetoothAddress() + ")" );

		} catch(Exception e){
			e.printStackTrace();
		}	

	}

    
	public void inquiryCompleted(int discType) {
		String inqStatus = null;
        
		
		if (discType == DiscoveryListener.INQUIRY_COMPLETED) {
			inqStatus = "[client:] Inquiry completed";            
		} else if (discType == DiscoveryListener.INQUIRY_TERMINATED) {
			inqStatus = "[client:] Inquiry terminated";
		} else if (discType == DiscoveryListener.INQUIRY_ERROR) {
			inqStatus = "[client:] Inquiry error";
		}
		
		System.out.println(inqStatus);
		
        synchronized(this) {
        	this.notify();
        }
		//client.updateStatus(inqStatus);
		//client.serviceButton.setEnabled(true);
	//	client.deviceButton.setEnabled(false);
	}

    public void servicesDiscovered(int transID, ServiceRecord[] servRecord){}
    
    public void serviceSearchCompleted(int transID, int respCode) {}
   

}
