package mezz.jeiaddons.plugins.thaumcraft.crucible;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.IResearchableRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.PluginThaumcraft;
import thaumcraft.api.crafting.CrucibleRecipe;

public class CrucibleRecipeWrapper extends BlankRecipeWrapper implements IResearchableRecipeWrapper {
	private final CrucibleRecipe recipe;

	public CrucibleRecipeWrapper(CrucibleRecipe recipe) {
		this.recipe = recipe;
	}

	@Override
	public List getInputs() {
		return Collections.singletonList(recipe.catalyst);
	}

	@Override
	public boolean isResearched() {
		return PluginThaumcraft.helper.isResearched(recipe.research);
	}

	@Override
	public List<ItemStack> getOutputs() {
		return Collections.singletonList(recipe.getRecipeOutput());
	}

	@Override
	public Object getRecipe() {
		return this;
	}

	@Override
	public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight) {
		PluginThaumcraft.helper.drawAspects(recipe.aspects, recipeWidth, recipeHeight - 18);
	}

	public ItemStack getRecipeOutput() {
		return recipe.getRecipeOutput();
	}
}
