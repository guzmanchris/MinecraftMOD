package tameAHorseMod.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import tameAHorseMod.containers.PowerGeneratorContainer;
import tameAHorseMod.tileEntities.PowerGeneratorTileEntity;
import tameAHorseMod.util.Reference;

public class PowerGeneratorGUI extends GuiContainer{

	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/power_generator.png");
	private InventoryPlayer player;
	private PowerGeneratorTileEntity tileEntity;
	
	public PowerGeneratorGUI(InventoryPlayer player, PowerGeneratorTileEntity tileEntity) {
		super(new PowerGeneratorContainer(player, tileEntity));
		this.player = player;
		this.tileEntity = tileEntity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRenderer.drawString(Integer.toString(this.tileEntity.getEnergyStored()), 137, 68, 2550000);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		int powerStored = this.getEnergyStorageScaled(115);
		this.drawTexturedModalRect(this.guiLeft+31, this.guiTop+32, 2, 168, powerStored, 11);
		
		int burntime = this.getBurnTimeScaled(13);
		this.drawTexturedModalRect(this.guiLeft+56, this.guiTop + 55+ 12-burntime, 176, 12-burntime, 13, burntime+1);
		
		int energyStored = this.getBurnTimeScaled(17);
		this.drawTexturedModalRect(this.guiLeft+103, this.guiTop+49, 176, 14, 15, 17-energyStored);
	}

	private int getEnergyStorageScaled(int pixels) {
		int power = this.tileEntity.getEnergyStored();
		int maxPower = this.tileEntity.getMaxEnergyStored();
		return power !=0 && maxPower != 0 ? power*pixels/maxPower : 0;
	}
	
	private int getBurnTimeScaled(int pixels) {
		int currentBurnTime = this.tileEntity.getField(2);
		int totalBurnTime = this.tileEntity.getField(1) + currentBurnTime;
		return currentBurnTime!=0 && totalBurnTime!=0? currentBurnTime*pixels/totalBurnTime : 0;
	}
	
}
