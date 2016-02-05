package mezz.jeiaddons.plugins.thaumcraft.infusion;

import javax.annotation.Nonnull;

import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeHandler;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeUids;

public class InfusionRecipeHandler extends ThaumcraftRecipeHandler<InfusionRecipeWrapper> {
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
}
