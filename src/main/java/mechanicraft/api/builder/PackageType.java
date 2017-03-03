package mechanicraft.api.builder;

import mechanicraft.content.MechanicalItem;
import mechanicraft.content.MechanicalShapedRecipe;
import mechanicraft.content.MechanicalShapelessRecipe;
import mechanicraft.content.MechanicalStack;

public enum PackageType
{
	MECHANICAL_SHAPED_RECIPE((byte) 4, MechanicalShapedRecipe.class),
	MECHANICAL_SHAPELESS_RECIPE((byte) 3, MechanicalShapelessRecipe.class),
	MECHANICAL_STACK((byte) 2, MechanicalStack.class),
	MECHANICAL_ITEM((byte) 1, MechanicalItem.class),
	DATA_PACK((byte) 0, DataPack.class);
	
	private byte type;
	private Class<? extends DataEntity> clazz;
	
	private PackageType(byte type, Class<? extends DataEntity> clazz)
	{
		this.type = type;
		this.clazz = clazz;
	}
	
	public byte getType()
	{
		return type;
	}
	
	public Class<? extends DataEntity> getClazz()
	{
		return clazz;
	}
	
	public DataEntity getInstance()
	{
		try
		{
			return getClazz().getConstructor().newInstance();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static PackageType getType(byte type)
	{
		for(PackageType i : PackageType.values())
		{
			if(i.getType() == type)
			{
				return i;
			}
		}
		
		return null;
	}
	
	public static DataEntity getInstance(byte type)
	{
		PackageType t = getType(type);
		
		if(t == null)
		{
			return null;
		}
		
		return t.getInstance();
	}
}
