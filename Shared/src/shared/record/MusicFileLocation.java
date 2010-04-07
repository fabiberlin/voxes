package shared.record;

import java.util.Vector;

public class MusicFileLocation {

    public MusicFile musicfile;
    public String location;

    public MusicFileLocation(MusicFile musicfile , String location){
        this.musicfile = musicfile;
        this.location = location;
    }

    public static String toString ( MusicFileLocation f ){
		String data ="";
		data += MusicFile.toString(f.musicfile) + "#";
		data += f.location;
		return data;
	}
    public static MusicFileLocation fromString ( String str ) {
		int index = str.indexOf("#");
		return new MusicFileLocation (MusicFile.fromString(str.substring(0, index)), str.substring(index + 1));
	}

	public static String toStringV ( Vector v ) {
		String str = "";
		for(int i=0;i < v.size() ; i++) {
			str += MusicFileLocation.toString(((MusicFileLocation) v.elementAt(i))) + "?";
		}
		return str;
	}

	public static Vector fromStringV ( String str ) {
		int index;
		Vector v = new Vector();
		while ((index = str.indexOf("?")) != -1) {
			v.addElement(MusicFileLocation.fromString(str.substring(0, index)));
			str = str.substring(index + 1);
		}
		return v;
	}
}
