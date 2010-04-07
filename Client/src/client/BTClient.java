package client;

import p2p.*;
import java.io.IOException;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.LocalDevice;
import javax.obex.ClientSession;
import javax.obex.HeaderSet;

import shared.discovery.DeviceDiscoverer;
import shared.discovery.ServiceDiscoverer;
import gui.GUIClient;
import java.util.Vector;
import shared.gui.AlertDisplayer;
import shared.p2p.ChatService;

public class BTClient {

    public GUIClient gui;
    public MusicSearcher musicSearcher;
    public PeerListRequester peerListRequester;
    public DeviceDiscoverer deviceDiscoverer;
    public ServiceDiscoverer serviceDiscoverer;
    //public DataTransaction dataTransaction;
    public HubConnector hubConnector;
    public ClientSession hubConn;
    public Publisher publisher;
    public PeerConnectionAcceptor acceptor;
    public LocalDevice localDevice;
    public ChatService chatService;


    public Vector peerDownloadConn = new Vector();
    public Vector peerChatConn = new Vector();


    public BTClient(GUIClient gui) {
        this.gui = gui;

        try {
            this.localDevice = LocalDevice.getLocalDevice();
        } catch (BluetoothStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        /* Connect to the Hub */
        hubConnector = new HubConnector(this);
        hubConn = hubConnector.connect();
        acceptor = new PeerConnectionAcceptor(this);
        musicSearcher = new MusicSearcher(this);
        publisher = new Publisher(this);
        peerListRequester = new PeerListRequester(this);
        ChatProcessor chatProcessor = new ChatProcessor(this);
        try {
            chatService = new ChatService(chatProcessor);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        acceptor.start();
        if (hubConn == null) {
            AlertDisplayer.showAlert("Error", "Couldn't Connect to Hub", gui.getDisplay(), gui.getEnterBTAddressForm());
        } else {
            this.connect();
            this.disconnect();
            
        }
    }

    public void connect() {
        HeaderSet hdr = hubConn.createHeaderSet();
        hdr.setHeader(HeaderSet.NAME, localDevice.getBluetoothAddress());
        hdr.setHeader(HeaderSet.DESCRIPTION, localDevice.getFriendlyName());
        try {
            hubConn.connect(hdr);
        } catch (IOException e) {// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void disconnect() {
        new Thread(new Runnable() {

            public void run() {

                try {
                    hubConn.disconnect(null);

                    System.out.println("disconnected");

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }
}
