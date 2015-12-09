package mezz.jeiaddons.plugins.thaumcraft.infernal;

import javax.annotation.Nonnull;
import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import mezz.jei.api.JEIManager;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeUids;
import mezz.jeiaddons.utils.Log;

public class InfernalSmeltingRecipeCategory implements IRecipeCategory {
	private static final int inputSlot = 0;
	private static final int outputSlotSmelt = 1;
	private static final int outputSlotBonus = 2;

	private static final int bonusSlotX = 86;
	private static final int bonusSlotY = 18;

	@Nonnull
	private final IDrawable smeltBackground;
	@Nonnull
	private final String localizedName;
	@Nonnull
	private final String bonusString;

	public InfernalSmeltingRecipeCategory() {
		ResourceLocation location = new ResourceLocation("minecraft", "textures/gui/container/furnace.png");
		smeltBackground = JEIManager.guiHelper.createDrawable(location, 55, 16, 82, 54, 0, 0, 0, 30);

		localizedName = StatCollector.translateToLocal("JEIAddons.category.thaumcraft.infernal.smelting");
		bonusString = StatCollector.translateToLocal("JEIAddons.thaumcraft.infernal.bonus");
	}

	@Override
	@Nonnull
	public IDrawable getBackground() {
		return smeltBackground;
	}

	@Override
	public void drawExtras(Minecraft minecraft) {
		JEIManager.guiHelper.getSlotDrawable().draw(minecraft, bonusSlotX, bonusSlotY);
		FontRenderer fontRenderer = minecraft.fontRendererObj;
		int textY = bonusSlotY + 20;
		fontRenderer.drawString(bonusString, bonusSlotX, textY, Color.gray.getRGB());
		textY += fontRenderer.FONT_HEIGHT;
		fontRenderer.drawString("(0 - 3)", bonusSlotX, textY, Color.gray.getRGB());
	}

	@Nonnull
	@Override
	public String getTitle() {
		return localizedName;
	}

	@Nonnull
	@Override
	public String getUid() {
		return ThaumcraftRecipeUids.INFERNAL;
	}

	@Override
	public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		guiItemStacks.init(inputSlot, true, 0, 0);
		guiItemStacks.init(outputSlotSmelt, false, 60, 18);
		guiItemStacks.init(outputSlotBonus, false, bonusSlotX, bonusSlotY);

		if (recipeWrapper instanceof InfernalSmeltingRecipeWrapper) {
			InfernalSmeltingRecipeWrapper recipe = (InfernalSmeltingRecipeWrapper) recipeWrapper;

			guiItemStacks.setFromRecipe(inputSlot, recipe.getInputs());
			guiItemStacks.set(outputSlotSmelt, recipe.getSmeltingOutput());
			guiItemStacks.set(outputSlotBonus, recipe.getBonusOutput());
		} else {
			Log.error("Unknown recipe wrapper type: {}", recipeWrapper);
		}
	}
}
