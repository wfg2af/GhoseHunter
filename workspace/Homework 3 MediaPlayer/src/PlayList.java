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
	public boolean loadMedia(String fileName)
	{
		File mediaFile = new File(fileName);
		Scanner mediaRead;
		try {
			mediaRead = new Scanner(mediaFile);
			while (mediaRead.hasNextLine())
			{
				boolean isVideo = false;
				int mins = 0;
				int secs = 0;
				String mediaTitle = "";
				String artistName = "";
				String time = "";
				String mediaName = "";
				while (mediaTitle.equals("") && mediaRead.hasNextLine())
				{
					mediaTitle = mediaRead.nextLine().trim();
					if (mediaTitle.indexOf("//") != -1)
					{
						mediaTitle = mediaTitle.substring(0, mediaTitle.indexOf("//"));
					}
					mediaTitle = mediaTitle.trim();
				}
				while (artistName.equals("") && mediaRead.hasNextLine())
				{
					artistName = mediaRead.nextLine().trim();
					if (artistName.indexOf("//") != -1)
					{
						artistName = artistName.substring(0, artistName.indexOf("//"));
					}
					artistName = artistName.trim();
				}
				while (time.equals("") && mediaRead.hasNextLine())
				{
					time = mediaRead.nextLine().trim();
					if (time.indexOf("//") != -1)
					{
						time = time.substring(0, time.indexOf("//"));
					}
					time = time.trim();
				}
				while (mediaName.equals("") && mediaRead.hasNextLine())
				{
					mediaName = mediaRead.nextLine().trim();
					if (mediaName.indexOf("//") != -1)
					{
						mediaName = mediaName.substring(0, mediaName.indexOf("//"));
					}
					mediaName = mediaName.trim();
				}
				
				
				if (mediaName.length() > 7 && mediaName.substring(0, 8).toLowerCase().equals("youtube:"))
				{
					isVideo = true;
					mediaName = "http://" + mediaName.substring(8, mediaName.length());
				}
				else
				{
					File test = new File(mediaName);
					if (!test.exists())
					{
						mediaRead.close();
						return (false);
					}
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
				if (isVideo)
				{
					playableList.add(new Video(artistName, mediaTitle, mins, secs, mediaName));
				}
				else
				{
					
					playableList.add(new Song(artistName, mediaTitle, mins, secs, mediaName));
				}
				if (mediaRead.hasNextLine())
				{
					mediaRead.nextLine();
				}
			}
			mediaRead.close();
			return (true);
		} catch (FileNotFoundException e) {
			System.out.println("hi");
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
	 * adds either a song or a video to the current playlist object
	 * @param p playable object
	 * @return true if add was successful
	 */
	public boolean addPlayable(Playable p)
	{
		playableList.add(p);
		return (true);
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
