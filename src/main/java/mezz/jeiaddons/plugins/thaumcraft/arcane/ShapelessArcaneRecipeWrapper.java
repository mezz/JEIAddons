package mezz.jeiaddons.plugins.thaumcraft.arcane;

import java.util.List;

import thaumcraft.api.crafting.ShapelessArcaneRecipe;

public class ShapelessArcaneRecipeWrapper extends ArcaneRecipeWrapper<ShapelessArcaneRecipe> {
	public ShapelessArcaneRecipeWrapper(ShapelessArcaneRecipe recipe) {
		super(recipe);
	}

	@Override
	public List getInputs() {
		return recipe.getInput();
	}
}
