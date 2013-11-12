// Robert Petri rdp2fm
//Eric Szymkowiak eas7cv
//Will Grayeski wfg2af
import java.util.*;
public class Student {
	private String name;
	private double gpa;
	private ArrayList<Course> courses;
	public Student(String name, double gpa)
	{
		if(gpa < 0 || gpa > 4)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			this.gpa = gpa;
		}
		if (name == null)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			this.name = name;
		}
	}
	
	public Student(String name)
	{
		gpa = 0.0;
		if (name == null)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			this.name = name;
		}
	}
	public Object getname() {
		// TODO Auto-generated method stub
		return (name);
	}
	
	public double gpaIn100() {
		// TODO Auto-generated method stub
		return (gpa*25);
	}
	public Object getgpa() {
		// TODO Auto-generated method stub
		return gpa;
	}

	public void setgpa(double i) {
		if (i < 0 || i > 4)
		{
			throw new IllegalArgumentException();
		}
		else
		{
		gpa=i;
		}
	}

	public void add(Course course) {
		// TODO Auto-generated method stub

		for(Course test: courses)
		{
			if(test == null || test.getid().equals(course.getid()))
			{
                return;
			}
		}
		

		courses.add(course);
	}

	public ArrayList<Course> getcourses() {
		// TODO Auto-generated method stub
		return courses;
	}
}
