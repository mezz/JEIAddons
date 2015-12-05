package mezz.jeiaddons.plugins.thaumcraft.arcane;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import net.minecraftforge.fluids.FluidStack;

import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.client.lib.UtilsFX;

public abstract class ArcaneRecipeWrapper<T extends IArcaneRecipe> implements ICraftingRecipeWrapper {
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
	public void drawInfo(@Nonnull Minecraft minecraft) {
		int count = 0;
		AspectList tags = recipe.getAspects();
		for (Aspect tag : tags.getAspectsSortedByAmount()) {
			UtilsFX.drawTag(14 + 18 * count + (5 - tags.size()) * 8, 60, tag, tags.getAmount(tag), 0, 0.0D, 771, 1.0F);
			count++;
		}
	}

	@Override
	public boolean usesOreDictionaryComparison() {
		return false;
	}
}
