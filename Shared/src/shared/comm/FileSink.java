package shared.comm;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.obex.Operation;

public class FileSink implements DataSink {

    private static final int TYPE = 0xFFDEAD;
    public static final int CHUNK_SIZE = 1024*30;

    /*
    public void consumeData(InputStream iStream, HeaderSet hSet) {
    try {
    String fileName = (String) hSet.getHeader(HeaderSet.NAME);
    int len = ((Integer) hSet.getHeader(HeaderSet.LENGTH)).intValue();
    FileConnection currDir = (FileConnection) Connector.open("file://SDCard/" + fileName);// TODO FIXME
    currDir.create();
    DataOutputStream oStream = currDir.openDataOutputStream();
    for (int i = 0; i < len; i++) {
    oStream.write(iStream.read());
    }
    } catch (IOException e) {
    e.printStackTrace();
    }
     */
    public long getType() {
        return TYPE;
    }

    public static byte[] consumeData(Operation op) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataInputStream in;
        try {
            in = op.openDataInputStream();
            byte[] data = new byte[CHUNK_SIZE];
            int bytesRead = 0;
            bytesRead = in.read(data);
            while (bytesRead >= 0) {
                out.write(data, 0, bytesRead);
                bytesRead = in.read(data);
            }

            in.close();
            out.close();
            byte[] arr = out.toByteArray();
            System.out.println("consume data Returning " + new String(arr));
            return arr;
        } catch (IOException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
            return "consumeDataException".getBytes();
        }

    }

    public static String consumeDataToFile(Operation op, String file) throws IOException {
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
                byte[] data = new byte[CHUNK_SIZE];
                int bytesRead = 0;
                bytesRead = in.read(data);
                while (bytesRead >= 0) {
                    os.write(data, 0, bytesRead);
                    bytesRead = in.read(data);
                }
                in.close();
                os.close();
                fc.close();
                return "done";
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                
                return "Exception";
            }
        } else {
            System.out.println("[client:] File Already Exists");
            
            fc.close();
            return "FileAlreadyExists";
        }

    }
}
