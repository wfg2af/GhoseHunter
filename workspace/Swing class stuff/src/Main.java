import java.util.concurrent.TimeUnit;


public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		for (int i = 0; i < 100; i++)
		{
			System.out.println(i);
			TimeUnit.MILLISECONDS.sleep(1000);
		}
	}

}
