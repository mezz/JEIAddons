package mezz.jeiaddons.plugins.thaumcraft.arcane;

import javax.annotation.Nonnull;

import net.minecraft.util.StatCollector;

import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeUids;

public class ArcaneWandRecipeCategory extends ArcaneRecipeCategory {
	@Nonnull
	private final String localizedName;

	public ArcaneWandRecipeCategory() {
		localizedName = StatCollector.translateToLocal("gui.jeiaddons.arcaneWorkbenchWandRecipes");
	}

	@Nonnull
	@Override
	public String getUid() {
		return ThaumcraftRecipeUids.WAND;
	}

	@Nonnull
	@Override
	public String getTitle() {
		return localizedName;
	}
}
