package mezz.jeiaddons.plugins.thaumcraft.arcane;

import javax.annotation.Nonnull;

import mezz.jeiaddons.plugins.thaumcraft.IResearchableRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.ResearchableRecipeHandler;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeUids;

public class ArcaneWandRecipeHandler extends ResearchableRecipeHandler<ArcaneWandRecipeWrapper> {
	@Nonnull
	@Override
	public Class<ArcaneWandRecipeWrapper> getRecipeClass() {
		return ArcaneWandRecipeWrapper.class;
	}

	@Nonnull
	@Override
	public String getRecipeCategoryUid() {
		return ThaumcraftRecipeUids.ARCANE;
	}

	@Nonnull
	@Override
	public IResearchableRecipeWrapper getRecipeWrapper(@Nonnull ArcaneWandRecipeWrapper recipe) {
		return recipe;
	}
}
