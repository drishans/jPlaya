package main;

public class Mp3File extends AudioTrack {
	
	private String title;
	private String album;
	private String artist;

	public Mp3File() {
		title = "unknown title";
		artist = "unknown artist";
		album = "unknown album";
	}
	
	public Mp3File(String title, String album) {
		this.title = title;
		this.album = album;
	}
	
	public String getTitle() {
		return title;
	}

	public String getAlbum() {
		return album;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
}
