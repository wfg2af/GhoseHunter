import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Blatherer Cubey = new Blatherer(4);
		Scanner smallText = new Scanner("I am Sam Sam I am");
		Cubey.feed(smallText);
		System.out.println(Cubey.runLengths());
		System.out.println(Cubey.getBlather());
		Cubey.removeDeadEnds();
		System.out.println(Cubey.getBlather());
		
	}

}
