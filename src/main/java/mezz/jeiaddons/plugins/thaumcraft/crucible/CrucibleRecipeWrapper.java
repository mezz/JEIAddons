package mezz.jeiaddons.plugins.thaumcraft.crucible;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import net.minecraftforge.fluids.FluidStack;

import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.StackUtil;
import mezz.jeiaddons.plugins.thaumcraft.IResearchableRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.PluginThaumcraft;
import thaumcraft.api.crafting.CrucibleRecipe;

public class CrucibleRecipeWrapper implements IRecipeWrapper, IResearchableRecipeWrapper {
	private final CrucibleRecipe recipe;
	private final List<ItemStack> catalyst;

	public CrucibleRecipeWrapper(CrucibleRecipe recipe) {
		this.recipe = recipe;
		this.catalyst = StackUtil.toItemStackList(recipe.catalyst);
	}

	@Override
	public List<List<ItemStack>> getInputs() {
		return Collections.singletonList(catalyst);
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
	public List<FluidStack> getFluidInputs() {
		return Collections.emptyList();
	}

	@Override
	public List<FluidStack> getFluidOutputs() {
		return Collections.emptyList();
	}

	@Override
	public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight) {
		PluginThaumcraft.helper.drawAspects(recipe.aspects, recipeWidth, recipeHeight - 18);
	}

	public List<ItemStack> getCatalyst() {
		return catalyst;
	}

	public ItemStack getRecipeOutput() {
		return recipe.getRecipeOutput();
	}
}
