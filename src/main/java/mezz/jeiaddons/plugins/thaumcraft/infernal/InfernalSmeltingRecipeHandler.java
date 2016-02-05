package mezz.jeiaddons.plugins.thaumcraft.infernal;

import javax.annotation.Nonnull;

import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeHandler;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeUids;

public class InfernalSmeltingRecipeHandler extends ThaumcraftRecipeHandler<InfernalSmeltingRecipeWrapper> {
	@Nonnull
	@Override
	public Class<InfernalSmeltingRecipeWrapper> getRecipeClass() {
		return InfernalSmeltingRecipeWrapper.class;
	}

	@Nonnull
	@Override
	public String getRecipeCategoryUid() {
		return ThaumcraftRecipeUids.INFERNAL;
	}

	@Nonnull
	@Override
	public IRecipeWrapper getRecipeWrapper(@Nonnull InfernalSmeltingRecipeWrapper recipe) {
		return recipe;
	}
}
