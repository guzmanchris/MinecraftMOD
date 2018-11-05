package tameAHorseMod.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import tameAHorseMod.Main;
import tameAHorseMod.init.ModItems;
import tameAHorseMod.util.HasModel;

public class ItemBase extends Item implements HasModel {

	public ItemBase(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.TOOLS);
		
		ModItems.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
		
	}

}
