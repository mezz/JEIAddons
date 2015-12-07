package mezz.jeiaddons;

import javax.annotation.Nonnull;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import mezz.jeiaddons.config.Config;

@Mod(
		modid = JEIAddons.MOD_ID,
		version = JEIAddons.VERSION,
		dependencies = "required-after:JEI@[1.3.0.45,)"
)
public class JEIAddons {
	public static final String MOD_ID = "JEIAddons";
	public static final String VERSION = "@VERSION@";

	@Mod.EventHandler
	public void preInit(@Nonnull FMLPreInitializationEvent event) {
		Config.preInit(event);
	}
}
