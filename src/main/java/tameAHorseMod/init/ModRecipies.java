package tameAHorseMod.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipies {

	public static void init() {
		GameRegistry.addSmelting(ModBlocks.MANGO_WOOD_ORE, new ItemStack(ModItems.WHISTLE,1),  0.5f);
	}
	
}
