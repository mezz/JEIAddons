package mezz.jeiaddons.plugins.aroma1997core;

import javax.annotation.Nonnull;

import aroma1997.core.recipes.ShapelessAromicRecipe;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;

public class ShapelessAromicRecipeHandler implements IRecipeHandler<ShapelessAromicRecipe> {
	@Nonnull
	@Override
	public Class<ShapelessAromicRecipe> getRecipeClass() {
		return ShapelessAromicRecipe.class;
	}

	@Nonnull
	@Override
	public String getRecipeCategoryUid() {
		return VanillaRecipeCategoryUid.CRAFTING;
	}

	@Nonnull
	@Override
	public IRecipeWrapper getRecipeWrapper(@Nonnull ShapelessAromicRecipe recipe) {
		return new ShapelessAromicRecipeWrapper(recipe);
	}

	@Override
	public boolean isRecipeValid(@Nonnull ShapelessAromicRecipe recipe) {
		return !recipe.isHidden();
	}
}
