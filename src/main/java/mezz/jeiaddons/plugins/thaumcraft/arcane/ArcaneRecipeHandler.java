package mezz.jeiaddons.plugins.thaumcraft.arcane;

import javax.annotation.Nonnull;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jeiaddons.plugins.thaumcraft.PluginThaumcraft;
import thaumcraft.api.crafting.IArcaneRecipe;

public abstract class ArcaneRecipeHandler<T extends IArcaneRecipe> implements IRecipeHandler<T> {
	@Override
	public boolean isRecipeValid(@Nonnull T recipe) {
		if (!PluginThaumcraft.helper.isResearched(recipe)) {
			PluginThaumcraft.helper.addUnresearchedRecipe(recipe);
			return false;
		}

		return recipe.getRecipeOutput() != null;
	}
}
