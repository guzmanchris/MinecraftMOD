package tameAHorseMod.util.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import tameAHorseMod.containers.PowerGeneratorContainer;
import tameAHorseMod.gui.PowerGeneratorGUI;
import tameAHorseMod.tileEntities.PowerGeneratorTileEntity;
import tameAHorseMod.util.Reference;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == Reference.GUI_POWER_GENERATOR) return new PowerGeneratorContainer(player.inventory, (PowerGeneratorTileEntity)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == Reference.GUI_POWER_GENERATOR) return new PowerGeneratorGUI(player.inventory, (PowerGeneratorTileEntity)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}

}
