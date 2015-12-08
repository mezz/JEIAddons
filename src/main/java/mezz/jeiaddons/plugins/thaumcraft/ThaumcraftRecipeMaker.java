package mezz.jeiaddons.plugins.thaumcraft;

import java.util.ArrayList;
import java.util.List;

import mezz.jeiaddons.plugins.thaumcraft.crucible.CrucibleRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.infusion.InfusionRecipeWrapper;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.InfusionRecipe;

public class ThaumcraftRecipeMaker {
	public static List<Object> getRecipes() {
		List<Object> recipes = new ArrayList<>();
		for (Object recipe : ThaumcraftApi.getCraftingRecipes()) {
			if (recipe instanceof InfusionRecipe) {
				InfusionRecipe infusionRecipe = (InfusionRecipe) recipe;
				if (infusionRecipe.getRecipeOutput() != null) {
					InfusionRecipeWrapper recipeWrapper = new InfusionRecipeWrapper(infusionRecipe);
					recipes.add(recipeWrapper);
				}
			} else if (recipe instanceof CrucibleRecipe) {
				CrucibleRecipe crucibleRecipe = (CrucibleRecipe) recipe;
				CrucibleRecipeWrapper recipeWrapper = new CrucibleRecipeWrapper(crucibleRecipe);
				recipes.add(recipeWrapper);
			}
		}
		return recipes;
	}
}
