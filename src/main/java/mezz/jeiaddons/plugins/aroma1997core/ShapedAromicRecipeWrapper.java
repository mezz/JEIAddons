package mezz.jeiaddons.plugins.aroma1997core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.minecraft.item.ItemStack;

import aroma1997.core.recipes.ShapedAromicRecipe;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;

public class ShapedAromicRecipeWrapper extends BlankRecipeWrapper implements IShapedCraftingRecipeWrapper {
	private final ShapedAromicRecipe recipe;

	public ShapedAromicRecipeWrapper(ShapedAromicRecipe recipe) {
		this.recipe = recipe;
	}

	@Override
	public List getInputs() {
		return Arrays.asList(recipe.getInput());
	}

	@Override
	public List<ItemStack> getOutputs() {
		return Collections.singletonList(recipe.getRecipeOutput());
	}

	@Override
	public int getWidth() {
		return recipe.getRecipeWidth();
	}

	@Override
	public int getHeight() {
		return recipe.getRecipeHeight();
	}
}
