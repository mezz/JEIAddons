package mezz.jeiaddons.plugins.thaumcraft.arcane;

import mezz.jei.api.IJeiHelpers;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftCraftingRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import thaumcraft.api.crafting.IArcaneRecipe;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public abstract class ArcaneRecipeWrapper<T extends IArcaneRecipe> extends ThaumcraftCraftingRecipeWrapper {
	protected final T recipe;

	public ArcaneRecipeWrapper(@Nonnull IJeiHelpers jeiHelpers, @Nonnull T recipe) {
		super(jeiHelpers);
		this.recipe = recipe;
	}

	@Override
	@Nonnull
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
