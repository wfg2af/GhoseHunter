import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class UseSwing {

	
	public static void main(String[] args)
	{
		JFrame window = new JFrame("Will's window of wonder");
		
		window.setSize(300, 500);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		window.add(panel);
		panel.setBackground(Color.RED);
		
		panel.setLayout(new BorderLayout());
		
		panel.add(new CircularButton(200));
		

		
		/*
		JButton button1 = new JButton("Fuck you");
		panel.add(button1, BorderLayout.CENTER);
		
		
		ActionListener listener = new MyActionListener();
		button1.addActionListener(listener);
		
		while(true)
		{
			if (((MyActionListener)listener).getTimesClicked() == 1)
			{
				button1.setText("You are terrible");
			}
			if (((MyActionListener)listener).getTimesClicked() == 2)
			{
				button1.setText("I hate you");
			}
			if (((MyActionListener)listener).getTimesClicked() == 3)
			{
				button1.setText("Stop Clicking Me");
			}
			if (((MyActionListener)listener).getTimesClicked() == 4)
			{
				button1.setText("You know what? I don't even care.");
			}
			if (((MyActionListener)listener).getTimesClicked() == 5)
			{
				button1.setText("Ugh you are the worst");
			}
			if (((MyActionListener)listener).getTimesClicked() == 6)
			{
				button1.setText("Ok I'm just done now!");
			}
			if (((MyActionListener)listener).getTimesClicked() == 7)
			{
				
				button1.setText("Ignoring you");
			}
			if (((MyActionListener)listener).getTimesClicked() == 40)
			{
				
				button1.setText("Still at it?");
			}
			if (((MyActionListener)listener).getTimesClicked() == 50)
			{
				
				button1.setText("What's the point even?");
			}
			if (((MyActionListener)listener).getTimesClicked() == 55)
			{
				
				button1.setText("...");
			}
			if (((MyActionListener)listener).getTimesClicked() == 75)
			{
				
				button1.setText("Yaaaaaaaawwwwwnnnnnn");
			}
			if (((MyActionListener)listener).getTimesClicked() == 100)
			{
				
				button1.setText("OUCH! those last few hurt");
			}
			if (((MyActionListener)listener).getTimesClicked() == 110)
			{
				
				button1.setText(":P");
			}
		}
		*/
		
	}
}
