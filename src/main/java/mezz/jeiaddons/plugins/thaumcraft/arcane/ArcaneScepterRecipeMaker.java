package mezz.jeiaddons.plugins.thaumcraft.arcane;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;

public class ArcaneScepterRecipeMaker {
	@Nonnull
	public static List<Object> getRecipes() {
		List<Object> recipes = new ArrayList<>();

		Collection<WandCap> wandCaps = WandCap.caps.values();
		Collection<WandRod> wandRods = WandRod.rods.values();
		for (WandCap wandCap : wandCaps) {
			for (WandRod wandRod : wandRods) {

				ArcaneSceptreRecipeWrapper recipe = new ArcaneSceptreRecipeWrapper(wandRod.getItem(), wandCap.getItem());
				recipes.add(recipe);
			}
		}

		return recipes;
	}
}
