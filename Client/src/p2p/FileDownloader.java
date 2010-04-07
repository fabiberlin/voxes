package p2p;

import client.BTClient;
import client.ClientSessionRecord;
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

    public String downloadFileTo(final String destinationFilePath) {
        //client.gui.showAlert("Downloadng to", destinationFilePath, client.gui.getWaitScreen());

        ClientSessionRecord csr = ClientSessionRecord.get(fromAddr, client.peerDownloadConn, PeerConnectionAcceptor.P2P_UUID);
        String str = "";
        int i = 0;
        if (csr != null) {
            try {
                ClientSession conn = csr.session;

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

                String str2 = FileSink.consumeDataToFile(op, destinationFilePath);

                op.close();
                conn.disconnect(hdr);
                i++;
                //conn.disconnect(null);
                return str + ":" + i + ":" + destinationFilePath;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return str + ":" + (i + 100) + e.getMessage();
            }
        } else {
            return "Error!!";
        }
    }
}
