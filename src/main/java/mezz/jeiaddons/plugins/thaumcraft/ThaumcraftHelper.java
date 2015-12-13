package mezz.jeiaddons.plugins.thaumcraft;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIManager;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.config.Config;
import mezz.jei.config.Constants;
import mezz.jei.gui.RecipesGuiInitEvent;
import mezz.jeiaddons.plugins.thaumcraft.arcane.ArcaneRecipeCategory;
import mezz.jeiaddons.plugins.thaumcraft.arcane.ArcaneScepterRecipeMaker;
import mezz.jeiaddons.plugins.thaumcraft.arcane.ArcaneSceptreRecipeHandler;
import mezz.jeiaddons.plugins.thaumcraft.arcane.ArcaneWandRecipeHandler;
import mezz.jeiaddons.plugins.thaumcraft.arcane.ArcaneWandRecipeMaker;
import mezz.jeiaddons.plugins.thaumcraft.arcane.ShapedArcaneRecipeHandler;
import mezz.jeiaddons.plugins.thaumcraft.arcane.ShapelessArcaneRecipeHandler;
import mezz.jeiaddons.plugins.thaumcraft.crucible.CrucibleRecipeCategory;
import mezz.jeiaddons.plugins.thaumcraft.crucible.CrucibleRecipeHandler;
import mezz.jeiaddons.plugins.thaumcraft.infernal.InfernalSmeltingRecipeCategory;
import mezz.jeiaddons.plugins.thaumcraft.infernal.InfernalSmeltingRecipeHandler;
import mezz.jeiaddons.plugins.thaumcraft.infernal.InfernalSmeltingRecipeMaker;
import mezz.jeiaddons.plugins.thaumcraft.infusion.InfusionRecipeCategory;
import mezz.jeiaddons.plugins.thaumcraft.infusion.InfusionRecipeHandler;
import mezz.jeiaddons.utils.ModUtil;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchHelper;
import thaumcraft.client.lib.UtilsFX;

/**
 * When IRecipeHandler.isRecipeValid is called, Thaumcraft hasn't synced the research to client yet.
 * We collect the recipes with ThaumcraftHelper and add them to JEI when they have been researched.
 */
public class ThaumcraftHelper {
	private static final String configRequireResearchId = "requireThaumcraftResearch";
	private static final String researchSound = "thaumcraft:learn";
	private boolean requireResearch = true;
	private final List<IResearchableRecipeWrapper> unresearchedRecipes = new ArrayList<>();
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

	@SubscribeEvent
	public void onConfigChange(@Nonnull ConfigChangedEvent.OnConfigChangedEvent event) {
		if (Constants.MOD_ID.equals(event.modID)) {
			requireResearch = Config.configFile.getBoolean(Config.categoryAddons, configRequireResearchId, requireResearch);

			List<ItemStack> thaumcraftItems = JEIManager.itemRegistry.getItemListForModId(PluginThaumcraft.modId);
			for (ItemStack itemStack : thaumcraftItems) {
				JEIManager.itemBlacklist.removeItemFromBlacklist(itemStack);
			}
		}
	}

	private boolean addResearchedRecipes() {
		boolean added = false;
		Iterator<IResearchableRecipeWrapper> iterator = unresearchedRecipes.iterator();
		while (iterator.hasNext()) {
			IResearchableRecipeWrapper recipeWrapper = iterator.next();
			if (recipeWrapper.isResearched()) {
				JEIManager.recipeRegistry.addRecipe(recipeWrapper.getRecipe());
				removeRecipeOutputsFromBlacklist(recipeWrapper);
				iterator.remove();
				added = true;
			}
		}
		return added;
	}

	public boolean isResearched(String... research) {
		if (!requireResearch || research == null || research[0].length() <= 0) {
			return true;
		}
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		return player != null && ResearchHelper.isResearchComplete(player.getName(), research);
	}

	public void addUnresearchedRecipe(IResearchableRecipeWrapper recipe) {
		unresearchedRecipes.add(recipe);
		addRecipeOutputsToBlacklist(recipe);
	}

	private void addRecipeOutputsToBlacklist(IResearchableRecipeWrapper recipe) {
		List<ItemStack> thaumcraftOutputs = ModUtil.getItemStacksFromMod(recipe.getOutputs(), PluginThaumcraft.modId);
		for (ItemStack output : thaumcraftOutputs) {
			JEIManager.itemBlacklist.addItemToBlacklist(output);
		}
	}

	private void removeRecipeOutputsFromBlacklist(IResearchableRecipeWrapper recipe) {
		List<ItemStack> thaumcraftOutputs = ModUtil.getItemStacksFromMod(recipe.getOutputs(), PluginThaumcraft.modId);
		for (ItemStack output : thaumcraftOutputs) {
			JEIManager.itemBlacklist.removeItemFromBlacklist(output);
		}
	}

	public void preInit() {
		Set<String> aspectTags = Aspect.aspects.keySet();
		String[] aspectTagsArray = aspectTags.toArray(new String[aspectTags.size()]);
		JEIManager.nbtIgnoreList.ignoreNbtTagNames(aspectTagsArray);
	}

	public void loadConfig() {
		requireResearch = Config.configFile.getBoolean(Config.categoryAddons, configRequireResearchId, requireResearch);

		if (Config.configFile.hasChanged()) {
			Config.configFile.save();
		}
	}

	public void register(IModRegistry registry) {
		loadConfig();

		registry.addRecipeCategories(
				new ArcaneRecipeCategory(),
				new InfusionRecipeCategory(),
				new CrucibleRecipeCategory(),
				new InfernalSmeltingRecipeCategory()
		);

		registry.addRecipeHandlers(
				new ShapedArcaneRecipeHandler(),
				new ShapelessArcaneRecipeHandler(),
				new InfusionRecipeHandler(),
				new CrucibleRecipeHandler(),
				new InfernalSmeltingRecipeHandler()
		);

		registry.addRecipes(ThaumcraftRecipeMaker.getRecipes());
		registry.addRecipes(InfernalSmeltingRecipeMaker.getRecipes());

		Class<? extends Container> arcaneWorkbenchClass = ModUtil.getContainerClassForName("thaumcraft.common.container.ContainerArcaneWorkbench");
		if (arcaneWorkbenchClass != null) {
			registry.addBasicRecipeTransferHelper(arcaneWorkbenchClass, ThaumcraftRecipeUids.ARCANE, 2, 9, 11, 36);
			registry.addBasicRecipeTransferHelper(arcaneWorkbenchClass, VanillaRecipeCategoryUid.CRAFTING, 2, 9, 11, 36);
		}

		Class arcaneWandRecipeClass = ModUtil.getClassForName("thaumcraft.common.lib.crafting.ArcaneWandRecipe");
		if (arcaneWandRecipeClass != null) {
			registry.addIgnoredRecipeClasses(arcaneWandRecipeClass);
			registry.addRecipeHandlers(new ArcaneWandRecipeHandler());
			registry.addRecipes(ArcaneWandRecipeMaker.getRecipes());
		}

		Class arcaneScepterRecipeClass = ModUtil.getClassForName("thaumcraft.common.lib.crafting.ArcaneSceptreRecipe");
		if (arcaneScepterRecipeClass != null) {
			registry.addIgnoredRecipeClasses(arcaneScepterRecipeClass);
			registry.addRecipeHandlers(new ArcaneSceptreRecipeHandler());
			registry.addRecipes(ArcaneScepterRecipeMaker.getRecipes());
		}
	}

	public void drawAspects(@Nullable AspectList aspectList, int recipeWidth, int y) {
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
}
