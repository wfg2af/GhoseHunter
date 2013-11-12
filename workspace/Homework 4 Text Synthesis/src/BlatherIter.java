import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author Will Grayeski wfg2af, Robert Petri rdp2fm
 *
 */
public class BlatherIter implements Iterator<String> {
	private int numberToGenerate;
	private String currentKey;
	private int alreadyGenerated;
	private Blatherer blab;
	/**
	 * Creates new BlatherIter
	 * @param blab Blatherer to use
	 * @param start starting key
	 * @param numberToGenerate how many words to generate
	 */
	public BlatherIter(Blatherer blab, String start, int numberToGenerate)
	{
		this.numberToGenerate = numberToGenerate;
		currentKey = start;
		this.blab = blab;
	}
	/**
	 * checks if can call next()
	 */
	public boolean hasNext()
	{
		return (!(numberToGenerate == alreadyGenerated)) && blab.getBlather().containsKey(currentKey) && !blab.getBlather().get(currentKey).isEmpty();
	}
	/**
	 * iterates through blatherer
	 */
	public String next()
	{
		String[] valueArray = blab.getBlather().get(currentKey).toArray(new String[0]);
		if (hasNext())
		{
			int random = (int) (Math.random() * valueArray.length);
			String value = valueArray[random];
			currentKey = blab.findNextKey(currentKey, value);
			alreadyGenerated++;
			return value;
		}
		else
		{
			throw new NoSuchElementException();
		}
	}
	/**
	 * not supported
	 */
	public void remove()
	{
		throw new UnsupportedOperationException();
	}
	/**
	 * adds more words to generate
	 * @param more how many more to add
	 */
	public void moreWords(int more)
	{
		numberToGenerate += more;
	}
}
