package mezz.jeiaddons.plugins.thaumcraft;

import java.util.List;

import net.minecraft.item.ItemStack;

import mezz.jei.api.recipe.IRecipeWrapper;

public interface IResearchableRecipeWrapper extends IRecipeWrapper {
	boolean isResearched();

	List<ItemStack> getOutputs();

	Object getRecipe();
}
