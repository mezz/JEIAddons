package mezz.jeiaddons.plugins.thaumcraft.infusion;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeUids;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import javax.annotation.Nonnull;
import java.util.List;

public class InfusionRecipeCategory extends BlankRecipeCategory {
	private static final int recipeOutputIndex = 0;
	private static final int recipeInputIndex = 1;
	private static final int recipeComponentStart = 2;

	@Nonnull
	private final IDrawable background;
	@Nonnull
	private final IDrawable backgroundDesign;
	@Nonnull
	private final String localizedName;

	public InfusionRecipeCategory(IGuiHelper guiHelper) {
		ResourceLocation location = new ResourceLocation("minecraft:textures/gui/container/crafting_table.png");
		background = guiHelper.createDrawable(location, 85, 16, 60, 54, 16, 56, 90, 0);

		ResourceLocation backgroundDesignLocation = new ResourceLocation("thaumcraft", "textures/gui/gui_researchbook_overlay.png");
		backgroundDesign = guiHelper.createDrawable(backgroundDesignLocation, 200, 75, 50, 50);

		localizedName = StatCollector.translateToLocal("recipe.type.infusion");
	}

	@Nonnull
	@Override
	public String getUid() {
		return ThaumcraftRecipeUids.INFUSION;
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
	public void drawExtras(@Nonnull Minecraft minecraft) {
		GlStateManager.enableBlend();
		GlStateManager.pushMatrix();
		GlStateManager.translate(-11, -4, 0);
		GlStateManager.scale(2, 2, 2);
		backgroundDesign.draw(minecraft, 0, 0);
		GlStateManager.popMatrix();
		GlStateManager.disableBlend();
	}

	@Override
	public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		if (recipeWrapper instanceof InfusionRecipeWrapper) {
			InfusionRecipeWrapper recipe = (InfusionRecipeWrapper) recipeWrapper;
			List<ItemStack> output = recipe.getOutputs();
			List<ItemStack> input = recipe.getRecipeInput();
			List<List<ItemStack>> components = recipe.getComponents();

			guiItemStacks.init(recipeOutputIndex, false, 128, 34);
			guiItemStacks.init(recipeInputIndex, true, 36, 34);

			int componentCount = components.size();
			float pieSlice = 360 / componentCount;
			float currentRot = -90.0F;
			for (int componentIndex = 0; componentIndex < componentCount; componentIndex++) {
				int xPos = (int) (MathHelper.cos(currentRot / 180.0F * 3.141593F) * 40.0F) + 36;
				int yPos = (int) (MathHelper.sin(currentRot / 180.0F * 3.141593F) * 38.0F) + 34;
				currentRot += pieSlice;
				guiItemStacks.init(recipeComponentStart + componentIndex, true, xPos, yPos);
			}

			guiItemStacks.set(recipeOutputIndex, output);
			guiItemStacks.set(recipeInputIndex, input);
			for (int componentIndex = 0; componentIndex < componentCount; componentIndex++) {
				List<ItemStack> component = components.get(componentIndex);
				guiItemStacks.set(recipeComponentStart + componentIndex, component);
			}
		}
	}
}
