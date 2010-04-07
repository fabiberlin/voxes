package shared.record;

import java.util.Hashtable;
import java.util.Vector;


public class MusicData {
	
	public Hashtable titleHashTable;
	public Hashtable artistHashTable;
        public Hashtable albumHashTable;
	
	public MusicData() {
		titleHashTable = new Hashtable();
                artistHashTable = new Hashtable();
		albumHashTable = new Hashtable();
		return;
	}

        public void addField(String field , FileLocation filelocation , Hashtable table){
            if(field == null || filelocation == null) return;
		if(table.get(field) == null){
                        System.out.println("[client:] null");
			Vector toAdd = new Vector();
			toAdd.addElement(filelocation);
			table.put( field , toAdd );
		}
		else{
			Vector toAdd = (Vector)table.get( field );
                        System.out.println("[client:] not null" + toAdd);
			toAdd.addElement( filelocation );
			table.put(field,toAdd);
		}
            return;
        }
	
	public void addFile(MusicFile file,FileLocation filelocation){
		this.addField( file.title , filelocation, this.titleHashTable);
                this.addField( file.artist , filelocation , this.artistHashTable);
                this.addField( file.album, filelocation, this.albumHashTable);
                return;
	}

        //TODO Change this function entirely
        public Vector searchFile(String str){
                Vector search = new Vector();

                /* Search corresponding to title */
                Vector titleLoc = (Vector) titleHashTable.get(str);
                if (titleLoc != null) { // title matches
                    for(int i=0 ;i < titleLoc.size(); i++){
                        if(((FileLocation)titleLoc.elementAt(i)).record.isConnected)
                            search.addElement(titleLoc.elementAt(i));
                    }
                }

                Vector artistLoc = (Vector) artistHashTable.get(str);
                if (artistLoc != null) {
                    for(int i=0 ;i < artistLoc.size();i++){
                        if(((FileLocation)titleLoc.elementAt(i)).record.isConnected)
                            search.addElement(artistLoc.elementAt(i));
                    }
                }

                Vector albumLoc = (Vector) albumHashTable.get(str);
                if (albumLoc != null) {
                    for(int i=0 ;i<albumLoc.size();i++){
                        if(((FileLocation)titleLoc.elementAt(i)).record.isConnected)
                            search.addElement(albumLoc.elementAt(i));
                    }
                }

		return search;
	}

	
	public void printData () 
	{
		System.out.println(titleHashTable + "" +artistHashTable + "" +albumHashTable);
	}

}
