package mezz.jeiaddons.plugins.thaumcraft;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import net.minecraftforge.fml.client.config.HoverChecker;

import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jeiaddons.JEIAddonsPlugin;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchHelper;
import thaumcraft.client.lib.UtilsFX;

public abstract class ThaumcraftRecipeWrapper extends BlankRecipeWrapper {
	private static final ResourceLocation icons = new ResourceLocation("jeiaddons", "textures/gui/icons.png");
	private static final List<String> notResearchedStrings = Collections.singletonList(StatCollector.translateToLocal("JEIAddons.thaumcraft.not.researched"));

	private final IDrawable notResearchedDrawable;
	private final HoverChecker notResearchedHover;
	private final int notResearchedX;
	private final int notResearchedY;

	protected ThaumcraftRecipeWrapper(int notResearchedX, int notResearchedY) {
		this.notResearchedDrawable = JEIAddonsPlugin.jeiHelpers.getGuiHelper().createDrawable(icons, 0, 0, 16, 16);
		this.notResearchedX = notResearchedX;
		this.notResearchedY = notResearchedY;
		this.notResearchedHover = new HoverChecker(notResearchedY, notResearchedY + 16, notResearchedX, notResearchedX + 16, 0);
	}

	@Override
	public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		if (!isResearched()) {
			notResearchedDrawable.draw(minecraft, notResearchedX, notResearchedY);
		}
	}

	@Nullable
	@Override
	public List<String> getTooltipStrings(int mouseX, int mouseY) {
		if (!isResearched() && notResearchedHover.checkHover(mouseX, mouseY)) {
			return notResearchedStrings;
		} else {
			return null;
		}
	}

	protected abstract boolean isResearched();

	protected void drawAspects(@Nullable AspectList aspectList, int recipeWidth, int y) {
		if (aspectList == null || aspectList.size() == 0) {
			return;
		}

		int aspectsWidth = 22 * aspectList.size();
		int aspectXStart = (recipeWidth - aspectsWidth) / 2;

		int count = 0;
		for (Aspect tag : aspectList.getAspectsSortedByAmount()) {
			UtilsFX.drawTag(aspectXStart + 22 * count, y, tag, aspectList.getAmount(tag), 0, 0.0D, 771, 1.0F);
			count++;
		}
	}

	protected boolean checkResearch(String... research) {
		if (research == null || research[0].length() <= 0) {
			return true;
		}
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		return player != null && ResearchHelper.isResearchComplete(player.getName(), research);
	}
}
