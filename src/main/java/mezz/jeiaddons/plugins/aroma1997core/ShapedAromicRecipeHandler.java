package mezz.jeiaddons.plugins.aroma1997core;

import javax.annotation.Nonnull;

import aroma1997.core.recipes.ShapedAromicRecipe;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;

public class ShapedAromicRecipeHandler implements IRecipeHandler<ShapedAromicRecipe> {
	@Nonnull
	@Override
	public Class<ShapedAromicRecipe> getRecipeClass() {
		return ShapedAromicRecipe.class;
	}

	@Nonnull
	@Override
	public String getRecipeCategoryUid() {
		return VanillaRecipeCategoryUid.CRAFTING;
	}

	@Nonnull
	@Override
	public IRecipeWrapper getRecipeWrapper(@Nonnull ShapedAromicRecipe recipe) {
		return new ShapedAromicRecipeWrapper(recipe);
	}

	@Override
	public boolean isRecipeValid(@Nonnull ShapedAromicRecipe recipe) {
		return !recipe.isHidden();
	}
}
