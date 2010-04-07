package client;

import java.io.IOException;
import java.util.Vector;

import javax.obex.ClientSession;
import javax.obex.HeaderSet;
import javax.obex.Operation;


import shared.record.*;
import shared.comm.FileSink;
import shared.gui.AlertDisplayer;
import shared.header.HeaderSets;
import shared.header.Types;

public class MusicSearcher {

    public BTClient client;
    public ClientSession hubConn;

    MusicSearcher(BTClient client) {
        this.client = client;
        this.hubConn = client.hubConn;
    }

    public Vector searchMusicFiles(String queryString) {
        //AlertDisplayer.showAlert("Search", "Searching For " + queryString, client.gui.getDisplay(), client.gui.getForm1());

        Vector fileLocations;

        /* First Connect to the hub */
        try {

            //client.connect();
            HeaderSet hdr = hubConn.createHeaderSet();
            hdr.setHeader(HeaderSet.NAME, client.localDevice.getBluetoothAddress());
            hdr.setHeader(HeaderSet.DESCRIPTION, client.localDevice.getFriendlyName());
            hubConn.connect(hdr);
            //AlertDisplayer.showAlert("connected", "Connected ", client.gui.getDisplay(), client.gui.getForm1());

            HeaderSet hdr2 = hubConn.createHeaderSet();
            hdr2.setHeader(HeaderSets.QUERY_TYPE, Types.QUERY_GET_LISTS);
            hdr2.setHeader(HeaderSets.QUERY_STRING, queryString);


            Operation op = hubConn.get(hdr2);

            System.out.println("[client]: in searchMusicFile");
            //AlertDisplayer.showAlert("connected", "Results found " + searchRes , client.gui.getDisplay(), client.gui.getSearchResults());


            byte[] arr = FileSink.consumeData(op);

            // read the byte data
            String searchRes = new String(arr);
            System.out.println(searchRes);
            //AlertDisplayer.showAlert("Search", "Search Results " + searchRes,
            //        client.gui.getDisplay(), client.gui.getSearchResults());

            fileLocations = FileLocation.fromStringV(searchRes);

            //op.close();




            //

            System.out.println(fileLocations);

            op.close();


            //return fileLocations;


            // client.disconnect();

            //AlertDisplayer.showAlert("connected", "Results found Data", client.gui.getDisplay(), client.gui.getSearchResults());

            return fileLocations;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            client.gui.showAlert("errorblah blah", e.getMessage(), client.gui.getForm1());

            e.printStackTrace();
        }

        return null;
    }
}
	

