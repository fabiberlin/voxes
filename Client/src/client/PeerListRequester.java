package client;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;
import javax.obex.ClientSession;
import javax.obex.HeaderSet;
import javax.obex.Operation;
import shared.header.HeaderSets;
import shared.header.Types;
import shared.record.ClientRecord;



public class PeerListRequester {
	
	public BTClient client;
        public static final int CHUNK_SIZE = 1024;


	PeerListRequester ( BTClient client )
	{
		this.client = client;
	}

        public Vector getMemberList() {


                ClientSession hubConn = client.hubConn;
                try{
                    client.connect();
                    HeaderSet hdr = hubConn.createHeaderSet();
                    hdr.setHeader(HeaderSets.QUERY_TYPE, Types.QUERY_GET_PEER_LIST);

                    System.out.println("[client:] Requesting Member List");
                    Operation op = hubConn.get(hdr);
                    DataInputStream in = op.openDataInputStream();


                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    byte[] data = new byte[CHUNK_SIZE];
                    int bytesRead = 0;
                    bytesRead = in.read(data);
                    while(bytesRead >= 0)
                    {
                            out.write(data, 0, bytesRead);
                            bytesRead = in.read(data);
                    }

                    out.close();
                    in.close();
                    op.close();
                    client.disconnect();

                    String clients = new String(out.toByteArray());
                    System.out.println("[client:] String Received " + clients);

                    System.out.println(clients);

                    Vector client_List = ClientRecord.fromStringV(clients);
                    return client_List;

                }
                catch(IOException e) {
                    e.printStackTrace();
                    return null;
                }


        }


}
