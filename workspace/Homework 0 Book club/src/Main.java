public class Main {
    public static void main(String[] args)
    {
    	Person p1 = new Person("Paul", 5);
    	Person p2 = new Person("Peter", 7);
    	
    	p1.addBook(new Book("Blah9", "Blaher"));
    	p1.addBook(new Book("Blah1", "Blaher"));
    	p1.addBook(new Book("Blah2", "Blaher"));
    	p1.addBook(new Book("Blah2", "Blaher"));
    	p1.addBook(new Book("Blah3", "Blaher"));
    	p1.addBook(new Book("Blah4", "Blaher"));
    	
    	p2.addBook(new Book("Blah1", "Blaher"));
    	p2.addBook(new Book("Blah1", "Blaher"));
    	p2.addBook(new Book("Blah2", "Blaher"));
    	p2.addBook(new Book("Blah2", "Blaher"));
    	p2.addBook(new Book("Blah3", "Blaher"));
    	p2.addBook(new Book("Blah4", "Blaher"));
    	p2.addBook(new Book("Blah5", "Blaher"));
    	p2.addBook(new Book("Blah6", "Blaher"));
    	System.out.println(p1);
    	System.out.println(p2);
    	System.out.println(BookClub.commonBooks(p1,p2));
    	System.out.println(BookClub.similarity(p1,p2));
    }
}
