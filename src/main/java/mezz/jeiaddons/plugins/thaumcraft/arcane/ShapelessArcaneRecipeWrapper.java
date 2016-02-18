package mezz.jeiaddons.plugins.thaumcraft.arcane;

import mezz.jei.api.IJeiHelpers;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;

import javax.annotation.Nonnull;
import java.util.List;

public class ShapelessArcaneRecipeWrapper extends ArcaneRecipeWrapper<ShapelessArcaneRecipe> {
	public ShapelessArcaneRecipeWrapper(@Nonnull IJeiHelpers jeiHelpers, @Nonnull ShapelessArcaneRecipe recipe) {
		super(jeiHelpers, recipe);
	}

	@Override
	@Nonnull
	public List getInputs() {
		return recipe.getInput();
	}
}
