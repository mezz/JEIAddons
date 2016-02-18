package mezz.jeiaddons.plugins.thaumcraft.arcane;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import thaumcraft.api.crafting.ShapedArcaneRecipe;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public class ShapedArcaneRecipeWrapper extends ArcaneRecipeWrapper<ShapedArcaneRecipe> implements IShapedCraftingRecipeWrapper {

	public ShapedArcaneRecipeWrapper(@Nonnull IJeiHelpers jeiHelpers, @Nonnull ShapedArcaneRecipe recipe) {
		super(jeiHelpers, recipe);
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
	@Nonnull
	public List getInputs() {
		return Arrays.asList(recipe.getInput());
	}
}
