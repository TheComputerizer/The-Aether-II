package com.gildedgames.aether.common.events.listeners.player;

import com.gildedgames.aether.api.entity.effects.IAetherStatusEffectPool;
import com.gildedgames.aether.api.entity.effects.IAetherStatusEffects;
import com.gildedgames.aether.api.registrar.BiomesAether;
import com.gildedgames.aether.api.registrar.CapabilitiesAether;
import com.gildedgames.aether.common.AetherCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid = AetherCore.MOD_ID)
public class PlayerWaterListener
{
    public static int tickRate = 30;
    public static int amount = 5;

    @SubscribeEvent
    public static void onPlayerStunned(final TickEvent.PlayerTickEvent event)
    {
        if (event.player != null)
        {
            EntityPlayer player = event.player;

            if (player.world != null)
            {
                World world = player.world;

                if (world.getBiome(player.getPosition()) == BiomesAether.IRRADIATED_FORESTS)
                {
                    IAetherStatusEffectPool statusEffectPool = player.getCapability(CapabilitiesAether.STATUS_EFFECT_POOL, null);

                    if (player.isInWater())
                    {
                        if (!player.isCreative())
                        {
                            boolean tick = world.getTotalWorldTime() % tickRate == 0;

                            if (tick)
                            {
                                if (statusEffectPool != null)
                                {
                                    if (!statusEffectPool.effectExists(IAetherStatusEffects.effectTypes.IRRADIATION))
                                    {
                                        statusEffectPool.applyStatusEffect(IAetherStatusEffects.effectTypes.IRRADIATION, amount);
                                    }
                                    else
                                    {
                                        statusEffectPool.modifyActiveEffectBuildup(IAetherStatusEffects.effectTypes.IRRADIATION,
                                                statusEffectPool.getBuildupFromEffect(IAetherStatusEffects.effectTypes.IRRADIATION) + amount);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
