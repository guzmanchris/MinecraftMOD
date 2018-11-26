package tameAHorseMod.items.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSpade;
import tameAHorseMod.Main;
import tameAHorseMod.init.ModItems;
import tameAHorseMod.util.HasModel;


public class ToolSpade extends ItemSpade implements HasModel{

	
	public ToolSpade(String name,ToolMaterial material) {
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
