package mezz.jeiaddons.plugins.thaumcraft;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.client.config.HoverChecker;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchHelper;
import thaumcraft.client.lib.UtilsFX;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class ThaumcraftRecipeWrapper extends BlankRecipeWrapper {
	private static final ResourceLocation icons = new ResourceLocation("jeiaddons", "textures/gui/icons.png");
	private static final List<String> notResearchedStrings = Collections.singletonList(StatCollector.translateToLocal("JEIAddons.thaumcraft.not.researched"));

	private final IDrawable notResearchedDrawable;
	private final HoverChecker notResearchedHover;
	private final int notResearchedX;
	private final int notResearchedY;

	@Nullable
	private Aspect hoveredAspect;

	protected ThaumcraftRecipeWrapper(@Nonnull IJeiHelpers jeiHelpers, int notResearchedX, int notResearchedY) {
		this.notResearchedDrawable = jeiHelpers.getGuiHelper().createDrawable(icons, 0, 0, 16, 16);
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
		} else if (hoveredAspect != null) {
			return Arrays.asList(
					hoveredAspect.getName(),
					EnumChatFormatting.GRAY + hoveredAspect.getLocalizedDescription()
			);
		} else {
			return null;
		}
	}

	protected abstract boolean isResearched();

	protected void drawAspects(@Nullable AspectList aspectList, final int recipeWidth, final int y, final int mouseX, final int mouseY) {
		if (aspectList == null || aspectList.size() == 0) {
			return;
		}

		final int aspectsWidth = 22 * aspectList.size();
		final int aspectXStart = (recipeWidth - aspectsWidth) / 2;

		hoveredAspect = null;

		int count = 0;
		for (Aspect tag : aspectList.getAspectsSortedByAmount()) {
			final int aspectX = aspectXStart + 22 * count;
			UtilsFX.drawTag(aspectX, y, tag, aspectList.getAmount(tag), 0, 0.0D, 771, 1.0F);
			if (mouseX >= aspectX && mouseX < (aspectX + 16) && mouseY >= y && mouseY < (y + 16)) {
				hoveredAspect = tag;
			}
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
