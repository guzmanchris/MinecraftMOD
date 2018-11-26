package tameAHorseMod.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import tameAHorseMod.tileEntities.PowerGeneratorTileEntity;

public class PowerGeneratorContainer extends Container {
	
	private PowerGeneratorTileEntity tileEntity = new PowerGeneratorTileEntity();
	private int power, burnTime, currentBurnTime;
	
	public PowerGeneratorContainer(InventoryPlayer player, PowerGeneratorTileEntity tileEntity) {
		this.tileEntity = tileEntity;
		IItemHandler handler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		
		this.addSlotToContainer(new SlotItemHandler(handler, 0, 77, 54));
		
		for(int y=0; y<3; y++) {
			for(int x=0; x<9; x++) {
				this.addSlotToContainer(new Slot(player, x+y*9+9, 8+x*18, 84+y*18));
			} 
		}
		
		for(int x=0; x<9; x++) {
			this.addSlotToContainer(new Slot(player, x, 8+x*18, 142));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.tileEntity.isUsableByPlayer(playerIn);
	}
	
	@Override
	public void updateProgressBar(int id, int data) {
		this.tileEntity.setField(id, data);
	}
	
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		
		for(int i=0;i<this.listeners.size();i++) {
			IContainerListener listener = (IContainerListener)this.listeners.get(i);
			if(this.power != this.tileEntity.getField(0)) listener.sendWindowProperty(this, 0, this.tileEntity.getField(0));
			if(this.burnTime != this.tileEntity.getField(1)) listener.sendWindowProperty(this, 1, this.tileEntity.getField(1));
			if(this.currentBurnTime != this.tileEntity.getField(2)) listener.sendWindowProperty(this, 2, this.tileEntity.getField(2));
		}
		
		this.power = this.tileEntity.getField(0);
		this.burnTime = this.tileEntity.getField(1);
		this.currentBurnTime = this.tileEntity.getField(2);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = (Slot)this.inventorySlots.get(index);
		
		if(slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			
			if(index>=0 && index<27) {
				if(!this.mergeItemStack(stack1, 27, 36, false)) return ItemStack.EMPTY;
			}
			else if(index>=27 && index<36) {
				if(!this.mergeItemStack(stack1, 0, 27, false)) return ItemStack.EMPTY;
			}
			else if(!this.mergeItemStack(stack1, 0, 36, false)) return ItemStack.EMPTY;
			
			if(stack1.isEmpty()) slot.putStack(ItemStack.EMPTY);
			else slot.onSlotChanged();
			
			if(stack1.getCount() == stack.getCount()) return ItemStack.EMPTY;
			slot.onTake(playerIn, stack1);
		}
		return stack;
	}

}
