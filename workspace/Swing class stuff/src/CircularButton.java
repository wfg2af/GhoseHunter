import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;




public class CircularButton extends JComponent {
	private int visualDiameter;
	private int logicalDiameter;
	private Color color;
	public CircularButton(int vis)
	{
		visualDiameter = vis;
		logicalDiameter = vis;
		setColor(color.BLUE);
		addMouseListener(new MouseListener() {
			
			@Override public void mouseClicked(MouseEvent arg0){}
			@Override public void mouseEntered(MouseEvent arg0){}
			@Override public void mouseExited(MouseEvent arg0){}
			@Override public void mousePressed(MouseEvent arg0){
				int x = arg0.getX() - logicalDiameter/2;
				int y = arg0.getY() - logicalDiameter/2;
				if (x*x + y*y < logicalDiameter * logicalDiameter/4)
				{
					setColor(Color.YELLOW);
				}
			}
			@Override public void mouseReleased(MouseEvent arg0){
				/*int x = arg0.getX() - logicalDiameter/2;
				int y = arg0.getY() - logicalDiameter/2;
				if (x*x + y*y < logicalDiameter * logicalDiameter/4)
				{*/
					CircularButton.this.setColor(Color.BLUE);
				/*}*/
			}
		});
		
	}
	
	@Override
	public int getWidth()
	{
		return visualDiameter;
	}
	@Override
	public int getHeight()
	{
		return visualDiameter;
	}
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(logicalDiameter, logicalDiameter);
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.setColor(color);
		g.fillOval(0, 0, visualDiameter, visualDiameter);
	}
	
	public void setColor(Color color)
	{
		this.color = color;
		this.repaint();
	}
}
