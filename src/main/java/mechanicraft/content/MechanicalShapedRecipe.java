package mechanicraft.content;

import java.io.IOException;
import mechanicraft.util.ByteReader;
import mechanicraft.util.ByteWriter;

public class MechanicalShapedRecipe extends MechanicalRecipe
{
	public MechanicalShapedRecipe()
	{
		super();
	}
	
	public MechanicalShapedRecipe addIngredient(int slot, MechanicalStack stack)
	{
		inputs.put(slot, stack);
		
		return this;
	}
	
	public MechanicalShapedRecipe setOutput(MechanicalStack stack)
	{
		output = stack;
		return this;
	}
	
	@Override
	public byte[] toData() throws IOException
	{
		ByteWriter b = new ByteWriter();
		b.wByte(getDataType());
		b.wBytes(output.toData());
		b.wInt(inputs.size());
		
		for(int i : inputs.k())
		{
			b.wInt(i);
			b.wBytes(inputs.get(i).toData());
		}
		
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
		
		output = new MechanicalStack();
		output.fromData(b.rBytes());
		int size = b.rInt();
		
		for(int i = 0; i < size; i++)
		{
			int slot = b.rInt();
			byte[] idata = b.rBytes();
			MechanicalStack st = new MechanicalStack();
			st.fromData(idata);
			inputs.put(slot, st);
		}
		
		b.close();
	}
	
	@Override
	public byte getDataType()
	{
		return 4;
	}
	
	@Override
	public String toString()
	{
		String base = "Recipe<ShapedCrafting> (i: " + inputs.size() + " o: 1)";
		
		for(int i : inputs.k())
		{
			base = base + "\n  Input " + i + ": " + inputs.get(i).toString();
		}
		
		base = base + "\n  Output 0: " + output.toString();
		
		return base;
	}
}
