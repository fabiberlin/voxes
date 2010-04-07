package hub;

import java.util.Vector;

import gui.GUIHub;
import javax.bluetooth.*;
import javax.obex.*;
import javax.microedition.io.*;
import shared.record.ClientRecord;

public class BTHub{
	
	public gui.GUIHub gui;
	
	public final String uuid = "00FF00FF00FF00FF00FF00FF00FF00FF";
	
	public SessionNotifier sn;
	public DataTransactionHub dataTransaction;
	
	public Vector clientList;
	
	public BTHub ( GUIHub gui ){
		try {
			System.out.println("Constructor Called " + LocalDevice.getLocalDevice().getBluetoothAddress());
		} catch (BluetoothStateException e) {
			e.printStackTrace();
		}
		this.gui = gui;	
		this.clientList = new Vector();
		this.startAcceptingConnections();
	}
	
	
	public void startAcceptingConnections () {
            new Thread(new Runnable() {
                public void run() {
                    try {
                            String url = "btgoep://localhost:" + uuid + ";authenticate=false;master=false;encrypt=false";
                            sn = (SessionNotifier) Connector.open(url);
                            dataTransaction = new DataTransactionHub(BTHub.this);

                            System.out.println("[hub:] BTAddress " + LocalDevice.getLocalDevice().getBluetoothAddress());
                            
                            // TODO maintain connection nos.
                            while (true) {
                                    System.out.println("[hub:] Opening Connection");
                                    sn.acceptAndOpen(dataTransaction);
                                    //System.out.println("[hub:] Client Connected");
                                    gui.getLogList().append("[hub:] Client Connected", null);
                            }
                    }
                    catch(Exception e) {
                           //System.err.println("Received error in startAcceptingConnections ()");
                             gui.getLogList().append("[hub:] Received error in startAcceptingConnections ()", null);
                    }
                }
            }).start();
            
			
	}

        public void addClient(String bluetoothAddress , String friendlyName){
            ClientRecord record = new ClientRecord(bluetoothAddress , friendlyName , true);
            ClientRecord cl = ClientRecord.searchClientRecord(bluetoothAddress, clientList);
            if( cl != null)
                cl.isConnected = true;
            else{
                clientList.addElement(record);
            }
        }

    public void removeClient(String clientBluetoothAddress) {
        ClientRecord c = ClientRecord.searchClientRecord(clientBluetoothAddress, clientList);
        if (c != null) {
            c.isConnected = false;
        }
    }
}
