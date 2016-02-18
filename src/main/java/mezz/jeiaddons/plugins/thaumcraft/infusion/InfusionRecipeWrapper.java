package mezz.jeiaddons.plugins.thaumcraft.infusion;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeWrapper;
import mezz.jeiaddons.utils.Log;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.StatCollector;
import thaumcraft.api.crafting.InfusionRecipe;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InfusionRecipeWrapper extends ThaumcraftRecipeWrapper {
	private final InfusionRecipe recipe;
	@Nonnull
	private final List<Object> inputs;
	@Nonnull
	private final List<ItemStack> outputs;
	private final List<ItemStack> recipeInput;
	private final List<List<ItemStack>> components;
	private final String instabilityString;

	public InfusionRecipeWrapper(@Nonnull IJeiHelpers jeiHelpers, @Nonnull InfusionRecipe recipe) {
		super(jeiHelpers, 100, 34);
		this.recipe = recipe;
		IStackHelper stackHelper = jeiHelpers.getStackHelper();

		this.recipeInput = stackHelper.toItemStackList(recipe.getRecipeInput());
		this.inputs = new ArrayList<>();
		this.inputs.add(this.recipeInput);

		this.components = new ArrayList<>();
		Object[] recipeComponents = recipe.getComponents();
		if (recipeComponents != null) {
			Collections.addAll(this.inputs, recipeComponents);

			for (Object component : recipeComponents) {
				List<ItemStack> componentList = stackHelper.toItemStackList(component);
				this.components.add(componentList);
			}
		}

		Object recipeOutput = recipe.getRecipeOutput();
		if (recipeOutput instanceof ItemStack) {
			this.outputs = stackHelper.toItemStackList(recipeOutput);
		} else {
			this.outputs = new ArrayList<>();
			List<ItemStack> outputs = stackHelper.toItemStackList(recipe.getRecipeInput());
			Object[] obj = (Object[]) recipeOutput;
			NBTBase tag = (NBTBase) obj[1];
			for (ItemStack outputStack : outputs) {
				ItemStack outputCopy = outputStack.copy();
				outputCopy.setTagInfo((String) obj[0], tag);
				this.outputs.add(outputCopy);
			}
		}

		ArrayList<ItemStack> recipeComponentFirstStacks = new ArrayList<>();
		for (List<ItemStack> componentList : components) {
			recipeComponentFirstStacks.add(componentList.get(0));
		}

		Integer instabilityValue = null;
		try {
			instabilityValue = recipe.getInstability(null, recipeInput.get(0), recipeComponentFirstStacks);
		} catch (Throwable e) {
			Log.error("Failed to get instability for infusion recipe for {}", recipe.getRecipeOutput());
		}

		if (instabilityValue != null) {
			int instabilityKey = Math.min(5, instabilityValue / 2);
			this.instabilityString = StatCollector.translateToLocal("tc.inst") + " " + StatCollector.translateToLocal("tc.inst." + instabilityKey);
		} else {
			this.instabilityString = "";
		}
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
		int textHeight = minecraft.fontRendererObj.FONT_HEIGHT;
		drawAspects(recipe.getAspects(), recipeWidth, recipeHeight - textHeight - 18 - 4, mouseX, mouseY);

		int xPos = (recipeWidth - minecraft.fontRendererObj.getStringWidth(instabilityString)) / 2;
		minecraft.fontRendererObj.drawString(instabilityString, xPos, recipeHeight - textHeight, Color.gray.getRGB());
	}

	@Override
	public boolean isResearched() {
		return checkResearch(recipe.getResearch());
	}

	public List<ItemStack> getRecipeInput() {
		return recipeInput;
	}

	public List<List<ItemStack>> getComponents() {
		return components;
	}
}
