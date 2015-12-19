package mezz.jeiaddons.utils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.versioning.ArtifactVersion;
import net.minecraftforge.fml.common.versioning.DefaultArtifactVersion;
import net.minecraftforge.fml.common.versioning.VersionParser;
import net.minecraftforge.fml.common.versioning.VersionRange;

import mezz.jei.api.IItemRegistry;
import mezz.jeiaddons.JEIAddonsPlugin;

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

	@Nonnull
	public static List<ItemStack> getItemStacksFromMod(Iterable<ItemStack> itemStacks, String modId) {
		IItemRegistry itemRegistry = JEIAddonsPlugin.itemRegistry;
		List<ItemStack> itemStacksFromMod = new ArrayList<>();
		for (ItemStack itemStack : itemStacks) {
			if (itemStack != null) {
				Item item = itemStack.getItem();
				if (itemRegistry.getModNameForItem(item).equals(modId)) {
					itemStacksFromMod.add(itemStack);
				}
			}
		}
		return itemStacksFromMod;
	}
}
