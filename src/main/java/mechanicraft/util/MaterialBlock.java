package mechanicraft.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class MaterialBlock
{
	private Material material;
	private byte data;
	
	public MaterialBlock()
	{
		material = Material.AIR;
		data = 0;
	}
	
	public MaterialBlock(Material material)
	{
		this();
		
		this.material = material;
	}
	
	public MaterialBlock(Material material, byte data)
	{
		this(material);
		
		this.data = data;
	}
	
	@SuppressWarnings("deprecation")
	public MaterialBlock(int id, byte data)
	{
		this(Material.getMaterial(id));
		
		this.data = data;
	}
	
	public MaterialBlock(Material material, int data)
	{
		this(material, (byte) data);
	}
	
	@SuppressWarnings("deprecation")
	public MaterialBlock(Block block)
	{
		this(block.getType(), block.getData());
	}
	
	public MaterialBlock(Location location)
	{
		this(location.getBlock());
	}
	
	@SuppressWarnings("deprecation")
	public MaterialBlock(ItemStack is)
	{
		this(is.getType(), is.getData().getData());
	}
	
	public MaterialBlock(String input)
	{
		if(input.contains(":"))
		{
			material = getMaterialFrom(input.split(":")[0]);
			data = getDataFrom(input.split(":")[1]);
		}
		
		else
		{
			material = getMaterialFrom(input);
		}
	}
	
	private static byte getDataFrom(String data)
	{
		try
		{
			return Byte.valueOf(data);
		}
		
		catch(Exception e)
		{
			
		}
		
		return 0;
	}
	
	private static Material getMaterialFrom(String material)
	{
		try
		{
			@SuppressWarnings("deprecation")
			Material m = Material.getMaterial(Integer.valueOf(material));
			
			if(m != null)
			{
				return m;
			}
			
			return null;
		}
		
		catch(Exception e)
		{
			try
			{
				Material m = Material.valueOf(material.toUpperCase());
				
				if(m != null)
				{
					return m;
				}
			}
			
			catch(Exception e2)
			{
				
			}
		}
		
		return null;
	}
	
	public Material getType()
	{
		return material;
	}
	
	public byte getData()
	{
		return data;
	}
	
	public ItemStack make()
	{
		return make(1);
	}
	
	@SuppressWarnings("deprecation")
	public ItemStack make(int amt)
	{
		return new ItemStack(material, amt, (short) 0, data);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public String toString()
	{
		return material.getId() + (data > 0 ? ":" + data : "");
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + data;
		result = prime * result + ((material == null) ? 0 : material.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
		{
			return true;
		}
		
		if(obj == null)
		{
			return false;
		}
		
		if(getClass() != obj.getClass())
		{
			return false;
		}
		
		MaterialBlock other = (MaterialBlock) obj;
		
		if(data != other.data)
		{
			return false;
		}
		
		if(material != other.material)
		{
			return false;
		}
		
		return true;
	}
}
