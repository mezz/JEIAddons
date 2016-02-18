package mezz.jeiaddons.plugins.thaumcraft.infernal;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.ITooltipCallback;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeUids;
import mezz.jeiaddons.utils.Log;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import javax.annotation.Nonnull;
import java.util.List;

public class InfernalSmeltingRecipeCategory extends BlankRecipeCategory {
	private static final int inputSlot = 0;
	private static final int outputSlotSmelt = 1;
	private static final int outputSlotBonus = 2;

	private static final int bonusSlotX = 86;
	private static final int bonusSlotY = 18;

	@Nonnull
	private final IDrawable smeltBackground;
	@Nonnull
	private final IDrawable slotDrawable;
	@Nonnull
	private final String localizedName;
	@Nonnull
	private final String bonusString;

	public InfernalSmeltingRecipeCategory(IGuiHelper guiHelper) {
		ResourceLocation location = new ResourceLocation("minecraft", "textures/gui/container/furnace.png");
		smeltBackground = guiHelper.createDrawable(location, 55, 16, 82, 54, 0, 0, 0, 30);
		slotDrawable = guiHelper.getSlotDrawable();

		localizedName = StatCollector.translateToLocal("JEIAddons.category.thaumcraft.infernal.smelting");
		bonusString = StatCollector.translateToLocal("JEIAddons.thaumcraft.infernal.bonus") + " (0 - 3)";
	}

	@Override
	@Nonnull
	public IDrawable getBackground() {
		return smeltBackground;
	}

	@Override
	public void drawExtras(@Nonnull Minecraft minecraft) {
		slotDrawable.draw(minecraft, bonusSlotX, bonusSlotY);
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

			guiItemStacks.addTooltipCallback(new ITooltipCallback<ItemStack>() {
				@Override
				public void onTooltip(int slotIndex, boolean input, ItemStack ingredient, List<String> tooltip) {
					if (slotIndex == outputSlotBonus) {
						tooltip.add(bonusString);
					}
				}
			});
		} else {
			Log.error("Unknown recipe wrapper type: {}", recipeWrapper);
		}
	}
}
