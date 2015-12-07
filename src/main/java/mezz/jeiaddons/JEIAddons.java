package mezz.jeiaddons;

import javax.annotation.Nonnull;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import mezz.jeiaddons.config.Config;
import mezz.jeiaddons.plugins.thaumcraft.PluginThaumcraft;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftHelper;
import mezz.jeiaddons.utils.ModUtil;

@Mod(
		modid = JEIAddons.MOD_ID,
		version = JEIAddons.VERSION,
		dependencies = "required-after:JEI@[1.3.0.51,)"
)
public class JEIAddons {
	public static final String MOD_ID = "JEIAddons";
	public static final String VERSION = "@VERSION@";

	@Mod.EventHandler
	public void preInit(@Nonnull FMLPreInitializationEvent event) {
		Config.preInit(event);

		if (ModUtil.isModLoaded(PluginThaumcraft.modId)) {
			ThaumcraftHelper thaumcraftHelper = new ThaumcraftHelper();
			thaumcraftHelper.preInit();
		}
	}
}
