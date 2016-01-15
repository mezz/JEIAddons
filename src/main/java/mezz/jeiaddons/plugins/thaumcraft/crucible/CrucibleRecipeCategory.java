package mezz.jeiaddons.plugins.thaumcraft.crucible;

import javax.annotation.Nonnull;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jeiaddons.plugins.thaumcraft.ThaumcraftRecipeUids;

public class CrucibleRecipeCategory implements IRecipeCategory {
	private static final int catalystIndex = 0;
	private static final int outputIndex = 1;

	@Nonnull
	private final IDrawable background;
	@Nonnull
	private final IDrawable backgroundDesign;
	@Nonnull
	private final IDrawable arrow;
	@Nonnull
	private final String localizedName;

	public CrucibleRecipeCategory(IGuiHelper guiHelper) {
		ResourceLocation location = new ResourceLocation("minecraft:textures/gui/container/crafting_table.png");
		background = guiHelper.createDrawable(location, 85, 30, 60, 26, 10, 20, 60, 0);

		ResourceLocation backgroundDesignLocation = new ResourceLocation("thaumcraft", "textures/gui/gui_researchbook_overlay.png");
		backgroundDesign = guiHelper.createDrawable(backgroundDesignLocation, 0, 20, 60, 50);
		arrow = guiHelper.createDrawable(backgroundDesignLocation, 100, 82, 16, 16);

		localizedName = StatCollector.translateToLocal("recipe.type.crucible");
	}

	@Nonnull
	@Override
	public String getUid() {
		return ThaumcraftRecipeUids.CRUCIBLE;
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
		{
			GlStateManager.pushMatrix();
			{
				GlStateManager.scale(0.65, 0.65, 0.65);
				backgroundDesign.draw(minecraft, 36, 26);
			}
			GlStateManager.popMatrix();

			arrow.draw(minecraft, 34, 4);
		}
		GlStateManager.disableBlend();
	}

	@Override
	public void drawAnimations(Minecraft minecraft) {

	}

	@Override
	public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		if (recipeWrapper instanceof CrucibleRecipeWrapper) {
			CrucibleRecipeWrapper recipe = (CrucibleRecipeWrapper) recipeWrapper;
			ItemStack output = recipe.getRecipeOutput();

			guiItemStacks.init(catalystIndex, true, 16, 0);
			guiItemStacks.init(outputIndex, false, 98, 14);

			guiItemStacks.setFromRecipe(catalystIndex, recipe.getInputs());
			guiItemStacks.set(outputIndex, output);
		}
	}
}
