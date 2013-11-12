import java.io.File;
import edu.virginia.cs2110.Mp3FilePlayer;

/**
 * webcat is not friendly to me, or anyone
 * @author Will Grayeski wfg2af, Robert Petri rdp2fm
 *
 */
public class Song implements Comparable<Song>, Playable
{
	private String artist; 
	private String title;
	private int minutes;
	private int seconds;
	private String fileName;
	/**
	 * Makes a song
	 * @param artist is the artist
	 * @param title is the title
	 */
	public Song(String artist, String title, String fileName)
	{
		this.artist = artist;
		this.title = title;
		this.minutes = 0;
		this.seconds = 0;
		this.fileName = fileName;
	}
	/**
	 * Makes a song
	 * @param artist is the artist
	 * @param title is the title
	 * @param minutes number of minutes in song
	 * @param seconds number of seconds in song
	 */
	public Song(String artist, String title, int minutes, int seconds, String fileName)
	{
		this.artist = artist;
		this.title = title;
		this.minutes = minutes;
		this.seconds = seconds;
		this.fileName = fileName;
		for (int counter = this.seconds; counter >= 60; counter -= 60)
		{
			this.minutes++;
			this.seconds -= 60;	
		}
	}
	/**
	 * Makes a song
	 * @param s song
	 */
	public Song(Song s)
	{
		this.artist = s.getArtist();
		this.title = s.getTitle();
		this.minutes = s.getMinutes();
		this.seconds = s.getSeconds();
		this.fileName = fileName;
		for (int counter = this.seconds; counter >= 60; counter -= 60)
		{
			this.minutes++;
			this.seconds -= 60;	
		}
	}
	/**
	 * plays the song
	 */
	public void play() 
	{
		Mp3FilePlayer mp3 = new Mp3FilePlayer(fileName);
		mp3.playAndBlock();
	}
	/**
	 * plays seconds secs of the song
	 */
	public void play(double secs) 
	{
		Mp3FilePlayer mp3 = new Mp3FilePlayer(fileName);
		mp3.playAndBlock(secs);
	}
	
	/**
	 * returns true if o equals this
	 */
	public boolean equals(Object o)
	{
		if (o instanceof Song)
		{
			Song s = (Song)o;
			return (artist.equals(s.getArtist()) && title.equals(s.getTitle()) && minutes == s.getMinutes() && seconds == s.getSeconds() && fileName.equals(s.getFileName()));
		}
		else
		{
			return (false);
		}
	}

    /**
     * compares arg0 to this. if this is less than arg0 return -1, if equal return 0, if arg0 is less than this return 1
     */
	@Override
	public int compareTo(Song arg0) {
		if (this.equals(arg0))
		{
			return (0);
		}
		else if (this.getArtist().compareTo(arg0.getArtist()) == 0)
		{
			if (this.getTitle().compareTo(arg0.getTitle()) == 0)
			{
				return (((this.getMinutes() * 60) + this.getSeconds()) - ((arg0.getMinutes() * 60) + arg0.getSeconds()));
			}
			else
			{
				return (this.getTitle().compareTo(arg0.getTitle()));
			}
		}
		else
		{
			return (this.getArtist().compareTo(arg0.getArtist()));
		}
	}




	public String getArtist() {
		return artist;
	}




	public void setArtist(String artist) {
		this.artist = artist;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public int getMinutes() {
		return minutes;
	}




	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}




	public int getSeconds() {
		return seconds;
	}



    /**
     * Changes seconds to int seconds and then updates minutes if seconds is >= 60
     * @param seconds is new seconds
     */
	public void setSeconds(int seconds) {
		this.seconds = seconds;
		for (int counter = this.seconds; counter >= 60; counter -= 60)
		{
			this.minutes++;
			this.seconds -= 60;	
		}
	}
	/**
	 * return file name
	 * @return
	 */
	public String getFileName()
	{
		return (fileName);
	}
	/**
	 * return total playtime in seconds
	 */
	public int getPlayTimeSeconds()
	{
		int secs = 0;
		int mins = 0;
		mins += getMinutes();
		secs += getSeconds();
		for (int counter = mins; counter > 0; counter--)
		{
			secs += 60;
			mins--;
		}
		return (secs);
	}
	public String getName()
	{
		return (title);
	}
	/**
	 * returns song as a string in the format of "title:" title  "artist:" artist 
	 */
	public String toString() 
	{
		return "{Song: title=" + title + " artist=" + artist + " minutes=" + minutes + " seconds=" + seconds + "}";
	}
}
