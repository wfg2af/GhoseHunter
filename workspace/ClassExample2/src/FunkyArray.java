
public class FunkyArray {
	private double[] x;
	
	/**
	 * Makes an array off the specified size
	 * @param i the size of the array
	 * @throws NegativeArraySizeException if i < 0
	 */
	public FunkyArray(int i) {
		// TODO Auto-generated constructor stub
		x = new double[i];
	}



	public Object size() {
		// TODO Auto-generated method stub
		return this.x.length;
	}



	public int min() {
		// TODO Auto-generated method stub
		return 0;
	}



	public void set(int i, int j) {
		// TODO Auto-generated method stub
		x[i] = j;
	}



	public double get(int i) {
		// TODO Auto-generated method stub
		return x[i];
	}

}
