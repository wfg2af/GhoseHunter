import java.util.*;
import java.io.*;
/**
 * 
 * @author Will Grayeski wfg2af
 *
 */
public class PlayList {
	private String name;
	private ArrayList<Song> songList;
	/**
	 * Creates PlayList that is empty and untitled
	 */
	public PlayList()
	{
		setName("Untitled");
		setSongList(new ArrayList<Song>(0));
	}
	/**
	 * creates empty playlist
	 * @param name is the name of the playlist
	 */
	public PlayList(String name)
	{
		this.setName(name);
		setSongList(new ArrayList<Song>(0));
	}
	/**
	 * Loads Songs
	 * @param fileName file loaded from
	 * @return true if songs loaded
	 * @throws FileNotFoundException if file is not found
	 */
	public boolean loadSongs(String fileName) throws FileNotFoundException
	{
		File songFile = new File(fileName);
		Scanner songRead = new Scanner(songFile);
		while (songRead.hasNext())
		{
			int mins = 0;
			int secs = 0;
			String songName = "";
			String artistName = "";
			String time = "";
			songName = songRead.nextLine().replace(" ", "");
			artistName = songRead.nextLine().replace(" ", "");
			time = songRead.nextLine().replace(" ", "");
			int index = 0;
			while (!time.substring(index, index + 1).equals(":"))
			{
				int addTo = Integer.parseInt(time.substring(index, index + 1));
				mins = (mins * 10) + addTo;
				index++;
			}
			secs = Integer.parseInt(time.substring(index + 1, time.length()));
			for (int counter = secs; counter >= 60; counter -= 60)
			{
				mins++;
				secs -= 60;
			}
		
			songList.add(new Song(artistName, songName, mins, secs));
			songRead.nextLine();
		}
		songRead.close();
		return (true);
	}
	/**
	 * empties playlist
	 * @return true if playlist is emptied
	 */
	public boolean clear()
	{
		songList = new ArrayList<Song>(0);
		if (songList.size() == 0)
		{
			return (true);
		}
		else
		{
			return (false);
		}
	}
	/**
	 * Puts song in playlist
	 * @param s is the song added
	 * @return true if song is added
	 */
	public boolean addSong(Song s)
	{
		songList.add(s);
		return (true);
		
	}
	/**
	 * removes song at index
	 * @param index of song to remove
	 * @return song removed. Null if index is not in array or array is empty.
	 */
	public Song removeSong(int index)
	{
		if (index < 0 || index >= songList.size())
		{
			return (null);
		}
		Song s = songList.get(index);
		songList.remove(index);
		return (s);
	}
	/**
	 * remove chosen song
	 * @param s song to remove
	 * @return song removed, or null if song is not in playlist
	 */
	public Song removeSong(Song s)
	{
		int before = songList.size();
		for (int index = songList.size() - 1; index >= 0; index--)
		{
			if (songList.get(index).equals(s))
			{
				songList.remove(index);
			}
		}
		if (songList.size() == before)
		{
			return (null);
		}
		return (s);
	}
	/**
	 * sorts array alphabetically by artist
	 */
	public void sortByArtist()
	{
		Collections.sort(songList);
	}
    /**
     * plays through playlist
     */
	public void play()
	{
		for (Song s: songList)
		{
			s.play();
		}
	}
	/**
	 * returns size of playlist
	 * @return size of playlist
	 */
	public int size()
	{
		return (songList.size());
	}
    /**
     * Total time played
     * @return total time played in HH:MM:SS
     */
	public String totalPlayTime()
	{
		int hours = 0;
		int mins = 0;
		int secs = 0;
		String time = "";
		for (Song s: songList)
		{
			mins += s.getMinutes();
			secs += s.getSeconds();
		}
		for (int counter = secs; counter >= 60; counter -= 60)
		{
			mins++;
			secs -= 60;	
		}
		for (int counter = mins; counter >= 60; counter -= 60)
		{
			hours++;
			mins -= 60;	
		}
		if (hours > 0)
		{
			time = "" + (hours / 10) + (hours % 10) + ":";
		}
		time = time + "" + (mins / 10) + (mins % 10) + ":" + (secs / 10) + (secs % 10);
		return (time);
	}
	/**
	 * return total play time in seconds
	 * @return total play time as an int in seconds
	 */
	public int getPlayTimeSeconds()
	{
		int secs = 0;
		int mins = 0;
		for (Song s: songList)
		{
			mins += s.getMinutes();
			secs += s.getSeconds();
		}
		for (int counter = mins; counter > 0; counter--)
		{
			secs += 60;
			mins--;
		}
		return (secs);
	}
	/**
	 * returns song
	 * @param index return song at index
	 * @return song at index
	 */
	public Song getSong(int index)
	{
		if (index >= songList.size() || index < 0 || songList.size() == 0)
		{
			return (null);
		}
		return (songList.get(index));
	}
    /**
     * name
     * @return name
     */
	public String getName() {
		return name;
	}
    /**
     * set name
     * @param name new name
     */
	public void setName(String name) {
		this.name = name;
	}
    /**
     * returns songList
     * @return songList
     */
	public ArrayList<Song> getSongList() {
		return songList;
	}
    /**
     * set songList
     * @param songList new songList
     */
	public void setSongList(ArrayList<Song> songList) {
		this.songList = songList;
	}


}
