import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 * @author Will Grayeski wfg2af, Robert Petri rdp2fm
 *
 */
public class Mp3Player {
	private ArrayList<PlayList> playlists;
	private Scanner userInput;
	/**
	 * creates new Mp3 Player which holds all playlists
	 */
	public Mp3Player()
	{
		playlists = new ArrayList<PlayList>(0);
		playlists.add(new PlayList("playlist"));
		userInput = new Scanner(System.in);
	}
	/**
	 * 
	 * @return finds and returns the default playList named "playlist"
	 */
	public PlayList getDefaultPlayList()
	{
		for (int index = 0; index < playlists.size(); index++)
		{
			if (playlists.get(index).getName().equals("playlist"))
			{
				return (playlists.get(index));
			}
		}
		return (playlists.get(0));
	}
	/**
	 * 
	 * @return returns all playLists in playlists
	 */
	public ArrayList<PlayList> getPlayLists()
	{
		return (playlists);
	}
	/**
	 * start is the method that loads songs given by the user and plays the first 5 seconds of each song after printing all the data 
	 * from each song.
	 */
	public void start()
	{
		String command = "";
		System.out.println("Hello there friend! Good to see you again. What song would you like me to play?\nMake sure you type in the .txt document location exactly! Thank you.");
		command = userInput.nextLine();
		playlists.get(0).loadSongs(command);
		System.out.println(playlists.get(0));
		playlists.get(0).play(5);
	}
	/**
	 * main method for testing
	 * @param args
	 */
	public static void main(String[] args) {
		Mp3Player musicPlayer = new Mp3Player();
		musicPlayer.start();
		
		
	}

}
