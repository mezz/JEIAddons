package mezz.jeiaddons.plugins.thaumcraft.arcane;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

import net.minecraftforge.fluids.FluidStack;

import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftCraftingRecipeWrapper;
import mezz.jeiaddons.utils.DummyInventoryCrafting;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.lib.crafting.ArcaneWandRecipe;

public class ArcaneWandRecipeWrapper extends ThaumcraftCraftingRecipeWrapper implements IShapedCraftingRecipeWrapper {
	private static final ArcaneWandRecipe recipe = new ArcaneWandRecipe();
	private final List inputs;
	private final List<ItemStack> outputs;
	private final InventoryCrafting crafting;
	private final AspectList aspectList;

	@Nullable
	public static ArcaneWandRecipeWrapper create(ItemStack wandRod, ItemStack wandCap) {
		InventoryCrafting crafting = new DummyInventoryCrafting(3, 3);
		crafting.setInventorySlotContents(2, wandCap);
		crafting.setInventorySlotContents(6, wandCap);
		crafting.setInventorySlotContents(4, wandRod);

		ItemStack wandOutput = recipe.getCraftingResult(crafting);
		if (wandOutput == null) {
			return null;
		}

		return new ArcaneWandRecipeWrapper(wandRod, wandCap, crafting, wandOutput);
	}

	private ArcaneWandRecipeWrapper(ItemStack wandRod, ItemStack wandCap, InventoryCrafting crafting, ItemStack wandOutput) {
		this.crafting = crafting;
		this.aspectList = recipe.getAspects(this.crafting);

		this.inputs = Arrays.asList(
				null, null, wandCap,
				null, wandRod, null,
				wandCap, null, null
		);
		this.outputs = Collections.singletonList(wandOutput);
	}

	@Override
	public List getInputs() {
		return inputs;
	}

	@Override
	public List<ItemStack> getOutputs() {
		return outputs;
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
		drawAspects(aspectList, recipeWidth, recipeHeight - 18);
	}

	@Override
	public boolean isResearched() {
		Minecraft minecraft = Minecraft.getMinecraft();
		return minecraft.thePlayer != null && recipe.matches(crafting, minecraft.theWorld, minecraft.thePlayer);
	}

	@Override
	public int getWidth() {
		return 3;
	}

	@Override
	public int getHeight() {
		return 3;
	}
}
