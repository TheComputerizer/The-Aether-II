package com.gildedgames.aether.common.events.listeners.items;

import com.gildedgames.aether.api.registrar.ItemsAether;
import com.gildedgames.aether.common.AetherCore;
import com.gildedgames.aether.common.init.MaterialsAether;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = AetherCore.MOD_ID)
public class ItemSkyrootSwordListener
{
	@SubscribeEvent
	public static void dropLoot(final LivingDropsEvent event)
	{
		if (event.getSource().getTrueSource() instanceof EntityPlayer)
		{
			final EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();

			final ItemStack held = player.getHeldItem(EnumHand.MAIN_HAND);

			boolean providesDrops = false;

			if (held.getItem() == ItemsAether.skyroot_sword)
			{
				providesDrops = true;
			}
			else if (held.getItem() instanceof ItemTool)
			{
				final String material = ((ItemTool) held.getItem()).getToolMaterialName();

				providesDrops = Objects.equals(material, MaterialsAether.SKYROOT_TOOL.name());
			}

			if (providesDrops)
			{
				Entity origin = event.getEntity();

				if (origin.isNonBoss())
				{
					final List<ItemStack> stacks = new ArrayList<>();

					for (final EntityItem item : event.getDrops())
					{
						stacks.add(item.getItem());
					}

					for (final ItemStack stack : stacks)
					{
						if (stack.getItem() != Items.SKULL && stack.getMetadata() != 1)
						{
							final EntityItem item = new EntityItem(player.getEntityWorld(), origin.posX, origin.posY, origin.posZ, stack);

							player.getEntityWorld().spawnEntity(item);
						}
					}
				}
			}
		}
	}

}
