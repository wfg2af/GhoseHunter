// Robert Petri rdp2fm
//Eric Szymkowiak eas7cv
//Will Grayeski wfg2af

import static org.junit.Assert.*;

import org.junit.Test;


public class StudentTester {

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor()
	{
		Student s = new Student(null, 0);
		Student s2 = new Student("Will", 5.0);
	}
	@Test
    public void testGpaIn100()
    {
    	Student s=new Student("Robert", 3.4);
    	assertEquals("3.4 in a 4 gpas system should be 85 in a 100 system", s.gpaIn100(), 85, .0001);
    }
	@Test
	public void testConstructor2()
	{
		Student s=new Student("Eric");
		assertEquals("A new student with a 0.0 gpa", s.getgpa(),0.0);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor3()
	{
		Student s=new Student(null);
		
	}
	@Test(expected = IllegalArgumentException.class)
	public void testGpa()
	{
		Student s=new Student("Mary", 3.9);
		s.setgpa(5);
	}
	@Test
	public void testAdd()
	{
		Student s = new Student("Bob", 2);
		Course course = new Course("CS 2110", "Software Development Methods");
		s.add(course);
		System.out.println("add 1");
		s.add(course);
		int counter = 0;
		for(Course test: s.getcourses())
		{
			if(course.getid().equals(test.getid())){
				counter++;
			}
		}
		assertEquals("2 identical courses were added", 1, counter);
	}
	/*
	@Test
	public void test()
	{
		
	}
	*/
}
