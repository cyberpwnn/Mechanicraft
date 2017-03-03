package mechanicraft.util;

import org.bukkit.event.Listener;

public interface Controllable extends Listener
{
	public void onStart();
	
	public void onStop();
	
	public void onTick();
	
	public String getName();
}
