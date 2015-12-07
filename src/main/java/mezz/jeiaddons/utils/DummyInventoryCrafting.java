package mezz.jeiaddons.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;

public class DummyInventoryCrafting extends InventoryCrafting {
	public DummyInventoryCrafting(int width, int height) {
		super(new DummyContainer(), width, height);
	}

	public static class DummyContainer extends Container {
		@Override
		public boolean canInteractWith(EntityPlayer playerIn) {
			return true;
		}
	}
}
