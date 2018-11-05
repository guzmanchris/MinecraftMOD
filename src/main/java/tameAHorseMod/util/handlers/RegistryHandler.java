package tameAHorseMod.util.handlers;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tameAHorseMod.init.ModItems;
import tameAHorseMod.util.HasModel;

@EventBusSubscriber
public class RegistryHandler {

@SubscribeEvent
public static void registerItem(RegistryEvent.Register<Item> event) {
	event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
}

@SubscribeEvent
public static void registerModel(ModelRegistryEvent event) {
	for(Item item : ModItems.ITEMS) {
		if(item instanceof HasModel) {
			((HasModel) item).registerModels();
		}
	}
}

}
