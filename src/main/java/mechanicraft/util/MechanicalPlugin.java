package mechanicraft.util;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class MechanicalPlugin extends JavaPlugin
{
	private ControllerManager controllerManager;
	private D d;
	
	@Override
	public void onEnable()
	{
		d = new D("Mechanicraft");
		controllerManager = new ControllerManager();
		
		start();
		
		controllerManager.start();
	}
	
	@Override
	public void onDisable()
	{
		controllerManager.stop();
		
		stop();
	}
	
	public void register(Controllable c)
	{
		controllerManager.register(c);
	}
	
	public abstract void start();
	
	public abstract void stop();
	
	public boolean canFindPlayer(String search)
	{
		return findPlayer(search) == null ? false : true;
	}
	
	public Player findPlayer(String search)
	{
		for(Player i : onlinePlayers())
		{
			if(i.getName().equalsIgnoreCase(search))
			{
				return i;
			}
		}
		
		for(Player i : onlinePlayers())
		{
			if(i.getName().toLowerCase().contains(search.toLowerCase()))
			{
				return i;
			}
		}
		
		return null;
	}
	
	public Player[] onlinePlayers()
	{
		return getServer().getOnlinePlayers().toArray(new Player[getServer().getOnlinePlayers().size()]);
	}
	
	public void registerListener(Listener listener)
	{
		getServer().getPluginManager().registerEvents(listener, this);
	}
	
	public void unRegisterListener(Listener listener)
	{
		HandlerList.unregisterAll(listener);
	}
	
	public int scheduleSyncRepeatingTask(int delay, int interval, Runnable runnable)
	{
		return getServer().getScheduler().scheduleSyncRepeatingTask(this, runnable, delay, interval);
	}
	
	public int scheduleSyncTask(int delay, Runnable runnable)
	{
		return getServer().getScheduler().scheduleSyncDelayedTask(this, runnable, delay);
	}
	
	public void cancelTask(int tid)
	{
		getServer().getScheduler().cancelTask(tid);
	}
	
	public void i(String s)
	{
		d.i(s);
	}
	
	public void s(String s)
	{
		d.s(s);
	}
	
	public void f(String s)
	{
		d.f(s);
	}
	
	public void w(String s)
	{
		d.w(s);
	}
	
	public void o(String s)
	{
		d.o(s);
	}
	
	public ControllerManager getControllerManager()
	{
		return controllerManager;
	}
}
