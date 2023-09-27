package com.gildedgames.aether.api.items.equipment.effects;

import com.gildedgames.aether.api.player.IPlayerAether;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Collection;

/**
 * Implementor of an effect for an entity. Effect instances are re-created
 * when the active {@link IEffectProvider} are changed, and are not persistent.
 *
 * Implementors should never store data passed to them, such as {@link IPlayerAether}.
 */
public abstract class EffectInstance
{
	/**
	 * Called each tick when the entity this instance belongs to updates. Server-side only.
	 *
	 * @param player The player entity that is updating
	 */
	public void onEntityUpdate(IPlayerAether player)
	{
	}

	/**
	 * Called when this instance is removed from a player. This is only called once per entity
	 * for the duration of the instance. In the event a player logs out or the entity containing
	 * this instance is destroyed, this method will not be called. Server-side only.
	 *
	 * @param player The player this instance was removed from
	 */
	public void onInstanceRemoved(IPlayerAether player)
	{
	}

	/**
	 * Called when this instance is added to a player. This is guaranteed to called exactly once,
	 * when the instance is attached to an entity. Server-side only.
	 *
	 * @param player The player this instance was added to
	 */
	public void onInstanceAdded(IPlayerAether player)
	{
	}

	/**
	 * Adds information about this instance to {@param label}, such as the stats
	 * it provides or a short description. Client-side only.
	 *
	 * @param label The {@link Collection} to add to
	 */
	@SideOnly(Side.CLIENT)
	public abstract void addInformation(Collection<String> label, TextFormatting format1, TextFormatting format2);
}
