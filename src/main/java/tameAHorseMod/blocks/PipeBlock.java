package tameAHorseMod.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class PipeBlock extends BlockBase {

	public static final AxisAlignedBB PIPE_BLOCK_AABB = new AxisAlignedBB(0, 0.3128D, 0, 1, 0.625D, 1);
	
	public PipeBlock(String name, Material material) {
		super(name, material);
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

}
