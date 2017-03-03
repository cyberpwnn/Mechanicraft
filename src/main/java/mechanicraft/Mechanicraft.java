package mechanicraft;

import java.io.IOException;
import org.bukkit.Material;
import mechanicraft.api.builder.DataPack;
import mechanicraft.content.MechanicalItem;
import mechanicraft.content.MechanicalShapedRecipe;
import mechanicraft.content.MechanicalShapelessRecipe;
import mechanicraft.content.MechanicalStack;
import mechanicraft.util.MaterialBlock;
import mechanicraft.util.MechanicalPlugin;

public class Mechanicraft extends MechanicalPlugin
{
	private static Mechanicraft instance;
	private ConfigurationController configurationController;
	
	@Override
	public void start()
	{
		s("Started " + getName());
		instance = this;
		
		configurationController = new ConfigurationController();
		
		s("Testing DataPack");
		
		try
		{
			DataPack dp = new DataPack();
			dp.put(new MechanicalItem("Item 1", new MaterialBlock(Material.GLASS, 29)));
			dp.put(new MechanicalItem("Item 2", new MaterialBlock(Material.GRASS)));
			dp.put(new MechanicalStack(new MechanicalItem("Mech Item 1", new MaterialBlock(Material.STAINED_CLAY, 4)), 3));
			dp.put(new MechanicalStack(new MechanicalItem("Mech Item 2", new MaterialBlock(Material.STAINED_GLASS, 6)), 4));
			dp.put(new MechanicalShapelessRecipe().addIngredient(new MechanicalStack(new MechanicalItem("Mech Item 4", new MaterialBlock(Material.STAINED_GLASS_PANE, 3)), 6)).addIngredient(new MechanicalStack(new MechanicalItem("Mech Item 4", new MaterialBlock(Material.STAINED_GLASS, 4)), 2)).setOutput(new MechanicalStack(new MechanicalItem("Mech Item 2", new MaterialBlock(Material.STAINED_GLASS, 2)), 8)));
			dp.put(new MechanicalShapedRecipe().addIngredient(0, new MechanicalStack(new MechanicalItem("Mech Item 6", new MaterialBlock(Material.STAINED_GLASS_PANE, 3)), 6)).addIngredient(1, new MechanicalStack(new MechanicalItem("Mech Item 4", new MaterialBlock(Material.STAINED_GLASS, 4)), 2)).addIngredient(2, new MechanicalStack(new MechanicalItem("Mech Item 4", new MaterialBlock(Material.STAINED_GLASS_PANE, 4)), 6)).addIngredient(3, new MechanicalStack(new MechanicalItem("Mech Item 7", new MaterialBlock(Material.STAINED_GLASS, 3)), 2)).addIngredient(4, new MechanicalStack(new MechanicalItem("Mech Item 2", new MaterialBlock(Material.STAINED_GLASS_PANE, 2)), 8)).addIngredient(5, new MechanicalStack(new MechanicalItem("Mech Item 3", new MaterialBlock(Material.STAINED_GLASS, 0)), 5)).addIngredient(6, new MechanicalStack(new MechanicalItem("Mech Item 5", new MaterialBlock(Material.STAINED_GLASS_PANE, 8)), 4)).addIngredient(7, new MechanicalStack(new MechanicalItem("Mech Item 1", new MaterialBlock(Material.STAINED_GLASS, 1)), 3)).addIngredient(8, new MechanicalStack(new MechanicalItem("Mech Item 3", new MaterialBlock(Material.STAINED_GLASS_PANE, 3)), 3)).setOutput(new MechanicalStack(new MechanicalItem("Mech Item 2", new MaterialBlock(Material.STAINED_GLASS, 2)), 8)));
			byte[] data = dp.toData();
			
			w("Data: " + new String(data));
			w("----------------------------");
			DataPack np = new DataPack();
			np.fromData(data);
			s(np.toString());
		}
		
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void stop()
	{
		s("Stopped " + getName());
	}
	
	public static Mechanicraft getInstance()
	{
		return instance;
	}
	
	public ConfigurationController getConfigurationController()
	{
		return configurationController;
	}
}
