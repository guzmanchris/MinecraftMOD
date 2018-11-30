package tameAHorseMod.tileEntities;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import tameAHorseMod.energy.ModEnergyStorage;

public class PowerGeneratorTileEntity extends TileEntity implements ITickable{

	public ItemStackHandler handler = new ItemStackHandler(1);
	private ModEnergyStorage storage = new ModEnergyStorage(20000);
	public static final int MAX_POWER = 20000;
	
	public int power = storage.getEnergyStored();
	public int burnTime;
	public int currentBurnTime = 0;
	private String customName;
	public boolean isSending=true;
	
	@Override
	public void update() {
		if(!handler.getStackInSlot(0).isEmpty() && isItemFuel(handler.getStackInSlot(0))) {
			this.currentBurnTime++; this.power++;
			burnTime = getItemBurnTime(handler.getStackInSlot(0)) - this.currentBurnTime;
			if(burnTime <= 0) {
				handler.getStackInSlot(0).shrink(1);
				currentBurnTime=0;
			}
		}
	}
	
	private boolean isItemFuel(ItemStack fuel) {
		return getItemBurnTime(fuel) > 0;
	}

	public static int getItemBurnTime(ItemStack fuel) {
		if(fuel.isEmpty()) return 0;
		else {
			Item item = fuel.getItem();
			
			if(item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR) {
				Block block = Block.getBlockFromItem(item);
				
				if(block == Blocks.WOODEN_SLAB) return 150;
				if(block.getDefaultState().getMaterial() == Material.WOOD) return 300;
				if(block == Blocks.COAL_BLOCK) return 16000;
				
			}
			
			if(item instanceof ItemTool && "WOOD".equals(((ItemTool)item).getToolMaterialName())) return 200;
			if(item instanceof ItemSword && "WOOD".equals(((ItemSword)item).getToolMaterialName())) return 200;
			if(item instanceof ItemHoe && "WOOD".equals(((ItemHoe)item).getMaterialName())) return 200;
			if(item == Items.STICK) return 100;
			if(item == Items.COAL) return 1600;
			if(item == Items.LAVA_BUCKET) return 20000;
			if(item == Item.getItemFromBlock(Blocks.SAPLING)) return 100;
			if(item == Items.BLAZE_ROD) return 2400;
			
			return GameRegistry.getFuelValue(fuel);
		}
	}
	

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityEnergy.ENERGY) return (T) this.storage;
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.handler;
		return super.getCapability(capability, facing);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if(capability == CapabilityEnergy.ENERGY) return true;
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setTag("Inventory", this.handler.serializeNBT());
		compound.setInteger("Power", this.power);
		compound.setInteger("Burn Time", this.burnTime);
		compound.setInteger("Current Burn Time", this.currentBurnTime);
		compound.setInteger("Power", this.power);
		compound.setString("Name", getDisplayName().toString());
		compound.setBoolean("Sending", this.isSending);
		this.storage.writeToNBT(compound);
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.power = compound.getInteger("Power");
		this.burnTime = compound.getInteger("Burn Time");
		this.currentBurnTime = compound.getInteger("Current Burn Time");
		this.customName = compound.getString("Name");
		this.isSending = compound.getBoolean("Sending");
		this.storage.readFromNBT(compound);
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentTranslation("container.power_generator");
	}
	
	public int getEnergyStored() {
		return this.power;
	}
	
	public void setIsSending(boolean isSending) {
		this.isSending = isSending;
	}
	
	public boolean getIsSending() {
		return isSending;
	}
	
	public int getField(int id) {
		switch(id) {
		case 0: return this.power;
		case 1: return this.burnTime;
		case 2: return this.currentBurnTime;
		default: return 0;
		}
	}
	
	public void setField(int id, int value) {
		switch(id) {
		case 0: this.power = value;
		case 1: this.burnTime = value;
		case 2: this.currentBurnTime = value;
		}
	}
	
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.pos) != this ? false: player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
	}

	

}
