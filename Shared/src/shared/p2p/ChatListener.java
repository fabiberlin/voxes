/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shared.p2p;

import shared.header.HeaderSets;
import java.io.IOException;
import javax.bluetooth.LocalDevice;
import javax.obex.*;
import shared.comm.FileSink;

/**
 *
 * @author Anshuman
 */
public class ChatListener extends ServerRequestHandler
{
    LocalDevice localDevice;
    SessionNotifier sn;

    /*
     * handles 'put' opertations
     */
    public ChatListener()
    {
    }
    public void stopListener()
    {
        try {
            sn.close();
        } catch (IOException ex) {
        }
    }
    public int onConnect(HeaderSet request, HeaderSet reply)
    {
        return ResponseCodes.OBEX_HTTP_OK;
    }
    public int onPut(Operation op)
    {
        HeaderSet hdr;
        try {
            hdr = op.getReceivedHeaders();
            byte[] msgInBytes = FileSink.consumeData(op);
            op.close();
            processMessage((String) new String(msgInBytes), (String) hdr.getHeader(HeaderSets.CLIENT_ALIAS), (String) hdr.getHeader(HeaderSets.CLIENT_ID));
            //op.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ResponseCodes.OBEX_HTTP_OK;
    }

    /*
     * The function is called when a new chat message arrives
     *
     * msg : message body
     *
     * fromAlias : alias of the sender
     *
     * btAddress : bluetooth address of the sender
     * 
     */
    public void processMessage(String msg, String fromAlias , String btAddress)
    {
        return;
    }
}
