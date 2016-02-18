package mezz.jeiaddons.plugins.thaumcraft.arcane;

import mezz.jei.api.IJeiHelpers;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ArcaneScepterRecipeMaker {
	@Nonnull
	public static List<Object> getRecipes(@Nonnull IJeiHelpers jeiHelpers) {
		List<Object> recipes = new ArrayList<>();

		Collection<WandCap> wandCaps = WandCap.caps.values();
		Collection<WandRod> wandRods = WandRod.rods.values();
		for (WandCap wandCap : wandCaps) {
			for (WandRod wandRod : wandRods) {
				ArcaneSceptreRecipeWrapper recipe = ArcaneSceptreRecipeWrapper.create(jeiHelpers, wandRod.getItem(), wandCap.getItem());
				if (recipe != null) {
					recipes.add(recipe);
				}
			}
		}

		return recipes;
	}
}
