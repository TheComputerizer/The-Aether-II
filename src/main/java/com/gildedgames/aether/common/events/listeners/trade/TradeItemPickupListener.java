package com.gildedgames.aether.common.events.listeners.trade;

import com.gildedgames.aether.common.AetherCore;
import com.gildedgames.aether.common.containers.ContainerTrade;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod.EventBusSubscriber(modid = AetherCore.MOD_ID)
public class TradeItemPickupListener
{

	@SubscribeEvent
	public static void onPlayerPickUpItem(final PlayerEvent.ItemPickupEvent event)
	{
		if (event.player.openContainer instanceof ContainerTrade)
		{
			((ContainerTrade) event.player.openContainer).addItemToQueue(event.getStack());
		}
	}

}
