package mezz.jeiaddons.config;

import javax.annotation.Nonnull;

import net.minecraft.util.StatCollector;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Config {
	public static Configuration configFile;

	public static final String categoryThaumcraft = Configuration.CATEGORY_GENERAL + ".thaumcraft";

	public static boolean thaumcraftRequireResearch = true;

	public static void preInit(@Nonnull FMLPreInitializationEvent event) {
		configFile = new Configuration(event.getSuggestedConfigurationFile());

		syncConfig();
	}

	public static void syncConfig() {
		String cheatItemsEnabledDescription = StatCollector.translateToLocal("config.JEIAddons.thaumcraft.requireResearch.description");
		thaumcraftRequireResearch = configFile.getBoolean("requireResearch", categoryThaumcraft, thaumcraftRequireResearch, cheatItemsEnabledDescription, "config.JEIAddons.thaumcraft.requireResearch");

		if (configFile.hasChanged()) {
			configFile.save();
		}
	}
}
