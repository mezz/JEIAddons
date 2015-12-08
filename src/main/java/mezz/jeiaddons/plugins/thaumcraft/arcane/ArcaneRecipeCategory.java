package mezz.jeiaddons.plugins.thaumcraft.arcane;

import javax.annotation.Nonnull;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import mezz.jei.api.JEIManager;
import mezz.jei.api.gui.ICraftingGridHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeUids;
import mezz.jeiaddons.utils.Log;

public class ArcaneRecipeCategory implements IRecipeCategory {
	private static final int craftOutputSlot = 0;
	private static final int craftInputSlot1 = 2;

	@Nonnull
	private final IDrawable background;
	@Nonnull
	private final IDrawable wand;
	@Nonnull
	private final String localizedName;
	@Nonnull
	private final ICraftingGridHelper craftingGridHelper;

	public ArcaneRecipeCategory() {
		ResourceLocation backgroundLocation = new ResourceLocation("minecraft", "textures/gui/container/crafting_table.png");
		background = JEIManager.guiHelper.createDrawable(backgroundLocation, 29, 16, 116, 54, 0, 20, 0, 0);

		ResourceLocation wandLocation = new ResourceLocation("thaumcraft", "textures/gui/gui_researchbook_overlay.png");
		wand = JEIManager.guiHelper.createDrawable(wandLocation, 65, 75, 15, 15);

		localizedName = StatCollector.translateToLocal("recipe.type.arcane");
		craftingGridHelper = JEIManager.guiHelper.createCraftingGridHelper(craftInputSlot1, craftOutputSlot);
	}

	@Nonnull
	@Override
	public String getUid() {
		return ThaumcraftRecipeUids.ARCANE;
	}

	@Nonnull
	@Override
	public String getTitle() {
		return localizedName;
	}

	@Nonnull
	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public void drawExtras(Minecraft minecraft) {
		GlStateManager.enableBlend();
		wand.draw(minecraft, 58, 0);
		GlStateManager.disableBlend();
	}

	@Override
	public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		guiItemStacks.init(craftOutputSlot, false, 94, 18);

		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 3; ++x) {
				int index = craftInputSlot1 + x + (y * 3);
				guiItemStacks.init(index, true, x * 18, y * 18);
			}
		}

		if (recipeWrapper instanceof IShapedCraftingRecipeWrapper) {
			IShapedCraftingRecipeWrapper wrapper = (IShapedCraftingRecipeWrapper) recipeWrapper;
			craftingGridHelper.setInput(guiItemStacks, wrapper.getInputs(), wrapper.getWidth(), wrapper.getHeight());
			craftingGridHelper.setOutput(guiItemStacks, wrapper.getOutputs());
		} else if (recipeWrapper instanceof ICraftingRecipeWrapper) {
			ICraftingRecipeWrapper wrapper = (ICraftingRecipeWrapper) recipeWrapper;
			craftingGridHelper.setInput(guiItemStacks, wrapper.getInputs());
			craftingGridHelper.setOutput(guiItemStacks, wrapper.getOutputs());
		} else {
			Log.error("Can't handle unknown recipe wrapper: {}", recipeWrapper);
		}
	}
}
