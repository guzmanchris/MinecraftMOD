package tameAHorseMod.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;
import tameAHorseMod.items.ItemBase;
import tameAHorseMod.items.armor.ArmorBase;
import tameAHorseMod.items.tools.ToolAxe;
import tameAHorseMod.items.tools.ToolHoe;
import tameAHorseMod.items.tools.ToolPickaxe;
import tameAHorseMod.items.tools.ToolSpade;
import tameAHorseMod.items.tools.ToolSword;
import tameAHorseMod.util.Reference;

public class ModItems {

	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	
	//Material 
	public static final ToolMaterial MATERIAL_MANGO=EnumHelper.addToolMaterial("material_mango", 3, 60, 8.0F, 3.0F,20);
	public static final ArmorMaterial ARMOR_MATERIAL_MANGO = EnumHelper.addArmorMaterial("armor_material_mango", Reference.MOD_ID+ ":mango", 16, new int[] {2,5,7,3}, 10,
			SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0F);
			
			//Items
	public static final Item WHISTLE = new ItemBase("whistle");
	public static final Item OCARINA = new ItemBase("ocarina");
	
	
	//Tools
	public static final ItemSword MANGO_SWORD = new ToolSword("mango_sword", MATERIAL_MANGO);
	public static final ItemSpade MANGO_SHOVEL = new ToolSpade("mango_shovel", MATERIAL_MANGO);
	public static final ItemPickaxe MANGO_PICKAXE = new ToolPickaxe("mango_pickaxe", MATERIAL_MANGO);
	public static final ItemAxe MANGO_AXE = new ToolAxe("mango_axe", MATERIAL_MANGO);
	public static final ItemHoe MANGO_HOE = new ToolHoe("mango_hoe", MATERIAL_MANGO);
	
	//Armor 
	public static final Item MANGO_HELMET= new ArmorBase("mango_helmet",ARMOR_MATERIAL_MANGO, 1,EntityEquipmentSlot.HEAD);
	public static final Item MANGO_CHESPLATE= new ArmorBase("mango_chestplate",ARMOR_MATERIAL_MANGO,1,EntityEquipmentSlot.CHEST);
	public static final Item MANGO_LEGGINS= new ArmorBase("mango_leggins",ARMOR_MATERIAL_MANGO,2,EntityEquipmentSlot.LEGS);
	public static final Item MANGO_BOOTS= new ArmorBase("mango_boots",ARMOR_MATERIAL_MANGO,1,EntityEquipmentSlot.FEET);
	
	
}

