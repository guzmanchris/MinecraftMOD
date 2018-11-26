package tameAHorseMod.items.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemHoe;
import tameAHorseMod.Main;
import tameAHorseMod.init.ModItems;
import tameAHorseMod.util.HasModel;

public class ToolHoe extends ItemHoe implements HasModel{

	
	public ToolHoe(String name,ToolMaterial material) {
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
