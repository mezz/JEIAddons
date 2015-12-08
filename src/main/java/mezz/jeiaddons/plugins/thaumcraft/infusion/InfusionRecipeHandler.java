package mezz.jeiaddons.plugins.thaumcraft.infusion;

import javax.annotation.Nonnull;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.PluginThaumcraft;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeUids;

public class InfusionRecipeHandler implements IRecipeHandler<InfusionRecipeWrapper> {
	@Nonnull
	@Override
	public Class<InfusionRecipeWrapper> getRecipeClass() {
		return InfusionRecipeWrapper.class;
	}

	@Nonnull
	@Override
	public String getRecipeCategoryUid() {
		return ThaumcraftRecipeUids.INFUSION;
	}

	@Nonnull
	@Override
	public IRecipeWrapper getRecipeWrapper(@Nonnull InfusionRecipeWrapper recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(@Nonnull InfusionRecipeWrapper recipe) {
		if (!recipe.isResearched()) {
			PluginThaumcraft.helper.addUnresearchedRecipe(recipe);
			return false;
		}
		return true;
	}
}
