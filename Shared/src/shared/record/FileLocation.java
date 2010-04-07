package shared.record;

import java.util.Vector;

public class FileLocation {
	
	public ClientRecord record;
	public String location;
	
	public FileLocation ( ClientRecord record , String location )
	{
		this.record = record;
		this.location = location;
	}
	
	
	public static String toString ( FileLocation f ){
		String data ="";
		data += ClientRecord.toString(f.record) + "#";
		data += f.location;
		return data;
	}
	
	public static FileLocation fromString ( String str ) {
		int index = str.indexOf("#");
		return new FileLocation (ClientRecord.fromString(str.substring(0, index)), str.substring(index + 1));
	}
	
	public static String toStringV ( Vector v ) {
		String str = "";
		for(int i=0;i < v.size() ; i++) {
			str += FileLocation.toString(((FileLocation) v.elementAt(i))) + "?";
		}
		return str;
	}
	
	public static Vector fromStringV ( String str ) {
		int index;
		Vector v = new Vector();
		while ((index = str.indexOf("?")) != -1) {
			v.addElement(FileLocation.fromString(str.substring(0, index)));
			str = str.substring(index + 1);
		}
		return v;
	}
	

}
