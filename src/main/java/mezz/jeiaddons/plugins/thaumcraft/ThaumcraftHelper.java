package mezz.jeiaddons.plugins.thaumcraft;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;

import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIManager;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.gui.RecipesGuiInitEvent;
import mezz.jeiaddons.config.Config;
import mezz.jeiaddons.plugins.thaumcraft.arcane.ArcaneRecipeCategory;
import mezz.jeiaddons.plugins.thaumcraft.arcane.ShapedArcaneRecipeHandler;
import mezz.jeiaddons.plugins.thaumcraft.arcane.ShapelessArcaneRecipeHandler;
import mezz.jeiaddons.utils.Log;
import mezz.jeiaddons.utils.ModUtil;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.research.ResearchHelper;

/**
 * When IRecipeHandler.isRecipeValid is called, Thaumcraft hasn't synced the research to client yet.
 * We collect the recipes with ThaumcraftHelper and add them to JEI when they have been researched.
 */
public class ThaumcraftHelper {
	private static final String researchSound = "thaumcraft:learn";
	private final List<IArcaneRecipe> unresearchedRecipes = new ArrayList<>();
	private boolean addedInitialRecipes = false;

	/**
	 * Thaumcraft research is synced on FML's PlayerEvent.PlayerLoggedInEvent.
	 * By the time the recipe gui is opened for the first time, the Thaumcraft research should by synced.
	 */
	@SubscribeEvent
	public void onRecipesGuiInit(@Nonnull RecipesGuiInitEvent event) {
		if (!addedInitialRecipes) {
			if (addResearchedRecipes()) {
				addedInitialRecipes = true;
			}
		}
	}

	/**
	 * There is no "researched" event, but there is a sound that is always played.
	 * Please forgive me for this horrible hack.
	 *
	 * This method checks the recipes to see if they were newly researched and adds them to JEI.
	 */
	@SubscribeEvent
	public void onSoundEvent(PlaySoundAtEntityEvent event) {
		if (researchSound.equals(event.name)) {
			addResearchedRecipes();
		}
	}

	private boolean addResearchedRecipes() {
		boolean added = false;
		Iterator<IArcaneRecipe> iterator = unresearchedRecipes.iterator();
		while (iterator.hasNext()) {
			IArcaneRecipe recipe = iterator.next();
			if (isResearched(recipe)) {
				JEIManager.recipeRegistry.addRecipe(recipe);
				JEIManager.itemBlacklist.removeItemFromBlacklist(recipe.getRecipeOutput());
				iterator.remove();
				added = true;
			}
		}
		return added;
	}

	public boolean isResearched(IArcaneRecipe recipe) {
		if (!Config.thaumcraftRequireResearch) {
			return true;
		}
		// Minecraft.thePlayer does not exist when JEI loads.
		String playerName = Minecraft.getMinecraft().getSession().getUsername();
		return ResearchHelper.isResearchComplete(playerName, recipe.getResearch());
	}

	public void addUnresearchedRecipe(IArcaneRecipe recipe) {
		unresearchedRecipes.add(recipe);
		JEIManager.itemBlacklist.addItemToBlacklist(recipe.getRecipeOutput());
	}

	public void register(IModRegistry registry) {
		registry.addRecipeCategories(
				new ArcaneRecipeCategory()
		);

		registry.addRecipeHandlers(
				new ShapedArcaneRecipeHandler(),
				new ShapelessArcaneRecipeHandler()
		);

		IGuiHelper guiHelper = JEIManager.guiHelper;

		Class<? extends Container> arcaneWorkbenchClass = ModUtil.getContainerClassForName("thaumcraft.common.container.ContainerArcaneWorkbench");
		if (arcaneWorkbenchClass != null) {
			registry.addRecipeTransferHelpers(
					guiHelper.createRecipeTransferHelper(arcaneWorkbenchClass, ThaumcraftRecipeUids.ARCANE, 2, 9, 11, 36),
					guiHelper.createRecipeTransferHelper(arcaneWorkbenchClass, VanillaRecipeCategoryUid.CRAFTING, 2, 9, 11, 36)
			);
		}
	}
}
