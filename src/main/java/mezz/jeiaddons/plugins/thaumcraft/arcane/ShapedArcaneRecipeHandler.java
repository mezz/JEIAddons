package mezz.jeiaddons.plugins.thaumcraft.arcane;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeHandler;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeUids;
import thaumcraft.api.crafting.ShapedArcaneRecipe;

import javax.annotation.Nonnull;

public class ShapedArcaneRecipeHandler extends ThaumcraftRecipeHandler<ShapedArcaneRecipe> {
	@Nonnull
	private final IJeiHelpers jeiHelpers;

	public ShapedArcaneRecipeHandler(@Nonnull IJeiHelpers jeiHelpers) {
		this.jeiHelpers = jeiHelpers;
	}

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
	public IRecipeWrapper getRecipeWrapper(@Nonnull ShapedArcaneRecipe recipe) {
		return new ShapedArcaneRecipeWrapper(jeiHelpers, recipe);
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
