package mezz.jeiaddons.plugins.thaumcraft;

import mezz.jei.api.IItemRegistry;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.IRecipeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jeiaddons.utils.ModUtil;

@JEIPlugin
public class PluginThaumcraft implements IModPlugin {
	public static final String modId = "Thaumcraft";

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

		ThaumcraftHelper.register(registry);
	}

	@Override
	public void onRecipeRegistryAvailable(IRecipeRegistry recipeRegistry) {

	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {

	}
}
