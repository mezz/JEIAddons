package mezz.jeiaddons.plugins.thaumcraft.arcane;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

import net.minecraftforge.fluids.FluidStack;

import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.IResearchableRecipeWrapper;
import mezz.jeiaddons.utils.DummyInventoryCrafting;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.lib.crafting.ArcaneWandRecipe;

public class ArcaneWandRecipeWrapper implements IShapedCraftingRecipeWrapper, IResearchableRecipeWrapper {
	private static final ArcaneWandRecipe recipe = new ArcaneWandRecipe();
	private final List inputs;
	private final List<ItemStack> outputs;
	private final InventoryCrafting crafting;
	private final AspectList aspectList;

	public ArcaneWandRecipeWrapper(ItemStack wandRod, ItemStack wandCap) {
		this.inputs = Arrays.asList(
				null, null, wandCap,
				null, wandRod, null,
				wandCap, null, null
		);

		this.crafting = new DummyInventoryCrafting(3, 3);
		this.crafting.setInventorySlotContents(2, wandCap);
		this.crafting.setInventorySlotContents(6, wandCap);
		this.crafting.setInventorySlotContents(4, wandRod);

		this.aspectList = recipe.getAspects(this.crafting);

		ItemStack wandOutput = recipe.getCraftingResult(this.crafting);
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
	public void drawInfo(@Nonnull Minecraft minecraft) {
		int count = 0;
		for (Aspect tag : aspectList.getAspectsSortedByAmount()) {
			UtilsFX.drawTag(14 + 18 * count + (5 - aspectList.size()) * 8, 60, tag, aspectList.getAmount(tag), 0, 0.0D, 771, 1.0F);
			count++;
		}
	}

	@Override
	public boolean usesOreDictionaryComparison() {
		return false;
	}

	public boolean isResearched() {
		Minecraft minecraft = Minecraft.getMinecraft();
		return minecraft.thePlayer != null && recipe.matches(crafting, minecraft.theWorld, minecraft.thePlayer);
	}

	@Override
	public Object getRecipe() {
		return this;
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