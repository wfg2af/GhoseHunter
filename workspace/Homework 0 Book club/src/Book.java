public class Book {
	private String title;
	private String author;
	public Book(String title, String author) {
	    this.title = title;
	    this.author = author;
	}
	
	public String gettitle()
	{
		return(title);
	}
	
	public String getauthor()
	{
		return(author);
	}
	
	public boolean equals(Object o)
	{
		Book b = (Book) o;
		if(this.title.equals(b.gettitle()) && this.author.equals(b.getauthor()))
		{
			return(true);
		}
		else
		{
			return(false);
		}
	}
	public String toString()
	{
		return(" Title: " + title + "  Author: " + author + " ");
	}
}
