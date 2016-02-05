package mezz.jeiaddons.plugins.thaumcraft.infernal;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeWrapper;

public class InfernalSmeltingRecipeWrapper extends ThaumcraftRecipeWrapper {
	@Nonnull
	private final List<ItemStack> inputs;
	@Nonnull
	private final ItemStack smeltingOutput;
	@Nonnull
	private final ItemStack bonusOutput;
	@Nullable
	private final String experienceString;

	public InfernalSmeltingRecipeWrapper(@Nonnull List<ItemStack> inputs, @Nonnull ItemStack smeltingOutput, @Nonnull ItemStack bonusOutput, float experience) {
		super(30, 18);
		this.inputs = inputs;
		this.smeltingOutput = smeltingOutput;

		this.bonusOutput = bonusOutput;

		if (experience > 0.0) {
			experienceString = StatCollector.translateToLocalFormatted("gui.jei.category.smelting.experience", experience);
		} else {
			experienceString = null;
		}
	}

	@Nonnull
	@Override
	public List<ItemStack> getInputs() {
		return inputs;
	}

	@Override
	public List<ItemStack> getOutputs() {
		return Arrays.asList(smeltingOutput, bonusOutput);
	}

	@Nonnull
	public ItemStack getSmeltingOutput() {
		return smeltingOutput;
	}

	@Nonnull
	public ItemStack getBonusOutput() {
		return bonusOutput;
	}

	@Override
	public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight) {
		if (experienceString != null) {
			FontRenderer fontRendererObj = minecraft.fontRendererObj;
			fontRendererObj.drawString(experienceString, 69 - fontRendererObj.getStringWidth(experienceString) / 2, 0, Color.gray.getRGB());
		}
	}

	@Override
	public boolean isResearched() {
		return checkResearch("INFERNALFURNACE");
	}
}
