package mezz.jeiaddons.plugins.thaumcraft.arcane;

import javax.annotation.Nonnull;

import mezz.jeiaddons.plugins.thaumcraft.IResearchableRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.ResearchableRecipeHandler;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeUids;

public class ArcaneSceptreRecipeHandler extends ResearchableRecipeHandler<ArcaneSceptreRecipeWrapper> {
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
	public IResearchableRecipeWrapper getRecipeWrapper(@Nonnull ArcaneSceptreRecipeWrapper recipe) {
		return recipe;
	}
}
