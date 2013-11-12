import java.util.Comparator;

/**
 * 
 * @author Will Grayeski wfg2af, Robert Petri rdp2fm
 *
 */
public class PlayableNameCompare implements Comparator<Playable> {

	@Override
	public int compare(Playable p1, Playable p2) {
		String p1Name = "";
		String p2Name = "";
		if (p1 instanceof Song)
		{
			p1Name = ((Song)p1).getName();
		}
		if (p1 instanceof PlayList)
		{
			p1Name = ((PlayList)p1).getName();
		}
		if (p2 instanceof Song)
		{
			p2Name = ((Song)p2).getName();
		}
		if (p2 instanceof PlayList)
		{
			p2Name = ((PlayList)p2).getName();
		}
		return (p1Name.compareTo(p2Name));
	}

}
