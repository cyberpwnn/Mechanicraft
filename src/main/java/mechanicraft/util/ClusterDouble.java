package mechanicraft.util;

import mechanicraft.util.DataCluster.ClusterDataType;

public class ClusterDouble extends Cluster
{
	public ClusterDouble(String key, Double value)
	{
		super(ClusterDataType.DOUBLE, key, value);
	}
	
	public double get()
	{
		return value.doubleValue();
	}
	
	public void set(double i)
	{
		value = i;
	}
}
