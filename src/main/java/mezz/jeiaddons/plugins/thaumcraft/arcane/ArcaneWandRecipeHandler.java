package mezz.jeiaddons.plugins.thaumcraft.arcane;

import javax.annotation.Nonnull;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.PluginThaumcraft;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeUids;

public class ArcaneWandRecipeHandler implements IRecipeHandler<ArcaneWandRecipeWrapper> {

	@Nonnull
	@Override
	public Class<ArcaneWandRecipeWrapper> getRecipeClass() {
		return ArcaneWandRecipeWrapper.class;
	}

	@Nonnull
	@Override
	public String getRecipeCategoryUid() {
		return ThaumcraftRecipeUids.WAND;
	}

	@Nonnull
	@Override
	public IRecipeWrapper getRecipeWrapper(@Nonnull ArcaneWandRecipeWrapper recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(@Nonnull ArcaneWandRecipeWrapper recipe) {
		if (!recipe.isResearched()) {
			PluginThaumcraft.helper.addUnresearchedRecipe(recipe);
			return false;
		}
		return true;
	}
}
