package mezz.jeiaddons.plugins.thaumcraft.infernal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.oredict.OreDictionary;

import mezz.jei.api.recipe.IStackHelper;
import mezz.jeiaddons.JEIAddonsPlugin;
import mezz.jeiaddons.utils.Log;
import thaumcraft.api.ThaumcraftApi;

public class InfernalSmeltingRecipeMaker {
	public static List<Object> getRecipes() {
		IStackHelper stackHelper = JEIAddonsPlugin.jeiHelpers.getStackHelper();
		List<Object> recipes = new ArrayList<>();
		Map<Object, ItemStack> smeltingBonus = Collections.emptyMap();

		try {
			smeltingBonus = ReflectionHelper.getPrivateValue(ThaumcraftApi.class, null, "smeltingBonus");
		} catch (ReflectionHelper.UnableToFindFieldException e) {
			Log.error("Unable to find smeltingBonus in ThaumcraftApi");
		} catch (ReflectionHelper.UnableToAccessFieldException e) {
			Log.error("Unable to access smeltingBonus in ThaumcraftApi");
		}

		for (Map.Entry<Object, ItemStack> smeltingEntry : smeltingBonus.entrySet()) {
			Object input = smeltingEntry.getKey();
			ItemStack bonusOutput = smeltingEntry.getValue().copy();
			bonusOutput.stackSize = 1;

			List<ItemStack> inputs = null;
			if (input instanceof String) {
				inputs = OreDictionary.getOres((String) input);
				if (inputs.size() == 0) {
					continue;
				}
			} else if (input instanceof List) {
				try {
					List inputList = (List) input;
					if (inputList.size() == 2) {
						Item item = (Item) inputList.get(0);
						int meta = (Integer) inputList.get(1);
						ItemStack itemStack = new ItemStack(item, 1, meta);
						inputs = stackHelper.getSubtypes(itemStack);
					}
				} catch (Throwable ignored) {
				}
			}

			if (inputs == null || inputs.size() == 0) {
				Log.error("Unknown input format for Infernal Recipe: {}", smeltingEntry);
				continue;
			}

			ItemStack smeltOutput = FurnaceRecipes.instance().getSmeltingResult(inputs.get(0));
			if (smeltOutput == null) {
				continue;
			}

			float experience = FurnaceRecipes.instance().getSmeltingExperience(smeltOutput);

			InfernalSmeltingRecipeWrapper recipe = new InfernalSmeltingRecipeWrapper(inputs, smeltOutput, bonusOutput, experience);
			recipes.add(recipe);
		}

		return recipes;
	}
}
