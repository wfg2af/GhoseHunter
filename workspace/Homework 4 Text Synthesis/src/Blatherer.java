import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 
 * @author Will Grayeski wfg2af, Robert Petri rdp2fm
 *
 */
public class Blatherer implements Iterable<String> {
	/**
	 * delimiter for keys in blather
	 */
	public static final char GRAM_DELIMITER = '#';
	private TreeMap<String, Set<String>> blather;
	private String currentElement;
	private int nGrams;
	/**
	 * Creates a Blatherer with n grams
	 * @param n # of grams
	 */
	public Blatherer(int n)
	{
		nGrams = n;
		blather = new TreeMap<String, Set<String>>();
	}
	/**
	 * puts scanner scan into nGram map form
	 * @param scan
	 */
	public void feed(Scanner scan)
	{
		ArrayList<String> source = new ArrayList<String>(0);
		while (scan.hasNext())
		{
			source.add(scan.next());
		}
		for (int index = 0; index < source.size() - (nGrams - 2); index++)
		{
			String currentKey = "";
			String currentValue = "";
			for (int counter = 0; counter < nGrams - 1; counter++)
			{
				currentKey += source.get(index + counter);
				if (counter < nGrams - 2)
				{
					currentKey += GRAM_DELIMITER;
				}
			}
			System.out.println(index + " :)");
			System.out.println(source.size() + " --");
			System.out.println(nGrams + " **");
			if (index < (source.size() - (nGrams - 1)))
			{
				currentValue = source.get(index + nGrams - 1);
			}
			else
			{
				currentValue = null;
			}
			if (blather.containsKey(currentKey))
			{
				if (currentValue != null)
				{
					blather.get(currentKey).add(currentValue);
				}
			}
			else
			{
				Set<String> valueSet = new TreeSet<String>();
				if (currentValue != null)
				{
					valueSet.add(currentValue);
				}
				blather.put(currentKey, valueSet);
			}
			
		}
			
	}
	/**
	 * removes dead ends from Batherer's map.
	 */
	public void removeDeadEnds()
	{
		while (removed())
		{
			
		}
	}
	/**
	 * helps removeDeadEnds to remove dead ends
	 * @return true if a dead end was removed false if not
	 */
	public boolean removed()
	{
		for (String toDeadEnd: new ArrayList<String>(blather.keySet()))
		{
			Set<String> iterate = blather.get(toDeadEnd);
			if (!iterate.isEmpty())
			{
				for (String value: iterate)
				{
					if (blather.get(findNextKey(toDeadEnd, value)).isEmpty())
					{
						blather.remove(findNextKey(toDeadEnd, value));
						blather.get(toDeadEnd).remove(value);
						return true;
					}
				}
			}
			else
			{
				blather.remove(toDeadEnd);
				return true;
			}
		}
		return false;
	}
	/**
	 * returns a map who's value is the run for each key (the counter int prevents this to run in an infinite loop if every key runs to the next in a loop)
	 * @return a map who's value is the run for each key
	 */
	public Map<String, Integer> runLengths()
	{
		Map<String, Integer> runs = new TreeMap<String, Integer>();
		for (String keyRun: blather.keySet())
		{
			int counter = 0;
			int currentRun = 0;
			String currentKey = keyRun;
			while (blather.get(currentKey).size() == 1 && counter < 300)
			{
				String[] values = blather.get(currentKey).toArray(new String[0]);
				currentRun++;
				currentKey = findNextKey(currentKey, values[0]);
				counter++;
			}
			runs.put(keyRun, new Integer(currentRun));
		}
		return runs;
	}
	/**
	 * removes map keys with runs longer than x
	 * @param x run limit
	 */
	public void removeRunsLongerThan(int x)
	{
		for (String key: new ArrayList<String>(blather.keySet()))
		{
			if (blather.containsKey(key))
			{
				if (runLengths().get(key) > x)
				{
					blather.remove(key);
				}
			}
		}
	}
	/**
	 * returns an unmodifiable map of key to collection of strings
	 * @return an unmodifiable map of key to collection of strings
	 */
	public Map<String, ? extends Collection<String>> nGrams()
	{
		return Collections.unmodifiableMap(blather);
	}
	/**
	 * returns the key with the most values
	 * @return key with most values
	 */
	public String mostCommonKey()
	{
		int compare = 0;
		String key = "";
		for (String keys: blather.keySet())
		{
			if (blather.get(keys).size() > compare)
			{
				compare = blather.get(keys).size();
				key = keys;
			}
		}
		return key;
	}
	/**
	 * returns blatherIter
	 * @param words number of words iterator produces
	 * @param start the starting key
	 * @return the iterator
	 */
	public BlatherIter iterator(int words, String start)
	{
		return new BlatherIter(this, start, words);
	}
	/**
	 * returns blatherIter starting at most common key
	 * @param words to produce
	 * @return the iterator
	 */
	public BlatherIter iterator(int words)
	{
		return new BlatherIter(this, mostCommonKey(), words);
	}
	/**
	 * returns blatherIter starting at most common key for 100 words
	 * @return the iterator
	 */
	public BlatherIter iterator()
	{
		return new BlatherIter(this, mostCommonKey(), 100);
	}
	/**
	 * returns the value of the key that lead to current key
	 * @param key current key
	 * @return value of previous key
	 */
	public String findValueToPreviousKey(String key)
	{
		String keyPart = key.substring(0, key.indexOf(GRAM_DELIMITER) + 1);
		keyPart += key.substring(keyPart.length(), key.indexOf(GRAM_DELIMITER, keyPart.length()));
		String value = key.substring(keyPart.length() + 1, key.length());
		return value;
	}
	/**
	 * next key
	 * @param key current key
	 * @param value current value
	 * @return next key
	 */
	public String findNextKey(String key, String value)
	{
		if (!blather.get(key).isEmpty())
		{
			int start = key.indexOf(GRAM_DELIMITER) + 1;
			String newKey = key.substring(start) + GRAM_DELIMITER + value;
			return newKey;
		}
		else
		{
			return null;
		}
	}
	/**
	 * get's blather
	 * @return blather
	 */
	public Map<String, Set<String>> getBlather()
	{
		return blather;
	}
	/*
	/**
	 * main method
	 * @param args
	 */
	/*
	public static void main(String[] args) {
		
		Blatherer b = new Blatherer(4);
		b.feed(scannerForWebPage("http://www.gutenberg.org/ebooks/98.txt.utf-8")); // Tale of Two Cities
		System.out.println("Fed pages");
		/*
		b.feed(scannerForWebPage("http://www.gutenberg.org/ebooks/1400.txt.utf-8")); // Great Expectations
		System.out.println("Fed pages");
		b.feed(scannerForWebPage("http://www.gutenberg.org/ebooks/730.txt.utf-8")); // Oliver Twist
		System.out.println("Fed pages");
		b.feed(scannerForWebPage("http://www.gutenberg.org/ebooks/766.txt.utf-8")); // David Copperfield
		System.out.println("Fed pages");
		b.feed(scannerForWebPage("http://www.gutenberg.org/ebooks/1023.txt.utf-8")); // Bleak House
		System.out.println("Fed pages");
		b.feed(scannerForWebPage("http://www.gutenberg.org/ebooks/580.txt.utf-8")); // The Pickwick Papers
		System.out.println("Fed pages");
		b.feed(scannerForWebPage("http://www.gutenberg.org/ebooks/967.txt.utf-8")); // Nicolas Nickleby
		System.out.println("Fed pages");
		
	}
	
	
	/**
	 * This helper method can print text neatly wrapped to 80 columns with indented paragraphs.
	 * 
	 * @param iter an iterator that gives an empty string for a paragraph break
	 * @author Luther Tychonievich (lat7h)
	 */
	/*
	public static void prettyPrint(Iterator<String> iter) {
		int col = 0;
		while (iter.hasNext()) {
			String w = iter.next();
			col += 1 + w.length();
			if (w.length() == 0) { 
				System.out.print("\n    "); 
				col = 0; 
			} else if (col > 80) {
				System.out.print(w + "\n");
				col = 0;
			} else {
				System.out.print(w + " ");
			}
		}
	}
	/**
	 * This helper method can print text neatly wrapped to 80 columns with indented paragraphs.
	 * 
	 * @param iter an iterable that gives an empty string for a paragraph break
	 * @author Luther Tychonievich (lat7h)
	 */
	/*
	public static void prettyPrint(Iterable<String> iter) {
		prettyPrint(iter.iterator());
	}
	
	/**
	 * A helper method to download pages only once, reducing network traffic.
	 * It also returns only words, ignoring non-word stuff.
	 * It returns Gutenberg.org-style paragraph breaks (1+ blank lines) the empty string ""  
	 * This is already finished; you don't need to tweak it.
	 * 
	 * @author Luther Tychonievich (lat7h)
	 * @param page the URL to open
	 * @return a Scanner ready to read from a local copy of that page
	 */
	/*
	public static Scanner scannerForWebPage(String page) {
		// A regular expression, or Pattern in Java-speak, for splitting text and finding paragraph breaks
		final String NOT_WORD_OR_PUNCTUATION_OR_PARAGRAPH_BREAK = 
				"[^-A-Za-z,.?!\"']*\\n[^-A-Za-z,.?!\"']*(?=\\n)" // 1+ newline followed by newline
				+ "|" + 
				"[^-A-Za-z,.?!\"'\\n]*\\n[^-A-Za-z,.?!\"'\\n]*" // one new line
				+ "|" + 
				"[^-A-Za-z,.?!\"'\\n]+" // no new line
				; 
		try {
			String filename = page.substring(page.lastIndexOf('/')+1);
			File local = new File(filename);
			if (local.isFile()) return new Scanner(local).useDelimiter(NOT_WORD_OR_PUNCTUATION_OR_PARAGRAPH_BREAK);
			URL u = new URL(page);
			FileOutputStream save = new FileOutputStream(local);
			InputStream read = u.openStream();
			byte[] got = new byte[1024];
			int have = read.read(got);
			while (have > 0) {
				save.write(got, 0, have);
				have = read.read(got);
			}
			save.close();
			read.close();
			return new Scanner(local).useDelimiter(NOT_WORD_OR_PUNCTUATION_OR_PARAGRAPH_BREAK);
		} catch (IOException e) {
			System.err.println("ERROR reading "+page);
			e.printStackTrace();
			return null;
		}
	}
	*/
}

