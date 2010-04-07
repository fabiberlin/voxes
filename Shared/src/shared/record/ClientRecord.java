package shared.record;

import java.util.*;

public class ClientRecord {
	public String bluetoothAddress;
	public  String friendlyName;
	public boolean isConnected;

	public ClientRecord ( String bluetoothAddress,String friendlyName, boolean isConnected)
	{
		this.bluetoothAddress = bluetoothAddress;
                this.friendlyName = friendlyName;
		this.isConnected = isConnected;
	}
	
	public void disconnect(){
		isConnected = false;
	}
	
	
	public static ClientRecord fromString ( String str )
	{
                int pos = str.indexOf("$");
                String btAddress = str.substring(0, pos);
                String friendlyName = str.substring(pos + 1);
                friendlyName = friendlyName.substring(0, friendlyName.indexOf("$"));
		ClientRecord record = new ClientRecord(btAddress,friendlyName, true);
		return record;
	}
	
	public static String toString ( ClientRecord record )
	{
            return record.bluetoothAddress + "$" + record.friendlyName + "$" + record.isConnected;
	}


        public static String toStringV ( Vector clientList )
        {
            String str = "";
            int i = 0;
            for (i = 0; i < clientList.size(); i++) {
                str += ClientRecord.toString((ClientRecord) clientList.elementAt(i));
                str += "#";
            }
            return str;

        }

        public static Vector fromStringV ( String str )
        {
            int index;
            Vector v = new Vector();
            while ((index = str.indexOf("#")) != -1) {
                ClientRecord cR = ClientRecord.fromString(str.substring(0, index));
                str = str.substring(index+1);
                v.addElement(cR);
            }
            return v;
        }
	
	public static ClientRecord searchClientRecord( String newbluetoothAddress ,Vector clientList){
		for(int i = 0 ; i < clientList.size(); i++){
			if(((ClientRecord)clientList.elementAt(i)).bluetoothAddress.equals(newbluetoothAddress))return ((ClientRecord)clientList.elementAt(i));
		}
		return null;
	}
	
	/* To add a new client , returns false if user is already present */
	/*public boolean addClient (String newClientID){
		if(clientID.contains(newClientID))return false;
		else{
			clientID.addElement(newClientID);
			if(clientID.capacity() == clientID.size())clientID.ensureCapacity(clientID.size()+1);
			return true;
		}
	}*/
	
	/* To remove a client , returns false if user is already removed */
	/*public boolean removeClient(String oldClientID){
		if(!clientID.contains(oldClientID))return false;
		else{
			clientID.removeElement(oldClientID);
			return true;
		}
	}*/
	

}
