package mezz.jeiaddons.plugins.thaumcraft.arcane;

import javax.annotation.Nonnull;

import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeHandler;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeUids;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;

public class ShapelessArcaneRecipeHandler extends ThaumcraftRecipeHandler<ShapelessArcaneRecipe> {
	@Nonnull
	@Override
	public Class<ShapelessArcaneRecipe> getRecipeClass() {
		return ShapelessArcaneRecipe.class;
	}

	@Nonnull
	@Override
	public String getRecipeCategoryUid() {
		return ThaumcraftRecipeUids.ARCANE;
	}

	@Nonnull
	@Override
	public IRecipeWrapper getRecipeWrapper(@Nonnull ShapelessArcaneRecipe recipe) {
		return new ShapelessArcaneRecipeWrapper(recipe);
	}

	@Override
	public boolean isRecipeValid(@Nonnull ShapelessArcaneRecipe recipe) {
		if (!super.isRecipeValid(recipe)) {
			return false;
		}

		int inputCount = 0;
		for (Object input : recipe.getInput()) {
			if (input != null) {
				inputCount++;
			}
		}
		return inputCount > 0 && recipe.getRecipeOutput() != null;
	}
}
