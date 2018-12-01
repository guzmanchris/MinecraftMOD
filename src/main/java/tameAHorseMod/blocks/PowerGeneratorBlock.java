package tameAHorseMod.blocks;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import tameAHorseMod.Main;
import tameAHorseMod.tileEntities.PowerGeneratorTileEntity;
import tameAHorseMod.util.Reference;

public class PowerGeneratorBlock extends BlockBase {
	
	public PowerGeneratorBlock(String name) {
		super(name, Material.IRON);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
				PowerGeneratorTileEntity powerGen = (PowerGeneratorTileEntity) worldIn.getTileEntity(pos);;
				powerGen.setIsSending(!powerGen.getIsSending());
				if(powerGen.getIsSending()) {
					playerIn.sendMessage(new TextComponentString("Sending Mode Activated"));
				}
				else {
					playerIn.sendMessage(new TextComponentString("Receiving Mode Activated"));
				}
					}
			else  {
					playerIn.openGui(Main.instance, Reference.GUI_POWER_GENERATOR, worldIn, pos.getX(), pos.getY(), pos.getZ());
			}
		}
		return true;
	}
	
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		PowerGeneratorTileEntity powerGen = (PowerGeneratorTileEntity) worldIn.getTileEntity(pos);;
		powerGen.setField(0, 10000);
	}
	
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new PowerGeneratorTileEntity();
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		PowerGeneratorTileEntity tileEntity = (PowerGeneratorTileEntity) worldIn.getTileEntity(pos);
		worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileEntity.handler.getStackInSlot(0)));
		super.breakBlock(worldIn, pos, state);
	}
}
