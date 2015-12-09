package mezz.jeiaddons.plugins.thaumcraft;

import javax.annotation.Nonnull;

import mezz.jei.api.recipe.IRecipeHandler;

public abstract class ResearchableRecipeHandler<T> implements IRecipeHandler<T> {
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

		return true;
	}
}
