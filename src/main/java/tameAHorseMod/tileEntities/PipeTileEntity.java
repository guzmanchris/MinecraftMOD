package tameAHorseMod.tileEntities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import tameAHorseMod.energy.ModEnergyStorage;

public class PipeTileEntity extends TileEntity implements ITickable{
	 
	private ModEnergyStorage storage = new ModEnergyStorage(1);
	public static final int MAX_POWER = 1;
	public boolean isSending=true;
	public int power=storage.getEnergyStored();
	
	@Override
	public void update() {
		checkForPowerGenerators();
		checkForPipes();
		
	}
 
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("Power", this.power);
		compound.setBoolean("Sending", this.isSending);
		this.storage.writeToNBT(compound);
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.power = compound.getInteger("Power");
		this.isSending = compound.getBoolean("Sending");
		this.storage.readFromNBT(compound);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityEnergy.ENERGY) return (T) this.storage;
		return super.getCapability(capability, facing);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if(capability == CapabilityEnergy.ENERGY) return true;
		return super.hasCapability(capability, facing);
	}
	
	
	public void checkForPowerGenerators() {
		//If connected to a power generator access its tile entity to modify its values
		PowerGeneratorTileEntity powerGen=null;
		if(world.getTileEntity(pos.north()) instanceof PowerGeneratorTileEntity) {
			powerGen =  (PowerGeneratorTileEntity) world.getTileEntity(pos.north());
			transferPowerGenAndPipe(powerGen);
		}
		if(world.getTileEntity(pos.south()) instanceof PowerGeneratorTileEntity) {
			powerGen =  (PowerGeneratorTileEntity) world.getTileEntity(pos.south());
			transferPowerGenAndPipe(powerGen);
		}
		if(world.getTileEntity(pos.east()) instanceof PowerGeneratorTileEntity) {
			powerGen =  (PowerGeneratorTileEntity) world.getTileEntity(pos.east());
			transferPowerGenAndPipe(powerGen);
		}
		if(world.getTileEntity(pos.west()) instanceof PowerGeneratorTileEntity) {
			powerGen =  (PowerGeneratorTileEntity) world.getTileEntity(pos.west());
			transferPowerGenAndPipe(powerGen);
		}
		
	}
	
	public void checkForPipes() {
		//If connected to a pipe access its tile entity to modify its values
		PipeTileEntity pipe=null;
		if(world.getTileEntity(pos.north()) instanceof PipeTileEntity) {
			pipe =  (PipeTileEntity) world.getTileEntity(pos.north());
			transferPipeToPipe(pipe);
		}
		else if(world.getTileEntity(pos.south()) instanceof PipeTileEntity) {
			pipe =  (PipeTileEntity) world.getTileEntity(pos.south());
			transferPipeToPipe(pipe);
		}
		else if(world.getTileEntity(pos.east()) instanceof PipeTileEntity) {
			pipe =  (PipeTileEntity) world.getTileEntity(pos.east());
			transferPipeToPipe(pipe);
		}
		else if(world.getTileEntity(pos.west()) instanceof PipeTileEntity) {
			pipe =  (PipeTileEntity) world.getTileEntity(pos.west());
			transferPipeToPipe(pipe);
		}
	}
	
	public void transferPowerGenAndPipe(PowerGeneratorTileEntity powerGen) {
		if(!powerGen.getIsSending() && powerGen.getEnergyStored()<PowerGeneratorTileEntity.MAX_POWER && power>0) {
			powerGen.setField(0, powerGen.getEnergyStored()+1);
			power--;
			isSending=false;
		}
		if(powerGen.getIsSending() && powerGen.getEnergyStored()>0 && power<MAX_POWER) {
			powerGen.setField(0, powerGen.getEnergyStored()-1);
			power++;
			isSending=true;
		}
	}
	
	public void transferPipeToPipe(PipeTileEntity pipe) {
		if(isSending && power>0 && pipe.getPower()<MAX_POWER) {	
				pipe.setPower(pipe.getPower()+1);
				power--;
				if(!pipe.isSending()) pipe.setSending(true);
		}
		if(!isSending && power<MAX_POWER && pipe.getPower()>0){
			pipe.setPower(pipe.getPower()-1);
			power++;
			if(pipe.isSending()) pipe.setSending(false);
			
		}
	}

	public boolean isSending() {
		return isSending;
	}

	public int getPower() {
		return power;
	}

	public void setSending(boolean isSending) {
		this.isSending = isSending;
	}

	public void setPower(int power) {
		this.power = power;
	}
}
