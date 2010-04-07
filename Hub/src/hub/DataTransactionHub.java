package hub;

import javax.obex.*;


import shared.record.*;
import shared.comm.FileSink;
import shared.header.HeaderSets;
import shared.header.Types;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.*;
import shared.gui.AlertDisplayer;

public class DataTransactionHub extends ServerRequestHandler {

    private Vector clientList;
    private MusicData store;
    public BTHub parent;
    public String clientBluetoothAddress;
    public String clientFriendlyName;

    DataTransactionHub(BTHub parent) {
        this.parent = parent;
        clientList = parent.clientList;
        store = new MusicData();
    }

    public int onConnect(HeaderSet request, HeaderSet reply) {
        try {
            clientBluetoothAddress = request.getHeader(HeaderSet.NAME).toString();
            clientFriendlyName = request.getHeader(HeaderSet.DESCRIPTION).toString();
            //System.out.println("[hub :toothAddress);
            parent.gui.getLogList().append("[hub:] Client Address " + clientBluetoothAddress, null);

            //AlertDisplayer.showAlert("Client Connected" , clientBluetoothAddress + clientFriendlyName , this.parent.gui.getDisplay() , this.parent.gui.getList());
            parent.addClient(clientBluetoothAddress, clientFriendlyName);
            return ResponseCodes.OBEX_HTTP_OK;
        } catch (IOException e) {
            System.err.println("Exception in DataTransaction.onConnect()");
            parent.gui.getLogList().append("[hub:] Error, Exception in onConnect()", null);
            AlertDisplayer.showAlert("Error", "Exception in DataTransaction.onConnect()", this.parent.gui.getDisplay(), this.parent.gui.getList());
            return ResponseCodes.OBEX_HTTP_BAD_REQUEST;
        }

    }

    public int onPut(Operation op) {

        //synchronized(this) {
        try {
            HeaderSet hdr = op.getReceivedHeaders();

            String str = (String) hdr.getHeader(HeaderSets.REQUEST_TYPE);
            if (str.equals(Types.REQUEST_PUBLISH_FILE)) {
                //System.out.println("Reached here");
                String location = (String) hdr.getHeader(HeaderSets.FILE_LOCATION);
                String btAddress = (String) hdr.getHeader(HeaderSet.NAME);
                byte[] b = FileSink.consumeData(op);
                //System.out.println("address " + btAddress);
                //System.out.println("Received" + new String(b));
                MusicFile mf = MusicFile.fromString(new String(b));
                System.out.println("[hub :" + mf.title + ":" + mf.album + ":" + mf.artist);
                store.addFile(mf, new FileLocation(ClientRecord.searchClientRecord(btAddress, clientList), location));
                store.printData();
                parent.gui.getLogList().append("[hub:] Published " + mf.title + ":" + mf.album + ":" + mf.artist, null);
                AlertDisplayer.showAlert("Published", mf.title + ":" + mf.album + ":" + mf.artist, this.parent.gui.getDisplay(), this.parent.gui.getList());
                op.close();
            }
        } catch (IOException e) {
            System.err.println("DataTransaction.onPut()");
            e.printStackTrace();
        }
        //}

        return ResponseCodes.OBEX_HTTP_OK;
    }

    public int onGet(Operation op) {

        try {
            System.out.println("[hub:] OnGet Called");
            //The server has received a GET request for client.
            HeaderSet hdr = op.getReceivedHeaders();
            String qType = (String) hdr.getHeader(HeaderSets.QUERY_TYPE);
            //AlertDisplayer.showAlert("Search", "Onget call " , this.parent.gui.getDisplay() , this.parent.gui.getList());

            if (qType.equals(Types.QUERY_GET_LISTS)) {


                String qString = (String) hdr.getHeader(HeaderSets.QUERY_STRING);
                System.out.println("[hub:] Received a request for the file " + qString);
                //AlertDisplayer.showAlert("Search", "Searching for " + qString , this.parent.gui.getDisplay() , this.parent.gui.getList());

                /* Get the corresponding locations, conver to String and send */
                Vector locations = store.searchFile(qString);

                System.out.println("[hub:] Found Locations " + locations);
                String clientList1 = FileLocation.toStringV(locations);

                byte[] fileAsBytes = clientList1.getBytes();
                DataOutputStream out = op.openDataOutputStream();
                out.write(fileAsBytes, 0, fileAsBytes.length);
                System.out.println("[hub: ] Data Sent to client");

                out.close();
                op.close();

                AlertDisplayer.showAlert("Sent", "Data Sent " + clientList1, this.parent.gui.getDisplay(), this.parent.gui.getList());

                return ResponseCodes.OBEX_HTTP_OK;
            }
            if (qType.equals(Types.QUERY_GET_PEER_LIST)) {
                System.out.println("[hub:] Peer List Requested");
                String cL = ClientRecord.toStringV(clientList);
                System.out.println(cL);

                byte[] fileAsBytes = cL.getBytes();
                DataOutputStream out = op.openDataOutputStream();
                out.write(fileAsBytes, 0, fileAsBytes.length);
                System.out.println("[hub:] Data Sent to client");
                out.close();

                return ResponseCodes.OBEX_HTTP_OK;
            }

            return ResponseCodes.OBEX_HTTP_OK;

        } catch (IOException e) {
            System.out.println("[hub: ] Exception in toGet");
            System.out.println(e.getMessage());
            return ResponseCodes.OBEX_HTTP_BAD_METHOD;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return ResponseCodes.OBEX_HTTP_BAD_METHOD;
        }


    }

    // TODO
    // remove from FileList
    public void onDisconnect(HeaderSet req, HeaderSet resp) {
        if(req == null)
            return;
        try {
            //clientBluetoothAddress = req.getHeader(HeaderSet.NAME).toString();
            Object reqType = req.getHeader(HeaderSets.REQUEST_TYPE);
            if(reqType == null)
                return;
            if (reqType.toString().equals(Types.REQUEST_DISCONNECT)) {
                parent.removeClient(clientBluetoothAddress);
                //AlertDisplayer.showAlert("Disconnected", "btADDR : " +clientBluetoothAddress,parent.gui.getDisplay(),parent.gui.getList());
            }
            
        } catch (IOException e) {
            System.err.println("Exception in DataTransaction.onConnect()");
            
        }
    }
}
