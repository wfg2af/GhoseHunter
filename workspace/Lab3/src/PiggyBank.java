/**
 * 
 * @author Will Grayeski wfg2af
 *
 */
public class PiggyBank {
	private int size;
	private int currentCoins;
	private boolean broken;
	PiggyBank(int maxNumberOfCoins)
	{
		size = maxNumberOfCoins;
		currentCoins = 0;
		broken = false;
	}
	/**
	 * 
	 * @return number of coins in bank. Return 0 if broken.
	 */
	public int countCoins()
	{
		if ( broken )
		{
			return ( 0 );
		}
		else
		{
			return ( currentCoins );
		}
	}
	/**
	 * adds c coins to bank
	 * @param c   coins added to bank
	 * c is not < 0
	 * if currentCoins > size break the bank
	 * throw exception if try to add coins to broken bank
	 */
	public void addCoins(int c)
	{
		if ( c >= 0 )
		{
			currentCoins += c;
			if ( currentCoins > size )
			{
				breakBank();
			}
		}
		if ( broken )
		{
			throw new IllegalStateException("Can't add coins to a broken bank.");
		}
	}
	/**
	 * break bank      broken becomes true
	 */
	public void breakBank()
	{
		broken = true;
		int lostCoins = currentCoins;
		currentCoins = 0;
		throw new RuntimeException(lostCoins + " coins fly all over the place!");
	}
	/**
	 * returns "A broken bank" if bank is broken
	 * 
	 * returns "a bank with x coins inside" if not broken.
	 */
	public String toString()
	{
		if ( broken )
		{
			return ( "A broken bank" );
		}
		else
		{
			return ( "A bank with " + currentCoins + " coins inside" );
		}
	}
}
