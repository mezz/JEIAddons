package mezz.jeiaddons.plugins.thaumcraft.arcane;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import net.minecraftforge.fluids.FluidStack;

import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.IResearchableRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.PluginThaumcraft;
import thaumcraft.api.crafting.IArcaneRecipe;

public abstract class ArcaneRecipeWrapper<T extends IArcaneRecipe> implements ICraftingRecipeWrapper, IResearchableRecipeWrapper {
	protected final T recipe;

	public ArcaneRecipeWrapper(T recipe) {
		this.recipe = recipe;
	}

	@Override
	public List<ItemStack> getOutputs() {
		return Collections.singletonList(recipe.getRecipeOutput());
	}

	@Override
	public List<FluidStack> getFluidInputs() {
		return Collections.emptyList();
	}

	@Override
	public List<FluidStack> getFluidOutputs() {
		return Collections.emptyList();
	}

	@Override
	public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight) {
		PluginThaumcraft.helper.drawAspects(recipe.getAspects(), recipeWidth, recipeHeight - 18);
	}

	@Override
	public boolean isResearched() {
		return PluginThaumcraft.helper.isResearched(recipe.getResearch());
	}

	@Override
	public T getRecipe() {
		return recipe;
	}
}
