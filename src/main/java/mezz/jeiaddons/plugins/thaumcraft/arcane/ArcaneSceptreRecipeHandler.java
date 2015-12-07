package mezz.jeiaddons.plugins.thaumcraft.arcane;

import javax.annotation.Nonnull;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.PluginThaumcraft;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeUids;

public class ArcaneSceptreRecipeHandler implements IRecipeHandler<ArcaneSceptreRecipeWrapper> {

	@Nonnull
	@Override
	public Class<ArcaneSceptreRecipeWrapper> getRecipeClass() {
		return ArcaneSceptreRecipeWrapper.class;
	}

	@Nonnull
	@Override
	public String getRecipeCategoryUid() {
		return ThaumcraftRecipeUids.ARCANE;
	}

	@Nonnull
	@Override
	public IRecipeWrapper getRecipeWrapper(@Nonnull ArcaneSceptreRecipeWrapper recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(@Nonnull ArcaneSceptreRecipeWrapper recipe) {
		if (!recipe.isResearched()) {
			PluginThaumcraft.helper.addUnresearchedRecipe(recipe);
			return false;
		}
		return true;
	}
}
