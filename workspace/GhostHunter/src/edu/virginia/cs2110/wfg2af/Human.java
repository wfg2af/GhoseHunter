package edu.virginia.cs2110.wfg2af;

import java.util.ArrayList;

public class Human {
	private double xLocation;
	private double yLocation;
	private double direction;
	private int ammo;
	private boolean living;
	public Human(double xLoc, double yLoc, double direction, int ammo)
	{
		xLocation = xLoc;
		yLocation = yLoc;
		this.direction = direction;
		this.ammo = ammo;
		living = true;
	}
	
	public void updateLocation(double newX, double newY, double newDir)
	{
		xLocation = newX;
		yLocation = newY;
		direction = newDir;
	}
	
	public void shoot()
	{
		if (!outOfAmmo())
		{
			ammo--;
		}
		else
		{
			ammo = 0;
		}
	}
	
	public void addAmmo(int bullets)
	{
		if (bullets >= 0)
		{
			ammo += bullets;
		}
	}
	
	public void kill()
	{
		living = false;
	}
	
	private boolean outOfAmmo() 
	{
		return ammo <= 0;
	}

	public double getXLocation()
	{
		return xLocation;
	}
	
	public double getYLocation()
	{
		return yLocation;
	}
	
	public double getAmmo()
	{
		return ammo;
	}

	public boolean getLiving()
	{
		return living;
	}
}
