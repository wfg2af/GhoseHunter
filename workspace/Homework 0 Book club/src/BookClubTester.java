import static org.junit.Assert.*;

import org.junit.Test;


public class BookClubTester {

	@Test
	public void testBookAdd() {
		Person p = new Person("Will", 5);
		p.addBook(new Book("Hi", "Hi"));
		assertEquals("Book added", true, p.addBook(new Book("Hi", "i")));
	}

}
