package tameAHorseMod.init;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import tameAHorseMod.blocks.BlockBase;
import tameAHorseMod.blocks.MangoWoodOre;
import tameAHorseMod.blocks.PipeBlock;
import tameAHorseMod.blocks.PowerGeneratorBlock;

public class ModBlocks {

	public static final ArrayList<Block> BLOCKS = new ArrayList<Block>();

	public static Block T_BLOCK = new BlockBase("t_block",Material.IRON);
	public static Block MANGO_WOOD_ORE = new MangoWoodOre("mango_wood_ore",Material.WOOD);
	public static Block POWER_GENERATOR = new PowerGeneratorBlock("power_generator");
	public static Block TWITTER_BLOCK = new BlockBase("twitter",Material.IRON);
	public static Block PIPE_BLOCK = new PipeBlock("pipe_block", Material.IRON);
	
}
