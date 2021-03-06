package mezz.jeiaddons.plugins.thaumcraft;

import com.google.common.collect.ImmutableList;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IItemRegistry;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.INbtIgnoreList;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
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
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.wands.IWand;
import thaumcraft.common.config.ConfigItems;

import javax.annotation.Nonnull;
import java.util.Set;

public class ThaumcraftHelper {
	private ThaumcraftHelper() {

	}

	public static void register(@Nonnull IModRegistry registry) {
		IJeiHelpers jeiHelpers = registry.getJeiHelpers();

		Set<String> aspectTags = Aspect.aspects.keySet();
		String[] aspectTagsArray = aspectTags.toArray(new String[aspectTags.size()]);
		INbtIgnoreList nbtIgnoreList = jeiHelpers.getNbtIgnoreList();
		nbtIgnoreList.ignoreNbtTagNames(ItemsTC.wand, aspectTagsArray);

		addMissingNbtToWands(registry.getItemRegistry());

		IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
		IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();

		registry.addRecipeCategories(
				new ArcaneRecipeCategory(guiHelper),
				new InfusionRecipeCategory(guiHelper),
				new CrucibleRecipeCategory(guiHelper),
				new InfernalSmeltingRecipeCategory(guiHelper)
		);

		registry.addRecipeHandlers(
				new ShapedArcaneRecipeHandler(jeiHelpers),
				new ShapelessArcaneRecipeHandler(jeiHelpers),
				new InfusionRecipeHandler(),
				new CrucibleRecipeHandler(),
				new InfernalSmeltingRecipeHandler()
		);

		registry.addRecipes(ThaumcraftRecipeMaker.getRecipes(jeiHelpers));
		registry.addRecipes(InfernalSmeltingRecipeMaker.getRecipes(jeiHelpers));

		Class<? extends Container> arcaneWorkbenchClass = ModUtil.getContainerClassForName("thaumcraft.common.container.ContainerArcaneWorkbench");
		if (arcaneWorkbenchClass != null) {
			recipeTransferRegistry.addRecipeTransferHandler(arcaneWorkbenchClass, ThaumcraftRecipeUids.ARCANE, 2, 9, 11, 36);
			recipeTransferRegistry.addRecipeTransferHandler(arcaneWorkbenchClass, VanillaRecipeCategoryUid.CRAFTING, 2, 9, 11, 36);
		}

		if (ModUtil.classExists("thaumcraft.common.lib.crafting.ArcaneWandRecipe")) {
			registry.addRecipeHandlers(new ArcaneWandRecipeHandler());
			registry.addRecipes(ArcaneWandRecipeMaker.getRecipes(jeiHelpers));
		}

		if (ModUtil.classExists("thaumcraft.common.lib.crafting.ArcaneSceptreRecipe")) {
			registry.addRecipeHandlers(new ArcaneSceptreRecipeHandler());
			registry.addRecipes(ArcaneScepterRecipeMaker.getRecipes(jeiHelpers));
		}
	}

	/**
	 * Thaumcraft handles the case where the wand is missing nbt for cap or rod.
	 * JEI needs this NBT set explicitly to look things up, so it is done here.
	 */
	private static void addMissingNbtToWands(@Nonnull IItemRegistry itemRegistry) {
		ImmutableList<ItemStack> itemStacks = itemRegistry.getItemListForModId(PluginThaumcraft.modId);
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

}
