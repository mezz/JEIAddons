package mezz.jeiaddons.plugins.thaumcraft;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;

import javax.annotation.Nonnull;

public abstract class ThaumcraftCraftingRecipeWrapper extends ThaumcraftRecipeWrapper implements ICraftingRecipeWrapper {
	protected ThaumcraftCraftingRecipeWrapper(@Nonnull IJeiHelpers jeiHelpers) {
		super(jeiHelpers, 66, 18);
	}
}
