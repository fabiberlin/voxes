package shared.record;

public class MusicFile {

	public String title;
        public String artist;
        public String album;
	
	public MusicFile ( String title , String artist , String album)
	{
		this.title = title;
                this.artist = artist;
                this.album = album;
	}
	
	public static String toString ( MusicFile f )
	{
		return f.title + "^" + f.artist + "^" +f.album;
	}
	
	public static MusicFile fromString ( String str )
	{
                int pos1 = str.indexOf("^");
                String title = str.substring(0, pos1);
                int pos2 = str.substring(pos1 + 1).indexOf("^");
                String artist= str.substring(pos1 + 1).substring(0, pos2);
                String album = str.substring(pos1 + 1).substring(pos2 + 1);
		return new MusicFile (title , artist , album);
	}
}
