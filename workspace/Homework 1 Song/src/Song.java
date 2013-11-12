/**
 * Webcat is not my friend
 * @author Will Grayeski wfg2af
 *
 */
public class Song implements Comparable<Song>
{
	private String artist; 
	private String title;
	private int minutes;
	private int seconds;
	/**
	 * Makes a song
	 * @param artist is the artist
	 * @param title is the title
	 */
	public Song(String artist, String title)
	{
		this.artist = artist;
		this.title = title;
		this.minutes = 0;
		this.seconds = 0;
	}
	/**
	 * Makes a song
	 * @param artist is the artist
	 * @param title is the title
	 * @param minutes number of minutes in song
	 * @param seconds number of seconds in song
	 */
	public Song(String artist, String title, int minutes, int seconds)
	{
		this.artist = artist;
		this.title = title;
		this.minutes = minutes;
		this.seconds = seconds;
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
		System.out.printf("Playing Song: artist=%-20s title=%s\n", artist, title);
	}
	/**
	 * returns true if o equals this
	 */
	public boolean equals(Object o)
	{
		if (o instanceof Song)
		{
			Song s = (Song)o;
			return (artist.equals(s.getArtist()) && title.equals(s.getTitle()) && minutes == s.getMinutes() && seconds == s.getSeconds());
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
	 * returns song as a string in the format of "title:" title  "artist:" artist 
	 */
	public String toString() 
	{
		return "{Song: title=" + title + " artist=" + artist + "}";
	}
}
