package edu.virginia.cs2110.wfg2af;

import java.util.ArrayList;
public class Ghost {
	private double xLocation;
	private double yLocation;
	private double[] direction;
	private double speed;
	public Ghost(double xLoc, double yLoc, double speed)
	{
		xLocation = xLoc;
		yLocation = yLoc;
		direction = new double[2];
		this.speed = speed;
	}
	
	public void move(Human h)
	{
		xLocation += direction[0]*speed;
		yLocation += direction[1]*speed;
		
	}
	public void updateDirection(Human h)
	{
		//updates direction arraylist based on 
		//ghost location human location and using vectors hopefully
		//h.getXLocation()
		//h.getYLocation()
		direction[0] = (h.getXLocation() - getXLocation())/totalDistance(getXLocation(), h.getXLocation(), getYLocation(), h.getYLocation());
		direction[1] = (h.getYLocation() - getYLocation())/totalDistance(getXLocation(), h.getXLocation(), getYLocation(), h.getYLocation());
	}
	
	public double totalDistance(double x1, double x2, double y1, double y2)
	{
		return Math.sqrt(Math.pow((x1 - x2),2) + Math.pow((y1 - y2),2));
	}
	
	public double getXLocation()
	{
		return xLocation;
	}
	
	public double getYLocation()
	{
		return yLocation;
	}
		
}
