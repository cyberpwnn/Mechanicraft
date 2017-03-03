package mechanicraft.util;

import mechanicraft.Mechanicraft;

public abstract class TaskLater implements Runnable
{
	public TaskLater(int delay)
	{
		Mechanicraft.getInstance().scheduleSyncTask(delay, this);
	}
	
	@Override
	public abstract void run();
}