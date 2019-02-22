package main;

public class WavFile {
	private String title;
	private String album;
	private String artist;

	public WavFile() {
		title = "unknown title";
		artist = "unknown artist";
		album = "unknown album";
	}
	
	public WavFile(String title, String album) {
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
