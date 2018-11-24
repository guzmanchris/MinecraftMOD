package tameAHorseMod.world;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import tameAHorseMod.init.ModBlocks;

public class ModWorldGenerator implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		if(world.provider.getDimension() == 0) {
			generateOverWorld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
		}
		
	}

	private void generateOverWorld(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		generateOre(ModBlocks.MANGO_WOOD_ORE.getDefaultState(), world, random, chunkX*16, chunkZ*16, 1, 64, random.nextInt(10) + 3, 20);
	}
	
	private void generateOre(IBlockState ore, World world, Random rand, int x, int z, int minY, int maxY, int size, int chances) {
		int deltaY = maxY - minY;
		for(int i=0; i<chances; i++) {
			BlockPos orePos = new BlockPos(x + rand.nextInt(16), deltaY + rand.nextInt(16), z + rand.nextInt(16));
			WorldGenMinable generator = new WorldGenMinable(ore, size);
			generator.generate(world, rand, orePos);
		}
	}
}
