package mezz.jeiaddons.plugins.thaumcraft.arcane;

import javax.annotation.Nonnull;

import mezz.jeiaddons.plugins.thaumcraft.IResearchableRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.ResearchableRecipeHandler;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeUids;
import thaumcraft.api.crafting.ShapedArcaneRecipe;

public class ShapedArcaneRecipeHandler extends ResearchableRecipeHandler<ShapedArcaneRecipe> {
	@Nonnull
	@Override
	public Class<ShapedArcaneRecipe> getRecipeClass() {
		return ShapedArcaneRecipe.class;
	}

	@Nonnull
	@Override
	public String getRecipeCategoryUid() {
		return ThaumcraftRecipeUids.ARCANE;
	}

	@Nonnull
	@Override
	public IResearchableRecipeWrapper getRecipeWrapper(@Nonnull ShapedArcaneRecipe recipe) {
		return new ShapedArcaneRecipeWrapper(recipe);
	}

	@Override
	public boolean isRecipeValid(@Nonnull ShapedArcaneRecipe recipe) {
		if (!super.isRecipeValid(recipe)) {
			return false;
		}

		int inputCount = 0;
		for (Object input : recipe.input) {
			if (input != null) {
				inputCount++;
			}
		}
		return inputCount > 0 && recipe.getRecipeOutput() != null;
	}
}
