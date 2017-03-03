package mechanicraft.content;

import mechanicraft.util.GMap;

public class ContentRegistry
{
	private GMap<String, MechanicalItem> items;
	
	public ContentRegistry()
	{
		items = new GMap<String, MechanicalItem>();
	}
	
	public void registerItem(MechanicalItem item)
	{
		items.put(item.getName(), item);
	}
	
	public void unregisterItem(MechanicalItem item)
	{
		items.remove(item.getName());
	}
	
	public void unregisterItem(String name)
	{
		items.remove(name);
	}
}
