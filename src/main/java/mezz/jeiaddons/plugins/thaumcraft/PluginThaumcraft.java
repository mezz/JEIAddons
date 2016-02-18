package mezz.jeiaddons.plugins.thaumcraft;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jeiaddons.utils.ModUtil;

import javax.annotation.Nonnull;

@JEIPlugin
public class PluginThaumcraft extends BlankModPlugin {
	public static final String modId = "Thaumcraft";

	@Override
	public void register(@Nonnull IModRegistry registry) {
		if (ModUtil.isModLoaded(modId)) {
			ThaumcraftHelper.register(registry);
		}
	}
}
