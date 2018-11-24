package tameAHorseMod.blocks;


import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class MangoWoodOre extends BlockBase {

	public MangoWoodOre(String name, Material material) {
		super(name, material);
		setSoundType(SoundType.WOOD);
		setHardness(1.5f);
		setResistance(15.0f);
		setHarvestLevel("axe",1);
	}
}
