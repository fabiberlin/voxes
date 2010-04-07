/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.util.Vector;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.obex.ClientSession;
import shared.discovery.DeviceDiscoverer;
import shared.discovery.ServiceDiscoverer;

/**
 *
 * @author V.Amarnath Raju
 */
public class ClientSessionRecord {

    public ClientSession session;
    public String btAddress;

    public ClientSessionRecord(ClientSession session, String btAddress) {
        this.session = session;
        this.btAddress = btAddress;
    }

    public static ClientSessionRecord search(String btAddr, Vector sessions) {
        if (sessions == null) {
            return null;
        }
        for (int i = 0; i < sessions.size(); i++) {
            ClientSessionRecord sess = (ClientSessionRecord) sessions.elementAt(i);
            if (sess.btAddress.equals(btAddr)) {
                return sess;
            }
        }
        return null;
    }

    public static ClientSessionRecord get(String btAddr, Vector sessions, String uuid) {

        ClientSessionRecord sRec =  ClientSessionRecord.search(btAddr, sessions);
        if (sRec != null) {
            return sRec;
        }
        try {
            String addr[] = {btAddr};
            DeviceDiscoverer devD = new DeviceDiscoverer(addr, LocalDevice.getLocalDevice());
            Vector devices = devD.getDiscoveredDevices();
            if (!devices.isEmpty()) {
                System.out.println("[client:] To download from " + devices.elementAt(0));
                // search for the service in the device(s) detected
                UUID[] uuidSet = new UUID[1];
                uuidSet[0] = new UUID(uuid, false);
                System.out.println("[client:] Some devices were found ");

                ServiceDiscoverer servD = new ServiceDiscoverer(uuidSet,
                        (RemoteDevice) devices.elementAt(0), LocalDevice.getLocalDevice());



                Vector servRec = servD.getServiceRecords();
                System.out.println(servRec);
                if (!servRec.isEmpty()) {
                    ClientSession conn = (ClientSession) Connector.open(((ServiceRecord) servRec.elementAt(0)).getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false));
                    ClientSessionRecord retVal = new ClientSessionRecord(conn, btAddr);
                    sessions.addElement(retVal);
                    return retVal;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (IOException e) {
            return null;
        }
    }
}
