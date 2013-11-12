
import java.util.*;
public class ApisAndArrays {
    public static void main(String[] args) {
    	int x;
    	int[] xs = new int[12];
    	int[] ys = { 1, 3, 5, 7, 9};
    	
    	System.out.println( xs.length );
    	// ys.length = 20 DOES NOT WORK!!!!!!!!!!!!!!! cannot change length
    	xs[3] = 12;
    	x = xs[2];
    	
    	int[][][][] odd = new int[2][4][7][];
    	
    	odd[0][2][5] = new int[6]; //TOTALLY LEGAL!!! WOAH!
    	odd[0][4] = new int[99][4]; //ALSO LEGAL!!! I didn't change the size. I made a new array
    	
    	
    	ArrayList<String> blammo = new ArrayList<String>(5);
    }
}
