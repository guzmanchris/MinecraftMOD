package tameAHorseMod.util.handlers;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tameAHorseMod.tileEntities.PowerGeneratorTileEntity;
import tameAHorseMod.util.Reference;

public class TileEntityHandler {

	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(PowerGeneratorTileEntity.class, new ResourceLocation(Reference.MOD_ID + ":power_generator"));
	}
}
