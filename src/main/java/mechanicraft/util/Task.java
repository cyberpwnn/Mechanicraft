package mechanicraft.util;

import org.bukkit.plugin.IllegalPluginAccessException;
import mechanicraft.Mechanicraft;

public abstract class Task implements Runnable
{
	private int[] task;
	private boolean running;
	
	public Task(int interval)
	{
		try
		{
			running = true;
			task = new int[] {Mechanicraft.getInstance().scheduleSyncRepeatingTask(0, interval, this)};
		}
		
		catch(IllegalPluginAccessException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public abstract void run();
	
	public void cancel()
	{
		Mechanicraft.getInstance().cancelTask(task[0]);
		running = false;
	}
	
	public boolean isRunning()
	{
		return running;
	}
}
