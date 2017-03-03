package mechanicraft.content;

import mechanicraft.api.builder.DataEntity;
import mechanicraft.util.GMap;

public abstract class MechanicalRecipe implements DataEntity
{
	protected GMap<Integer, MechanicalStack> inputs;
	protected MechanicalStack output;
	
	public MechanicalRecipe()
	{
		inputs = new GMap<Integer, MechanicalStack>();
		output = null;
	}
	
	public GMap<Integer, MechanicalStack> getInputs()
	{
		return inputs;
	}
	
	public MechanicalStack getOutput()
	{
		return output;
	}
}
