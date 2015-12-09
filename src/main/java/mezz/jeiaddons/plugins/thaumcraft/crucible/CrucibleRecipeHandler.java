package mezz.jeiaddons.plugins.thaumcraft.crucible;

import javax.annotation.Nonnull;

import mezz.jeiaddons.plugins.thaumcraft.IResearchableRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.ResearchableRecipeHandler;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeUids;

public class CrucibleRecipeHandler extends ResearchableRecipeHandler<CrucibleRecipeWrapper> {
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
	public IResearchableRecipeWrapper getRecipeWrapper(@Nonnull CrucibleRecipeWrapper recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(@Nonnull CrucibleRecipeWrapper recipe) {
		return super.isRecipeValid(recipe) && recipe.getOutputs() != null && recipe.getOutputs().size() > 0;
	}
}
