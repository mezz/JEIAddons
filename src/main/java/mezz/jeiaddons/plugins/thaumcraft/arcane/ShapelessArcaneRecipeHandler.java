package mezz.jeiaddons.plugins.thaumcraft.arcane;

import javax.annotation.Nonnull;

import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.IResearchableRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeUids;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;

public class ShapelessArcaneRecipeHandler extends ArcaneRecipeHandler<ShapelessArcaneRecipe> {
	@Nonnull
	@Override
	public Class<ShapelessArcaneRecipe> getRecipeClass() {
		return ShapelessArcaneRecipe.class;
	}

	@Nonnull
	@Override
	public IResearchableRecipeWrapper getRecipeWrapper(@Nonnull ShapelessArcaneRecipe recipe) {
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
		return inputCount > 0;
	}
}
