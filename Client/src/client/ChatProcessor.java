/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import shared.p2p.ChatListener;

/**
 *
 * @author V.Amarnath Raju
 */
public class ChatProcessor extends ChatListener
{
    BTClient client;
    ChatProcessor(BTClient client)
    {
        this.client = client;
    }
    public void processMessage(String msg, String fromAlias, String btAddress)
    {
        client.gui.addChat(fromAlias, msg);
        client.gui.showAlert("Chat", "New Message Received", client.gui.getChatList());
    }
}
