package edu.virginia.cs2110.wfg2af;

import java.util.ArrayList;

import android.os.AsyncTask;

public class GameTask extends AsyncTask<Human, String, String>{
	private ArrayList<Ghost> ghosts;
	private Human player;
	private final double KILL_RADIUS = 5;
	
	public GameTask(Human player)
	{
		ghosts = new ArrayList<Ghost>(0);
		this.player = player;
	}
	@Override
	protected void onPreExecute()
	{
		
	}
	@Override
	protected String doInBackground(Human... params)
	{
		while(!isGameOver() && !isCancelled())
		{
			
			/*10th of a second of lag for each iteration
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * */
			
		}
		return null;
	}
	@Override
	protected void onProgressUpdate(String...strings)
	{
		
	}
	@Override
	protected void onPostExecute(String str)
	{
		
	}
	@Override
	protected void onCancelled()
	{
		
	}
	public boolean isGameOver()
	{
		for(Ghost g: ghosts)
		{
			if (g.totalDistance(g.getXLocation(), player.getXLocation(), g.getYLocation(), player.getYLocation()) < KILL_RADIUS)
			{
				player.kill();
			}
		}
		if (!player.getLiving())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
}
