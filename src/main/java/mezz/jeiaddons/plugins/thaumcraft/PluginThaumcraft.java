package mezz.jeiaddons.plugins.thaumcraft;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIManager;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jeiaddons.ModUtil;
import mezz.jeiaddons.plugins.thaumcraft.arcane.ArcaneRecipeCategory;
import mezz.jeiaddons.plugins.thaumcraft.arcane.ShapedArcaneRecipeHandler;
import mezz.jeiaddons.plugins.thaumcraft.arcane.ShapelessArcaneRecipeHandler;
import thaumcraft.common.container.ContainerArcaneWorkbench;

@JEIPlugin
public class PluginThaumcraft implements IModPlugin {
	@Override
	public boolean isModLoaded() {
		return ModUtil.isModLoaded("Thaumcraft");
	}

	@Override
	public void register(IModRegistry registry) {
		registry.addRecipeCategories(
				new ArcaneRecipeCategory()
		);

		registry.addRecipeHandlers(
				new ShapedArcaneRecipeHandler(),
				new ShapelessArcaneRecipeHandler()
		);

		IGuiHelper guiHelper = JEIManager.guiHelper;
		registry.addRecipeTransferHelpers(
				guiHelper.createRecipeTransferHelper(ContainerArcaneWorkbench.class, ThaumcraftRecipeUids.ARCANE, 2, 9, 11, 36),
				guiHelper.createRecipeTransferHelper(ContainerArcaneWorkbench.class, VanillaRecipeCategoryUid.CRAFTING, 2, 9, 11, 36)
		);
	}
}
