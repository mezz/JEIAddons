package mezz.jeiaddons.plugins.thaumcraft;

import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;

public abstract class ThaumcraftCraftingRecipeWrapper extends ThaumcraftRecipeWrapper implements ICraftingRecipeWrapper {
	protected ThaumcraftCraftingRecipeWrapper() {
		super(64, 28);
	}
}
