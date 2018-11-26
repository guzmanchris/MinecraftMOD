package tameAHorseMod.energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.energy.EnergyStorage;

public class ModEnergyStorage extends EnergyStorage{

	 public ModEnergyStorage(int capacity)
	    {
	        super(capacity, capacity, capacity, 0);
	    }

	    public ModEnergyStorage(int capacity, int maxTransfer)
	    {
	        super(capacity, maxTransfer, maxTransfer, 0);
	    }

	    public ModEnergyStorage(int capacity, int maxReceive, int maxExtract)
	    {
	        super(capacity, maxReceive, maxExtract, 0);
	    }

	    public ModEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy)
	    {
	    	super(capacity, maxReceive, maxExtract, energy);
	    }

	    public void readFromNBT(NBTTagCompound compound) {
	    	this.energy = compound.getInteger("Energy");
	    	this.capacity = compound.getInteger("Capacity");
	    	this.maxReceive = compound.getInteger("MaxReceive");
	    	this.maxExtract = compound.getInteger("MaxExtract");
	    }

	    public void writeToNBT(NBTTagCompound compound) {
	    	compound.setInteger("Energy", this.energy);
	    	compound.setInteger("Capacity", this.capacity);
	    	compound.setInteger("MaxReceive", this.maxReceive);
	    	compound.setInteger("MaxExtract", this.maxExtract);
	    }
}
