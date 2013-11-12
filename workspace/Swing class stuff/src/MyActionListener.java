import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyActionListener implements ActionListener {
	private int timesClicked;
	public MyActionListener()
	{
		timesClicked = 0;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e);
		System.out.println(timesClicked);
		timesClicked++;
	}

	public int getTimesClicked()
	{
		return timesClicked;
	}
	
}
