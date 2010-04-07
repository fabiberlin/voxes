package shared.helpers;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;


public class Extractor {

        public static final int ID3_HEADER_SIZE = 128;
	public static long length =0;
	public static String Header=null;
	public static String SongTitle=null;
	public static String Artist=null;
	public static String Album=null;
	public static String Year=null;
	public static String Comment=null;
	public static String Genre=null;
	public static String[] genrearr={"Blues","Classic Rock","Country","Dance","Disco","Funk","Grunge",
	 "Hip-Hop","Jazz","Metal","New Age","Oldies","Other","Pop","R&B",
	 "Rap","Reggae","Rock","Techno","Industrial","Alternative","Ska",
	 "Death Metal","Pranks","Soundtrack","Euro-Techno","Ambient",
	 "Trip-Hop","Vocal","Jazz+Funk","Fusion","Trance","Classical",
	 "Instrumental","Acid","House","Game","Sound Clip","Gospel",
	 "Noise","AlternRock","Bass","Soul","Punk","Space","Meditative",
	 "Instrumental Pop","Instrumental Rock","Ethnic","Gothic",
	 "Darkwave","Techno-Industrial","Electronic","Pop-Folk",
	 "Eurodance","Dream","Southern Rock","Comedy","Cult","Gangsta",
	 "Top 40","Christian Rap","Pop/Funk","Jungle","Native American",
	 "Cabaret","New Wave","Psychadelic","Rave","Showtunes","Trailer",
	 "Lo-Fi","Tribal","Acid Punk","Acid Jazz","Polka","Retro",
	 "Musical","Rock & Roll","Hard Rock","Folk","Folk-Rock",
	 "National Folk","Swing","Fast Fusion","Bebob","Latin","Revival",
	 "Celtic","Bluegrass","Avantgarde","Gothic Rock","Progressive Rock",
	 "Psychedelic Rock","Symphonic Rock","Slow Rock","Big Band",
	 "Chorus","Easy Listening","Acoustic","Humour","Speech","Chanson",
	 "Opera","Chamber Music","Sonata","Symphony","Booty Bass","Primus",
	 "Porn Groove","Satire","Slow Jam","Club","Tango","Samba",
	 "Folklore","Ballad","Power Ballad","Rhythmic Soul","Freestyle",
	 "Duet","Punk Rock","Drum Solo","Acapella","Euro-House","Dance Hall"};

	public Extractor(){

        }

	public static byte[] getBytesFromFile(FileConnection file) throws IOException {
	    InputStream is = file.openInputStream();
	    length = file.fileSize();
            

            System.out.println(length + "is the length of file");

	    byte[] bytes = new byte[(int)Extractor.ID3_HEADER_SIZE];


	    /*int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length
	           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	        offset += numRead;
	    }*/

	    // Ensure all the bytes have been read in
	    /*if (offset < bytes.length) {
	        throw new IOException("Could not completely read file "+file.getName());
	    }*/

            is.skip((long) (length - Extractor.ID3_HEADER_SIZE));
            is.read(bytes, 0, Extractor.ID3_HEADER_SIZE);

	    is.close();
	    return bytes;
	}

	public static void TagExtractor(FileConnection filename) throws IOException{
		byte bytes[]=Extractor.getBytesFromFile(filename);
                String str = new String(bytes);
                System.out.println(str);
		byte[] temp;
		int i;
		temp=new byte[3];
                /*
		for(i=0;i<3;i++){
			temp[i]=bytes[i];
		}*/
		Header = new String(temp);
		//int k=(int)length-128;;
                int k =0;
                Header = "ID3";
		if(Header.equals("ID3"))
		{
			temp=new byte[30];
			for(i=0;i<30;i++){
				temp[i]=bytes[k+i+3];
			}
			SongTitle = new String(temp);

			for(i=0;i<30;i++){
				temp[i]=bytes[i+33+k];
			}
			Artist = new String(temp);

			for(i=0;i<30;i++){
				temp[i]=bytes[i+63+k];
			}
			Album = new String(temp);

			temp=new byte[4];
			for(i=0;i<4;i++){
				temp[i]=bytes[i+93+k];
			}
			Year = new String(temp);

			temp=new byte[30];
			for(i=0;i<30;i++){
				temp[i]=bytes[i+97+k];
			}
			Comment = new String(temp);

			temp=new byte[1];
			for(i=0;i<1;i++){
				temp[i]=bytes[i+127+k];
			}
			int index=(int)temp[0];
			Genre=genrearr[index];
		}

	}

	public static String[] getID3(String location) throws IOException{
                FileConnection fc = (FileConnection)Connector.open("file:///" + location);
		Extractor.TagExtractor(fc);
                String []tags = new String[3];
                tags[0] = SongTitle.trim();
                tags[1] = Artist.trim();
                tags[2] = Album.trim();
                System.out.println("[client]: id3 "+tags[0] +tags[1] +tags[2]);
                return tags;
	}

}
