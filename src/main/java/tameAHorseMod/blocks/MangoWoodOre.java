package tameAHorseMod.blocks;


import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class MangoWoodOre extends BlockBase {

	public MangoWoodOre(String name, Material material) {
		super(name, material);
		setSoundType(SoundType.WOOD);
		setHardness(2.5f);
		setResistance(10.0f);
		setHarvestLevel("pickaxe",1);
	}
}
