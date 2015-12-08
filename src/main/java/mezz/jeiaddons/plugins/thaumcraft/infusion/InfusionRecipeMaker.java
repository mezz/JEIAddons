package mezz.jeiaddons.plugins.thaumcraft.infusion;

import java.util.ArrayList;
import java.util.List;

import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.crafting.InfusionRecipe;

public class InfusionRecipeMaker {
	public static List<Object> getRecipes() {
		List<Object> infusionRecipes = new ArrayList<>();
		for (Object recipe : ThaumcraftApi.getCraftingRecipes()) {
			if (recipe instanceof InfusionRecipe) {
				InfusionRecipe infusionRecipe = (InfusionRecipe) recipe;
				if (infusionRecipe.getRecipeOutput() != null) {
					InfusionRecipeWrapper recipeWrapper = new InfusionRecipeWrapper(infusionRecipe);
					infusionRecipes.add(recipeWrapper);
				}
			}
		}
		return infusionRecipes;
	}
}
