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
import mezz.jeiaddons.plugins.thaumcraft.PluginThaumcraft;
import mezz.jeiaddons.utils.DummyInventoryCrafting;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.lib.crafting.ArcaneSceptreRecipe;

public class ArcaneSceptreRecipeWrapper implements IShapedCraftingRecipeWrapper, IResearchableRecipeWrapper {
	private static final ArcaneSceptreRecipe recipe = new ArcaneSceptreRecipe();
	private static final ItemStack primalCharm = new ItemStack(ItemsTC.primalCharm);
	private final List inputs;
	private final List<ItemStack> outputs;
	private final InventoryCrafting crafting;
	private final AspectList aspectList;

	public ArcaneSceptreRecipeWrapper(ItemStack wandRod, ItemStack wandCap) {
		this.inputs = Arrays.asList(
				null, wandCap, primalCharm,
				null, wandRod, wandCap,
				wandCap, null, null
		);

		this.crafting = new DummyInventoryCrafting(3, 3);
		this.crafting.setInventorySlotContents(1, wandCap);
		this.crafting.setInventorySlotContents(2, primalCharm);
		this.crafting.setInventorySlotContents(4, wandRod);
		this.crafting.setInventorySlotContents(5, wandCap);
		this.crafting.setInventorySlotContents(6, wandCap);

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
	public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight) {
		PluginThaumcraft.helper.drawAspects(aspectList, recipeWidth, recipeHeight - 18);
	}

	@Override
	public void drawAnimations(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight) {

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
