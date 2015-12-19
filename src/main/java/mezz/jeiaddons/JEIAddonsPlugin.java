package mezz.jeiaddons;

import mezz.jei.api.IItemRegistry;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.IRecipeRegistry;
import mezz.jei.api.JEIPlugin;

@JEIPlugin
public class JEIAddonsPlugin implements IModPlugin {
	public static IJeiHelpers jeiHelpers;
	public static IItemRegistry itemRegistry;
	public static IRecipeRegistry recipeRegistry;

	@Override
	public boolean isModLoaded() {
		return true;
	}

	@Override
	public void onJeiHelpersAvailable(IJeiHelpers jeiHelpers) {
		JEIAddonsPlugin.jeiHelpers = jeiHelpers;
	}

	@Override
	public void onItemRegistryAvailable(IItemRegistry itemRegistry) {
		JEIAddonsPlugin.itemRegistry = itemRegistry;
	}

	@Override
	public void register(IModRegistry registry) {

	}

	@Override
	public void onRecipeRegistryAvailable(IRecipeRegistry recipeRegistry) {
		JEIAddonsPlugin.recipeRegistry = recipeRegistry;
	}
}
