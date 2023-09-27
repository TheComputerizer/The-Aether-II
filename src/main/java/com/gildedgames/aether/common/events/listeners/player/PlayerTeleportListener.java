package com.gildedgames.aether.common.events.listeners.player;

import com.gildedgames.aether.common.AetherCore;
import com.gildedgames.aether.common.capabilities.entity.player.PlayerAether;
import com.gildedgames.aether.common.capabilities.entity.player.modules.PlayerTeleportingModule;
import com.gildedgames.aether.common.containers.ContainerLoadingScreen;
import com.gildedgames.aether.common.events.PostAetherTravelEvent;
import com.gildedgames.aether.common.init.DimensionsAether;
import com.gildedgames.aether.common.network.AetherGuiHandler;
import com.gildedgames.aether.common.network.NetworkingAether;
import com.gildedgames.aether.common.network.packets.PacketCloseLoadingScreen;
import com.gildedgames.aether.common.network.packets.PacketLoadingScreenPercent;
import com.gildedgames.aether.common.util.helpers.AetherHelper;
import com.gildedgames.aether.common.util.helpers.MathUtil;
import com.gildedgames.aether.common.world.preparation.PrepHelper;
import com.gildedgames.orbis.lib.util.mc.BlockPosDimension;
import com.google.common.collect.Lists;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.List;

@Mod.EventBusSubscriber(modid = AetherCore.MOD_ID)
public class PlayerTeleportListener
{

	@SubscribeEvent
	public static void onEvent(EntityTravelToDimensionEvent event)
	{
		if (AetherHelper.isEnabled(event.getDimension()) && AetherHelper.isEnabled(event.getDimension()) && event.getEntity() instanceof EntityPlayer)
		{
			PlayerAether playerAether = PlayerAether.getPlayer((EntityPlayer) event.getEntity());

			if (!AetherHelper.isNecromancerTower(event.getEntity().dimension))
			{
				playerAether.getModule(PlayerTeleportingModule.class)
						.setNonAetherPos(new BlockPosDimension(event.getEntity().getPosition(), event.getEntity().dimension));
			}
		}
	}

	@SubscribeEvent
	public static void onEvent(PostAetherTravelEvent event)
	{
		if (!event.getEntity().getEntityWorld().isRemote)
		{
			if (event.getEntity() instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) event.getEntity();

				player.openGui(AetherCore.INSTANCE, AetherGuiHandler.AETHER_LOADING_ID, player.getEntityWorld(), player.getPosition().getX(),
						player.getPosition().getY(), player.getPosition().getZ());
			}
		}
	}

	@SubscribeEvent
	public static void onEvent(LivingEvent.LivingUpdateEvent event)
	{
		if (!event.getEntity().getEntityWorld().isRemote && event.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getEntity();
			PlayerAether playerAether = PlayerAether.getPlayer(player);

			if (player.getEntityWorld().provider.getDimensionType() != DimensionsAether.AETHER)
			{
				return;
			}

			PlayerTeleportingModule teleportingModule = playerAether.getModule(PlayerTeleportingModule.class);

			if (player.openContainer instanceof ContainerLoadingScreen)
			{
				if (PrepHelper.isSectorLoaded(player.getEntityWorld(), player.chunkCoordX, player.chunkCoordZ))
				{
					boolean isLoaded = true;

					int radius = Math.min(player.getServer().getPlayerList().getViewDistance(), 10);

					int count = 0;

					for (int x = player.chunkCoordX - radius; x < player.chunkCoordX + radius; x++)
					{
						for (int z = player.chunkCoordZ - radius; z < player.chunkCoordZ + radius; z++)
						{
							Chunk chunk = player.world.getChunkProvider().getLoadedChunk(x, z);

							if (chunk == null)
							{
								isLoaded = false;
							}
							else
							{
								count++;
							}
						}
					}

					if (isLoaded)
					{
						player.closeScreen();

						NetworkingAether.sendPacketToPlayer(new PacketCloseLoadingScreen(), (EntityPlayerMP) player);

						NetworkingAether.sendPacketToPlayer(new PacketLoadingScreenPercent(0.0F), (EntityPlayerMP) player);

						List<IRecipe> toUnlock = Lists.newArrayList();

						for (IRecipe r : ForgeRegistries.RECIPES)
						{
							ResourceLocation loc = Item.REGISTRY.getNameForObject(r.getRecipeOutput().getItem());

							if (loc != null && loc.getNamespace().equals(AetherCore.MOD_ID))
							{
								toUnlock.add(r);
							}
						}

						player.unlockRecipes(toUnlock);
					}
					else
					{
						float diam = radius + radius;

						float percent = ((float) count / (diam * diam)) * 100.0F;

						if (!MathUtil.epsilonEquals(teleportingModule.getLastPercent(), percent))
						{
							teleportingModule.setLastPercent(percent);

							NetworkingAether.sendPacketToPlayer(new PacketLoadingScreenPercent(percent), (EntityPlayerMP) player);
						}
					}
				}
			}
		}
	}
}
