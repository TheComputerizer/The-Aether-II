package com.gildedgames.aether.client.events.listeners.tooltip;

import com.gildedgames.aether.api.AetherAPI;
import com.gildedgames.aether.api.entity.damage.IDamageLevelsHolder;
import com.gildedgames.aether.api.items.equipment.ItemEquipmentSlot;
import com.gildedgames.aether.api.items.equipment.effects.EffectInstance;
import com.gildedgames.aether.api.items.equipment.effects.IEffectFactory;
import com.gildedgames.aether.api.items.equipment.effects.IEffectPool;
import com.gildedgames.aether.api.items.equipment.effects.IEffectProvider;
import com.gildedgames.aether.api.items.properties.IItemProperties;
import com.gildedgames.aether.api.items.properties.ItemRarity;
import com.gildedgames.aether.client.gui.util.ToolTipCurrencyHelper;
import com.gildedgames.aether.common.AetherCore;
import com.gildedgames.aether.common.entities.effects.IEffectDamageHolder;
import com.gildedgames.aether.common.entities.effects.IEffectResistanceHolder;
import com.gildedgames.aether.common.entities.effects.StatusEffect;
import com.gildedgames.aether.common.items.armor.ItemAetherArmor;
import com.gildedgames.aether.common.items.weapons.swords.ItemAetherSword;
import com.gildedgames.aether.common.shop.ShopCurrencyGilt;
import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.*;

@Mod.EventBusSubscriber(modid = AetherCore.MOD_ID, value = Side.CLIENT)
public class TooltipItemPropertiesListener
{
	private static final ToolTipCurrencyHelper toolTipHelper = new ToolTipCurrencyHelper();

	@SubscribeEvent
	@SuppressWarnings("unchecked")
	public static void onTooltipConstruction(final ItemTooltipEvent event)
	{
		final IItemProperties properties = AetherAPI.content().items().getProperties(event.getItemStack().getItem());

		final double value = AetherAPI.content().currency().getValue(event.getItemStack(), ShopCurrencyGilt.class);

		String durability = null;
		String modID = null;
		String NBT = null;

		for (String tooltip : event.getToolTip())
		{
			{
				if (tooltip.contains("Durability:"))
				{
					durability = tooltip;
				}

				if (tooltip.contains(event.getItemStack().getItem().getCreatorModId(event.getItemStack()) + ":"))
				{
					modID = tooltip;
				}

				if (tooltip.contains("NBT:"))
				{
					NBT = tooltip;
				}
			}
		}

		event.getToolTip().removeIf(tooltip -> tooltip.contains(event.getItemStack().getItem().getCreatorModId(event.getItemStack()) + ":") || tooltip.contains("NBT:")
				|| tooltip.contains("Durability:"));

		if (event.getItemStack().getItem() instanceof IDamageLevelsHolder)
		{
			IDamageLevelsHolder item = (IDamageLevelsHolder) event.getItemStack().getItem();

			event.getToolTip().removeIf(tooltip -> tooltip.contains("Attack Damage"));

			if (item.getSlashDamageLevel() > 0)
			{
				String slashValue;

				if (item.getSlashDamageLevel() % 1 == 0)
				{
					int n = Math.round(item.getSlashDamageLevel());
					slashValue = String.valueOf(n);
				}
				else
				{
					float n = item.getSlashDamageLevel();
					slashValue = String.valueOf(n);
				}

				event.getToolTip().add(String.format(" %s %s",
						slashValue,
						String.format("%s %s",
								TextFormatting.BLUE + I18n.format("attribute.name.aether.slash"),
								TextFormatting.GRAY + I18n.format("attribute.name.aether.damageLevel"))));
			}

			if (item.getPierceDamageLevel() > 0)
			{
				String pierceValue;

				if (item.getPierceDamageLevel() % 1 == 0)
				{
					int n = Math.round(item.getPierceDamageLevel());
					pierceValue = String.valueOf(n);
				}
				else
				{
					float n = item.getPierceDamageLevel();
					pierceValue = String.valueOf(n);
				}

				event.getToolTip().add(String.format(" %s %s",
						pierceValue,
						String.format("%s %s",
								TextFormatting.RED + I18n.format("attribute.name.aether.pierce"),
								TextFormatting.GRAY + I18n.format("attribute.name.aether.damageLevel"))));
			}

			if (item.getImpactDamageLevel() > 0)
			{
				String impactValue;

				if (item.getImpactDamageLevel() % 1 == 0)
				{
					int n = Math.round(item.getImpactDamageLevel());
					impactValue = String.valueOf(n);
				}
				else
				{
					float n = item.getImpactDamageLevel();
					impactValue = String.valueOf(n);
				}

				event.getToolTip().add(String.format(" %s %s",
						impactValue,
						String.format("%s %s",
								TextFormatting.YELLOW + I18n.format("attribute.name.aether.impact"),
								TextFormatting.GRAY + I18n.format("attribute.name.aether.damageLevel"))));
			}
		}

		if (event.getItemStack().getItem() instanceof IEffectDamageHolder)
		{
			IEffectDamageHolder item = (IEffectDamageHolder) event.getItemStack().getItem();

			if (!item.getStatusEffects().isEmpty())
			{
				for (Map.Entry<StatusEffect, Integer> effect : item.getStatusEffects().entrySet())
				{
					Collection<String> stringCollection = Lists.newArrayList();
					effect.getKey().addInformation(stringCollection);

					event.getToolTip().add(String.format(" %s %s",
							effect.getValue(),
							String.format("%s %s",
									stringCollection.toArray()[0],
									TextFormatting.GRAY + I18n.format("attribute.name.aether.damageLevel"))));
				}
			}
		}

		if (event.getItemStack().getItem() instanceof IEffectResistanceHolder)
		{
			IEffectResistanceHolder item = (IEffectResistanceHolder) event.getItemStack().getItem();

			if (!item.getStatusEffects().isEmpty())
			{
				for (Map.Entry<StatusEffect, Double> effect : item.getStatusEffects().entrySet())
				{
					Collection<String> stringCollection = Lists.newArrayList();
					effect.getKey().addInformation(stringCollection);

					String trueValue;

					if (effect.getValue() >= -1.0D && effect.getValue() < 1.0D && effect.getValue() != 0.0D)
					{
						if (effect.getValue() > 0.0)
						{
							trueValue = ((int) (effect.getValue() * 100)) + "%";

							event.getToolTip().add(String.format(" +%s %s",
									trueValue,
									String.format("%s %s",
											stringCollection.toArray()[0],
											TextFormatting.GRAY + I18n.format("attribute.name.aether.resistance"))));
						}
						else if (effect.getValue() < 0.0)
						{
							trueValue = ((int) (effect.getValue() * -100)) + "%";

							event.getToolTip().add(String.format(" +%s %s",
									trueValue,
									String.format("%s %s",
											stringCollection.toArray()[0],
											TextFormatting.GRAY + I18n.format("attribute.name.aether.weakness"))));
						}
					}
					else if (effect.getValue() >= 1.0D)
					{
						event.getToolTip().add(String.format(" %s %s",
								TextFormatting.GRAY + I18n.format("attribute.name.aether.complete_resistance"),
								stringCollection.toArray()[0]));
					}
				}
			}
		}

		// Equipment Effects
		for (final IEffectProvider provider : properties.getEffectProviders())
		{
			final IEffectFactory<IEffectProvider> factory = AetherAPI.content().effects().getFactory(provider.getFactory());

			final EffectPoolTemporary pool = new EffectPoolTemporary(event.getItemStack(), provider);

			TextFormatting textFormatting1 = TextFormatting.BLUE;
			TextFormatting textFormatting2 = TextFormatting.RED;

			if (properties.getEquipmentSlot() == ItemEquipmentSlot.NONE)
			{
				textFormatting1 = TextFormatting.GRAY;
				textFormatting2 = TextFormatting.GRAY;
			}

			factory.createInstance(pool).addInformation(event.getToolTip(), textFormatting1, textFormatting2);
		}

		// Slot Type
		if (properties.getEquipmentSlot() != ItemEquipmentSlot.NONE)
		{
			final ItemEquipmentSlot slot = properties.getEquipmentSlot();

			event.getToolTip().add("");
			event.getToolTip().add(I18n.format(slot.getUnlocalizedName()));
		}

		// Rarity
		if (properties.getRarity() != ItemRarity.NONE)
		{
			event.getToolTip().add(I18n.format(properties.getRarity().getUnlocalizedName()));
		}

		if (Minecraft.getMinecraft().gameSettings.advancedItemTooltips)
		{
			if (durability != null)
			{
				event.getToolTip().add(durability);
			}

			if (modID != null)
			{
				event.getToolTip().add(modID);
			}

			if (event.getItemStack().hasTagCompound())
			{
				if (NBT != null)
				{
					event.getToolTip().add(NBT);
				}
			}
		}

		//Currency
		if (value >= 1 && value < 100)
		{
			event.getToolTip().add("");
		}
		else if (value >= 100 && value < 10000)
		{
			event.getToolTip().add("            ");
		}
		else if (value >= 10000)
		{
			event.getToolTip().add("            ");
			event.getToolTip().add("");
		}
	}

	@SubscribeEvent
	public static void onToolTipRender(final RenderTooltipEvent.PostText event)
	{
		final double value = AetherAPI.content().currency().getValue(event.getStack(), ShopCurrencyGilt.class);

		toolTipHelper.render(event.getFontRenderer(), event.getX(), event.getY(), event.getHeight(), value);
	}

	private static class EffectPoolTemporary<T extends IEffectProvider> implements IEffectPool<T>
	{
		private final ItemStack stack;

		private final T provider;

		public EffectPoolTemporary(final ItemStack stack, final T provider)
		{
			this.stack = stack;
			this.provider = provider;
		}

		@Override
		public ItemStack getProvider(final IEffectProvider instance)
		{
			return this.provider == instance ? this.stack : ItemStack.EMPTY;
		}

		@Override
		public Collection<T> getActiveProviders()
		{
			return Collections.singleton(this.provider);
		}

		@Override
		public Optional<EffectInstance> getInstance()
		{
			return Optional.empty();
		}

		@Override
		public boolean isEmpty()
		{
			return false;
		}
	}
}
