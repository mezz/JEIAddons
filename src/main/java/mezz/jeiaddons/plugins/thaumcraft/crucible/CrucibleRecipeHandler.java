package mezz.jeiaddons.plugins.thaumcraft.crucible;

import javax.annotation.Nonnull;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.PluginThaumcraft;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeUids;

public class CrucibleRecipeHandler implements IRecipeHandler<CrucibleRecipeWrapper> {
	@Nonnull
	@Override
	public Class<CrucibleRecipeWrapper> getRecipeClass() {
		return CrucibleRecipeWrapper.class;
	}

	@Nonnull
	@Override
	public String getRecipeCategoryUid() {
		return ThaumcraftRecipeUids.CRUCIBLE;
	}

	@Nonnull
	@Override
	public IRecipeWrapper getRecipeWrapper(@Nonnull CrucibleRecipeWrapper recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(@Nonnull CrucibleRecipeWrapper recipe) {
		if (!recipe.isResearched()) {
			PluginThaumcraft.helper.addUnresearchedRecipe(recipe);
			return false;
		}

		return recipe.getOutputs() != null && recipe.getOutputs().size() > 0;
	}
}
