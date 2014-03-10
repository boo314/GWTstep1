package ba.dum.tss.server;

public class Album {
	private String artist = "";
	private String album = "";
	private String apiKey = "0dedf5a696bdd3356c6e4e7077b2973b";
	
	public Album (String artist, String album){
		this.artist = artist;
		this.album = album;
	}
	
	public String getApiKey(){
		return apiKey;
	}
	
	public String getArtist(){
		return artist;
	}
	
	public void setArtist(String artist){
		this.artist = artist;
	}
	
	public String getAlbum(){
		return album;
	}
	
	public void setAlbum(String album){
		this.album = album;
	}
}
