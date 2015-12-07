package mezz.jeiaddons.utils;

import javax.annotation.Nullable;

import net.minecraft.inventory.Container;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.versioning.ArtifactVersion;
import net.minecraftforge.fml.common.versioning.DefaultArtifactVersion;
import net.minecraftforge.fml.common.versioning.VersionParser;
import net.minecraftforge.fml.common.versioning.VersionRange;

public class ModUtil {
	public static boolean isModLoaded(String modname) {
		return Loader.isModLoaded(modname);
	}

	public static boolean isModLoaded(String modname, String versionRangeString) {
		if (!isModLoaded(modname)) {
			return false;
		}

		if (versionRangeString != null) {
			ModContainer mod = Loader.instance().getIndexedModList().get(modname);
			ArtifactVersion modVersion = mod.getProcessedVersion();

			VersionRange versionRange = VersionParser.parseRange(versionRangeString);
			DefaultArtifactVersion requiredVersion = new DefaultArtifactVersion(modname, versionRange);

			if (!requiredVersion.containsVersion(modVersion)) {
				return false;
			}
		}

		return true;
	}

	@Nullable
	public static Class getClassForName(String className) {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			Log.error("Couldn't find class for {}", className);
			return null;
		}
	}

	@Nullable
	public static Class<? extends Container> getContainerClassForName(String className) {
		try {
			return Class.forName(className).asSubclass(Container.class);
		} catch (ClassNotFoundException | ClassCastException e) {
			Log.error("Couldn't find class for {}", className);
			return null;
		}
	}
}
