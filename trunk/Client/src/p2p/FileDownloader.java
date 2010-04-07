package p2p;

import client.BTClient;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;

import shared.discovery.*;
import java.io.OutputStream;


import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.obex.ClientSession;
import javax.obex.HeaderSet;
import javax.obex.Operation;

import shared.comm.FileSink;
import shared.gui.AlertDisplayer;
import shared.header.HeaderSets;
import shared.header.Types;

public class FileDownloader {

    String fromAddr;
    String filePath;
    BTClient client;
    LocalDevice localDevice;
    //  String destFilePath;
    // Vector servRec;

    public FileDownloader(String fromAddr, String filePath, LocalDevice localDevice, BTClient client) {
        this.fromAddr = fromAddr;
        this.filePath = filePath;
        this.localDevice = localDevice;
        this.client = client;
    }

    public String  downloadFileTo(final String destinationFilePath) {
        //client.gui.showAlert("Downloadng to", destinationFilePath, client.gui.getWaitScreen());

        String str = "";
        int i = 0;
        try {
            //client.hubConn.close();
            // search for the device with given bluetooth address
            String addr[] = {fromAddr};
            //client.gui.showAlert("device search", fromAddr +" vs " + client.localDevice.getBluetoothAddress(), client.gui.getFileBrowser());
            DeviceDiscoverer devD = new DeviceDiscoverer(addr, localDevice);
            Vector devices = devD.getDiscoveredDevices();
            if (!devices.isEmpty()) {
                i++;
                System.out.println("[client:] To download from " + devices.elementAt(0));
                str = str + devices.elementAt(0) + ":";
                // search for the service in the device(s) detected
                UUID[] uuidSet = new UUID[1];
		uuidSet[0] = new UUID(PeerConnectionAcceptor.P2P_UUID, false);
                System.out.println("[client:] Some devices were found ");

                ServiceDiscoverer servD = new ServiceDiscoverer(uuidSet,
                        (RemoteDevice) devices.elementAt(0), localDevice);



                Vector servRec = servD.getServiceRecords();
                System.out.println(servRec);
                i++;
                if (!servRec.isEmpty()) {
                    i++;

                    //client.gui.showAlert("record", "record found " + servRec.elementAt(0), client.gui.getWaitScreen());
                    /*
                    System.out.println("[client:] Corresponding Service Found");

                    //AlertDisplayer.showAlert("found", "Service Found",
                      //      client.gui.getDisplay(), client.gui.getSearchResults());

                     *
                     */
                    str += servRec.elementAt(0);
                    ClientSession conn = (ClientSession) Connector.open(((ServiceRecord) servRec.elementAt(0)).getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false));


                    i++;

                    HeaderSet hdr = conn.createHeaderSet();
                    hdr.setHeader(HeaderSet.NAME, localDevice.getBluetoothAddress());
                    conn.connect(hdr);
                    i++;
                    System.out.println("[client:] Sending Request for " + filePath);
                    hdr.setHeader(HeaderSets.QUERY_TYPE, Types.QUERY_GET_FILE);
                    hdr.setHeader(HeaderSets.FILE_LOCATION, filePath);

                    Operation op = conn.get(hdr);
                    i++;
                    System.out.println("[client:] Data Received");

                    //System.out.println(new String(arr));
                    //String url = "file:///" + destinationFilePath;
                    //System.out.println("[client:] Creating " + url);
                    /*
                    FileConnection fc = (FileConnection)Connector.open(url);

                    if (!fc.exists()) {
                    fc.create();

                    System.out.println("[client:] File Created " + url);
                    fc.setWritable(true);
                    OutputStream os = fc.openOutputStream();
                    os.write(arr);
                    os.close();

                    System.out.println("[client:] Data written to File");
                    }
                    else {
                    System.out.println("[client:] File Already Exists");
                    }
                    fc.close();*/

                    String str2 = FileSink.consumeDataToFile(op, destinationFilePath);
                    //str += ":ds::" + str2;
                    i++;

                    op.close();

                    //conn.disconnect(null);
                    return str + ":" + i + ":" + destinationFilePath;

                } else {
                    return str + ":" + (i+500);
                }
            } else {
                return str + ":" + (i + 800);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return str + ":" + (i + 100) + e.getMessage();
        }
        // }// end of run
        // }).start();

    }

    public String downloadPreview(String string) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
