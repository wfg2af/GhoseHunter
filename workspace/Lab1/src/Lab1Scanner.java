import java.util.*;
public class Lab1Scanner 
{
	public static void main(String[] args) {    
        Scanner scan = new Scanner(System.in);
        int printer = 0;
        String show = "";
        boolean restart = true;
        do
        {
        	
        	if(scan.hasNextInt() && restart) {
        		printer = scan.nextInt();
        		System.out.println(printer + " 1");
        		restart = false;
        	}
        	System.out.println("before");
        	scan.nextLine();
        	if(scan.hasNext() && !restart) {
        		show = scan.next();
        		System.out.println(show + " 2");
        		restart = true;
        	}
        	System.out.println("after");
        }
        while(scan.hasNext());
    }
}
