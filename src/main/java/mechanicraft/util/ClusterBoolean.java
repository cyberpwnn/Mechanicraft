package mechanicraft.util;

import mechanicraft.util.DataCluster.ClusterDataType;

public class ClusterBoolean extends Cluster
{
	public ClusterBoolean(String key, boolean value)
	{
		super(ClusterDataType.BOOLEAN, key, (double) (value ? 1 : 0));
	}
	
	public boolean get()
	{
		return value == 1;
	}
	
	public void set(boolean b)
	{
		value = (double) (b ? 1 : 0);
	}
}
