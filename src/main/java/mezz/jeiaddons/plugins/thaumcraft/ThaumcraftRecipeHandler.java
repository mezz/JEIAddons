package mezz.jeiaddons.plugins.thaumcraft;

import javax.annotation.Nonnull;

import mezz.jei.api.recipe.IRecipeHandler;

public abstract class ThaumcraftRecipeHandler<T> implements IRecipeHandler<T> {
	@Override
	public boolean isRecipeValid(@Nonnull T recipe) {
		return true;
	}
}
