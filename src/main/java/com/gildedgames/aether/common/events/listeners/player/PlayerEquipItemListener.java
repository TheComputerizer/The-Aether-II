package com.gildedgames.aether.common.events.listeners.player;

import com.gildedgames.aether.api.AetherAPI;
import com.gildedgames.aether.api.items.equipment.ItemEquipmentSlot;
import com.gildedgames.aether.api.items.properties.IItemProperties;
import com.gildedgames.aether.api.player.IPlayerAether;
import com.gildedgames.aether.api.player.inventory.IInventoryEquipment;
import com.gildedgames.aether.common.AetherCore;
import com.gildedgames.aether.common.capabilities.entity.player.PlayerAether;
import com.gildedgames.aether.common.capabilities.entity.player.modules.PlayerEquipmentModule;
import com.gildedgames.aether.common.items.armor.ItemAetherGloves;
import net.minecraft.client.Minecraft;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = AetherCore.MOD_ID)
public class PlayerEquipItemListener
{

	@SubscribeEvent
	public static void onPlayerRightClickItem(final PlayerInteractEvent.RightClickItem event)
	{
		final IPlayerAether aePlayer = PlayerAether.getPlayer(event.getEntityPlayer());

		if (event.getItemStack().isEmpty())
		{
			return;
		}

		final boolean result = PlayerEquipItemListener.tryEquipEquipment(aePlayer, event.getItemStack(), event.getHand());

		if (result)
		{
			// Unfortunately we have to play the equip animation manually...
			if (event.getEntityPlayer().world.isRemote)
			{
				Minecraft.getMinecraft().getItemRenderer().resetEquippedProgress(EnumHand.MAIN_HAND);
			}

			event.setCanceled(true);
		}
	}

	private static boolean tryEquipEquipment(final IPlayerAether player, final ItemStack stack, final EnumHand hand)
	{
		final IInventoryEquipment inventory = player.getModule(PlayerEquipmentModule.class).getInventory();

		final IItemProperties equipment = AetherAPI.content().items().getProperties(stack.getItem());

		if (equipment.getEquipmentSlot() != ItemEquipmentSlot.NONE)
		{
			final int slot = inventory.getNextEmptySlotForType(equipment.getEquipmentSlot());

			if (slot >= 0)
			{
				final ItemStack newStack = stack.copy();
				newStack.setCount(1);

				inventory.setInventorySlotContents(slot, newStack);

				if (stack.getItem() instanceof ItemAetherGloves)
				{
					final SoundEvent gloveEquipSound = ((ItemAetherGloves) stack.getItem()).getGloveSound();

					player.getEntity().world
							.playSound(player.getEntity(), player.getEntity().getPosition(), gloveEquipSound, SoundCategory.NEUTRAL,
									1.0f, 1.0f);
				}
				else
				{
					player.getEntity().world
							.playSound(player.getEntity(), player.getEntity().getPosition(), SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.NEUTRAL,
									1.0f, 1.0f);
				}

				if (!player.getEntity().capabilities.isCreativeMode)
				{
					// Technically, there should never be STACKABLE equipment, but in case there is, we need to handle it.
					stack.shrink(1);
				}

				player.getEntity().setHeldItem(hand, stack.getCount() <= 0 ? ItemStack.EMPTY : stack);

				return true;
			}
		}

		return false;
	}

}
