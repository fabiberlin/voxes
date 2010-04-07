package shared.discovery;
import java.util.*;

import javax.bluetooth.*;

public class ServiceDiscoverer implements DiscoveryListener {

	UUID[] uuidSet;
	int[] attrSet;
    

	public Vector serviceRecord;
	public String connectionURL;
	public RemoteDevice remoteDevice;
	public LocalDevice localDevice;

	/* Initialize a service discoverer object to search for records with uuid set on a 
	 * remote device
	 */
	public ServiceDiscoverer(UUID[] uuidSet, RemoteDevice device, LocalDevice localDevice){
                serviceRecord = new Vector();
		this.localDevice = localDevice;
		this.uuidSet = uuidSet;
		this.remoteDevice = device;
		
	}
	
	public Vector getServiceRecords()
	{
		synchronized(this)
		{
			try
			{
				DiscoveryAgent agent = localDevice.getDiscoveryAgent();
				attrSet = null;
				agent.searchServices(attrSet, uuidSet, remoteDevice, this);
				this.wait();
			}
			catch(InterruptedException e)
			{
				System.out.println("InterruptedException at 45 in ServiceDiscoverer");
				e.printStackTrace();
			}
			catch (BluetoothStateException e) {
				System.out.println("BluetoothStateException at 48 in ServiceDiscoverer");
				e.printStackTrace();
			}
			
		}
                System.out.println(serviceRecord.size());
		return serviceRecord;
	}

   
	public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
        
		for(int i = 0; i < servRecord.length; i++) {
			serviceRecord.addElement(servRecord[i]);
		}
	}
    


	public void serviceSearchCompleted(int transID, int respCode) {

		String searchStatus = null;

		if (respCode == DiscoveryListener.SERVICE_SEARCH_DEVICE_NOT_REACHABLE) {
			searchStatus = "Device not reachable";
		}
		else if (respCode == DiscoveryListener.SERVICE_SEARCH_NO_RECORDS) {
			searchStatus = "Service not available";
		}
		else if (respCode == DiscoveryListener.SERVICE_SEARCH_COMPLETED) {
			searchStatus = "Service search completed";
		}
		else if (respCode == DiscoveryListener.SERVICE_SEARCH_TERMINATED) {
			searchStatus = "Service search terminated";
		}
		else if (respCode == DiscoveryListener.SERVICE_SEARCH_ERROR) {
			searchStatus = "Service search error";
		}
		synchronized (this) {
			this.notify();
		}
		System.out.println("[client:] " + searchStatus);
	//	client.updateStatus("[client:] " + searchStatus);
	}

    
    public void inquiryCompleted(int discType){}
    
    public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod){}
    
}
