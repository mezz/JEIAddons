package mezz.jeiaddons.plugins.thaumcraft;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jeiaddons.utils.ModUtil;

@JEIPlugin
public class PluginThaumcraft implements IModPlugin {
	public static final String modId = "Thaumcraft";
	public static ThaumcraftHelper helper;

	@Override
	public boolean isModLoaded() {
		return ModUtil.isModLoaded(modId);
	}

	@Override
	public void register(IModRegistry registry) {
		// the plugin may get created multiple times for multiple game runs
		if (helper != null) {
			MinecraftForge.EVENT_BUS.unregister(helper);
			FMLCommonHandler.instance().bus().unregister(helper);
		}
		helper = new ThaumcraftHelper();
		MinecraftForge.EVENT_BUS.register(helper);
		FMLCommonHandler.instance().bus().register(helper);

		helper.register(registry);
	}
}
