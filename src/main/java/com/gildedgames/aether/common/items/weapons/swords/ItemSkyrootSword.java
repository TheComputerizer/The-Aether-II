package com.gildedgames.aether.common.items.weapons.swords;

import com.gildedgames.aether.common.AetherCore;
import com.gildedgames.aether.common.items.ItemAbilityType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AetherCore.MOD_ID)
public class ItemSkyrootSword extends ItemAetherSword
{
	public ItemSkyrootSword()
	{
		super(ToolMaterial.WOOD, ItemAbilityType.PASSIVE);
	}

	@Override
	public int getItemBurnTime(ItemStack itemStack)
	{
		return 200;
	}
}
