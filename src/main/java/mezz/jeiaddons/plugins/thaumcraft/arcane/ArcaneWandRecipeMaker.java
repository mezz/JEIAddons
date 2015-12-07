package mezz.jeiaddons.plugins.thaumcraft.arcane;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;

public class ArcaneWandRecipeMaker {
	@Nonnull
	public static List<Object> getRecipes() {
		List<Object> recipes = new ArrayList<>();

		WandCap ironCap = WandCap.caps.get("iron");
		WandRod woodRod = WandRod.getRod("wood");

		Collection<WandCap> wandCaps = WandCap.caps.values();
		Collection<WandRod> wandRods = WandRod.rods.values();
		for (WandCap wandCap : wandCaps) {
			for (WandRod wandRod : wandRods) {
				// skip iron capped wood wand, it has a regular crafting recipe already
				if (wandCap == ironCap && wandRod == woodRod) {
					continue;
				}
				ArcaneWandRecipeWrapper recipe = new ArcaneWandRecipeWrapper(wandRod.getItem(), wandCap.getItem());
				recipes.add(recipe);
			}
		}

		return recipes;
	}
}
