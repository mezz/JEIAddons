package mezz.jeiaddons.plugins.thaumcraft;

import com.google.common.collect.ImmutableList;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IItemBlacklist;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.INbtIgnoreList;
import mezz.jei.api.IRecipeRegistry;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import mezz.jei.config.Config;
import mezz.jei.config.Constants;
import mezz.jeiaddons.JEIAddonsPlugin;
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
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.ResearchHelper;
import thaumcraft.api.wands.IWand;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.config.ConfigItems;

/**
 * When IRecipeHandler.isRecipeValid is called, Thaumcraft hasn't synced the research to client yet.
 * We collect the recipes with ThaumcraftHelper and add them to JEI when they have been researched.
 */
public class ThaumcraftHelper {
	private static final String configRequireResearchId = "requireThaumcraftResearch";
	private static final String researchSound = "thaumcraft:learn";
	private final boolean researchRequired = false;
	private final List<IResearchableRecipeWrapper> unresearchedRecipes = new ArrayList<>();

	/**
	 * There is no "researched" event, but there is a sound that is always played.
	 * Please forgive me for this horrible hack.
	 *
	 * This method checks the recipes to see if they were newly researched and adds them to JEI.
	 */
//	@SubscribeEvent
//	public void onSoundEvent(PlaySoundAtEntityEvent event) {
//		if (researchSound.equals(event.name)) {
//			addResearchedRecipes();
//		}
//	}

//	@SubscribeEvent
//	public void onConfigChange(@Nonnull ConfigChangedEvent.OnConfigChangedEvent event) {
//		if (Constants.MOD_ID.equals(event.modID)) {
//			researchRequired = Config.configFile.getBoolean(Config.CATEGORY_ADDONS, configRequireResearchId, researchRequired);
//
//			IItemBlacklist itemBlacklist = JEIAddonsPlugin.jeiHelpers.getItemBlacklist();
//			List<ItemStack> thaumcraftItems = JEIAddonsPlugin.itemRegistry.getItemListForModId(PluginThaumcraft.modId);
//			for (ItemStack itemStack : thaumcraftItems) {
//				itemBlacklist.removeItemFromBlacklist(itemStack);
//			}
//		}
//	}

	private boolean addResearchedRecipes() {
		boolean added = false;
		Iterator<IResearchableRecipeWrapper> iterator = unresearchedRecipes.iterator();
		IRecipeRegistry recipeRegistry = JEIAddonsPlugin.recipeRegistry;
		while (iterator.hasNext()) {
			IResearchableRecipeWrapper recipeWrapper = iterator.next();
			if (recipeWrapper.isResearched()) {
				recipeRegistry.addRecipe(recipeWrapper.getRecipe());
				removeRecipeOutputsFromBlacklist(recipeWrapper);
				iterator.remove();
				added = true;
			}
		}
		return added;
	}

	public boolean isResearchRequired() {
		return researchRequired;
	}

	public boolean isResearched(String... research) {
		return true;
//		if (!researchRequired || research == null || research[0].length() <= 0) {
//			return true;
//		}
//		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
//		return player != null && ResearchHelper.isResearchComplete(player.getName(), research);
	}

	public void addUnresearchedRecipe(IResearchableRecipeWrapper recipe) {
		unresearchedRecipes.add(recipe);
		addRecipeOutputsToBlacklist(recipe);
	}

	private void addRecipeOutputsToBlacklist(IResearchableRecipeWrapper recipe) {
		List<ItemStack> thaumcraftOutputs = ModUtil.getItemStacksFromMod(recipe.getOutputs(), PluginThaumcraft.modId);
		IItemBlacklist itemBlacklist = JEIAddonsPlugin.jeiHelpers.getItemBlacklist();
		for (ItemStack output : thaumcraftOutputs) {
			itemBlacklist.addItemToBlacklist(output);
		}
	}

	private void removeRecipeOutputsFromBlacklist(IResearchableRecipeWrapper recipe) {
		List<ItemStack> thaumcraftOutputs = ModUtil.getItemStacksFromMod(recipe.getOutputs(), PluginThaumcraft.modId);
		IItemBlacklist itemBlacklist = JEIAddonsPlugin.jeiHelpers.getItemBlacklist();
		for (ItemStack output : thaumcraftOutputs) {
			itemBlacklist.removeItemFromBlacklist(output);
		}
	}

	public void preInit() {
		Set<String> aspectTags = Aspect.aspects.keySet();
		String[] aspectTagsArray = aspectTags.toArray(new String[aspectTags.size()]);
		INbtIgnoreList nbtIgnoreList = JEIAddonsPlugin.jeiHelpers.getNbtIgnoreList();
		nbtIgnoreList.ignoreNbtTagNames(ItemsTC.wand, aspectTagsArray);
	}

//	public void loadConfig() {
//		researchRequired = Config.configFile.getBoolean(Config.CATEGORY_ADDONS, configRequireResearchId, researchRequired);
//
//		if (Config.configFile.hasChanged()) {
//			Config.configFile.save();
//		}
//	}

	public void register(IModRegistry registry) {
//		loadConfig();

		addMissingNbtToWands();

		IGuiHelper guiHelper = JEIAddonsPlugin.jeiHelpers.getGuiHelper();
		IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();

		registry.addRecipeCategories(
				new ArcaneRecipeCategory(guiHelper),
				new InfusionRecipeCategory(guiHelper),
				new CrucibleRecipeCategory(guiHelper),
				new InfernalSmeltingRecipeCategory(guiHelper)
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
			recipeTransferRegistry.addRecipeTransferHandler(arcaneWorkbenchClass, ThaumcraftRecipeUids.ARCANE, 2, 9, 11, 36);
			recipeTransferRegistry.addRecipeTransferHandler(arcaneWorkbenchClass, VanillaRecipeCategoryUid.CRAFTING, 2, 9, 11, 36);
		}

		if (ModUtil.classExists("thaumcraft.common.lib.crafting.ArcaneWandRecipe")) {
			registry.addRecipeHandlers(new ArcaneWandRecipeHandler());
			registry.addRecipes(ArcaneWandRecipeMaker.getRecipes());
		}

		if (ModUtil.classExists("thaumcraft.common.lib.crafting.ArcaneSceptreRecipe")) {
			registry.addRecipeHandlers(new ArcaneSceptreRecipeHandler());
			registry.addRecipes(ArcaneScepterRecipeMaker.getRecipes());
		}
	}

	/**
	 * Thaumcraft handles the case where the wand is missing nbt for cap or rod.
	 * JEI needs this NBT set explicitly to look things up, so it is done here.
	 */
	private static void addMissingNbtToWands() {
		ImmutableList<ItemStack> itemStacks = JEIAddonsPlugin.itemRegistry.getItemListForModId(PluginThaumcraft.modId);
		for (ItemStack itemStack : itemStacks) {
			Item item = itemStack.getItem();
			if (item instanceof IWand) {
				IWand wand = (IWand) item;
				if (wand.getCap(itemStack) == ConfigItems.WAND_CAP_IRON) {
					wand.setCap(itemStack, ConfigItems.WAND_CAP_IRON);
				}
				if (wand.getRod(itemStack) == ConfigItems.WAND_ROD_WOOD) {
					wand.setRod(itemStack, ConfigItems.WAND_ROD_WOOD);
				}
			}
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
