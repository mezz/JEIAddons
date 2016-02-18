package mezz.jeiaddons.plugins.thaumcraft.arcane;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeHandler;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeUids;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;

import javax.annotation.Nonnull;

public class ShapelessArcaneRecipeHandler extends ThaumcraftRecipeHandler<ShapelessArcaneRecipe> {
	@Nonnull
	private final IJeiHelpers jeiHelpers;

	public ShapelessArcaneRecipeHandler(@Nonnull IJeiHelpers jeiHelpers) {
		this.jeiHelpers = jeiHelpers;
	}

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
		return new ShapelessArcaneRecipeWrapper(jeiHelpers, recipe);
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
