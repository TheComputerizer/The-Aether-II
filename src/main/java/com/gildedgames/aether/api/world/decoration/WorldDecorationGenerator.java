package com.gildedgames.aether.api.world.decoration;

import com.gildedgames.orbis.lib.world.WorldSlice;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public interface WorldDecorationGenerator
{
	boolean generate(WorldSlice slice, Random rand, BlockPos pos);
}
