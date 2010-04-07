/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shared.comm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.obex.Operation;

/**
 *
 * @author Gautam
 */
public class MusicPreview {

    public static Player player;
    private static final int TYPE = 0xFFDEAD;
    public static final int CHUNK_SIZE = 1024;

    public static String previewData(Operation op, String file) throws IOException {
        DataOutputStream os = null;
        DataInputStream in;
        String url = "file:///" + file;
        System.out.println("[client:] Creating " + url);
        FileConnection fc = (FileConnection) Connector.open(url);
        if (!fc.exists()) {
            fc.create();

            System.out.println("[client:] File Created " + url);
            fc.setWritable(true);

            os = new DataOutputStream(fc.openOutputStream());

            try {
                in = op.openDataInputStream();
                try {
                    player = Manager.createPlayer(in, "audio/mpeg");
                    if(player != null){
                        player.setLoopCount(1);
                        player.start();
                    }
                } catch (MediaException ex) {
                    ex.printStackTrace();
                }

                player.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
    
}

