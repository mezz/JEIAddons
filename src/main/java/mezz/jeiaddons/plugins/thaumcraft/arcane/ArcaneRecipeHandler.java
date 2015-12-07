package mezz.jeiaddons.plugins.thaumcraft.arcane;

import javax.annotation.Nonnull;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jeiaddons.plugins.thaumcraft.IResearchableRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.PluginThaumcraft;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeUids;
import thaumcraft.api.crafting.IArcaneRecipe;

public abstract class ArcaneRecipeHandler<T extends IArcaneRecipe> implements IRecipeHandler<T> {
	@Nonnull
	@Override
	public String getRecipeCategoryUid() {
		return ThaumcraftRecipeUids.ARCANE;
	}

	@Nonnull
	@Override
	public abstract IResearchableRecipeWrapper getRecipeWrapper(@Nonnull T recipe);

	@Override
	public boolean isRecipeValid(@Nonnull T recipe) {
		IResearchableRecipeWrapper recipeWrapper = getRecipeWrapper(recipe);
		if (!recipeWrapper.isResearched()) {
			PluginThaumcraft.helper.addUnresearchedRecipe(recipeWrapper);
			return false;
		}

		return recipe.getRecipeOutput() != null;
	}
}
