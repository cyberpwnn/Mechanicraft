package mechanicraft.content;

import java.io.IOException;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import mechanicraft.api.builder.DataEntity;
import mechanicraft.util.ByteReader;
import mechanicraft.util.ByteWriter;
import mechanicraft.util.MaterialBlock;

public class MechanicalStack extends ItemStack implements DataEntity
{
	private boolean mechanical = false;
	
	public MechanicalStack()
	{
		this(new MechanicalItem(), 1);
	}
	
	@SuppressWarnings("deprecation")
	public MechanicalStack(MechanicalItem type, int amt)
	{
		super(type.getTarget().getType(), amt, (short) 0, type.getTarget().getData());
		setMechanical(true);
		
		if(!type.getName().equals(""))
		{
			ItemMeta im = getItemMeta();
			im.setDisplayName(type.getName());
			setItemMeta(im);
		}
	}
	
	public MechanicalStack(MechanicalItem type)
	{
		this(type, 1);
	}
	
	public boolean isMechanical()
	{
		return mechanical;
	}
	
	public void setMechanical(boolean mechanical)
	{
		this.mechanical = mechanical;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public byte[] toData() throws IOException
	{
		ByteWriter b = new ByteWriter();
		b.wByte(getDataType());
		
		b.wInt(getTypeId());
		b.wByte(getData().getData());
		
		b.wInt(getAmount());
		b.wString(hasItemMeta() ? getItemMeta().getDisplayName() != null ? getItemMeta().getDisplayName() : "" : "");
		b.close();
		
		return b.getData();
	}
	
	@SuppressWarnings("deprecation")
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
		
		setTypeId(b.rInt());
		getData().setData(b.rByte());
		setAmount(b.rInt());
		String n = b.rString();
		
		if(!n.equals(""))
		{
			ItemMeta im = getItemMeta();
			im.setDisplayName(n);
			setItemMeta(im);
		}
	}
	
	@Override
	public byte getDataType()
	{
		return 2;
	}
	
	@Override
	public String toString()
	{
		if(hasItemMeta())
		{
			return "Stack<" + new MaterialBlock(this).toString() + "> (" + getAmount() + " | " + getItemMeta().getDisplayName() + ")";
		}
		
		else
		{
			return "Stack<" + new MaterialBlock(this).toString() + "> (" + getAmount() + ")";
		}
	}
}
