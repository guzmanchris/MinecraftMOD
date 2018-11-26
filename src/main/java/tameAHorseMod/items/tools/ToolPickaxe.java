package tameAHorseMod.items.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;
import tameAHorseMod.Main;
import tameAHorseMod.init.ModItems;
import tameAHorseMod.util.HasModel;

public class ToolPickaxe extends ItemPickaxe implements HasModel{ 

	
	public ToolPickaxe(String name,ToolMaterial material) {
		super(material);
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
