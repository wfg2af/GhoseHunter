/**
 * 
 * @author Will Grayeski wfg2af, Robert Petri rdp2fm
 *
 */
public interface Playable {
	/**
	 * Plays song all the way through
	 */
	public void play();
	/**
	 * plays song for seconds seconds and then stops
	 * @param seconds
	 */
	public void play(double seconds);
	/**
	 * 
	 * @return total time of playable object in seconds
	 */
	public int getPlayTimeSeconds();
	/**
	 * 
	 * @return name of playable object
	 */
	public String getName();
	
	
}
