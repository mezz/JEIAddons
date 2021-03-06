package mezz.jeiaddons.plugins.thaumcraft.crucible;

import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeHandler;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeUids;

import javax.annotation.Nonnull;

public class CrucibleRecipeHandler extends ThaumcraftRecipeHandler<CrucibleRecipeWrapper> {
	@Nonnull
	@Override
	public Class<CrucibleRecipeWrapper> getRecipeClass() {
		return CrucibleRecipeWrapper.class;
	}

	@Nonnull
	@Override
	public String getRecipeCategoryUid() {
		return ThaumcraftRecipeUids.CRUCIBLE;
	}

	@Nonnull
	@Override
	public IRecipeWrapper getRecipeWrapper(@Nonnull CrucibleRecipeWrapper recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(@Nonnull CrucibleRecipeWrapper recipe) {
		return super.isRecipeValid(recipe) && !recipe.getOutputs().isEmpty();
	}
}
