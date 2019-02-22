/**
 * AudioTrack.java
 * @author drishan
 * 
 * Abstract class for songs
 */

package main;

public abstract class AudioTrack {

	public String path;
	private boolean isPlaying;
	private String title;
	private int track;
	private String albumName;
	private String artistName;
	private String genre;
	private int length;

	public AudioTrack() {} 


	public boolean isPlaying() {
		return isPlaying;
	}

	public String getTitle() {
		return title;
	}

	public String getAlbum() {
		return albumName;
	}

	public String getArtistName() {
		return artistName;
	}

	public String getGenre() {
		return genre;
	}

	public int getLengthInSeconds() {
		return length;
	}

	public int getTrackID() {
		return track;
	};

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAlbum(String album) {
		albumName = album;
	}

	public void setArtist(String artist) {
		artistName = artist;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return title + " - " + artistName + " (" + albumName + ")";
	}

}
