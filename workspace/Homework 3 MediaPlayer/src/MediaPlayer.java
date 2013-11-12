import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Will Grayeski wfg2af, Robert Petri rdp2fm
 *
 */
public class MediaPlayer {
	private ArrayList<PlayList> playlists;
	private Scanner userInput;
	/**
	 * creates new Mp3 Player which holds all playlists
	 */
	public MediaPlayer()
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
	 * from each media thingy.
	 */
	public void start()
	{

		String command = "";
		System.out.println("Hey there best buddy ever. Here is a list of commands you can enter. I hope you have a great time.");
		commandList();
		while (!command.equals("end"))
		{
			System.out.println("Please give me a command.");
			command = userInput.nextLine();
			while (incorrectCommand(command.toLowerCase()))
			{
				
				System.out.println("That is an incorrect command. Please refer to the command list and try again.");
				commandList();
				System.out.println("Please give me a command.");
				System.out.println(command.toLowerCase());
				command = userInput.nextLine();
			}
			if (command.toLowerCase().equals("add play list"))
			{
				System.out.println("Please enter the name of the playlist you want to add");
				command = userInput.nextLine();
				PlayList p = new PlayList(command);
				if (!addPlayList(p))
				{
					System.out.println("I'm sorry but that play list already exists.");
				}
				else
				{
					System.out.println("Tada! Your play list was added.");
				}
				
			}
			if (command.toLowerCase().equals("add play list to play list"))
			{
				System.out.println("Please enter the name of the playlist you want to add");
				command = userInput.nextLine();
				PlayList p1 = new PlayList(command);
				System.out.println("Please enter the name of the play list you want your new play list to be added to");
				command = userInput.nextLine();
				PlayList addingTo = new PlayList(command);
				while (!playListExists(addingTo))
				{
					System.out.println("Sorry but that play list doesn't exist please try again.");
					System.out.println("Please enter the name of the play list you want your new play list to be added to");
					command = userInput.nextLine();
					addingTo = new PlayList(command);
				}
				if (!addPlayListToPlayList(addingTo, p1))
				{
					System.out.println("I'm sorry but either that play list already exists in your chosen play list or your play list\nhas the same name as the chosen play list.");
				}
				else
				{
					System.out.println("Guess what buddy? Your playlist was added!");
				}
				
			}
			if (command.toLowerCase().equals("load file"))
			{
				System.out.println("Please enter the file that you want to load.");
				String file = userInput.nextLine();
				System.out.println("Now please enter the play list you want to load to.");
				command = userInput.nextLine();
				PlayList addingTo = new PlayList(command);
				while (!playListExists(addingTo))
				{
					System.out.println("Sorry but that play list doesn't exist please try again.");
					System.out.println("Please enter the name of the play list you want to load to.");
					command = userInput.nextLine();
					addingTo = new PlayList(command);
				}
				if (!loadFile(file, addingTo))
				{
					System.out.println("Sorry but the media could not be loaded.");
				}
				else
				{
					System.out.println("Media loaded correctly!!! Alright!");
				}
				
			}
			if (command.toLowerCase().equals("add song"))
			{
				System.out.println("Please type the mp3 file that you want to add.");
				String mp3 = userInput.nextLine();
				System.out.println("Now please enter the play list you want to add to.");
				command = userInput.nextLine();
				PlayList addingTo = new PlayList(command);
				while (!playListExists(addingTo))
				{
					System.out.println("Sorry but that play list doesn't exist please try again.");
					System.out.println("Please enter the name of the play list you want to load to.");
					command = userInput.nextLine();
					addingTo = new PlayList(command);
				}
				if (!addSong(mp3, addingTo))
				{
					System.out.println("Sorry but your song couldn't be added");
				}
				else
				{
					System.out.println("Success!!!! You added a song. I'm so proud.");
				}
			}
			if (command.toLowerCase().equals("play"))
			{
				System.out.println("Now please enter the play list you want to play.");
				command = userInput.nextLine();
				PlayList playing = new PlayList(command);
				while (!playListExists(playing))
				{
					System.out.println("Sorry but that play list doesn't exist please try again.");
					System.out.println("Please enter the name of the play list you want to play.");
					command = userInput.nextLine();
					playing = new PlayList(command);
				}
				System.out.println("How much of each song do you want to play? Please enter a number for the time or all if you want each\n song to just play all the way through.");
				double time = -1;
				command = "";
				if (userInput.hasNextDouble())
				{
					time = userInput.nextDouble();
					if (time < 0)
					{
						System.out.println("Woah there, what's next dividing by 0? You can't play negative seconds! Time is now 0");
						time = 0.0;
					}
				}
				else
				{
					command = userInput.nextLine();
					while (!command.equals("all"))
					{
						System.out.println("You didn't actually say anything that I can read. So I am assuming you said all");
						command = "all";
					}
				}
				if (time < 0)
				{
					if (!playPlaylist(playing))
					{
						System.out.println("Couldn't play it. woops");
					}
					else
					{
						System.out.println("Played it! Cool.");
					}
				}
				else
				{
					if (!playPlaylist(playing, time))
					{
						System.out.println("Couldn't play it. woops");
					}
					else
					{
						System.out.println("Played it! Cool.");
					}
				}

			}
			if (command.toLowerCase().equals("print default"))
			{
				printDefaultContents();
			}
			if (command.toLowerCase().equals("print play list"))
			{
				System.out.println("Now please enter the play list you want to print out.");
				command = userInput.nextLine();
				PlayList printer = new PlayList(command);
				while (!playListExists(printer))
				{
					System.out.println("Sorry but that play list doesn't exist please try again.");
					System.out.println("Please enter the name of the play list you want to print out.");
					command = userInput.nextLine();
					printer = new PlayList(command);
				}
				printPlaylist(printer);
			}
			if (command.toLowerCase().equals("play mp3"))
			{
				System.out.println("Please enter the name of the mp3 file you want to play");
				String fileName = userInput.nextLine();
				System.out.println("How much of each song do you want to play? Please enter a number for the time or all if you want each\n song to just play all the way through.");
				double time = -1;
				command = "";
				if (userInput.hasNextDouble())
				{
					time = userInput.nextDouble();
					if (time < 0)
					{
						System.out.println("Woah there, what's next dividing by 0? You can't play negative seconds! Time is now 0");
						time = 0.0;
					}
				}
				else
				{
					command = userInput.nextLine();
					while (!command.equals("all"))
					{
						System.out.println("You didn't actually say anything that I can read. So I am assuming you said all");
						command = "all";
					}
				}
				if (time < 0)
				{
					if (!playMp3(fileName))
					{
						System.out.println("Couldn't play it. woops");
					}
					else
					{
						System.out.println("Played it! Cool.");
					}
				}
				else
				{
					if (!playMp3(fileName, time))
					{
						System.out.println("Couldn't play it. woops");
					}
					else
					{
						System.out.println("Played it! Cool.");
					}
				}
			}
			if (command.toLowerCase().equals("command list"))
			{
				commandList();
			}
			/*
			if (!command.toLowerCase().equals("end"));
			{
				command = "";
			}
			*/
			
		}
		

	}
	/**
	 * adds playlist to media player
	 * @return true if playlist doesn't already exist in media player
	 */
	public boolean addPlayList(PlayList add)
	{
		for (PlayList p: playlists)
		{
			if (p.getName().equals(add.getName()))
			{
				return (false);
			}
		}
		playlists.add(add);
		return (true);
	}
	/**
	 * adds playlist to a playlist in media player
	 * @param pl is the wanted playlist to add
	 * @return true if playlist doesn't already exist in media player
	 */
	public boolean addPlayListToPlayList(PlayList pl, PlayList add)
	{
		for (PlayList p: playlists)
		{
			if (p.getName().equals(pl.getName()))
			{
				if (p.getName().equals(add.getName()))
				{
					return false;
				}
				else
				{
					return p.addPlayable(add);
				}
			}
		}
		return (false);
	}
	/**
	 * loads videos or songs to specified playlist
	 * @param fileName song or video file
	 * @param playList wanted playlist to load to
	 * @return true if media is loaded
	 */
	public boolean loadFile(String fileName, PlayList playList)
	{
		for (PlayList p: playlists)
		{
			if (p.getName().equals(playList.getName()))
			{
				return p.loadMedia(fileName);
			}
		}
		return false;
	}
	/**
	 * adds individual song to specified playlist
	 * @param mp3Name  mp3 file
	 * @param playList  wanted playlist
	 * @return true if song was added
	 */
	public boolean addSong(String mp3Name, PlayList playList)
	{
		for (PlayList p: playlists)
		{
			if (p.getName().equals(playList.getName()))
			{
				return p.addSong(new Song("Unkown", "Unknown", mp3Name));
			}
		}
		return false;
	}
	/**
	 * Plays specified playlist
	 * @param p wanted playlist
	 * @param seconds seconds for songs to play
	 * @return true if playlist is played
	 */
	public boolean playPlaylist(PlayList p, double seconds)
	{
		for (PlayList choose: playlists)
		{
			if (choose.getName().equals(p.getName()))
			{
				p.play(seconds);
				return true;
			}
		}
		return false;
	}
	/**
	 * plays specified playlist
	 * @param p wanted playlist
	 * @return true if playlist is played
	 */
	public boolean playPlaylist(PlayList p)
	{
		for (PlayList choose: playlists)
		{
			if (choose.getName().equals(p.getName()))
			{
				p.play();
				return true;
			}
		}
		return false;
	}
	/**
	 * prints out contents of specified playlist
	 * @param playlist wanted playlist
	 */
	public void printPlaylist(PlayList playlist)
	{
		for (PlayList p: playlists)
		{
			if (p.getName().equals(playlist.getName()))
			{
				System.out.println(p.toString());
			}
		}
	}
	/**
	 * prints contents of the default playlist "Playlist"
	 */
	public void printDefaultContents()
	{
		for (Playable play: playlists.get(0).getPlayableList())
		{
			System.out.println(play.getName());
		}
	}
	/**
	 * prints out the names of the playlists in MediaPlayer
	 */
	public void printPlayListNames()
	{
		for (PlayList p: playlists)
		{
			System.out.println(p.getName());
		}
	}
	
	/**
	 * plays MP3 file
	 * @param fileName MP3 file to play
	 * @param sec duration of song to play
	 * @return true if song is played
	 */
	public boolean playMp3(String fileName, double sec)
	{
		Song s = new Song("", "", fileName);
		s.play(sec);
		return true;
	}
	/**
	 * plays MP3 file
	 * @param fileName MP3 file to play
	 * @return true if song is played
	 */
	public boolean playMp3(String fileName)
	{
		Song s = new Song("", "", fileName);
		s.play();
		return true;
	}
	/**
	 * Command list
	 */
	public void commandList()
	{
		
		System.out.println("Please write the commands that are inside parenthases without the parenthases.");
		System.out.println("(add play list) lets you add a play list to the media player.");
		System.out.println("(add play list to play list) lets you add a play list to an existing play list in the media player.");
		System.out.println("(load file) lets you give a file name and have that file be loaded into an existing play list");
		System.out.println("(add song) lets you add an mp3 to an existing play list");
		System.out.println("(play) plays all songs in existing playlist");
		System.out.println("(print default) prints out the names of the contents of the default playlist called \"playlist\"");
		System.out.println("(print play list) prints out the names and details of the contents inside an existing playlist");
		System.out.println("(play mp3) plays mp3 file");
		System.out.println("(command list) displays the list of commands");
		System.out.println("(end) ends the program");
	}
	/**
	 * lets you know if a command is incorrect
	 * @param input what you input
	 * @return if the input is incorrect it returns true
	 */
	public boolean incorrectCommand(String input)
	{
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("add play list");
		expected.add("add play list to play list");
		expected.add("load file");
		expected.add("add song");
		expected.add("play");
		expected.add("print default");
		expected.add("print play list");
		expected.add("play mp3");
		expected.add("command list");
		expected.add("end");
		for (String check: expected)
		{
			if (check.equals(input))
			{
				return false;
			}
		}
		return true;
	}
	/**
	 * Lets you know if a play list exists
	 * @param p playlist to check
	 * @return true if play list is in this
	 */
	public boolean playListExists(PlayList p)
	{
		for (PlayList check: playlists)
		{
			if (p.getName().equals(check.getName()))
			{
				return true;
			}
		}
		return false;
	}
	/**
	 * main method for testing
	 * @param args
	 */
	public static void main(String[] args) {
		
		MediaPlayer musicPlayer = new MediaPlayer();
		musicPlayer.start();


	}

}
