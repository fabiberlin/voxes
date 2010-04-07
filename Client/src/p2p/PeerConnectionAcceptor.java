package p2p;

import client.BTClient;
import java.io.IOException;

import javax.microedition.io.Connector;
import javax.obex.SessionNotifier;

public class PeerConnectionAcceptor extends Thread
{
	public static String P2P_UUID = "00FF00FF00FF00FF00FF00FF00FF00F2";
	String uuid;
	boolean stop;
	SessionNotifier sn;
	BTClient btClient;

	public PeerConnectionAcceptor(BTClient client)

        {
            this.btClient = client;
		this.uuid = P2P_UUID;
		stop = false;
		//this.start();
	}
	
	/*
	PeerConnectionAcceptor(String UUID)
	{ 
		this.uuid = UUID;
		stop = false;
		this.start();
	}
	*/
	
	public void run()
	{
		try {
			String url = "btgoep://localhost:" + uuid + ";authenticate=false;master=false;encrypt=false";
			sn = (SessionNotifier) Connector.open(url);
                       // btClient.gui.showAlert("opened", url, btClient.gui.getFileBrowser());
			while(true)
			{
				sn.acceptAndOpen(new FileSender(btClient));	// create a new object to enable || downloads
                             //   btClient.gui.showAlert("accepted", url, btClient.gui.getFileBrowser());
                        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
                    btClient.gui.showAlert("error", e.getMessage() +"btgoep://localhost:" + uuid + ";authenticate=false;master=false;encrypt=false", btClient.gui.getFileBrowser());
			e.printStackTrace();
			stop = true;
			return;
		}
	}
	
	public void stopAcceptor()
	{
		stop = true;
		try {
			sn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
