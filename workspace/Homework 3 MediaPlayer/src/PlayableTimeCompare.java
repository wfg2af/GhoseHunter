import java.util.Comparator;

/**
 * 
 * @author Will Grayeski wfg2af, Robert Petri rdp2fm
 *
 */
public class PlayableTimeCompare implements Comparator<Playable> {

	@Override
	public int compare(Playable p1, Playable p2) {
		int p1Sec = 0;
		int p2Sec = 0;
		if (p1 instanceof Song)
		{
			p1Sec = ((Song)p1).getPlayTimeSeconds();
		}
		if (p1 instanceof PlayList)
		{
			p1Sec = ((PlayList)p1).getPlayTimeSeconds();
		}
		if (p2 instanceof Song)
		{
			p2Sec = ((Song)p2).getPlayTimeSeconds();
		}
		if (p2 instanceof PlayList)
		{
			p2Sec = ((PlayList)p2).getPlayTimeSeconds();
		}
		if (p1Sec < p2Sec)
			return (-1);
		else if (p1Sec > p2Sec)
			return (1);
		else
			return (0);
	}

}
