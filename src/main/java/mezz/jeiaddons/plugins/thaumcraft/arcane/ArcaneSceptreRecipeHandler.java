package mezz.jeiaddons.plugins.thaumcraft.arcane;

import javax.annotation.Nonnull;

import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeHandler;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeUids;

public class ArcaneSceptreRecipeHandler extends ThaumcraftRecipeHandler<ArcaneSceptreRecipeWrapper> {
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
}
