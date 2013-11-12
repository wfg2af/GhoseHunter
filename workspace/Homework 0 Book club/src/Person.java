import java.util.*;
public class Person {
	private String name;
	private int id;
	private ArrayList<Book> read;
	
	public Person(String name, int id)
	{
		this.name = name;
		this.id = id;
		read = new ArrayList<Book>(0);
	}
	
	public String getname()
	{
		return(name);
	}
	public int getid()
	{
		return(id);
	}
	public ArrayList<Book> getread()
	{
		return(read);
	}
	public void setname(String x)
	{
		if(x != null)
		{
			name = x;
		}
	}
	public boolean addBook(Book b)
	{
		for(Book check: read)
		{
			if(check.equals(b))
			{
				return(false);
			}
		}
		read.add(b);
		return(true);
	}
	public boolean hasRead(Book b)
	{
		for(Book check: read)
		{
			if(check.equals(b))
			{
				return(false);
			}
		}
		return(true);
	}
	public boolean forgetBook(Book b)
	{
		for(int i = 0; i < read.size(); i++)
		{
			if(read.get(i).equals(b))
			{
				read.remove(i);
				return(true);
			}
		}
		return(false);
	}
	public int numBooksRead()
	{
		return(read.size());
	}
	public boolean equals(Object o)
	{
		Person p = (Person) o;
		return(this.getid() == p.getid());
	}
	public String toString()
	{
		String x = "Name: " + name + "  Id: " + id + "\n";
		for(int counter = 0; counter < read.size(); counter++)
		{
			x = x + "Book #" + (counter + 1) + "  " + read.get(counter).gettitle() + " by " + read.get(counter).getauthor() + "\n";
		}
		return(x);
	}
}
