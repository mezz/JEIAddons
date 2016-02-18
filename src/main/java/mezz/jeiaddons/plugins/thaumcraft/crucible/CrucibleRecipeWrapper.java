package mezz.jeiaddons.plugins.thaumcraft.crucible;

import mezz.jei.api.IJeiHelpers;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import thaumcraft.api.crafting.CrucibleRecipe;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public class CrucibleRecipeWrapper extends ThaumcraftRecipeWrapper {
	private final CrucibleRecipe recipe;

	public CrucibleRecipeWrapper(@Nonnull IJeiHelpers jeiHelpers, @Nonnull CrucibleRecipe recipe) {
		super(jeiHelpers, 70, 15);
		this.recipe = recipe;
	}

	@Override
	@Nonnull
	public List getInputs() {
		return Collections.singletonList(recipe.catalyst);
	}

	@Override
	public boolean isResearched() {
		return checkResearch(recipe.research);
	}

	@Override
	@Nonnull
	public List<ItemStack> getOutputs() {
		return Collections.singletonList(recipe.getRecipeOutput());
	}

	@Override
	public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		super.drawInfo(minecraft, recipeWidth, recipeHeight, mouseX, mouseY);
		drawAspects(recipe.aspects, recipeWidth, recipeHeight - 18, mouseX, mouseY);
	}

	public ItemStack getRecipeOutput() {
		return recipe.getRecipeOutput();
	}
}
