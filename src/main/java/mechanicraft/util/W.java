package mechanicraft.util;

import org.bukkit.World;

public class W
{
	public static boolean isLoaded(World w, int x, int z)
	{
		return w.isChunkLoaded(x, z);
	}
}
