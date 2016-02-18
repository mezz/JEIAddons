package mezz.jeiaddons.plugins.thaumcraft.arcane;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftCraftingRecipeWrapper;
import mezz.jeiaddons.utils.DummyInventoryCrafting;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.lib.crafting.ArcaneSceptreRecipe;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArcaneSceptreRecipeWrapper extends ThaumcraftCraftingRecipeWrapper implements IShapedCraftingRecipeWrapper {
	private static final ArcaneSceptreRecipe recipe = new ArcaneSceptreRecipe();
	private static final ItemStack primalCharm = new ItemStack(ItemsTC.primalCharm);
	@Nonnull
	private final List inputs;
	@Nonnull
	private final List<ItemStack> outputs;
	private final InventoryCrafting crafting;
	private final AspectList aspectList;

	@Nullable
	public static ArcaneSceptreRecipeWrapper create(@Nonnull IJeiHelpers jeiHelpers, @Nonnull ItemStack wandRod, @Nonnull ItemStack wandCap) {
		InventoryCrafting crafting = new DummyInventoryCrafting(3, 3);
		crafting.setInventorySlotContents(1, wandCap);
		crafting.setInventorySlotContents(2, primalCharm);
		crafting.setInventorySlotContents(4, wandRod);
		crafting.setInventorySlotContents(5, wandCap);
		crafting.setInventorySlotContents(6, wandCap);

		ItemStack wandOutput = recipe.getCraftingResult(crafting);
		if (wandOutput == null) {
			return null;
		}

		return new ArcaneSceptreRecipeWrapper(jeiHelpers, wandRod, wandCap, crafting, wandOutput);
	}

	private ArcaneSceptreRecipeWrapper(@Nonnull IJeiHelpers jeiHelpers, @Nonnull ItemStack wandRod, @Nonnull ItemStack wandCap, @Nonnull InventoryCrafting crafting, @Nonnull ItemStack wandOutput) {
		super(jeiHelpers);
		this.crafting = crafting;
		this.aspectList = recipe.getAspects(crafting);

		this.inputs = Arrays.asList(
				null, wandCap, primalCharm,
				null, wandRod, wandCap,
				wandCap, null, null
		);
		this.outputs = Collections.singletonList(wandOutput);
	}

	@Override
	@Nonnull
	public List getInputs() {
		return inputs;
	}

	@Override
	@Nonnull
	public List<ItemStack> getOutputs() {
		return outputs;
	}

	@Override
	public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		super.drawInfo(minecraft, recipeWidth, recipeHeight, mouseX, mouseY);
		drawAspects(aspectList, recipeWidth, recipeHeight - 18, mouseX, mouseY);
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
