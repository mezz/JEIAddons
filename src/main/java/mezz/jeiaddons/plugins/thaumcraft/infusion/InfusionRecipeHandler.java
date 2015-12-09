package mezz.jeiaddons.plugins.thaumcraft.infusion;

import javax.annotation.Nonnull;

import mezz.jeiaddons.plugins.thaumcraft.IResearchableRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.ResearchableRecipeHandler;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeUids;

public class InfusionRecipeHandler extends ResearchableRecipeHandler<InfusionRecipeWrapper> {
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
	public IResearchableRecipeWrapper getRecipeWrapper(@Nonnull InfusionRecipeWrapper recipe) {
		return recipe;
	}
}
