import java.net.URI;
import java.awt.Desktop;
import java.lang.Thread;

/**
 * 
 * @author Will Grayeski wfg2af, Robert Petri rdp2fm
 *
 */
public class Video implements Playable {
	
	private String videoName;
	private double minutes;
	private double seconds;
	private String user;
	private String title;
	
	// BLOCK_ADJUSTMENT used to increase time we block when playing a song to allow for
	//	time it takes to get video to start up in browser.  Adjust for your system if needed.
	private static final int BLOCK_ADJUSTMENT = 3; // units are seconds
   
	/**
	 * constructs a Video object
	 * @param user
	 * @param title
	 * @param min
	 * @param sec
	 * @param videoName
	 */
	public Video(String user, String title, int min, int sec, String videoName)
	{
		this.user = user;
		this.title = title;
		this.minutes = min;
		this.seconds = sec;
		this.videoName = videoName;  // must in this form: http://www.youtube.com/embed/FzRH3iTQPrk
		
		if (!videoName.toLowerCase().startsWith("http://www.youtube.com/embed/")) {
			System.out.println("* Constructor given videoName "
				+ videoName + " which is not the proper form.");
			System.out.println("* This video will almost certainly not play.");
		}
	}

	/**
	 * plays the song
	 */
	public void play() {
		this.play( this.minutes * 60 + this.seconds );
	}

	/**
	 * plays seconds secs of the song
	 */
	public void play(double secs) {
		try {
			Desktop.getDesktop().browse( new URI( videoName + "?autoplay=1") );
			Thread.sleep((int) (1000 * (secs + BLOCK_ADJUSTMENT))); // block for length of song
		} catch (Exception e) {
			System.out.println("* Error: " + e + " when playing YouTube video "
				+ videoName );
		}
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
		return videoName;
	}
	
	public String getUser()
	{
		return user;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public double getMinutes()
	{
		return (minutes);
	}
	
	public double getSeconds()
	{
		return (seconds);
	}
	/**
	 * returns the Video in string form
	 */
	public String toString() 
	{
		return "{Video: title=" + title + " user=" + user + " minutes=" + minutes + " seconds=" + seconds + "}";
	}
	/**
	 * checks to see if 2 videos are equal
	 * return true if videos are equal
	 */
	public boolean equals(Object o)
	{
		if (o instanceof Video)
		{
			Video v = (Video)o;
			return (user.equals(v.getUser()) && title.equals(v.getTitle()) && minutes == v.getMinutes() && seconds == v.getSeconds() && videoName.equals(v.getName()));
		}
		else
		{
			return (false);
		}
	}
	/**
	 * main method for testing
	 * @param args are arguments
	 */
	public static void main(String[] args)
	{
		Video v1 = new Video("jimvwmoss", "The Sneezing Baby Panda", 0, 17,
			"http://www.youtube.com/embed/4hpEnLtqUDg");
		System.out.println("* Playing video for 10 seconds.");
		v1.play(10);

		Video v2 = new Video("jimvwmoss", "The Sneezing Baby Panda", 0, 17,
			"http://www.youtube.com/embed/FzRH3iTQPrk");
		System.out.println("* Playing video for full length.");
		v2.play();
		
		System.out.println("* Should be done when this prints.");

	}
	
}
