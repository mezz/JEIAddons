package mezz.jeiaddons.plugins.thaumcraft.infernal;

import javax.annotation.Nonnull;

import mezz.jeiaddons.plugins.thaumcraft.IResearchableRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.ResearchableRecipeHandler;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeUids;

public class InfernalSmeltingRecipeHandler extends ResearchableRecipeHandler<InfernalSmeltingRecipeWrapper> {
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
	public IResearchableRecipeWrapper getRecipeWrapper(@Nonnull InfernalSmeltingRecipeWrapper recipe) {
		return recipe;
	}
}
