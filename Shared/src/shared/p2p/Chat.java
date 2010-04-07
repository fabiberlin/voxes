/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shared.p2p;

import shared.discovery.DeviceDiscoverer;
import shared.discovery.ServiceDiscoverer;
import shared.header.HeaderSets;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;
import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.obex.ClientSession;
import javax.obex.HeaderSet;
import javax.obex.Operation;

/**
 *
 * @author Anshuman
 */
public class Chat
{
    LocalDevice localDevice;
    String peerBTAddress;
    String connectionURL;
    ClientSession client;

    /*
     * Use this constructor if the RemoteDevice object for the target is unavailable
     * Creates a new Chat session with device with given blue tooth adddress
     * throws IOException if unable to open a session
     */
    public Chat(String peerBTAddress) throws IOException
    {
        this.peerBTAddress = peerBTAddress;
        try {
            localDevice = LocalDevice.getLocalDevice();
            String [] addr = {peerBTAddress};
            DeviceDiscoverer devD = new DeviceDiscoverer(addr, localDevice);
            Vector dev =devD.getDiscoveredDevices();
            if(!dev.isEmpty())
            {
                UUID[] arr = {new UUID(ChatService.UUID_CHAT,false)};
                ServiceDiscoverer servD = new ServiceDiscoverer(arr, (RemoteDevice)dev.elementAt(0), localDevice);
                Vector serRec = servD.getServiceRecords();
                if(!serRec.isEmpty())
                {
                    connectionURL = ((ServiceRecord) serRec.elementAt(0)).getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT,false);
                    client = (ClientSession) Connector.open(connectionURL);
                }
                else
                    throw new IOException("unable to open chat session with " + peerBTAddress);
            }
            else
                throw new IOException("unable to find " + peerBTAddress);
        } catch (BluetoothStateException ex) {
            throw new IOException("unable to open chat session with " + peerBTAddress);
        }
    }

    /*
     * Use  this constructor if the RemoteDevice object is available from a previous
     * device search (eg. chat with hub)
     */
    public Chat(RemoteDevice rDev) throws IOException
    {
        UUID[] arr = {new UUID(ChatService.UUID_CHAT,false)};
        ServiceDiscoverer servD = new ServiceDiscoverer(arr, rDev, localDevice);
        Vector serRec = servD.getServiceRecords();
        if(!serRec.isEmpty())
        {
            connectionURL = ((ServiceRecord) serRec.elementAt(0)).getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
            client = (ClientSession) Connector.open(connectionURL);
        }
    }

    /*
     * send a chat message to the target device
     */
    public void sendChatMessage(String fromAlias, String msg) throws IOException
    {
        client.connect(null);
        HeaderSet hdr = client.createHeaderSet();
        hdr.setHeader(HeaderSets.CLIENT_ID, peerBTAddress);
        hdr.setHeader(HeaderSets.CLIENT_ALIAS, fromAlias);
        hdr.setHeader(HeaderSets.CHAT_BODY, msg);
        Operation op = client.put(hdr);
        DataOutputStream dataOut = op.openDataOutputStream();
        dataOut.write(msg.getBytes());
        dataOut.close();
        op.close();
        client.disconnect(null);
    }
}
