package mechanicraft.content;

import java.io.IOException;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import mechanicraft.api.builder.DataEntity;
import mechanicraft.util.ByteReader;
import mechanicraft.util.ByteWriter;
import mechanicraft.util.MaterialBlock;

public class MechanicalItem implements DataEntity
{
	private MaterialBlock target;
	private String name;
	
	public MechanicalItem(String name, MaterialBlock target)
	{
		this.target = target;
		this.name = name;
	}
	
	public MechanicalItem()
	{
		target = new MaterialBlock(Material.STONE);
		name = "";
	}
	
	public MaterialBlock getTarget()
	{
		return target;
	}
	
	public String getName()
	{
		return name;
	}
	
	public ItemStack create()
	{
		return create(1);
	}
	
	public ItemStack create(int amt)
	{
		ItemStack is = target.make(amt);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(name);
		is.setItemMeta(im);
		
		return is;
	}
	
	public boolean is(ItemStack is)
	{
		if(new MaterialBlock(is).equals(target) && is.hasItemMeta() && is.getItemMeta().getDisplayName().equals(name))
		{
			return true;
		}
		
		return false;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public byte[] toData() throws IOException
	{
		ByteWriter b = new ByteWriter();
		b.wByte(getDataType());
		b.wInt(getTarget().getType().getId());
		b.wByte(getTarget().getData());
		b.wString(getName());
		b.close();
		
		return b.getData();
	}
	
	@Override
	public void fromData(byte[] data) throws IOException
	{
		ByteReader b = new ByteReader(data);
		byte ty = b.rByte();
		
		if(ty != getDataType())
		{
			b.close();
			return;
		}
		
		int id = b.rInt();
		byte da = b.rByte();
		name = b.rString();
		target = new MaterialBlock(id, da);
		b.close();
	}
	
	@Override
	public String toString()
	{
		return "Item<" + name + "> (" + target.toString() + ")";
	}
	
	@Override
	public byte getDataType()
	{
		return 1;
	}
}
