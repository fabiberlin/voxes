/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import client.BTClient;
import client.HubConnector;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

import javax.microedition.io.file.*;
import javax.obex.HeaderSet;
import org.netbeans.microedition.lcdui.WaitScreen;
import org.netbeans.microedition.util.SimpleCancellableTask;

import p2p.FileDownloader;
import shared.gui.AlertDisplayer;
import shared.header.HeaderSets;
import shared.header.Types;
import shared.p2p.Chat;
import shared.record.ClientRecord;
import shared.record.FileLocation;

/**
 * @author Varun Srivastava
 */
public class GUIClient extends MIDlet implements CommandListener {

    public Vector fileLocation;
    public Vector peerListVector;
    private boolean midletPaused = false;
    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Command exitApplication;
    private Command screenCommand;
    private Command removeSong;
    private Command itemCommand;
    private Command screenCommand1;
    private Command screenCommand2;
    private Command backChatSendingFormComand;
    private Command backCommand1;
    private Command okCommand1;
    private Command backCommand2;
    private Command backCommand3;
    private Command viewFileList;
    private Command searchNet;
    private Command backCommand4;
    private Command sendIM;
    private Command okCommand4;
    private Command backCommand5;
    private Command uploadSong;
    private Command sendMessageCommand;
    private Command Share;
    private Command backCommand6;
    private Command okCommand;
    private Command backCommand8;
    private Command okCommand2;
    private Command backCommand7;
    private Command backCommand9;
    private Command downloadCommand;
    private Command connectCommand;
    private Command backChatListCommand;
    private List HubConnection;
    private List peerList;
    private Form form1;
    private StringItem stringItem1;
    private TextField textField;
    private Spacer spacer;
    private Spacer spacer1;
    private Form chatSendingForm;
    private TextField chatMessageField;
    private Spacer spacer2;
    private FileBrowser fileBrowser;
    private Form enterBTAddressForm;
    private TextField btAddressTextField;
    private List searchResults;
    private WaitScreen waitScreen;
    private WaitScreen Welcome;
    private List chatList;
    private SimpleCancellableTask task1;
    private SimpleCancellableTask task;
    private Ticker ticker;
    private Image image;
    private Ticker ticker1;
    private Image image1;
    //</editor-fold>//GEN-END:|fields|0|
    public BTClient client;
    Chat ch = null;

    /**
     * The GUIClient constructor.
     */
    public GUIClient() {
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    //</editor-fold>//GEN-END:|methods|0|
    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initilizes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        switchDisplayable(null, getEnterBTAddressForm());//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here

    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
        switchDisplayable(null, getHubConnection());//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
    }//GEN-BEGIN:|4-resumeMIDlet|2|
    //</editor-fold>//GEN-END:|4-resumeMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
        // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
    //</editor-fold>//GEN-END:|5-switchDisplayable|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == HubConnection) {//GEN-BEGIN:|7-commandAction|1|24-preAction
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|1|24-preAction
                // write pre-action user code here
                HubConnectionAction();//GEN-LINE:|7-commandAction|2|24-postAction
                // write post-action user code here
            } else if (command == backChatSendingFormComand) {//GEN-LINE:|7-commandAction|3|55-preAction
                // write pre-action user code here
                switchDisplayable(null, getEnterBTAddressForm());//GEN-LINE:|7-commandAction|4|55-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|5|189-preAction
        } else if (displayable == Welcome) {
            if (command == WaitScreen.FAILURE_COMMAND) {//GEN-END:|7-commandAction|5|189-preAction
                // write pre-action user code here
                switchDisplayable(null, getEnterBTAddressForm());//GEN-LINE:|7-commandAction|6|189-postAction
                // write post-action user code here
            } else if (command == WaitScreen.SUCCESS_COMMAND) {//GEN-LINE:|7-commandAction|7|188-preAction
                // write pre-action user code here
                switchDisplayable(null, getHubConnection());//GEN-LINE:|7-commandAction|8|188-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|9|180-preAction
        } else if (displayable == chatList) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|9|180-preAction
                // write pre-action user code here
                chatListAction();//GEN-LINE:|7-commandAction|10|180-postAction
                // write post-action user code here
            } else if (command == backChatListCommand) {//GEN-LINE:|7-commandAction|11|185-preAction
                // write pre-action user code here
                switchDisplayable(null, getHubConnection());//GEN-LINE:|7-commandAction|12|185-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|13|134-preAction
        } else if (displayable == chatSendingForm) {
            if (command == backChatSendingFormComand) {//GEN-END:|7-commandAction|13|134-preAction
                this.ch = null; // write pre-action user code here
                switchDisplayable(null, getPeerList());//GEN-LINE:|7-commandAction|14|134-postAction

            } else if (command == sendMessageCommand) {//GEN-LINE:|7-commandAction|15|116-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|16|116-postAction

                new Thread(
                        new Runnable() {

                            public void run() {
                                int index = GUIClient.this.peerList.getSelectedIndex();
                                ClientRecord rec = (ClientRecord) peerListVector.elementAt(index);
                                try {
                                    GUIClient.this.showAlert("INFO", GUIClient.this.getChatMessageField().getString(), GUIClient.this.getChatSendingForm());
                                    if (GUIClient.this.ch == null) {
                                        GUIClient.this.ch = new Chat(rec.bluetoothAddress);
                                    }
                                    GUIClient.this.ch.sendChatMessage(GUIClient.this.client.localDevice.getFriendlyName(),
                                            GUIClient.this.getChatMessageField().getString());
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                    GUIClient.this.showAlert("Erroorrr", "Can't open chat", GUIClient.this.getPeerList());
                                }

                            }
                        }).start();
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|17|165-preAction
        } else if (displayable == enterBTAddressForm) {
            if (command == connectCommand) {//GEN-END:|7-commandAction|17|165-preAction
                // write pre-action user code here
                /*
                new Thread(new Runnable() {

                public void run() {

                }
                }).start();
                 */
                switchDisplayable(null, getWelcome());//GEN-LINE:|7-commandAction|18|165-postAction
                // write post-action user code here
            } else if (command == exitApplication) {//GEN-LINE:|7-commandAction|19|178-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|20|178-postAction

                destroyApp(false);
                notifyDestroyed();
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|21|142-preAction
        } else if (displayable == fileBrowser) {
            if (command == FileBrowser.SELECT_FILE_COMMAND) {//GEN-END:|7-commandAction|21|142-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|22|142-postAction
                // write post-action user code here
            } else if (command == Share) {//GEN-LINE:|7-commandAction|23|147-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|24|147-postAction
                System.out.println("Share Command Specified");
                String name = fileBrowser.getToBeSharedFileName();
                String fileLocation = fileBrowser.getToBeSharedFileLocation();
                System.out.println("[client:] Sharing " + fileLocation);
                client.publisher.publish(fileLocation, name);




                // write post-action user code here
            } else if (command == backCommand7) {//GEN-LINE:|7-commandAction|25|145-preAction
                // write pre-action user code here
                switchDisplayable(null, getHubConnection());//GEN-LINE:|7-commandAction|26|145-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|27|84-preAction
        } else if (displayable == form1) {
            if (command == backCommand4) {//GEN-END:|7-commandAction|27|84-preAction
                // write pre-action user code here
                switchDisplayable(null, getHubConnection());//GEN-LINE:|7-commandAction|28|84-postAction
                // write post-action user code here
            } else if (command == searchNet) {//GEN-LINE:|7-commandAction|29|82-preAction
                // write pre-action user code here

                new Thread(new Runnable() {

                    public void run() {
                        String queryString = getTextField().getString();
                        System.out.println(queryString);
                        //AlertDisplayer.showAlert("Search", "Searching For " + queryString, this.getDisplay(), this.getForm1());
                        fileLocation = client.musicSearcher.searchMusicFiles(queryString);

                        client.disconnect();
                        GUIClient.this.searchResults = null;
                        GUIClient.this.showAlert("connected", "Results found " + fileLocation, GUIClient.this.getSearchResults());
                        //AlertDisplayer.showAlert("HI", fileLocation.toString(), null, displayable);
                    }
                }).start();

//GEN-LINE:|7-commandAction|30|82-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|31|47-preAction
        } else if (displayable == peerList) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|31|47-preAction
                // write pre-action user code here
                peerListAction();//GEN-LINE:|7-commandAction|32|47-postAction
                // write post-action user code here
            } else if (command == backCommand1) {//GEN-LINE:|7-commandAction|33|59-preAction
                // write pre-action user code here
                switchDisplayable(null, getHubConnection());//GEN-LINE:|7-commandAction|34|59-postAction
                // write post-action user code here
            } else if (command == sendIM) {//GEN-LINE:|7-commandAction|35|89-preAction
                // write pre-action user code here
                switchDisplayable(null, getChatSendingForm());//GEN-LINE:|7-commandAction|36|89-postAction
                // write post-action user code here
            } else if (command == viewFileList) {//GEN-LINE:|7-commandAction|37|87-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|38|87-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|39|150-preAction
        } else if (displayable == searchResults) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|39|150-preAction
                // write pre-action user code here
                searchResultsAction();//GEN-LINE:|7-commandAction|40|150-postAction
                // write post-action user code here
            } else if (command == backCommand8) {//GEN-LINE:|7-commandAction|41|153-preAction
                // write pre-action user code here
                switchDisplayable(null, getForm1());//GEN-LINE:|7-commandAction|42|153-postAction
                // write post-action user code here
            } else if (command == downloadCommand) {//GEN-LINE:|7-commandAction|43|169-preAction




                // write pre-action user code he
                //               AlertDisplayer.showAlert("Download", "Downloading from " + btAddress + location + s,
                //                   this.getDisplay(), this.getWaitScreen());






                switchDisplayable(null, getWaitScreen());//GEN-LINE:|7-commandAction|44|169-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|45|176-preAction
        } else if (displayable == waitScreen) {
            if (command == WaitScreen.FAILURE_COMMAND) {//GEN-END:|7-commandAction|45|176-preAction
                // write pre-action user code here
                switchDisplayable(null, getHubConnection());//GEN-LINE:|7-commandAction|46|176-postAction
                // write post-action user code here
            } else if (command == WaitScreen.SUCCESS_COMMAND) {//GEN-LINE:|7-commandAction|47|175-preAction
                // write pre-action user code here
                switchDisplayable(null, getSearchResults());//GEN-LINE:|7-commandAction|48|175-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|49|7-postCommandAction
        }//GEN-END:|7-commandAction|49|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|50|
    //</editor-fold>//GEN-END:|7-commandAction|50|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitApplication ">//GEN-BEGIN:|18-getter|0|18-preInit
    /**
     * Returns an initiliazed instance of exitApplication component.
     * @return the initialized component instance
     */
    public Command getExitApplication() {
        if (exitApplication == null) {//GEN-END:|18-getter|0|18-preInit
            // write pre-init user code here
            exitApplication = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|18-getter|1|18-postInit
            // write post-init user code here
        }//GEN-BEGIN:|18-getter|2|
        return exitApplication;
    }
    //</editor-fold>//GEN-END:|18-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand ">//GEN-BEGIN:|26-getter|0|26-preInit
    /**
     * Returns an initiliazed instance of screenCommand component.
     * @return the initialized component instance
     */
    public Command getScreenCommand() {
        if (screenCommand == null) {//GEN-END:|26-getter|0|26-preInit
            // write pre-init user code here
            screenCommand = new Command("Screen", Command.SCREEN, 0);//GEN-LINE:|26-getter|1|26-postInit
            // write post-init user code here
        }//GEN-BEGIN:|26-getter|2|
        return screenCommand;
    }
    //</editor-fold>//GEN-END:|26-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: HubConnection ">//GEN-BEGIN:|22-getter|0|22-preInit
    /**
     * Returns an initiliazed instance of HubConnection component.
     * @return the initialized component instance
     */
    public List getHubConnection() {
        if (HubConnection == null) {//GEN-END:|22-getter|0|22-preInit
            // write pre-init user code here
            HubConnection = new List("Hub Connection", Choice.IMPLICIT);//GEN-BEGIN:|22-getter|1|22-postInit
            HubConnection.append("Search Songs", null);
            HubConnection.append("View Members", null);
            HubConnection.append("Your File System", null);
            HubConnection.addCommand(getBackChatSendingFormComand());
            HubConnection.setCommandListener(this);
            HubConnection.setSelectedFlags(new boolean[] { false, false, false });//GEN-END:|22-getter|1|22-postInit
            // write post-init user code here

        }//GEN-BEGIN:|22-getter|2|
        return HubConnection;
    }
    //</editor-fold>//GEN-END:|22-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: HubConnectionAction ">//GEN-BEGIN:|22-action|0|22-preAction
    /**
     * Performs an action assigned to the selected list element in the HubConnection component.
     */
    public void HubConnectionAction() {//GEN-END:|22-action|0|22-preAction
        // enter pre-action user code here
        String __selectedString = getHubConnection().getString(getHubConnection().getSelectedIndex());//GEN-BEGIN:|22-action|1|40-preAction
        if (__selectedString != null) {
            if (__selectedString.equals("Search Songs")) {//GEN-END:|22-action|1|40-preAction
                // write pre-action user code here
                switchDisplayable(null, getForm1());//GEN-LINE:|22-action|2|40-postAction
                // write post-action user code here
            } else if (__selectedString.equals("View Members")) {//GEN-LINE:|22-action|3|41-preAction

                new Thread(new Runnable() {

                    public void run() {
                        System.out.println("1");
                        peerListVector = GUIClient.this.client.peerListRequester.getMemberList();
                        System.out.println("2");

                        GUIClient.this.peerList = null;
                        GUIClient.this.showAlert("connected", "PeerList: " + peerListVector, GUIClient.this.getPeerList());


                    }
                }).start();


                // write pre-action user code here
//GEN-LINE:|22-action|4|41-postAction


                // write post-action user code here
            } else if (__selectedString.equals("Your File System")) {//GEN-LINE:|22-action|5|42-preAction
                // write pre-action user code here
                switchDisplayable(null, getFileBrowser());//GEN-LINE:|22-action|6|42-postAction
                // write post-action user code here
            }//GEN-BEGIN:|22-action|7|22-postAction
        }//GEN-END:|22-action|7|22-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|22-action|8|
    //</editor-fold>//GEN-END:|22-action|8|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: removeSong ">//GEN-BEGIN:|28-getter|0|28-preInit
    /**
     * Returns an initiliazed instance of removeSong component.
     * @return the initialized component instance
     */
    public Command getRemoveSong() {
        if (removeSong == null) {//GEN-END:|28-getter|0|28-preInit
            // write pre-init user code here
            removeSong = new Command("Remove Songs", Command.OK, 5);//GEN-LINE:|28-getter|1|28-postInit
            // write post-init user code here
        }//GEN-BEGIN:|28-getter|2|
        return removeSong;
    }
    //</editor-fold>//GEN-END:|28-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: itemCommand ">//GEN-BEGIN:|31-getter|0|31-preInit
    /**
     * Returns an initiliazed instance of itemCommand component.
     * @return the initialized component instance
     */
    public Command getItemCommand() {
        if (itemCommand == null) {//GEN-END:|31-getter|0|31-preInit
            // write pre-init user code here
            itemCommand = new Command("Item", Command.ITEM, 0);//GEN-LINE:|31-getter|1|31-postInit
            // write post-init user code here
        }//GEN-BEGIN:|31-getter|2|
        return itemCommand;
    }
    //</editor-fold>//GEN-END:|31-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand1 ">//GEN-BEGIN:|37-getter|0|37-preInit
    /**
     * Returns an initiliazed instance of screenCommand1 component.
     * @return the initialized component instance
     */
    public Command getScreenCommand1() {
        if (screenCommand1 == null) {//GEN-END:|37-getter|0|37-preInit
            // write pre-init user code here
            screenCommand1 = new Command("Upload Songs", Command.SCREEN, 0);//GEN-LINE:|37-getter|1|37-postInit
            // write post-init user code here
        }//GEN-BEGIN:|37-getter|2|
        return screenCommand1;
    }
    //</editor-fold>//GEN-END:|37-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand2 ">//GEN-BEGIN:|43-getter|0|43-preInit
    /**
     * Returns an initiliazed instance of screenCommand2 component.
     * @return the initialized component instance
     */
    public Command getScreenCommand2() {
        if (screenCommand2 == null) {//GEN-END:|43-getter|0|43-preInit
            // write pre-init user code here
            screenCommand2 = new Command("Select", Command.SCREEN, 0);//GEN-LINE:|43-getter|1|43-postInit
            // write post-init user code here
        }//GEN-BEGIN:|43-getter|2|
        return screenCommand2;
    }
    //</editor-fold>//GEN-END:|43-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: peerList ">//GEN-BEGIN:|46-getter|0|46-preInit
    /**
     * Returns an initiliazed instance of peerList component.
     * @return the initialized component instance
     */
    public List getPeerList() {
        if (peerList == null) {//GEN-END:|46-getter|0|46-preInit
            // write pre-init user code here

            peerList = new List("Active Members ", Choice.IMPLICIT);//GEN-BEGIN:|46-getter|1|46-postInit
            peerList.addCommand(getBackCommand1());
            peerList.addCommand(getViewFileList());
            peerList.addCommand(getSendIM());
            peerList.setCommandListener(this);//GEN-END:|46-getter|1|46-postInit
            // write post-init user code here

            for (int i = 0; i < peerListVector.size(); i++) {
                ClientRecord temp = (ClientRecord) peerListVector.elementAt(i);
                peerList.append(temp.friendlyName, null);
            }
            /*Vector clients = client.peerListRequester.getMemberList();
            shared.record.ClientRecord temp;
            client.peers = client.peerListRequester.getMemberList();
            for (int i = 0; i < client.peers.size(); i++) {
            temp = (shared.record.ClientRecord) client.peers.elementAt(i);
            list.append(temp.friendlyName, null);
            }*/
        }//GEN-BEGIN:|46-getter|2|
        return peerList;
    }
    //</editor-fold>//GEN-END:|46-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: peerListAction ">//GEN-BEGIN:|46-action|0|46-preAction
    /**
     * Performs an action assigned to the selected list element in the peerList component.
     */
    public void peerListAction() {//GEN-END:|46-action|0|46-preAction
        // enter pre-action user code here
        String __selectedString = getPeerList().getString(getPeerList().getSelectedIndex());//GEN-LINE:|46-action|1|46-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|46-action|2|
    //</editor-fold>//GEN-END:|46-action|2|
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backChatSendingFormComand ">//GEN-BEGIN:|54-getter|0|54-preInit
    /**
     * Returns an initiliazed instance of backChatSendingFormComand component.
     * @return the initialized component instance
     */
    public Command getBackChatSendingFormComand() {
        if (backChatSendingFormComand == null) {//GEN-END:|54-getter|0|54-preInit
            // write pre-init user code here
            backChatSendingFormComand = new Command("Back", Command.BACK, 0);//GEN-LINE:|54-getter|1|54-postInit
            // write post-init user code here
        }//GEN-BEGIN:|54-getter|2|
        return backChatSendingFormComand;
    }
    //</editor-fold>//GEN-END:|54-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand1 ">//GEN-BEGIN:|56-getter|0|56-preInit
    /**
     * Returns an initiliazed instance of okCommand1 component.
     * @return the initialized component instance
     */
    public Command getOkCommand1() {
        if (okCommand1 == null) {//GEN-END:|56-getter|0|56-preInit
            // write pre-init user code here
            okCommand1 = new Command("Ok", Command.OK, 0);//GEN-LINE:|56-getter|1|56-postInit
            // write post-init user code here
        }//GEN-BEGIN:|56-getter|2|
        return okCommand1;
    }
    //</editor-fold>//GEN-END:|56-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand1 ">//GEN-BEGIN:|58-getter|0|58-preInit
    /**
     * Returns an initiliazed instance of backCommand1 component.
     * @return the initialized component instance
     */
    public Command getBackCommand1() {
        if (backCommand1 == null) {//GEN-END:|58-getter|0|58-preInit
            // write pre-init user code here
            backCommand1 = new Command("Back", Command.BACK, 0);//GEN-LINE:|58-getter|1|58-postInit
            // write post-init user code here
        }//GEN-BEGIN:|58-getter|2|
        return backCommand1;
    }
    //</editor-fold>//GEN-END:|58-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand2 ">//GEN-BEGIN:|60-getter|0|60-preInit
    /**
     * Returns an initiliazed instance of backCommand2 component.
     * @return the initialized component instance
     */
    public Command getBackCommand2() {
        if (backCommand2 == null) {//GEN-END:|60-getter|0|60-preInit
            // write pre-init user code here
            backCommand2 = new Command("Back", Command.BACK, 0);//GEN-LINE:|60-getter|1|60-postInit
            // write post-init user code here
        }//GEN-BEGIN:|60-getter|2|
        return backCommand2;
    }
    //</editor-fold>//GEN-END:|60-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand3 ">//GEN-BEGIN:|64-getter|0|64-preInit
    /**
     * Returns an initiliazed instance of backCommand3 component.
     * @return the initialized component instance
     */
    public Command getBackCommand3() {
        if (backCommand3 == null) {//GEN-END:|64-getter|0|64-preInit
            // write pre-init user code here
            backCommand3 = new Command("Back", Command.BACK, 0);//GEN-LINE:|64-getter|1|64-postInit
            // write post-init user code here
        }//GEN-BEGIN:|64-getter|2|
        return backCommand3;
    }
    //</editor-fold>//GEN-END:|64-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: form1 ">//GEN-BEGIN:|68-getter|0|68-preInit
    /**
     * Returns an initiliazed instance of form1 component.
     * @return the initialized component instance
     */
    public Form getForm1() {
        if (form1 == null) {//GEN-END:|68-getter|0|68-preInit
            // write pre-init user code here
            form1 = new Form("Search", new Item[] { getStringItem1(), getSpacer(), getSpacer1(), getTextField() });//GEN-BEGIN:|68-getter|1|68-postInit
            form1.addCommand(getSearchNet());
            form1.addCommand(getBackCommand4());
            form1.setCommandListener(this);//GEN-END:|68-getter|1|68-postInit
            // write post-init user code here
        }//GEN-BEGIN:|68-getter|2|
        return form1;
    }
    //</editor-fold>//GEN-END:|68-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem1 ">//GEN-BEGIN:|69-getter|0|69-preInit
    /**
     * Returns an initiliazed instance of stringItem1 component.
     * @return the initialized component instance
     */
    public StringItem getStringItem1() {
        if (stringItem1 == null) {//GEN-END:|69-getter|0|69-preInit
            // write pre-init user code here
            stringItem1 = new StringItem("", "Search Songs");//GEN-LINE:|69-getter|1|69-postInit
            // write post-init user code here
        }//GEN-BEGIN:|69-getter|2|
        return stringItem1;
    }
    //</editor-fold>//GEN-END:|69-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: spacer ">//GEN-BEGIN:|74-getter|0|74-preInit
    /**
     * Returns an initiliazed instance of spacer component.
     * @return the initialized component instance
     */
    public Spacer getSpacer() {
        if (spacer == null) {//GEN-END:|74-getter|0|74-preInit
            // write pre-init user code here
            spacer = new Spacer(16, 50);//GEN-LINE:|74-getter|1|74-postInit
            // write post-init user code here
        }//GEN-BEGIN:|74-getter|2|
        return spacer;
    }
    //</editor-fold>//GEN-END:|74-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: spacer1 ">//GEN-BEGIN:|75-getter|0|75-preInit
    /**
     * Returns an initiliazed instance of spacer1 component.
     * @return the initialized component instance
     */
    public Spacer getSpacer1() {
        if (spacer1 == null) {//GEN-END:|75-getter|0|75-preInit
            // write pre-init user code here
            spacer1 = new Spacer(16, 16);//GEN-LINE:|75-getter|1|75-postInit
            // write post-init user code here
        }//GEN-BEGIN:|75-getter|2|
        return spacer1;
    }
    //</editor-fold>//GEN-END:|75-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField ">//GEN-BEGIN:|76-getter|0|76-preInit
    /**
     * Returns an initiliazed instance of textField component.
     * @return the initialized component instance
     */
    public TextField getTextField() {
        if (textField == null) {//GEN-END:|76-getter|0|76-preInit
            // write pre-init user code here
            textField = new TextField("Enter Keyword to search", null, 32, TextField.ANY);//GEN-LINE:|76-getter|1|76-postInit
            // write post-init user code here
        }//GEN-BEGIN:|76-getter|2|
        return textField;
    }
    //</editor-fold>//GEN-END:|76-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: searchNet ">//GEN-BEGIN:|81-getter|0|81-preInit
    /**
     * Returns an initiliazed instance of searchNet component.
     * @return the initialized component instance
     */
    public Command getSearchNet() {
        if (searchNet == null) {//GEN-END:|81-getter|0|81-preInit
            // write pre-init user code here
            searchNet = new Command("Search", Command.SCREEN, 0);//GEN-LINE:|81-getter|1|81-postInit
            // write post-init user code here
        }//GEN-BEGIN:|81-getter|2|
        return searchNet;
    }
    //</editor-fold>//GEN-END:|81-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand4 ">//GEN-BEGIN:|83-getter|0|83-preInit
    /**
     * Returns an initiliazed instance of backCommand4 component.
     * @return the initialized component instance
     */
    public Command getBackCommand4() {
        if (backCommand4 == null) {//GEN-END:|83-getter|0|83-preInit
            // write pre-init user code here
            backCommand4 = new Command("Back", Command.BACK, 0);//GEN-LINE:|83-getter|1|83-postInit
            // write post-init user code here
        }//GEN-BEGIN:|83-getter|2|
        return backCommand4;
    }
    //</editor-fold>//GEN-END:|83-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: viewFileList ">//GEN-BEGIN:|86-getter|0|86-preInit
    /**
     * Returns an initiliazed instance of viewFileList component.
     * @return the initialized component instance
     */
    public Command getViewFileList() {
        if (viewFileList == null) {//GEN-END:|86-getter|0|86-preInit
            // write pre-init user code here
            viewFileList = new Command("View File List", Command.OK, 0);//GEN-LINE:|86-getter|1|86-postInit
            // write post-init user code here
        }//GEN-BEGIN:|86-getter|2|
        return viewFileList;
    }
    //</editor-fold>//GEN-END:|86-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: sendIM ">//GEN-BEGIN:|88-getter|0|88-preInit
    /**
     * Returns an initiliazed instance of sendIM component.
     * @return the initialized component instance
     */
    public Command getSendIM() {
        if (sendIM == null) {//GEN-END:|88-getter|0|88-preInit
            // write pre-init user code here
            sendIM = new Command("Send IM", Command.OK, 0);//GEN-LINE:|88-getter|1|88-postInit
            // write post-init user code here
        }//GEN-BEGIN:|88-getter|2|
        return sendIM;
    }
    //</editor-fold>//GEN-END:|88-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand4 ">//GEN-BEGIN:|90-getter|0|90-preInit
    /**
     * Returns an initiliazed instance of okCommand4 component.
     * @return the initialized component instance
     */
    public Command getOkCommand4() {
        if (okCommand4 == null) {//GEN-END:|90-getter|0|90-preInit
            // write pre-init user code here
            okCommand4 = new Command("Ok", Command.OK, 0);//GEN-LINE:|90-getter|1|90-postInit
            // write post-init user code here
        }//GEN-BEGIN:|90-getter|2|
        return okCommand4;
    }
    //</editor-fold>//GEN-END:|90-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: uploadSong ">//GEN-BEGIN:|99-getter|0|99-preInit
    /**
     * Returns an initiliazed instance of uploadSong component.
     * @return the initialized component instance
     */
    public Command getUploadSong() {
        if (uploadSong == null) {//GEN-END:|99-getter|0|99-preInit
            // write pre-init user code here
            uploadSong = new Command("Upload ", "Upload Songs", Command.SCREEN, 0);//GEN-LINE:|99-getter|1|99-postInit
            // write post-init user code here
        }//GEN-BEGIN:|99-getter|2|
        return uploadSong;
    }
    //</editor-fold>//GEN-END:|99-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand5 ">//GEN-BEGIN:|103-getter|0|103-preInit
    /**
     * Returns an initiliazed instance of backCommand5 component.
     * @return the initialized component instance
     */
    public Command getBackCommand5() {
        if (backCommand5 == null) {//GEN-END:|103-getter|0|103-preInit
            // write pre-init user code here
            backCommand5 = new Command("Back", Command.BACK, 0);//GEN-LINE:|103-getter|1|103-postInit
            // write post-init user code here
        }//GEN-BEGIN:|103-getter|2|
        return backCommand5;
    }
    //</editor-fold>//GEN-END:|103-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: chatSendingForm ">//GEN-BEGIN:|108-getter|0|108-preInit
    /**
     * Returns an initiliazed instance of chatSendingForm component.
     * @return the initialized component instance
     */
    public Form getChatSendingForm() {
        if (chatSendingForm == null) {//GEN-END:|108-getter|0|108-preInit
            // write pre-init user code here
            chatSendingForm = new Form("IM", new Item[] { getSpacer2(), getChatMessageField() });//GEN-BEGIN:|108-getter|1|108-postInit
            chatSendingForm.addCommand(getSendMessageCommand());
            chatSendingForm.addCommand(getBackChatSendingFormComand());
            chatSendingForm.setCommandListener(this);//GEN-END:|108-getter|1|108-postInit
            // write post-init user code here
        }//GEN-BEGIN:|108-getter|2|
        return chatSendingForm;
    }
    //</editor-fold>//GEN-END:|108-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: chatMessageField ">//GEN-BEGIN:|109-getter|0|109-preInit
    /**
     * Returns an initiliazed instance of chatMessageField component.
     * @return the initialized component instance
     */
    public TextField getChatMessageField() {
        if (chatMessageField == null) {//GEN-END:|109-getter|0|109-preInit
            // write pre-init user code here
            chatMessageField = new TextField("Chat Message", "", 32, TextField.ANY);//GEN-LINE:|109-getter|1|109-postInit
            // write post-init user code here
        }//GEN-BEGIN:|109-getter|2|
        return chatMessageField;
    }
    //</editor-fold>//GEN-END:|109-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: spacer2 ">//GEN-BEGIN:|113-getter|0|113-preInit
    /**
     * Returns an initiliazed instance of spacer2 component.
     * @return the initialized component instance
     */
    public Spacer getSpacer2() {
        if (spacer2 == null) {//GEN-END:|113-getter|0|113-preInit
            // write pre-init user code here
            spacer2 = new Spacer(16, 100);//GEN-LINE:|113-getter|1|113-postInit
            // write post-init user code here
        }//GEN-BEGIN:|113-getter|2|
        return spacer2;
    }
    //</editor-fold>//GEN-END:|113-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: sendMessageCommand ">//GEN-BEGIN:|115-getter|0|115-preInit
    /**
     * Returns an initiliazed instance of sendMessageCommand component.
     * @return the initialized component instance
     */
    public Command getSendMessageCommand() {
        if (sendMessageCommand == null) {//GEN-END:|115-getter|0|115-preInit
            // write pre-init user code here
            sendMessageCommand = new Command("Send", Command.SCREEN, 0);//GEN-LINE:|115-getter|1|115-postInit
            // write post-init user code here
        }//GEN-BEGIN:|115-getter|2|
        return sendMessageCommand;
    }
    //</editor-fold>//GEN-END:|115-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: Share ">//GEN-BEGIN:|122-getter|0|122-preInit
    /**
     * Returns an initiliazed instance of Share component.
     * @return the initialized component instance
     */
    public Command getShare() {
        if (Share == null) {//GEN-END:|122-getter|0|122-preInit
            // write pre-init user code here
            Share = new Command("Share", Command.OK, 0);//GEN-LINE:|122-getter|1|122-postInit
            // write post-init user code here
        }//GEN-BEGIN:|122-getter|2|
        return Share;
    }
    //</editor-fold>//GEN-END:|122-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand6 ">//GEN-BEGIN:|128-getter|0|128-preInit
    /**
     * Returns an initiliazed instance of backCommand6 component.
     * @return the initialized component instance
     */
    public Command getBackCommand6() {
        if (backCommand6 == null) {//GEN-END:|128-getter|0|128-preInit
            // write pre-init user code here
            backCommand6 = new Command("Back", Command.BACK, 0);//GEN-LINE:|128-getter|1|128-postInit
            // write post-init user code here
        }//GEN-BEGIN:|128-getter|2|
        return backCommand6;
    }
    //</editor-fold>//GEN-END:|128-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">//GEN-BEGIN:|132-getter|0|132-preInit
    /**
     * Returns an initiliazed instance of okCommand component.
     * @return the initialized component instance
     */
    public Command getOkCommand() {
        if (okCommand == null) {//GEN-END:|132-getter|0|132-preInit
            // write pre-init user code here
            okCommand = new Command("Ok", Command.OK, 0);//GEN-LINE:|132-getter|1|132-postInit
            // write post-init user code here
        }//GEN-BEGIN:|132-getter|2|
        return okCommand;
    }
    //</editor-fold>//GEN-END:|132-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand7 ">//GEN-BEGIN:|144-getter|0|144-preInit
    /**
     * Returns an initiliazed instance of backCommand7 component.
     * @return the initialized component instance
     */
    public Command getBackCommand7() {
        if (backCommand7 == null) {//GEN-END:|144-getter|0|144-preInit
            // write pre-init user code here
            backCommand7 = new Command("Back", Command.BACK, 1);//GEN-LINE:|144-getter|1|144-postInit
            // write post-init user code here
        }//GEN-BEGIN:|144-getter|2|
        return backCommand7;
    }
    //</editor-fold>//GEN-END:|144-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: fileBrowser ">//GEN-BEGIN:|141-getter|0|141-preInit
    /**
     * Returns an initiliazed instance of fileBrowser component.
     * @return the initialized component instance
     */
    public FileBrowser getFileBrowser() {
        if (fileBrowser == null) {//GEN-END:|141-getter|0|141-preInit
            // write pre-init user code here
            fileBrowser = new FileBrowser(getDisplay());//GEN-BEGIN:|141-getter|1|141-postInit
            fileBrowser.setTitle("fileBrowser");
            fileBrowser.setCommandListener(this);
            fileBrowser.addCommand(getShare());
            fileBrowser.addCommand(FileBrowser.SELECT_FILE_COMMAND);
            fileBrowser.addCommand(getBackCommand7());//GEN-END:|141-getter|1|141-postInit
            // write post-init user code here
        }//GEN-BEGIN:|141-getter|2|
        return fileBrowser;
    }
    //</editor-fold>//GEN-END:|141-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand8 ">//GEN-BEGIN:|152-getter|0|152-preInit
    /**
     * Returns an initiliazed instance of backCommand8 component.
     * @return the initialized component instance
     */
    public Command getBackCommand8() {
        if (backCommand8 == null) {//GEN-END:|152-getter|0|152-preInit
            // write pre-init user code here
            backCommand8 = new Command("Back", Command.BACK, 0);//GEN-LINE:|152-getter|1|152-postInit
            // write post-init user code here
        }//GEN-BEGIN:|152-getter|2|
        return backCommand8;
    }
    //</editor-fold>//GEN-END:|152-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand2 ">//GEN-BEGIN:|154-getter|0|154-preInit
    /**
     * Returns an initiliazed instance of okCommand2 component.
     * @return the initialized component instance
     */
    public Command getOkCommand2() {
        if (okCommand2 == null) {//GEN-END:|154-getter|0|154-preInit
            // write pre-init user code here
            okCommand2 = new Command("Ok", Command.OK, 0);//GEN-LINE:|154-getter|1|154-postInit
            // write post-init user code here
        }//GEN-BEGIN:|154-getter|2|
        return okCommand2;
    }
    //</editor-fold>//GEN-END:|154-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: searchResults ">//GEN-BEGIN:|149-getter|0|149-preInit
    /**
     * Returns an initiliazed instance of searchResults component.
     * @return the initialized component instance
     */
    public List getSearchResults() {
        if (searchResults == null) {//GEN-END:|149-getter|0|149-preInit
            // write pre-init user code here
            searchResults = new List("Search Results", Choice.IMPLICIT);//GEN-BEGIN:|149-getter|1|149-postInit
            searchResults.addCommand(getBackCommand8());
            searchResults.addCommand(getDownloadCommand());
            searchResults.setCommandListener(this);//GEN-END:|149-getter|1|149-postInit
            //  System.out.println(((FileLocation) fileLocation.elementAt(0)).location);
            FileLocation downloadLocation;
            String btAddress;

            /*if(fileLocation == null) {
            AlertDisplayer.showAlert("Not Found", "No matching Songs", this.getDisplay(), this.form);
            }*/

            if (fileLocation != null) {
                for (int i = 0; i < fileLocation.size(); i++) {
                    downloadLocation = (FileLocation) fileLocation.elementAt(i);
                    btAddress = downloadLocation.record.bluetoothAddress;
                    searchResults.append(btAddress + "@" + downloadLocation.location, null);
                }
            }


            //System.out.println("[client:] Download File from " + btAddress + " at " + downloadLocation.location);
            // write post-init user code here
        }//GEN-BEGIN:|149-getter|2|
        return searchResults;
    }
    //</editor-fold>//GEN-END:|149-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: searchResultsAction ">//GEN-BEGIN:|149-action|0|149-preAction
    /**
     * Performs an action assigned to the selected list element in the searchResults component.
     */
    public void searchResultsAction() {//GEN-END:|149-action|0|149-preAction
        // enter pre-action user code here
        String __selectedString = getSearchResults().getString(getSearchResults().getSelectedIndex());//GEN-LINE:|149-action|1|149-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|149-action|2|
    //</editor-fold>//GEN-END:|149-action|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: connectCommand ">//GEN-BEGIN:|164-getter|0|164-preInit
    /**
     * Returns an initiliazed instance of connectCommand component.
     * @return the initialized component instance
     */
    public Command getConnectCommand() {
        if (connectCommand == null) {//GEN-END:|164-getter|0|164-preInit
            // write pre-init user code here
            connectCommand = new Command("Ok", Command.OK, 0);//GEN-LINE:|164-getter|1|164-postInit
            // write post-init user code here
        }//GEN-BEGIN:|164-getter|2|
        return connectCommand;
    }
    //</editor-fold>//GEN-END:|164-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: enterBTAddressForm ">//GEN-BEGIN:|158-getter|0|158-preInit
    /**
     * Returns an initiliazed instance of enterBTAddressForm component.
     * @return the initialized component instance
     */
    public Form getEnterBTAddressForm() {
        if (enterBTAddressForm == null) {//GEN-END:|158-getter|0|158-preInit
            // write pre-init user code here
            enterBTAddressForm = new Form("form2", new Item[] { getBtAddressTextField() });//GEN-BEGIN:|158-getter|1|158-postInit
            enterBTAddressForm.addCommand(getConnectCommand());
            enterBTAddressForm.addCommand(getExitApplication());
            enterBTAddressForm.setCommandListener(this);//GEN-END:|158-getter|1|158-postInit
            // write post-init user code here
        }//GEN-BEGIN:|158-getter|2|
        return enterBTAddressForm;
    }
    //</editor-fold>//GEN-END:|158-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: btAddressTextField ">//GEN-BEGIN:|163-getter|0|163-preInit
    /**
     * Returns an initiliazed instance of btAddressTextField component.
     * @return the initialized component instance
     */
    public TextField getBtAddressTextField() {
        if (btAddressTextField == null) {//GEN-END:|163-getter|0|163-preInit
            // write pre-init user code here
            btAddressTextField = new TextField("Enter the Bluetooth Address:", "0022988C6DE6", 32, TextField.ANY);//GEN-LINE:|163-getter|1|163-postInit
            // write post-init user code here
        }//GEN-BEGIN:|163-getter|2|
        return btAddressTextField;
    }
    //</editor-fold>//GEN-END:|163-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: downloadCommand ">//GEN-BEGIN:|168-getter|0|168-preInit
    /**
     * Returns an initiliazed instance of downloadCommand component.
     * @return the initialized component instance
     */
    public Command getDownloadCommand() {
        if (downloadCommand == null) {//GEN-END:|168-getter|0|168-preInit
            // write pre-init user code here
            downloadCommand = new Command("Download this file", Command.OK, 0);//GEN-LINE:|168-getter|1|168-postInit
            // write post-init user code here
        }//GEN-BEGIN:|168-getter|2|
        return downloadCommand;
    }
    //</editor-fold>//GEN-END:|168-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand9 ">//GEN-BEGIN:|171-getter|0|171-preInit
    /**
     * Returns an initiliazed instance of backCommand9 component.
     * @return the initialized component instance
     */
    public Command getBackCommand9() {
        if (backCommand9 == null) {//GEN-END:|171-getter|0|171-preInit
            // write pre-init user code here
            backCommand9 = new Command("Back", Command.BACK, 0);//GEN-LINE:|171-getter|1|171-postInit
            // write post-init user code here
        }//GEN-BEGIN:|171-getter|2|
        return backCommand9;
    }
    //</editor-fold>//GEN-END:|171-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: waitScreen ">//GEN-BEGIN:|172-getter|0|172-preInit
    /**
     * Returns an initiliazed instance of waitScreen component.
     * @return the initialized component instance
     */
    public WaitScreen getWaitScreen() {
        if (waitScreen == null) {//GEN-END:|172-getter|0|172-preInit
            // write pre-init user code here
            waitScreen = new WaitScreen(getDisplay());//GEN-BEGIN:|172-getter|1|172-postInit
            waitScreen.setTitle("Please Wait");
            waitScreen.setTicker(getTicker1());
            waitScreen.setCommandListener(this);
            waitScreen.setImage(getImage1());
            waitScreen.setTask(getTask());//GEN-END:|172-getter|1|172-postInit
            // write post-init user code here
        }//GEN-BEGIN:|172-getter|2|
        return waitScreen;
    }
    //</editor-fold>//GEN-END:|172-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: task ">//GEN-BEGIN:|177-getter|0|177-preInit
    /**
     * Returns an initiliazed instance of task component.
     * @return the initialized component instance
     */
    public SimpleCancellableTask getTask() {
        if (task == null) {//GEN-END:|177-getter|0|177-preInit
            // write pre-init user code here
            task = new SimpleCancellableTask();//GEN-BEGIN:|177-getter|1|177-execute
            task.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {//GEN-END:|177-getter|1|177-execute
                    // write task-execution user code here

                    String abc = searchResults.getString(searchResults.getSelectedIndex());
                    //client.gui.showAlert("abc", abc, client.gui.getWaitScreen());
                    if (abc != null) {
                        System.out.println(abc);
                        final String btAddress = abc.substring(0, abc.indexOf("@"));
                        final String location = abc.substring(abc.indexOf("@") + 1);

                        Enumeration e = FileSystemRegistry.listRoots();
                        final String s = (String) e.nextElement();



                        FileDownloader fileDownload = new FileDownloader(btAddress, location,
                                client.localDevice, GUIClient.this.client);
                        //fileDownload.downloadFileTo(location);

                        String fileName = location.substring(location.lastIndexOf('/') + 1);
                        String a = fileDownload.downloadFileTo("e:/sounds/" + fileName);

                        //client.gui.showAlert("found", "Device Found with s at " + s + ": " + a, AlertType.INFO, 10000, client.gui.getWaitScreen());
                    }
                }//GEN-BEGIN:|177-getter|2|177-postInit
            });//GEN-END:|177-getter|2|177-postInit
            // write post-init user code here
        }//GEN-BEGIN:|177-getter|3|
        return task;
    }
    //</editor-fold>//GEN-END:|177-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: chatList ">//GEN-BEGIN:|179-getter|0|179-preInit
    /**
     * Returns an initiliazed instance of chatList component.
     * @return the initialized component instance
     */
    public List getChatList() {
        if (chatList == null) {//GEN-END:|179-getter|0|179-preInit
            // write pre-init user code here
            chatList = new List("list", Choice.IMPLICIT);//GEN-BEGIN:|179-getter|1|179-postInit
            chatList.addCommand(getBackChatListCommand());
            chatList.setCommandListener(this);//GEN-END:|179-getter|1|179-postInit
            // write post-init user code here
        }//GEN-BEGIN:|179-getter|2|
        return chatList;
    }
    //</editor-fold>//GEN-END:|179-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: chatListAction ">//GEN-BEGIN:|179-action|0|179-preAction
    /**
     * Performs an action assigned to the selected list element in the chatList component.
     */
    public void chatListAction() {//GEN-END:|179-action|0|179-preAction
        // enter pre-action user code here
        String __selectedString = getChatList().getString(getChatList().getSelectedIndex());//GEN-LINE:|179-action|1|179-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|179-action|2|
    //</editor-fold>//GEN-END:|179-action|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backChatListCommand ">//GEN-BEGIN:|184-getter|0|184-preInit
    /**
     * Returns an initiliazed instance of backChatListCommand component.
     * @return the initialized component instance
     */
    public Command getBackChatListCommand() {
        if (backChatListCommand == null) {//GEN-END:|184-getter|0|184-preInit
            // write pre-init user code here
            backChatListCommand = new Command("Back", Command.BACK, 0);//GEN-LINE:|184-getter|1|184-postInit
            // write post-init user code here
        }//GEN-BEGIN:|184-getter|2|
        return backChatListCommand;
    }
    //</editor-fold>//GEN-END:|184-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: Welcome ">//GEN-BEGIN:|187-getter|0|187-preInit
    /**
     * Returns an initiliazed instance of Welcome component.
     * @return the initialized component instance
     */
    public WaitScreen getWelcome() {
        if (Welcome == null) {//GEN-END:|187-getter|0|187-preInit
            // write pre-init user code here
            Welcome = new WaitScreen(getDisplay());//GEN-BEGIN:|187-getter|1|187-postInit
            Welcome.setTitle("Welcome");
            Welcome.setTicker(getTicker());
            Welcome.setCommandListener(this);
            Welcome.setImage(getImage());
            Welcome.setTask(getTask1());//GEN-END:|187-getter|1|187-postInit
            // write post-init user code here
        }//GEN-BEGIN:|187-getter|2|
        return Welcome;
    }
    //</editor-fold>//GEN-END:|187-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: task1 ">//GEN-BEGIN:|190-getter|0|190-preInit
    /**
     * Returns an initiliazed instance of task1 component.
     * @return the initialized component instance
     */
    public SimpleCancellableTask getTask1() {
        if (task1 == null) {//GEN-END:|190-getter|0|190-preInit
            // write pre-init user code here
            task1 = new SimpleCancellableTask();//GEN-BEGIN:|190-getter|1|190-execute
            task1.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {//GEN-END:|190-getter|1|190-execute
                    // write task-execution user code here
                    HubConnector.HUB_BLUETOOTH_ADDRESS = getBtAddressTextField().getString();
                    client = new BTClient(GUIClient.this);
                }//GEN-BEGIN:|190-getter|2|190-postInit
            });//GEN-END:|190-getter|2|190-postInit
            // write post-init user code here
        }//GEN-BEGIN:|190-getter|3|
        return task1;
    }
    //</editor-fold>//GEN-END:|190-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: ticker ">//GEN-BEGIN:|193-getter|0|193-preInit
    /**
     * Returns an initiliazed instance of ticker component.
     * @return the initialized component instance
     */
    public Ticker getTicker() {
        if (ticker == null) {//GEN-END:|193-getter|0|193-preInit
            // write pre-init user code here
            ticker = new Ticker("Connecting to Hub . . . Please Wait . . .");//GEN-LINE:|193-getter|1|193-postInit
            // write post-init user code here
        }//GEN-BEGIN:|193-getter|2|
        return ticker;
    }
    //</editor-fold>//GEN-END:|193-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: image ">//GEN-BEGIN:|194-getter|0|194-preInit
    /**
     * Returns an initiliazed instance of image component.
     * @return the initialized component instance
     */
    public Image getImage() {
        if (image == null) {//GEN-END:|194-getter|0|194-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|194-getter|1|194-@java.io.IOException
                image = Image.createImage("/connNeighbors.jpg");
            } catch (java.io.IOException e) {//GEN-END:|194-getter|1|194-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|194-getter|2|194-postInit
            // write post-init user code here
        }//GEN-BEGIN:|194-getter|3|
        return image;
    }
    //</editor-fold>//GEN-END:|194-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: ticker1 ">//GEN-BEGIN:|198-getter|0|198-preInit
    /**
     * Returns an initiliazed instance of ticker1 component.
     * @return the initialized component instance
     */
    public Ticker getTicker1() {
        if (ticker1 == null) {//GEN-END:|198-getter|0|198-preInit
            // write pre-init user code here
            ticker1 = new Ticker("Downloading . . .");//GEN-LINE:|198-getter|1|198-postInit
            // write post-init user code here
        }//GEN-BEGIN:|198-getter|2|
        return ticker1;
    }
    //</editor-fold>//GEN-END:|198-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: image1 ">//GEN-BEGIN:|203-getter|0|203-preInit
    /**
     * Returns an initiliazed instance of image1 component.
     * @return the initialized component instance
     */
    public Image getImage1() {
        if (image1 == null) {//GEN-END:|203-getter|0|203-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|203-getter|1|203-@java.io.IOException
                image1 = Image.createImage("/wait.jpg");
            } catch (java.io.IOException e) {//GEN-END:|203-getter|1|203-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|203-getter|2|203-postInit
            // write post-init user code here
        }//GEN-BEGIN:|203-getter|3|
        return image1;
    }
    //</editor-fold>//GEN-END:|203-getter|3|

    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay() {
        return Display.getDisplay(this);


    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable(null, null);
        destroyApp(
                true);
        notifyDestroyed();


    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet();


        } else {
            initialize();
            startMIDlet();


        }
        midletPaused = false;


    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;


    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
        HeaderSet hdr = client.hubConn.createHeaderSet();
        hdr.setHeader(HeaderSet.NAME, client.localDevice.getBluetoothAddress());
        hdr.setHeader(HeaderSet.DESCRIPTION, client.localDevice.getFriendlyName());


        try {
            client.hubConn.connect(hdr);
            hdr.setHeader(HeaderSets.REQUEST_TYPE, Types.REQUEST_DISCONNECT);
            client.hubConn.disconnect(hdr);
            client.hubConn.close();



        } catch (IOException ex) {
            ex.printStackTrace();


            this.showAlert("exception", ex.getMessage(), peerList);


        }
    }

    public void showAlert(String title, String message, Displayable disp) {
        showAlert(title, message, AlertType.INFO, 2000, disp);


    }

    public void showAlert(String title, String message, AlertType type,
            int timeOut, Displayable displayable) {
        Alert alert = new Alert(title, message, null, type);
        alert.setTimeout(timeOut);


        this.getDisplay().setCurrent(alert, displayable);



    }

    public void addChat(String fromAlias, String msg) {
        if (chatList == null) {

            chatList = getChatList();


        }
        this.chatList.append("[" + fromAlias + ":] " + msg, null);

    }
}
