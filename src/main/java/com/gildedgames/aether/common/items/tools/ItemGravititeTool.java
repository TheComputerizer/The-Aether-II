package com.gildedgames.aether.common.items.tools;

import com.gildedgames.aether.common.capabilities.player.PlayerAetherImpl;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemGravititeTool extends ItemAetherTool
{
	
	public ItemGravititeTool(EnumToolType toolType, float attackDamage, float attackSpeed)
	{
		super(ToolMaterial.DIAMOND, "gravitite", toolType, attackDamage, attackSpeed);

		this.setHarvestLevel(toolType.getToolClass(), 3);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand)
	{
		if (!world.isRemote && hand == EnumHand.MAIN_HAND)
		{
			PlayerAetherImpl aePlayer = PlayerAetherImpl.getPlayer(player);

			if (aePlayer.getGravititeAbility().getHeldBlock() != null && aePlayer.getGravititeAbility().getHeldBlock().ticksExisted > 1)
			{
				aePlayer.getGravititeAbility().dropHeldBlock();
			}
		}

		return new ActionResult<>(EnumActionResult.PASS, stack);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote && hand == EnumHand.MAIN_HAND)
		{
			PlayerAetherImpl aePlayer = PlayerAetherImpl.getPlayer(player);

			if (aePlayer.getGravititeAbility().getHeldBlock() == null)
			{
				IBlockState state = world.getBlockState(pos);

				if (!state.isNormalCube() || !state.getBlock().getHarvestTool(state).equals(this.toolType.getToolClass()) || state.getBlockHardness(world, pos) < 0)
				{
					return EnumActionResult.PASS;
				}

				if (aePlayer.getGravititeAbility().pickupBlock(pos, world))
				{
					stack.damageItem(2, player);

					return EnumActionResult.SUCCESS;
				}
			}
		}

		return EnumActionResult.FAIL;
	}

	@Override
	protected boolean isAbilityPassive()
	{
		return false;
	}
}
