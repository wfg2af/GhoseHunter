import java.util.*;
import java.io.*;
/**
 * 
 * @author Will Grayeski wfg2af, Robert Petri rdp2fm
 *
 */
public class PlayList implements Playable {
	private String name;
	private ArrayList<Playable> playableList;
	/**
	 * Creates PlayList that is empty and untitled
	 */
	public PlayList()
	{
		setName("Untitled");
		setPlayableList(new ArrayList<Playable>(0));
	}
	/**
	 * creates empty playlist
	 * @param name is the name of the playlist
	 */
	public PlayList(String name)
	{
		this.setName(name);
		setPlayableList(new ArrayList<Playable>(0));
	}
	/**
	 * Loads Songs
	 * @param fileName file loaded from
	 * @return true if songs loaded
	 */
	public boolean loadSongs(String fileName)
	{
		File songFile = new File(fileName);
		Scanner songRead;
		try {
			songRead = new Scanner(songFile);
			while (songRead.hasNextLine())
			{
				int mins = 0;
				int secs = 0;
				String songName = "";
				String artistName = "";
				String time = "";
				String mp3Name = "";
				songName = songRead.nextLine().trim();
				artistName = songRead.nextLine().trim();
				time = songRead.nextLine().trim();
				mp3Name = songRead.nextLine().trim();
				if (songRead.hasNextLine())
				{
					songRead.nextLine();
				}
			/*
			if (mp3Name == null)
			{
				songRead.close();
				return (false);
			}
			*/
				File test = new File(mp3Name);
				if (!test.exists())
				{
					songRead.close();
					return (false);
				}
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
				playableList.add(new Song(artistName, songName, mins, secs, mp3Name));
			}
			songRead.close();
			return (true);
		} catch (FileNotFoundException e) {
			return (false);
		}
	}
	/**
	 * returns true if playlists are equal
	 */
	public boolean equals(Object o)
	{
		PlayList pl;
		if (o instanceof PlayList)
		{
			pl = (PlayList)o;
		}
		else
		{
			return (false);
		}
		return (this.getName().equals(pl.getName()));
	}
	/**
	 * empties playlist
	 * @return true if playlist is emptied
	 */
	public boolean clear()
	{
		playableList = new ArrayList<Playable>(0);
		if (playableList.size() == 0)
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
		playableList.add(s);
		return (true);
		
	}

	/**
	 * sorts array alphabetically by artist
	 */
	public void sortByName()
	{
		Collections.sort(playableList, new PlayableNameCompare());
	}
	/**
	 * sorts array by increasing time
	 */
	public void sortByTime()
	{
		Collections.sort(playableList, new PlayableTimeCompare());
	}
    /**
     * plays through playlist
     */
	public void play()
	{
		for (Playable p: playableList)
		{
			if (p instanceof Song)
			{
				((Song)p).play();
			}
			if (p instanceof PlayList)
			{ 
				((PlayList)p).play();
			}
		}
	}
	/**
	 * Plays through playlist only playing seconds seconds of each song
	 */
	public void play(double seconds)
	{
		for (Playable p: playableList)
		{
			if (p instanceof Song)
			{
				((Song)p).play(seconds);
			}
			if (p instanceof PlayList)
			{
				((PlayList)p).play(seconds);
			}
		}
	}
	/**
	 * returns size of playlist
	 * @return size of playlist
	 */
	public int size()
	{
		return (playableList.size());
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
		for (Playable p: playableList)
		{
			if (p instanceof Song)
			{
				secs += ((Song)p).getPlayTimeSeconds();
			}
			if (p instanceof PlayList)
			{
				secs += ((PlayList)p).getPlayTimeSeconds();
			}
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
		for (Playable p: playableList)
		{
			if (p instanceof Song)
			{
				secs += ((Song)p).getPlayTimeSeconds();
			}
			if (p instanceof PlayList)
			{
				secs += ((PlayList)p).getPlayTimeSeconds();
			}
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
	public Playable getPlayable(int index)
	{
		if (index >= playableList.size() || index < 0 || playableList.size() == 0)
		{
			return (null);
		}
		return (playableList.get(index));
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
	public ArrayList<Playable> getPlayableList() {
		return playableList;
	}
    /**
     * set songList
     *
     */
	public void setPlayableList(ArrayList<Playable> pL) {
		this.playableList = pL;
	}
	/**
	 * Adds Playlists
	 * @param pl
	 * @return true if playlist was added succesfully
	 */
	public boolean addPlayList(PlayList pl)
	{
		for (Playable p: playableList)
		{
			if (p instanceof PlayList && ((PlayList)p).getName().equals(((PlayList)pl).getName()))
			{
				return (false);
			}
		}
		playableList.add(pl);
		return (true);
	}
	/**
	 * returns string representation of the playlist
	 */
	public String toString()
	{
		String allPlayables = "";
		for (Playable p: playableList)
		{
			allPlayables = allPlayables + ", " + p.toString();
		}
		return (getName() + " " + allPlayables);
	}

}
