package mezz.jeiaddons;

import javax.annotation.Nonnull;

import net.minecraft.nbt.NBTTagCompound;

import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

import mezz.jeiaddons.plugins.thaumcraft.PluginThaumcraft;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftHelper;
import mezz.jeiaddons.utils.ModUtil;

@Mod(
		modid = JEIAddons.MOD_ID,
		version = JEIAddons.VERSION,
		clientSideOnly = true,
		acceptedMinecraftVersions = "[1.8.8, 1.8.9]",
		dependencies =
				"required-after:JEI@[2.16,);" +
				"after:Thaumcraft"
)
public class JEIAddons {
	public static final String MOD_ID = "JEIAddons";
	public static final String VERSION = "@VERSION@";

	@Mod.EventHandler
	public void preInit(@Nonnull FMLPreInitializationEvent event) {
		if (event.getSide() != Side.CLIENT) {
			return;
		}

		if (ModUtil.isModLoaded(PluginThaumcraft.modId)) {
			ThaumcraftHelper thaumcraftHelper = new ThaumcraftHelper();
			thaumcraftHelper.preInit();
		}

		initVersionChecker();
	}

	private void initVersionChecker() {
		final NBTTagCompound compound = new NBTTagCompound();
		compound.setString("curseProjectName", "jeiaddons");
		compound.setString("curseFilenameParser", "JEIAddons_" + ForgeVersion.mcVersion + "-[].jar");
		FMLInterModComms.sendRuntimeMessage(JEIAddons.MOD_ID, "VersionChecker", "addCurseCheck", compound);
	}
}
