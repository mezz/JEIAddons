package mezz.jeiaddons.plugins.thaumcraft;

import net.minecraftforge.common.MinecraftForge;

import mezz.jei.api.IItemRegistry;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.IRecipeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jeiaddons.utils.ModUtil;

@JEIPlugin
public class PluginThaumcraft implements IModPlugin {
	public static final String modId = "Thaumcraft";
	public static ThaumcraftHelper helper;

	@Override
	public void onJeiHelpersAvailable(IJeiHelpers jeiHelpers) {

	}

	@Override
	public void onItemRegistryAvailable(IItemRegistry itemRegistry) {

	}

	@Override
	public void register(IModRegistry registry) {
		if (!ModUtil.isModLoaded(modId)) {
			return;
		}

		// the plugin may get created multiple times for multiple game runs
		if (helper != null) {
			MinecraftForge.EVENT_BUS.unregister(helper);
		}
		helper = new ThaumcraftHelper();
		MinecraftForge.EVENT_BUS.register(helper);

		helper.register(registry);
	}

	@Override
	public void onRecipeRegistryAvailable(IRecipeRegistry recipeRegistry) {

	}
}
