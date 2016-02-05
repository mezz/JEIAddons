package mezz.jeiaddons.plugins.thaumcraft.arcane;

import javax.annotation.Nonnull;

import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeHandler;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeUids;

public class ArcaneWandRecipeHandler extends ThaumcraftRecipeHandler<ArcaneWandRecipeWrapper> {
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
	public IRecipeWrapper getRecipeWrapper(@Nonnull ArcaneWandRecipeWrapper recipe) {
		return recipe;
	}
}
