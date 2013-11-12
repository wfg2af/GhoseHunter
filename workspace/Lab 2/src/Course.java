// Robert Petri rdp2fm
//Eric Szymkowiak eas7cv
//Will Grayeski wfg2af
import java.util.*;
public class Course {
	private String id;
	private String name;
	private ArrayList<Student> roll;
	public Course(String id, String name)
	{
		this.id = id;
		this.name = name;
	}
	public String getid()
	{
		return(id);
	}
	public String getname()
	{
		return(name);
	}
}
