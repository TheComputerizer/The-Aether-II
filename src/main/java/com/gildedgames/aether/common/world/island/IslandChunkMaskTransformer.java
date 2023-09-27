package com.gildedgames.aether.common.world.island;

import com.gildedgames.aether.api.registrar.BlocksAether;
import com.gildedgames.aether.api.world.preparation.IChunkMaskTransformer;
import com.gildedgames.aether.common.AetherCore;
import com.gildedgames.aether.common.blocks.natural.BlockHolystone;
import com.gildedgames.aether.common.world.preparation.mask.ChunkMask;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

/**
 * Transforms generic blocks in a {@link ChunkMask} into real-world blocks for usage by block accessors or
 * chunk generation.
 */
public class IslandChunkMaskTransformer implements IChunkMaskTransformer
{
	private final IBlockState[] states;

	public IslandChunkMaskTransformer()
	{
		AetherCore.LOGGER.error("TEST 1");
		this.states = new IBlockState[IslandBlockType.VALUES.length];
		AetherCore.LOGGER.error("TEST 2");
		this.setMaskValue(IslandBlockType.AIR_BLOCK, Blocks.AIR.getDefaultState());
		AetherCore.LOGGER.error("TEST 3");
		this.setMaskValue(IslandBlockType.WATER_BLOCK, Blocks.WATER.getDefaultState());
		AetherCore.LOGGER.error("TEST 4");
		this.setMaskValue(IslandBlockType.STONE_BLOCK, BlocksAether.holystone.getDefaultState());
		AetherCore.LOGGER.error("TEST 5");
		this.setMaskValue(IslandBlockType.STONE_MOSSY_BLOCK,
				BlocksAether.holystone.getDefaultState().withProperty(BlockHolystone.PROPERTY_VARIANT, BlockHolystone.MOSSY_HOLYSTONE));
		AetherCore.LOGGER.error("TEST 6");
		this.setMaskValue(IslandBlockType.STONE_IRRADIATED_BLOCK,
				BlocksAether.holystone.getDefaultState().withProperty(BlockHolystone.PROPERTY_VARIANT, BlockHolystone.IRRADIATED_HOLYSTONE));
		AetherCore.LOGGER.error("TEST 7");
		this.setMaskValue(IslandBlockType.SNOW_BLOCK, BlocksAether.highlands_snow.getDefaultState());
		AetherCore.LOGGER.error("TEST 8");
		this.setMaskValue(IslandBlockType.FERROSITE_BLOCK, BlocksAether.ferrosite.getDefaultState());
		AetherCore.LOGGER.error("TEST 9");
		this.setMaskValue(IslandBlockType.TOPSOIL_BLOCK, BlocksAether.aether_grass.getDefaultState());
		AetherCore.LOGGER.error("TEST 10");
		this.setMaskValue(IslandBlockType.SOIL_BLOCK, BlocksAether.aether_dirt.getDefaultState());
		AetherCore.LOGGER.error("TEST 11");
		this.setMaskValue(IslandBlockType.COAST_BLOCK, BlocksAether.quicksoil.getDefaultState());
		AetherCore.LOGGER.error("TEST 12");
		this.setMaskValue(IslandBlockType.CLOUD_BED_BLOCK, BlocksAether.aercloud.getDefaultState());
		AetherCore.LOGGER.error("TEST 13");
		this.setMaskValue(IslandBlockType.VEIN_BLOCK, BlocksAether.highlands_packed_ice.getDefaultState());
		AetherCore.LOGGER.error("TEST 14");
	}

	public void setMaskValue(IslandBlockType type, IBlockState state)
	{
		int key = type.ordinal();

		this.states[key] = state;
	}

	@Override
	public IBlockState getBlockState(int key)
	{
		return this.states[key];
	}

	@Override
	public int getBlockCount()
	{
		return this.states.length;
	}
}
