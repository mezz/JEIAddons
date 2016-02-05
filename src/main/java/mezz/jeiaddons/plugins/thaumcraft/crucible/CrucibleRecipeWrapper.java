package mezz.jeiaddons.plugins.thaumcraft.crucible;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeWrapper;
import thaumcraft.api.crafting.CrucibleRecipe;

public class CrucibleRecipeWrapper extends ThaumcraftRecipeWrapper {
	private final CrucibleRecipe recipe;

	public CrucibleRecipeWrapper(CrucibleRecipe recipe) {
		super(70, 15);
		this.recipe = recipe;
	}

	@Override
	public List getInputs() {
		return Collections.singletonList(recipe.catalyst);
	}

	@Override
	public boolean isResearched() {
		return checkResearch(recipe.research);
	}

	@Override
	public List<ItemStack> getOutputs() {
		return Collections.singletonList(recipe.getRecipeOutput());
	}

	@Override
	public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		drawAspects(recipe.aspects, recipeWidth, recipeHeight - 18, mouseX, mouseY);
	}

	public ItemStack getRecipeOutput() {
		return recipe.getRecipeOutput();
	}
}
