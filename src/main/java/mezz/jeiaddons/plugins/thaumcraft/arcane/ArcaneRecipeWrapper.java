package mezz.jeiaddons.plugins.thaumcraft.arcane;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftCraftingRecipeWrapper;
import thaumcraft.api.crafting.IArcaneRecipe;

public abstract class ArcaneRecipeWrapper<T extends IArcaneRecipe> extends ThaumcraftCraftingRecipeWrapper {
	protected final T recipe;

	public ArcaneRecipeWrapper(T recipe) {
		this.recipe = recipe;
	}

	@Override
	public List<ItemStack> getOutputs() {
		return Collections.singletonList(recipe.getRecipeOutput());
	}

	@Override
	public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		super.drawInfo(minecraft, recipeWidth, recipeHeight, mouseX, mouseY);
		drawAspects(recipe.getAspects(), recipeWidth, recipeHeight - 18, mouseX, mouseY);
	}

	@Override
	public boolean isResearched() {
		return checkResearch(recipe.getResearch());
	}
}
