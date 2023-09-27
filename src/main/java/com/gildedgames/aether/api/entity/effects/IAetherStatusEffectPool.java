package com.gildedgames.aether.api.entity.effects;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nonnegative;
import java.util.HashMap;

/**
 * Requires an EntityLivingBase being attached to it (recommended through constructor
 */
public interface IAetherStatusEffectPool
{

	/**
	 * Call supplied tick() method for all StatusEffects in pool.
	 */
	void tick();

	/**
	 * Handles all calls to add buildup to effects.
	 * Given the effectType and buildup, addBuildup to corresponding effect.
	 * Method handles any temporary additions to resistances.
	 * @param effectType The effect buildup should be applied to.
	 * @param buildup The amount of buildup to add to the effect.
	 */
	void applyStatusEffect(IAetherStatusEffects.effectTypes effectType, @Nonnegative int buildup);

	/**
	 * @param effectType The effect to retrieve buildup from.
	 * @return integer representing the current effect's buildup.
	 */
	int getBuildupFromEffect(IAetherStatusEffects.effectTypes effectType);

	/**
	 * @param effectType The effect to retrieve application status information.
	 * @return boolean representing if the effect is being applied or not.
	 */
	boolean isEffectApplied(IAetherStatusEffects.effectTypes effectType);

	boolean effectExists(IAetherStatusEffects.effectTypes effectType);

	/**
	 * @param effectType The effect to retrive resistance information.
	 * @return double representing effect resistance.
	 */
	double getResistanceToEffect(IAetherStatusEffects.effectTypes effectType);

	/**
	 * Resistances to effects are multiplied towards buildup, given this they have the limits of 0.0 and 2.0.
	 * Due to multiplication principles, adding more resistance to an effect will lower that Entity's resistance. (buildup * 2.0resistance) =
	 * 																												buildup*2 additional buildup.
	 * Decreasing resistance to an effect will raise that Entity's resistance. (buildup * 0.0resistance) = 0 additional buildup.
	 * @param effectType The effect to apply resistance to.
	 * @param addResistance    The amount of resistance to add to effect (can be negative, to lower resistance number).
	 */
	void addResistanceToEffect(IAetherStatusEffects.effectTypes effectType, double addResistance);

	/**
	 * Reset's a specified resistance back to its default (1.0)
	 * @param effectType effect to reset.
	 */
	void resetResistanceToEffect(IAetherStatusEffects.effectTypes effectType);

	/**
	 * Reset's every effect's resistance in the pool.
	 */
	void resetAllResistances();

	/**
	 * Method to change how long an effect lasts.
	 * @param effectType The effect to modify
	 * @param activeEffectTimeModifier Multiplied by the active effect time of an effect.
	 */
	void modifyActiveEffectTime(IAetherStatusEffects.effectTypes effectType, double activeEffectTimeModifier);

	/**
	 * Modifies all the active effect times in the pool.
	 * @param activeEffectTimeModifier Multiplied by the active effect time of an effect.
	 */
	void modifyAllActiveEffectTimes(double activeEffectTimeModifier);

	/**
	 * Modifies the buildup value of an effect.
	 * @param effectType The effect to modify.
	 * @param activeEffectBuildup The buildup value of the active effect.
	 */
	void modifyActiveEffectBuildup(IAetherStatusEffects.effectTypes effectType, int activeEffectBuildup);

	/**
	 * Modifies whether the effect is applied or not (which means it has reached maximum buildup).
	 * @param effectType The effect to modify.
	 * @param setApplied Whether the effect should be applied or not.
	 */
	void modifyActiveEffectApplication(IAetherStatusEffects.effectTypes effectType, boolean setApplied);

	/**
	 * Cures a specific effect, method essentially modifies the active effect time to 0.
	 * @param effectType The effect to cure.
	 */
	void cureActiveEffect(IAetherStatusEffects.effectTypes effectType);

	/**
	 * Cures all active effects by modifying their active effect time to 0.
	 */
	void cureAllActiveEffects();

	IAetherStatusEffects createEffect(String name, EntityLivingBase entity);

	HashMap<String, IAetherStatusEffects> getPool();

	/**
	 * Writes this capability to {@param tag}.
	 * @param tag The {@link NBTTagCompound} to write to
	 */
	void write(NBTTagCompound tag);

	/**
	 * Updates this capability from {@param tag}.
	 * @param tag The {@link NBTTagCompound} to read from
	 */
	void read(NBTTagCompound tag);
}
