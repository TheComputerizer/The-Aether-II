package com.gildedgames.aether.common.world.aether.island.gen;

import com.gildedgames.aether.api.world.islands.IIslandData;
import com.gildedgames.aether.common.blocks.BlocksAether;
import com.gildedgames.aether.common.world.aether.biomes.BiomeAetherBase;
import com.gildedgames.aether.common.world.util.OpenSimplexNoise;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

public class IslandGenerator
{
	// Resolution = x^2
	private static final int NOISE_RESOLUTION = 9;

	private static final double NOISE_SCALE = 16.0D / NOISE_RESOLUTION,
			NOISE_INVERSE = (16.0D / (NOISE_RESOLUTION - 1));

	private final World world;

	public IslandGenerator(final World world)
	{
		this.world = world;
	}

	public void genIslandForChunk(final ChunkPrimer primer, final IIslandData island, final int chunkX, final int chunkZ)
	{
		final Biome biome = this.world.getBiome(new BlockPos(chunkX * 16, 0, chunkZ * 16));

		final IBlockState coastBlock = ((BiomeAetherBase) biome).getCoastalBlock();

		final double height = island.getBounds().getHeight();

		final double[] heightMap = this.generateNoise(island, chunkX, chunkZ);

		for (int x = 0; x < 16; x++)
		{
			for (int z = 0; z < 16; z++)
			{
				final double sample = this.interpolate(heightMap, x, z);
				final double heightSample = sample + 1.0D;

				final double bottomHeight = 0.8 * height;
				final double bottomMaxY = island.getBounds().getMinY() + bottomHeight;

				final double topHeight = 0.2 * height;

				final double cutoffPoint = 0.8;

				final double bottomHeightMod = Math.min(1.0, (heightSample - cutoffPoint) * 1.4);

				if (heightSample > cutoffPoint)
				{
					for (int y = (int) bottomMaxY; y > bottomMaxY - (bottomHeight * bottomHeightMod); y--)
					{
						if (coastBlock != null && heightSample < cutoffPoint + 0.05 && y == bottomMaxY - 1)
						{
							primer.setBlockState(x, y, z, coastBlock);
						}
						else
						{
							primer.setBlockState(x, y, z, BlocksAether.holystone.getDefaultState());
						}
					}

					for (int y = (int) bottomMaxY; y < bottomMaxY + ((heightSample - cutoffPoint) * topHeight); y++)
					{
						if (coastBlock != null && (heightSample < cutoffPoint + 0.05 && y < bottomMaxY + 1))
						{
							primer.setBlockState(x, y, z, coastBlock);
						}
						else
						{
							primer.setBlockState(x, y, z, BlocksAether.holystone.getDefaultState());
						}
					}
				}
			}
		}
	}

	private double interpolate(final double[] heightMap, final int x, final int z)
	{
		final double x0 = (x / NOISE_INVERSE);
		final double z0 = (z / NOISE_INVERSE);

		final double x1 = Math.floor(x0);
		final double x2 = Math.ceil(x0) + 0.5D;

		final double z1 = Math.floor(z0);
		final double z2 = Math.ceil(z0) + 0.5D;

		final double q11 = this.valueAt(heightMap, (int) x1, (int) z1);
		final double q12 = this.valueAt(heightMap, (int) x1, (int) z2);
		final double q21 = this.valueAt(heightMap, (int) x2, (int) z1);
		final double q22 = this.valueAt(heightMap, (int) x2, (int) z2);

		final double a1 = (x2 - x0) / (x2 - x1);
		final double a2 = (x0 - x1) / (x2 - x1);

		final double r1 = a1 * q11 + a2 * q21;
		final double r2 = a1 * q12 + a2 * q22;

		return ((z2 - z0) / (z2 - z1)) * r1 + ((z0 - z1) / (z2 - z1)) * r2;
	}

	private double valueAt(final double[] heightMap, final int x, final int z)
	{
		return heightMap[x + (z * NOISE_RESOLUTION)];
	}

	private double[] generateNoise(final IIslandData island, final int chunkX, final int chunkZ)
	{
		final OpenSimplexNoise simplex = new OpenSimplexNoise(island.getSeed());

		final double posX = chunkX * 16;
		final double posZ = chunkZ * 16;

		final double minX = island.getBounds().getMinX();
		final double minZ = island.getBounds().getMinZ();

		final double centerX = island.getBounds().getCenterX();
		final double centerZ = island.getBounds().getCenterZ();

		final double[] data = new double[NOISE_RESOLUTION * NOISE_RESOLUTION];

		// Generate half-resolution noise
		for (int x = 0; x < NOISE_RESOLUTION; x++)
		{
			// Creates world coordinate and normalized noise coordinate
			final double worldX = posX + (x * NOISE_SCALE);
			final double nx = (worldX + minX) / 300.0D;

			for (int z = 0; z < NOISE_RESOLUTION; z++)
			{
				// Creates world coordinate and normalized noise coordinate
				final double worldZ = posZ + (z * NOISE_SCALE);
				final double nz = (worldZ + minZ) / 300.0D;

				final double distX = Math.abs(centerX - worldX);
				final double distZ = Math.abs(centerZ - worldZ);

				// Get distance from center of Island
				final double dist = (distX + distZ) / 450.0D;

				// Generate noise for X/Z coordinate
				final double noise1 = simplex.eval(nx, nz);
				final double noise2 = 0.5D * simplex.eval(nx * 8D, nz * 8D);
				final double noise3 = 0.25D * simplex.eval(nx * 16D, nz * 16D);
				final double noise4 = 0.1D * simplex.eval(nx * 32D, nz * 32D);

				// Averages noise samples linearly
				final double sample = (noise1 + noise2 + noise3 + noise4) / 4.0D;

				// Apply formula to shape noise into island, noise decreases in value the further the coord is from the center
				final double height = sample - (0.7D * Math.pow(dist, 2));

				data[x + (z * NOISE_RESOLUTION)] = height;
			}
		}

		return data;
	}

}