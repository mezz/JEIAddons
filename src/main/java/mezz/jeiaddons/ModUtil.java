package mezz.jeiaddons;

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
}
