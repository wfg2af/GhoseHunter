import java.util.*;
public class BookClub {
	public static ArrayList<Book> commonBooks(Person a, Person b)
	{
		ArrayList<Book> common = new ArrayList<Book>();
		for(Book checkA: a.getread())
		{
			for(Book checkB: b.getread())
			{
				if(checkA.equals(checkB))
				{
					common.add(checkA);
				}
			}
		}
		return(common);
	}
	public static double similarity(Person a, Person b)
	{
		double smallerSize = 0;
		if(a.getread().size() == 0 || b.getread().size() == 0)
		{
			return(0);
		}
		if(a.getread().size() < b.getread().size())
		{
			smallerSize = a.getread().size();
		}
		else
		{
			smallerSize = b.getread().size();
		}
		return((double)commonBooks(a,b).size() / smallerSize);
	}
}
