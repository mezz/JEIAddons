package mezz.jeiaddons.plugins.aroma1997core;

import java.util.Collections;
import java.util.List;

import net.minecraft.item.ItemStack;

import aroma1997.core.recipes.ShapelessAromicRecipe;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;

public class ShapelessAromicRecipeWrapper extends BlankRecipeWrapper implements ICraftingRecipeWrapper {

	private final ShapelessAromicRecipe recipe;

	public ShapelessAromicRecipeWrapper(ShapelessAromicRecipe recipe) {
		this.recipe = recipe;
	}

	@Override
	public List getInputs() {
		return recipe.getInput();
	}

	@Override
	public List<ItemStack> getOutputs() {
		return Collections.singletonList(recipe.getRecipeOutput());
	}
}
