package client;

import shared.helpers.Extractor;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.io.file.FileSystemRegistry;

import javax.obex.ClientSession;
import javax.obex.HeaderSet;
import javax.obex.Operation;
import shared.gui.AlertDisplayer;
import shared.header.HeaderSets;
import shared.header.Types;
import shared.record.MusicFile;

public class Publisher {
	
	public BTClient client;
	private static final String UP_DIRECTORY = "..";

    /* special string that denotes upper directory accessible by this browser.
     * this virtual directory contains all roots.
     */
        private static final String MEGA_ROOT = "/";

    /* separator string as defined by FC specification */
        private static final String SEP_STR = "/";

    /* separator character as defined by FC specification */
        private static final char SEP = '/';
	
        public Publisher ( BTClient client )
	{
		this.client = client;
	}

        public void publish ( final String fileLocation , final String name )
        {
            new Thread(new Runnable() {
                public void run ()
                {
                    System.out.println("[client:] Request to publish " + fileLocation);
                    if (fileLocation.charAt(fileLocation.length() - 1) == SEP) {
                        publishDirectory(fileLocation);
                    }
                    else/* if (fileLocation.endsWith("mp3"))*/ {
                        System.out.println("[client:] It was a file!");
                        publishFile (fileLocation, name);
                    }
                }
            }).start();
            
        }

	public void publishFile ( String fileLocation , String name)
	{
            try {
                String[] tags = Extractor.getID3(fileLocation);
                //    System.out.println(tags);

                String title = tags[0];
                String artist = tags[1];
                String album = tags[2];
                ClientSession hubConn = client.hubConn;
                System.out.println(title + artist + album);
                /*
                 * Get the music File object
                 * treating name to be title atm
                 */
                MusicFile file = new MusicFile(title, artist, album);
                //MusicFile file = new MusicFile (name, name, name);
                try {
                    client.connect();
                    HeaderSet hdr = hubConn.createHeaderSet();
                    hdr.setHeader(HeaderSets.REQUEST_TYPE, Types.REQUEST_PUBLISH_FILE);
                    hdr.setHeader(HeaderSets.FILE_LOCATION, fileLocation);
                    hdr.setHeader(HeaderSet.NAME, client.localDevice.getBluetoothAddress());
                    Operation op = hubConn.put(hdr);
                    System.out.println("Put Conn Established");
                    OutputStream os = op.openOutputStream();
                    System.out.println("Output Stream opened");
                    os.write(MusicFile.toString(file).getBytes());
                    System.out.println("Written");
                    os.close();
                    System.out.println("Closed");
                    op.close();
                    AlertDisplayer.showAlert("Success", "File Shared",
                                                    client.gui.getDisplay(), client.gui.getFileBrowser());
                    client.disconnect();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    System.out.println("IOException at 38 : Publisher");
                    e.printStackTrace();
                }
            } catch (Exception ex) {
                    System.out.println("problem wid id3tag");
                    ex.printStackTrace();
            }
			
	}
	
	public void publishDirectory ( String currDirName ) {
	
            Enumeration e = null;
            FileConnection currDir = null;


            try {
                currDir = (FileConnection) Connector.open("file:///" + currDirName);
                e = currDir.list();

            } catch (IOException ioe) {
            }

            if (e == null) {
                try {
                    currDir.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                return;
            }

            while (e.hasMoreElements()) {
                String fileName = (String) e.nextElement();

                if (fileName.charAt(fileName.length() - 1) == SEP) {
                    // This is directory
                    publishDirectory(currDirName + fileName);
                } else {
                    // this is regular file
                    if ( fileName.endsWith("mp3") == true  ) {
                        System.out.println("[client:] Publishing " + fileName);
                        System.out.println(currDir.getURL());
                        publishFile(currDir.getURL() , fileName);
                    }
                }
            }

            if (currDir != null) {
                try {
                    currDir.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            
            
            
	}
}
