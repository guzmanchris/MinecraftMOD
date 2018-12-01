package tameAHorseMod.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tameAHorseMod.tileEntities.PipeTileEntity;

public class PipeBlock extends BlockBase implements ITileEntityProvider{

	public static final AxisAlignedBB PIPE_BLOCK_AABB = new AxisAlignedBB(0, 0.3128D, 0, 1, 0.625D, 1);
	
	public PipeBlock(String name, Material material) {
		super(name, material);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			PipeTileEntity pipe = (PipeTileEntity) worldIn.getTileEntity(pos);
			playerIn.sendMessage(new TextComponentString("Energy on pipe: "+Integer.toString(pipe.getPower())));
		}
		return true;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return PIPE_BLOCK_AABB;
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}
	
//	@Override
//	public TileEntity createTileEntity(World world, IBlockState state) {
//		return new PipeTileEntity();
//	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new PipeTileEntity();
	}
}
