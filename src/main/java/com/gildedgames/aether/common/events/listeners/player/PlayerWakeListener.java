package com.gildedgames.aether.common.events.listeners.player;

import com.gildedgames.aether.common.AetherCore;
import com.gildedgames.aether.common.init.DimensionsAether;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = AetherCore.MOD_ID)
public class PlayerWakeListener
{
	@SubscribeEvent
	public static void onPlayerSleepInBed(final PlayerWakeUpEvent event)
	{
		final World world = event.getEntityPlayer().world;

		if (!world.isRemote && world.provider.getDimensionType() == DimensionsAether.AETHER)
		{
			final MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();

			final WorldServer worldServer = server.getWorld(0);

			if (worldServer.playerEntities.size() > 0)
			{
				if (worldServer.areAllPlayersAsleep())
				{
					performTimeSet(event, world, worldServer);
				}
			}
			else
			{
				performTimeSet(event, world, worldServer);
			}
		}
	}

	public static void performTimeSet(PlayerWakeUpEvent event, World world, WorldServer worldServer)
	{
		if (world.getGameRules().getBoolean("doDaylightCycle") && event.getEntityPlayer().isPlayerFullyAsleep())
		{
			final long i = worldServer.getWorldInfo().getWorldTime() + 24000L;

			worldServer.getWorldInfo().setWorldTime(i - i % 24000L);
		}
	}


}
