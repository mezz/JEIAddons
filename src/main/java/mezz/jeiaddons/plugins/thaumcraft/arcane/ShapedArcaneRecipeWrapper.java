package mezz.jeiaddons.plugins.thaumcraft.arcane;

import java.util.Arrays;
import java.util.List;

import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import thaumcraft.api.crafting.ShapedArcaneRecipe;

public class ShapedArcaneRecipeWrapper extends ArcaneRecipeWrapper<ShapedArcaneRecipe> implements IShapedCraftingRecipeWrapper {

	public ShapedArcaneRecipeWrapper(ShapedArcaneRecipe recipe) {
		super(recipe);
	}

	@Override
	public int getWidth() {
		return recipe.width;
	}

	@Override
	public int getHeight() {
		return recipe.height;
	}

	@Override
	public List getInputs() {
		return Arrays.asList(recipe.getInput());
	}
}
