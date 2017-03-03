package mechanicraft.api.builder;

import java.io.IOException;
import mechanicraft.util.ByteReader;
import mechanicraft.util.ByteWriter;
import mechanicraft.util.GList;

public class DataPack implements DataEntity
{
	GList<DataEntity> entities;
	
	public DataPack()
	{
		entities = new GList<DataEntity>();
	}
	
	public void put(DataEntity e)
	{
		entities.add(e);
	}
	
	@Override
	public byte[] toData() throws IOException
	{
		ByteWriter b = new ByteWriter();
		b.wByte(getDataType());
		b.wInt(entities.size());
		
		for(DataEntity i : entities)
		{
			b.wBytes(i.toData());
		}
		
		b.close();
		
		return DataEntity.compress(b.getData());
	}
	
	@Override
	public void fromData(byte[] cdata) throws IOException
	{
		byte[] data = DataEntity.decompress(cdata);
		ByteReader b = new ByteReader(data);
		byte ty = b.rByte();
		
		if(ty != getDataType())
		{
			b.close();
			return;
		}
		
		int size = b.rInt();
		
		for(int i = 0; i < size; i++)
		{
			byte[] idata = b.rBytes();
			ByteReader bi = new ByteReader(idata);
			byte type = bi.rByte();
			DataEntity e = PackageType.getInstance(type);
			
			if(e != null)
			{
				e.fromData(idata);
				put(e);
			}
		}
	}
	
	@Override
	public byte getDataType()
	{
		return 0;
	}
	
	public GList<DataEntity> getEntities()
	{
		return entities;
	}
	
	@Override
	public String toString()
	{
		String n = "Packages: " + entities.size();
		
		for(DataEntity i : entities)
		{
			n = n + "\n" + i.toString();
		}
		
		return n;
	}
}
