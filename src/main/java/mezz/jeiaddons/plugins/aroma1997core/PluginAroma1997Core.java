package mezz.jeiaddons.plugins.aroma1997core;

import mezz.jei.api.IItemRegistry;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.IRecipeRegistry;
import mezz.jei.api.JEIPlugin;

@JEIPlugin
public class PluginAroma1997Core implements IModPlugin {
	@Override
	public boolean isModLoaded() {
		return true;
	}

	@Override
	public void onJeiHelpersAvailable(IJeiHelpers jeiHelpers) {

	}

	@Override
	public void onItemRegistryAvailable(IItemRegistry itemRegistry) {

	}

	@Override
	public void register(IModRegistry registry) {
		registry.addRecipeHandlers(
				new ShapedAromicRecipeHandler(),
				new ShapelessAromicRecipeHandler()
		);
	}

	@Override
	public void onRecipeRegistryAvailable(IRecipeRegistry recipeRegistry) {

	}
}
