package p2p;

import client.BTClient;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.bluetooth.RemoteDevice;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.obex.HeaderSet;
import javax.obex.Operation;
import javax.obex.ResponseCodes;
import javax.obex.ServerRequestHandler;


import javax.microedition.io.file.*;
import shared.gui.AlertDisplayer;

import shared.header.HeaderSets;
import shared.header.Types;

public class FileSender extends ServerRequestHandler {

    public static final int CHUNK_SIZE = 1024;
    String remoteClientAddr;
    BTClient btClient;

    public FileSender(BTClient btClient) {
        this.btClient = btClient;
    }

    public int onConnect(HeaderSet request, HeaderSet reply) {
        try {
            remoteClientAddr = (String) request.getHeader(HeaderSet.NAME);
            //btClient.gui.showAlert("Connected", remoteClientAddr, btClient.gui.getFileBrowser());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseCodes.OBEX_HTTP_OK;
    }

    public int onGet(Operation op) {
        try {
            HeaderSet hdr = op.getReceivedHeaders();
            if (hdr.getHeader(HeaderSets.QUERY_TYPE).equals(Types.QUERY_GET_FILE)) {

                String location = (String) hdr.getHeader(HeaderSets.FILE_LOCATION);
               // AlertDisplayer.showAlert("Send", "[client:] Received REquest for " + location,
                 //       this.btClient.gui.getDisplay() , this.btClient.gui.getHubConnection() );
                FileConnection fc =
                        (FileConnection) Connector.open("file:///" + location);

                if (!fc.exists()) {
                    throw new IOException("File does not exist");
                }

                InputStream fis = fc.openInputStream();
                DataOutputStream out = op.openDataOutputStream();
                int size = (int) fc.fileSize();

                byte[] b = new byte[CHUNK_SIZE];
                int length;
                while ((length = fis.read(b)) > 0) {
                    out.write(b, 0, length);
                }
                fis.close();
                out.close();

                fc.close();
                op.close();
                System.out.println("sent: " + new String(b));
                btClient.gui.showAlert("Connected", "from " + location + "sent" + (new String(b)), btClient.gui.getHubConnection());

                
                //AlertDisplayer.showAlert("Sent", "File Has been sent", btClient.gui.getDisplay(), btClient.gui.getHubConnection());

                System.out.println("[client:] File Sent");
            }

        } catch (IOException e) {
            System.out.println("Error");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseCodes.OBEX_HTTP_OK;
    }
}
