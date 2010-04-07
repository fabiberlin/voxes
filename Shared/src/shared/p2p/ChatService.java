/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shared.p2p;

import java.io.IOException;
import javax.microedition.io.*;
import javax.obex.*;

/**
 *
 * @author Anshuman
 */
public class ChatService extends Thread
{
    public static String UUID_CHAT = "0B0B0B0B0B0B0B0B0B0B0B0B0B0B0B0B";
    public boolean stopNow;
    SessionNotifier sn;
    ChatListener listener;

    /*
     * Starts a new chating service on uuid UUID_CHAT and installs listener
     * for the newly created service
     */
    public ChatService(ChatListener listener) throws IOException
    {
        this.listener = listener;
        String url  = "btgoep://localhost:" + ChatService.UUID_CHAT + ";name=FTP;authenticate=false;master=false;encrypt=false";
        sn = (SessionNotifier) Connector.open(url);
        this.start();
    }
    public void run()
    {
        while(!stopNow)
        {
            try {
                sn.acceptAndOpen(listener);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void stopChatService()
    {
        stopNow = true;
        try {
            sn.close();
            this.join();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        catch(InterruptedException ex)
        {
            ex.printStackTrace();
        }
    }
}
