package com.gildedgames.aether.client.init;

import com.gildedgames.aether.api.registrar.BlocksAether;
import com.gildedgames.aether.api.registrar.ItemsAether;
import com.gildedgames.aether.client.util.ItemModelBuilder;
import com.gildedgames.aether.common.AetherCore;
import com.gildedgames.aether.common.blocks.containers.BlockHolystoneFurnace;
import com.gildedgames.aether.common.blocks.containers.BlockIncubator;
import com.gildedgames.aether.common.blocks.decorative.*;
import com.gildedgames.aether.common.blocks.natural.*;
import com.gildedgames.aether.common.blocks.natural.plants.*;
import com.gildedgames.aether.common.blocks.natural.plants.saplings.BlockAetherGreatrootSapling;
import com.gildedgames.aether.common.blocks.natural.plants.saplings.BlockAetherSkyrootSapling;
import com.gildedgames.aether.common.blocks.natural.plants.saplings.BlockAetherUniqueSapling;
import com.gildedgames.aether.common.blocks.natural.plants.saplings.BlockAetherWisprootSapling;
import com.gildedgames.aether.common.entities.blocks.EntityParachute;
import com.gildedgames.aether.common.entities.effects.*;
import com.gildedgames.aether.common.entities.effects.unique.StatusEffectGuardBreak;
import com.gildedgames.aether.common.entities.monsters.EntitySwet;
import com.gildedgames.aether.common.entities.tiles.*;
import com.gildedgames.aether.common.init.CreativeTabsAether;
import com.gildedgames.aether.common.items.accessories.ItemAccessory;
import com.gildedgames.aether.common.items.weapons.ItemDartType;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Map;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = AetherCore.MOD_ID, value = Side.CLIENT)
public class ItemModelsAetherInit
{
	@SubscribeEvent()
	public static void onModelRegistryReady(final ModelRegistryEvent event)
	{
		registerModels();

		ForgeHooksClient.registerTESRItemStack(getItem(BlocksAether.skyroot_chest), 0, TileEntitySkyrootChest.class);
		ForgeHooksClient.registerTESRItemStack(getItem(BlocksAether.altar), 0, TileEntityAltar.class);
		ForgeHooksClient.registerTESRItemStack(getItem(BlocksAether.present), 0, TileEntityPresent.class);
		ForgeHooksClient.registerTESRItemStack(getItem(BlocksAether.icestone_cooler), 0, TileEntityIcestoneCooler.class);
		ForgeHooksClient.registerTESRItemStack(getItem(BlocksAether.masonry_bench), 0, TileEntityMasonryBench.class);
		ForgeHooksClient.registerTESRItemStack(getItem(BlocksAether.outpost_campfire), 0, TileEntityOutpostCampfire.class);
	}

	private static void registerModels()
	{
		registerItemModels(BlocksAether.therastone_brick_decorative, new ItemModelBuilder("therastone_bricks/")
				.add(BlockTherastoneDecorative.BASE_BRICKS.getMeta(), "base_bricks")
				.add(BlockTherastoneDecorative.BASE_PILLAR.getMeta(), "base_pillar")
				.add(BlockTherastoneDecorative.CAPSTONE_BRICKS.getMeta(), "capstone_bricks")
				.add(BlockTherastoneDecorative.CAPSTONE_PILLAR.getMeta(), "capstone_pillar")
				.add(BlockTherastoneDecorative.FLAGSTONES.getMeta(), "flagstones")
				.add(BlockTherastoneDecorative.HEADSTONE.getMeta(), "headstone")
				.add(BlockTherastoneDecorative.KEYSTONE.getMeta(), "keystone"));

		registerItemModels(BlocksAether.therastone_brick, "therastone_bricks/normal");

		registerItemModels(BlocksAether.therastone_pillar, "therastone_bricks/pillar");

		registerItemModels(BlocksAether.therawood_log, "logs/therawood_log");

		registerItemModels(BlocksAether.therawood_leaves, "leaves/therawood_leaves");

		registerItemModels(BlocksAether.therawood_planks, "therawood_planks/normal");

		registerItemModels(BlocksAether.therawood_decorative, new ItemModelBuilder("therawood_planks/")
				.add(BlockTherawoodDecorative.BASE_PLANKS.getMeta(), "base_planks")
				.add(BlockTherawoodDecorative.BASE_BEAM.getMeta(), "base_beam")
				.add(BlockTherawoodDecorative.TOP_PLANKS.getMeta(), "top_planks")
				.add(BlockTherawoodDecorative.TOP_BEAM.getMeta(), "top_beam")
				.add(BlockTherawoodDecorative.FLOORBOARDS.getMeta(), "floorboards")
				.add(BlockTherawoodDecorative.HIGHLIGHT.getMeta(), "highlight"));

		registerItemModels(BlocksAether.therawood_beam, "therawood_planks/beam");

		registerItemModels(BlocksAether.aether_dirt, new ItemModelBuilder("aether_dirt/")
				.add(BlockAetherDirt.DIRT.getMeta(), "dirt")
				.add(BlockAetherDirt.COARSE_DIRT.getMeta(), "coarse_dirt"));

		registerItemModels(BlocksAether.aether_grass, new ItemModelBuilder("grass/")
				.add(BlockAetherGrass.AETHER.getMeta(), "aether_grass")
				.add(BlockAetherGrass.ENCHANTED.getMeta(), "enchanted_grass")
				.add(BlockAetherGrass.ARCTIC.getMeta(), "arctic_grass")
				.add(BlockAetherGrass.MAGNETIC.getMeta(), "magnetic_grass")
				.add(BlockAetherGrass.IRRADIATED.getMeta(), "irradiated_grass"));

		registerItemModels(BlocksAether.thera_dirt, new ItemModelBuilder("thera_dirt/")
				.add(BlockTheraDirt.DIRT.getMeta(), "thera_dirt"));

		registerItemModels(BlocksAether.thera_grass, new ItemModelBuilder("thera_grass/")
				.add(BlockTheraGrass.NORMAL.getMeta(), "thera_grass"));

		registerItemModels(BlocksAether.holystone, new ItemModelBuilder("holystone/")
				.add(BlockHolystone.NORMAL_HOLYSTONE.getMeta(), "holystone")
				.add(BlockHolystone.MOSSY_HOLYSTONE.getMeta(), "mossy_holystone")
				.add(BlockHolystone.IRRADIATED_HOLYSTONE.getMeta(), "irradiated_holystone"));

		registerItemModels(BlocksAether.aercloud, new ItemModelBuilder("aercloud/")
				.add(BlockAercloud.COLD_AERCLOUD.getMeta(), "cold_aercloud")
				.add(BlockAercloud.BLUE_AERCLOUD.getMeta(), "blue_aercloud")
				.add(BlockAercloud.GOLDEN_AERCLOUD.getMeta(), "golden_aercloud")
				.add(BlockAercloud.GREEN_AERCLOUD.getMeta(), "green_aercloud")
				.add(BlockAercloud.STORM_AERCLOUD.getMeta(), "storm_aercloud")
				.add(BlockAercloud.PURPLE_AERCLOUD.getMeta(), "purple_aercloud"));

		registerItemModels(BlocksAether.cloudwool_block, "cloudwool_block");

		registerItemModels(BlocksAether.ferrosite, "ferrosite");
		registerItemModels(BlocksAether.rusted_ferrosite, "rusted_ferrosite");

		registerItemModels(BlocksAether.skyroot_log, "logs/skyroot_log");
		registerItemModels(BlocksAether.dark_skyroot_log, "logs/dark_skyroot_log");
		registerItemModels(BlocksAether.light_skyroot_log, "logs/light_skyroot_log");
		registerItemModels(BlocksAether.golden_oak_log, "logs/golden_oak_log");

		registerItemModels(BlocksAether.blue_skyroot_leaves, "leaves/blue_skyroot_leaves");
		registerItemModels(BlocksAether.green_skyroot_leaves, "leaves/green_skyroot_leaves");
		registerItemModels(BlocksAether.dark_blue_skyroot_leaves, "leaves/dark_blue_skyroot_leaves");

		registerItemModels(BlocksAether.blue_dark_skyroot_leaves, "leaves/blue_dark_skyroot_leaves");
		registerItemModels(BlocksAether.green_dark_skyroot_leaves, "leaves/green_dark_skyroot_leaves");
		registerItemModels(BlocksAether.dark_blue_dark_skyroot_leaves, "leaves/dark_blue_dark_skyroot_leaves");

		registerItemModels(BlocksAether.blue_light_skyroot_leaves, "leaves/blue_light_skyroot_leaves");
		registerItemModels(BlocksAether.green_light_skyroot_leaves, "leaves/green_light_skyroot_leaves");
		registerItemModels(BlocksAether.dark_blue_light_skyroot_leaves, "leaves/dark_blue_light_skyroot_leaves");

		registerItemModels(BlocksAether.amberoot_leaves, "leaves/amberoot_leaves");

		registerItemModels(BlocksAether.highlands_bush, "bushes/highlands_bush");

		registerItemModels(BlocksAether.mutant_leaves, "leaves/mutant_leaves");
		registerItemModels(BlocksAether.mutant_leaves_decorated, "leaves/mutant_leaves_decorated");

		registerItemModels(BlocksAether.blueberry_bush, new ItemModelBuilder("bushes/")
				.add(BlockBlueberryBush.BERRY_BUSH_STEM, "blueberry_bush_stem")
				.add(BlockBlueberryBush.BERRY_BUSH_RIPE, "blueberry_bush_ripe"));

		registerItemModels(BlocksAether.aether_flower, new ItemModelBuilder("flowers/")
				.add(BlockAetherFlower.WHITE_ROSE.getMeta(), "white_rose")
				.add(BlockAetherFlower.PURPLE_FLOWER.getMeta(), "purple_flower")
				.add(BlockAetherFlower.BURSTBLOSSOM.getMeta(), "burstblossom")
				.add(BlockAetherFlower.AECHOR_SPROUT.getMeta(), "aechor_sprout")
				.add(BlockAetherFlower.WHITE_ROSE.getMeta() + BlockAetherFlower.PROPERTY_VARIANT.getAllowedValues().size(), "white_rose_snowy")
				.add(BlockAetherFlower.PURPLE_FLOWER.getMeta() + BlockAetherFlower.PROPERTY_VARIANT.getAllowedValues().size(), "purple_flower_snowy")
				.add(BlockAetherFlower.BURSTBLOSSOM.getMeta() + BlockAetherFlower.PROPERTY_VARIANT.getAllowedValues().size(), "burstblossom_snowy")
				.add(BlockAetherFlower.AECHOR_SPROUT.getMeta() + BlockAetherFlower.PROPERTY_VARIANT.getAllowedValues().size(), "aechor_sprout_snowy"));

		registerSnowyFlower(BlocksAether.arctic_spikespring, "arctic_spikespring");
		registerSnowyFlower(BlocksAether.barkshroom, "barkshroom");
		registerSnowyFlower(BlocksAether.blue_swingtip, "blue_swingtip");
		registerSnowyFlower(BlocksAether.forgotten_rose, "forgotten_rose");
		registerSnowyFlower(BlocksAether.green_swingtip, "green_swingtip");
		registerSnowyFlower(BlocksAether.highlands_tulips, "highlands_tulips");
		registerSnowyFlower(BlocksAether.irradiated_flower, "irradiated_flower");
		registerSnowyFlower(BlocksAether.magnetic_shroom, "magnetic_shroom");
		registerSnowyFlower(BlocksAether.neverbloom, "neverbloom");
		registerSnowyFlower(BlocksAether.pink_swingtip, "pink_swingtip");
		registerSnowyFlower(BlocksAether.quickshoot, "quickshoot");
		registerSnowyFlower(BlocksAether.stoneshroom, "stoneshroom");

		registerItemModels(BlocksAether.skyroot_sapling, new ItemModelBuilder("saplings/")
				.add(BlockAetherSkyrootSapling.GREEN.getMeta(), "green_skyroot_sapling")
				.add(BlockAetherSkyrootSapling.BLUE.getMeta(), "blue_skyroot_sapling")
				.add(BlockAetherSkyrootSapling.DARK_BLUE.getMeta(), "dark_blue_skyroot_sapling"));

		registerItemModels(BlocksAether.unique_sapling, new ItemModelBuilder("saplings/")
				.add(BlockAetherUniqueSapling.AMBEROOT.getMeta(), "amberoot_sapling")
				.add(BlockAetherUniqueSapling.MUTANT_TREE.getMeta(), "mutant_tree_sapling"));

		registerItemModels(BlocksAether.wisproot_sapling, new ItemModelBuilder("saplings/")
				.add(BlockAetherWisprootSapling.GREEN.getMeta(), "green_wisproot_sapling")
				.add(BlockAetherWisprootSapling.BLUE.getMeta(), "blue_wisproot_sapling")
				.add(BlockAetherWisprootSapling.DARK_BLUE.getMeta(), "dark_blue_wisproot_sapling"));

		registerItemModels(BlocksAether.greatroot_sapling, new ItemModelBuilder("saplings/")
				.add(BlockAetherGreatrootSapling.GREEN.getMeta(), "green_greatroot_sapling")
				.add(BlockAetherGreatrootSapling.BLUE.getMeta(), "blue_greatroot_sapling")
				.add(BlockAetherGreatrootSapling.DARK_BLUE.getMeta(), "dark_blue_greatroot_sapling"));

		registerItemModels(BlocksAether.holystone_furnace, new ItemModelBuilder("holystone_furnace/")
				.add(BlockHolystoneFurnace.LIT_META, "holystone_furnace_lit")
				.add(BlockHolystoneFurnace.UNLIT_META, "holystone_furnace_unlit"));

		registerItemModels(BlocksAether.skyroot_fence, "skyroot_fence");
		registerItemModels(BlocksAether.skyroot_fence_gate, "skyroot_fence_gate");

		registerItemModels(BlocksAether.wisproot_fence, "wisproot_fence");
		registerItemModels(BlocksAether.wisproot_fence_gate, "wisproot_fence_gate");

		registerItemModels(BlocksAether.greatroot_fence, "greatroot_fence");
		registerItemModels(BlocksAether.greatroot_fence_gate, "greatroot_fence_gate");

		registerItemModels(BlocksAether.therawood_fence, "therawood_fence");
		registerItemModels(BlocksAether.therawood_fence_gate, "therawood_fence_gate");

		registerItemModels(BlocksAether.ambrosium_ore, "ores/ambrosium_ore");
		registerItemModels(BlocksAether.zanite_ore, "ores/zanite_ore");
		registerItemModels(BlocksAether.gravitite_ore, "ores/gravitite_ore");
		registerItemModels(BlocksAether.arkenium_ore, "ores/arkenium_ore");

		registerItemModels(BlocksAether.quicksoil, "quicksoil");
		registerItemModels(BlocksAether.ferrosite_sand, "ferrosite_sand");
		registerItemModels(BlocksAether.icestone_ore, "icestone_ore");
		registerItemModels(BlocksAether.icestone_bricks, "icestone_bricks");

		registerItemModels(BlocksAether.holystone_quartz_ore, "holystone_quartz_ore");

		registerItemModels(BlocksAether.icestone_bricks_decorative, new ItemModelBuilder("icestone_bricks/")
				.add(BlockIcestoneBricksDecorative.BASE_BRICKS.getMeta(), "base_bricks")
				.add(BlockIcestoneBricksDecorative.BASE_PILLAR.getMeta(), "base_pillar")
				.add(BlockIcestoneBricksDecorative.CAPSTONE_BRICKS.getMeta(), "capstone_bricks")
				.add(BlockIcestoneBricksDecorative.CAPSTONE_PILLAR.getMeta(), "capstone_pillar")
				.add(BlockIcestoneBricksDecorative.FLAGSTONES.getMeta(), "flagstones")
				.add(BlockIcestoneBricksDecorative.KEYSTONE.getMeta(), "keystone"));

		registerItemModels(BlocksAether.icestone_pillar, "icestone_bricks/pillar");

		registerItemModels(BlocksAether.quicksoil_glass, "quicksoil_glass/normal");
		registerItemModels(BlocksAether.quicksoil_glass_decorative, new ItemModelBuilder("quicksoil_glass/")
				.add(BlockRockGlassDecorative.SKYROOT_FRAME.getMeta(), "skyroot_frame")
				.add(BlockRockGlassDecorative.ARKENIUM_FRAME.getMeta(), "arkenium_frame"));

		registerItemModels(BlocksAether.crude_scatterglass, "crude_scatterglass/normal");
		registerItemModels(BlocksAether.crude_scatterglass_decorative, new ItemModelBuilder("crude_scatterglass/")
				.add(BlockRockGlassDecorative.SKYROOT_FRAME.getMeta(), "skyroot_frame")
				.add(BlockRockGlassDecorative.ARKENIUM_FRAME.getMeta(), "arkenium_frame"));

		registerItemModels(BlocksAether.scatterglass, "scatterglass/normal");
		registerItemModels(BlocksAether.scatterglass_decorative, new ItemModelBuilder("scatterglass/")
				.add(BlockRockGlassDecorative.SKYROOT_FRAME.getMeta(), "skyroot_frame")
				.add(BlockRockGlassDecorative.ARKENIUM_FRAME.getMeta(), "arkenium_frame"));

		registerItemModels(BlocksAether.quicksoil_glass_pane, "glass_pane/quicksoil_glass");
		registerItemModels(BlocksAether.quicksoil_glass_pane_decorative, new ItemModelBuilder("glass_pane/")
				.add(BlockRockGlassPaneDecorative.SKYROOT_FRAME.getMeta(), "quicksoil_glass_skyroot")
				.add(BlockRockGlassPaneDecorative.ARKENIUM_FRAME.getMeta(), "quicksoil_glass_arkenium"));

		registerItemModels(BlocksAether.scatterglass_pane, "glass_pane/scatterglass");
		registerItemModels(BlocksAether.scatterglass_pane_decorative, new ItemModelBuilder("glass_pane/")
				.add(BlockRockGlassPaneDecorative.SKYROOT_FRAME.getMeta(), "scatterglass_skyroot")
				.add(BlockRockGlassPaneDecorative.ARKENIUM_FRAME.getMeta(), "scatterglass_arkenium"));

		registerItemModels(BlocksAether.crude_scatterglass_pane, "glass_pane/crude_scatterglass");
		registerItemModels(BlocksAether.crude_scatterglass_pane_decorative, new ItemModelBuilder("glass_pane/")
				.add(BlockRockGlassPaneDecorative.SKYROOT_FRAME.getMeta(), "crude_scatterglass_skyroot")
				.add(BlockRockGlassPaneDecorative.ARKENIUM_FRAME.getMeta(), "crude_scatterglass_arkenium"));

		registerItemModels(BlocksAether.skyroot_twigs, "skyroot_twigs");
		registerItemModels(BlocksAether.holystone_rock, "holystone_rock");

		registerItemModels(BlocksAether.cloudwool_carpet, "cloudwool_carpet");

		registerItemModels(BlocksAether.aether_crafting_table, "crafting_tables/skyroot_crafting_table");

		registerItemModels(ItemsAether.skyroot_bed_item, "skyroot_bed");

		registerItemModels(BlocksAether.tall_aether_grass, new ItemModelBuilder("tall_grass/")
				.add(BlockTallAetherGrass.SHORT.getMeta(), "short_aether")
				.add(BlockTallAetherGrass.NORMAL.getMeta(), "normal_aether")
				.add(BlockTallAetherGrass.LONG.getMeta(), "long_aether"));

		registerItemModels(BlocksAether.valkyrie_grass, new ItemModelBuilder("valkyrie_grass/")
				.add(BlockValkyrieGrass.SPROUT.getMeta(), "valkyrie_grass_sprout")
				.add(BlockValkyrieGrass.FULL.getMeta(), "valkyrie_grass_full"));

		registerItemModels(BlocksAether.brettl_plant, "brettl_plant/brettl_plant");
		registerItemModels(ItemsAether.brettl_cane, "brettl_plant/brettl_cane");
		registerItemModels(ItemsAether.brettl_grass, "brettl_plant/brettl_grass");

		registerItemModels(BlocksAether.orange_tree, "orange_tree");

		registerItemModels(BlocksAether.zanite_block, "zanite_block");
		registerItemModels(BlocksAether.gravitite_block, "gravitite_block");

		registerItemModels(BlocksAether.holystone_brick_decorative, new ItemModelBuilder("holystone_bricks/")
				.add(BlockHolystoneDecorative.BASE_BRICKS.getMeta(), "base_bricks")
				.add(BlockHolystoneDecorative.BASE_PILLAR.getMeta(), "base_pillar")
				.add(BlockHolystoneDecorative.CAPSTONE_BRICKS.getMeta(), "capstone_bricks")
				.add(BlockHolystoneDecorative.CAPSTONE_PILLAR.getMeta(), "capstone_pillar")
				.add(BlockHolystoneDecorative.FLAGSTONES.getMeta(), "flagstones")
				.add(BlockHolystoneDecorative.HEADSTONE.getMeta(), "headstone")
				.add(BlockHolystoneDecorative.KEYSTONE.getMeta(), "keystone"));

		registerItemModels(BlocksAether.holystone_brick, "holystone_bricks/normal");

		registerItemModels(BlocksAether.plumproot, "plumproot");

		registerItemModels(ItemsAether.plumproot_mash, "plumproot_mash");
		registerItemModels(ItemsAether.plumproot_pie, "plumproot_pie");

		registerItemModels(BlocksAether.holystone_pillar, "holystone_bricks/pillar");

		registerItemModels(BlocksAether.sentrystone_brick_decorative, new ItemModelBuilder("sentrystone_bricks/")
				.add(BlockSentrystoneDecorative.BASE_BRICKS.getMeta(), "base_bricks")
				.add(BlockSentrystoneDecorative.BASE_PILLAR.getMeta(), "base_pillar")
				.add(BlockSentrystoneDecorative.CAPSTONE_BRICKS.getMeta(), "capstone_bricks")
				.add(BlockSentrystoneDecorative.CAPSTONE_PILLAR.getMeta(), "capstone_pillar")
				.add(BlockSentrystoneDecorative.FLAGSTONES.getMeta(), "flagstones")
				.add(BlockSentrystoneDecorative.LIGHTSTONE.getMeta(), "lightstone")
				.add(BlockSentrystoneDecorative.KEYSTONE.getMeta(), "keystone"));

		registerItemModels(BlocksAether.sentrystone_brick_decorative_lit, new ItemModelBuilder("sentrystone_bricks/")
				.add(BlockSentrystoneDecorativeLit.BASE_BRICKS.getMeta(), "base_bricks_lit")
				.add(BlockSentrystoneDecorativeLit.BASE_PILLAR.getMeta(), "base_pillar_lit")
				.add(BlockSentrystoneDecorativeLit.CAPSTONE_BRICKS.getMeta(), "capstone_bricks_lit")
				.add(BlockSentrystoneDecorativeLit.CAPSTONE_PILLAR.getMeta(), "capstone_pillar_lit")
				.add(BlockSentrystoneDecorativeLit.LIGHTSTONE.getMeta(), "lightstone_lit")
				.add(BlockSentrystoneDecorativeLit.KEYSTONE.getMeta(), "keystone_lit"));

		registerItemModels(BlocksAether.sentrystone_brick, "sentrystone_bricks/normal");

		registerItemModels(BlocksAether.sentrystone_pillar, "sentrystone_bricks/pillar");

		registerItemModels(BlocksAether.sentrystone_pillar_lit, "sentrystone_bricks/pillar_lit");

		registerItemModels(BlocksAether.hellfirestone_brick_decorative, new ItemModelBuilder("hellfirestone_bricks/")
				.add(BlockHellfirestoneDecorative.BASE_BRICKS.getMeta(), "base_bricks")
				.add(BlockHellfirestoneDecorative.BASE_PILLAR.getMeta(), "base_pillar")
				.add(BlockHellfirestoneDecorative.CAPSTONE_BRICKS.getMeta(), "capstone_bricks")
				.add(BlockHellfirestoneDecorative.CAPSTONE_PILLAR.getMeta(), "capstone_pillar")
				.add(BlockHellfirestoneDecorative.FLAGSTONES.getMeta(), "flagstones")
				.add(BlockHellfirestoneDecorative.KEYSTONE.getMeta(), "keystone"));

		registerItemModels(BlocksAether.hellfirestone_brick, "hellfirestone_bricks/normal");

		registerItemModels(BlocksAether.hellfirestone_lantern, "hellfirestone_bricks/lantern");

		registerItemModels(BlocksAether.hellfirestone_pillar, "hellfirestone_bricks/pillar");

		registerItemModels(BlocksAether.faded_holystone_brick, "faded_holystone_bricks/normal");

		registerItemModels(BlocksAether.faded_holystone_brick_decorative, new ItemModelBuilder("faded_holystone_bricks/")
				.add(BlockFadedHolystoneDecorative.BASE_BRICKS.getMeta(), "base_bricks")
				.add(BlockFadedHolystoneDecorative.BASE_PILLAR.getMeta(), "base_pillar")
				.add(BlockFadedHolystoneDecorative.CAPSTONE_BRICKS.getMeta(), "capstone_bricks")
				.add(BlockFadedHolystoneDecorative.CAPSTONE_PILLAR.getMeta(), "capstone_pillar")
				.add(BlockFadedHolystoneDecorative.FLAGSTONES.getMeta(), "flagstones")
				.add(BlockFadedHolystoneDecorative.HEADSTONE.getMeta(), "headstone")
				.add(BlockFadedHolystoneDecorative.KEYSTONE.getMeta(), "keystone"));

		registerItemModels(BlocksAether.faded_holystone_pillar, "faded_holystone_bricks/pillar");

		registerItemModels(BlocksAether.agiosite, "agiosite");

		registerItemModels(BlocksAether.agiosite_brick, "agiosite_bricks/normal");

		registerItemModels(BlocksAether.agiosite_brick_decorative, new ItemModelBuilder("agiosite_bricks/")
				.add(BlockAgiositeDecorative.BASE_BRICKS.getMeta(), "base_bricks")
				.add(BlockAgiositeDecorative.BASE_PILLAR.getMeta(), "base_pillar")
				.add(BlockAgiositeDecorative.CAPSTONE_BRICKS.getMeta(), "capstone_bricks")
				.add(BlockAgiositeDecorative.CAPSTONE_PILLAR.getMeta(), "capstone_pillar")
				.add(BlockAgiositeDecorative.FLAGSTONES.getMeta(), "flagstones")
				.add(BlockAgiositeDecorative.KEYSTONE.getMeta(), "keystone"));

		registerItemModels(BlocksAether.agiosite_pillar, "agiosite_bricks/pillar");

		registerItemModels(BlocksAether.skyroot_planks, "skyroot_planks/normal");

		registerItemModels(BlocksAether.skyroot_decorative, new ItemModelBuilder("skyroot_planks/")
				.add(BlockSkyrootDecorative.BASE_PLANKS.getMeta(), "base_planks")
				.add(BlockSkyrootDecorative.BASE_BEAM.getMeta(), "base_beam")
				.add(BlockSkyrootDecorative.TOP_PLANKS.getMeta(), "top_planks")
				.add(BlockSkyrootDecorative.TOP_BEAM.getMeta(), "top_beam")
				.add(BlockSkyrootDecorative.FLOORBOARDS.getMeta(), "floorboards")
				.add(BlockSkyrootDecorative.HIGHLIGHT.getMeta(), "highlight")
				.add(BlockSkyrootDecorative.TILES.getMeta(), "tiles")
				.add(BlockSkyrootDecorative.TILES_SMALL.getMeta(), "tiles_small"));

		registerItemModels(BlocksAether.skyroot_beam, "skyroot_planks/beam");

		registerItemModels(BlocksAether.dark_skyroot_planks, "dark_skyroot_planks/normal");

		registerItemModels(BlocksAether.dark_skyroot_decorative, new ItemModelBuilder("dark_skyroot_planks/")
				.add(BlockSkyrootDecorative.BASE_PLANKS.getMeta(), "base_planks")
				.add(BlockSkyrootDecorative.BASE_BEAM.getMeta(), "base_beam")
				.add(BlockSkyrootDecorative.TOP_PLANKS.getMeta(), "top_planks")
				.add(BlockSkyrootDecorative.TOP_BEAM.getMeta(), "top_beam")
				.add(BlockSkyrootDecorative.FLOORBOARDS.getMeta(), "floorboards")
				.add(BlockSkyrootDecorative.HIGHLIGHT.getMeta(), "highlight")
				.add(BlockSkyrootDecorative.TILES.getMeta(), "tiles")
				.add(BlockSkyrootDecorative.TILES_SMALL.getMeta(), "tiles_small"));

		registerItemModels(BlocksAether.dark_skyroot_beam, "dark_skyroot_planks/beam");

		registerItemModels(BlocksAether.light_skyroot_planks, "light_skyroot_planks/normal");

		registerItemModels(BlocksAether.light_skyroot_decorative, new ItemModelBuilder("light_skyroot_planks/")
				.add(BlockSkyrootDecorative.BASE_PLANKS.getMeta(), "base_planks")
				.add(BlockSkyrootDecorative.BASE_BEAM.getMeta(), "base_beam")
				.add(BlockSkyrootDecorative.TOP_PLANKS.getMeta(), "top_planks")
				.add(BlockSkyrootDecorative.TOP_BEAM.getMeta(), "top_beam")
				.add(BlockSkyrootDecorative.FLOORBOARDS.getMeta(), "floorboards")
				.add(BlockSkyrootDecorative.HIGHLIGHT.getMeta(), "highlight")
				.add(BlockSkyrootDecorative.TILES.getMeta(), "tiles")
				.add(BlockSkyrootDecorative.TILES_SMALL.getMeta(), "tiles_small"));

		registerItemModels(BlocksAether.light_skyroot_beam, "light_skyroot_planks/beam");

		registerItemModels(BlocksAether.aether_portal, "aether_portal");

		registerItemModels(BlocksAether.standing_skyroot_sign, "tesr");
		registerItemModels(BlocksAether.wall_skyroot_sign, "tesr");

		registerItemModels(BlocksAether.skyroot_bookshelf, "skyroot_bookshelf");

		registerItemModels(BlocksAether.holystone_bookshelf, "holystone_bookshelf");

		registerItemModels(BlocksAether.irradiated_dust_block, "irradiated_dust_block");

		registerItemModels(ItemsAether.skyroot_stick, "skyroot_stick");
		registerItemModels(ItemsAether.cloudtwine, "cloudtwine");
		registerItemModels(ItemsAether.golden_amber, "golden_amber");
		registerItemModels(ItemsAether.moa_feather, "moa_feather");
		registerItemModels(ItemsAether.cockatrice_feather, "cockatrice_feather");

		registerItemModels(ItemsAether.ambrosium_shard, "ambrosium_shard");
		registerItemModels(ItemsAether.ambrosium_chunk, "ambrosium_chunk");
		registerItemModels(ItemsAether.zanite_gemstone, "zanite_gemstone");
		registerItemModels(ItemsAether.arkenium, "arkenium");
		registerItemModels(ItemsAether.arkenium_strip, "arkenium_strip");
		registerItemModels(ItemsAether.gravitite_plate, "gravitite_plate");

		registerItemModels(ItemsAether.skyroot_pickaxe, "tools/skyroot_pickaxe");
		registerItemModels(ItemsAether.skyroot_axe, "tools/skyroot_axe");
		registerItemModels(ItemsAether.skyroot_shovel, "tools/skyroot_shovel");
		registerItemModels(ItemsAether.skyroot_sword, "weapons/skyroot_sword");

		registerItemModels(ItemsAether.holystone_pickaxe, "tools/holystone_pickaxe");
		registerItemModels(ItemsAether.holystone_axe, "tools/holystone_axe");
		registerItemModels(ItemsAether.holystone_shovel, "tools/holystone_shovel");
		registerItemModels(ItemsAether.holystone_sword, "weapons/holystone_sword");

		registerItemModels(ItemsAether.zanite_pickaxe, "tools/zanite_pickaxe");
		registerItemModels(ItemsAether.zanite_axe, "tools/zanite_axe");
		registerItemModels(ItemsAether.zanite_shovel, "tools/zanite_shovel");
		registerItemModels(ItemsAether.zanite_sword, "weapons/zanite_sword");

		registerItemModels(ItemsAether.gravitite_pickaxe, "tools/gravitite_pickaxe");
		registerItemModels(ItemsAether.gravitite_axe, "tools/gravitite_axe");
		registerItemModels(ItemsAether.gravitite_shovel, "tools/gravitite_shovel");
		registerItemModels(ItemsAether.gravitite_sword, "weapons/gravitite_sword");

		registerItemModels(ItemsAether.arkenium_pickaxe, "tools/arkenium_pickaxe");
		registerItemModels(ItemsAether.arkenium_axe, "tools/arkenium_axe");
		registerItemModels(ItemsAether.arkenium_shovel, "tools/arkenium_shovel");
		registerItemModels(ItemsAether.arkenium_sword, "weapons/arkenium_sword");
		registerItemModels(ItemsAether.arkenium_shears, "tools/arkenium_shears");

		registerItemModels(ItemsAether.taegore_hide_helmet, "armor/taegore_hide_helmet");
		registerItemModels(ItemsAether.taegore_hide_chestplate, "armor/taegore_hide_chestplate");
		registerItemModels(ItemsAether.taegore_hide_leggings, "armor/taegore_hide_leggings");
		registerItemModels(ItemsAether.taegore_hide_boots, "armor/taegore_hide_boots");

		registerItemModels(ItemsAether.burrukai_pelt_helmet, "armor/burrukai_pelt_helmet");
		registerItemModels(ItemsAether.burrukai_pelt_chestplate, "armor/burrukai_pelt_chestplate");
		registerItemModels(ItemsAether.burrukai_pelt_leggings, "armor/burrukai_pelt_leggings");
		registerItemModels(ItemsAether.burrukai_pelt_boots, "armor/burrukai_pelt_boots");

		registerItemModels(ItemsAether.zanite_helmet, "armor/zanite_helmet");
		registerItemModels(ItemsAether.zanite_chestplate, "armor/zanite_chestplate");
		registerItemModels(ItemsAether.zanite_leggings, "armor/zanite_leggings");
		registerItemModels(ItemsAether.zanite_boots, "armor/zanite_boots");

		registerItemModels(ItemsAether.arkenium_helmet, "armor/arkenium_helmet");
		registerItemModels(ItemsAether.arkenium_chestplate, "armor/arkenium_chestplate");
		registerItemModels(ItemsAether.arkenium_leggings, "armor/arkenium_leggings");
		registerItemModels(ItemsAether.arkenium_boots, "armor/arkenium_boots");

		registerItemModels(ItemsAether.gravitite_helmet, "armor/gravitite_helmet");
		registerItemModels(ItemsAether.gravitite_chestplate, "armor/gravitite_chestplate");
		registerItemModels(ItemsAether.gravitite_leggings, "armor/gravitite_leggings");
		registerItemModels(ItemsAether.gravitite_boots, "armor/gravitite_boots");

		registerItemModels(ItemsAether.aechor_petal, "aechor_petal");
		registerItemModels(ItemsAether.blueberries, "blueberries");
		registerItemModels(ItemsAether.enchanted_blueberry, "enchanted_blueberry");
		registerItemModels(ItemsAether.orange, "orange");
		registerItemModels(ItemsAether.healing_stone, "healing_stone/healing_stone");
		registerItemModels(ItemsAether.healing_stone_depleted, "healing_stone/healing_stone_depleted");
		registerItemModels(ItemsAether.wyndberry, "wyndberry");
		registerItemModels(ItemsAether.enchanted_wyndberry, "enchanted_wyndberry");
		registerItemModels(ItemsAether.candy_corn, "candy_corn");
		registerItemModels(ItemsAether.cocoatrice, "cocoatrice");
		registerItemModels(ItemsAether.wrapped_chocolates, "wrapped_chocolates");
		registerItemModels(ItemsAether.jelly_plumproot, "jelly_plumproot");
		registerItemModels(ItemsAether.stomper_pop, "stomper_pop");
		registerItemModels(ItemsAether.blueberry_lollipop, "blueberry_lollipop");
		registerItemModels(ItemsAether.orange_lollipop, "orange_lollipop");
		registerItemModels(ItemsAether.icestone_poprocks, "icestone_poprocks");
		registerItemModels(ItemsAether.ginger_bread_man, "ginger_bread_man");
		registerItemModels(ItemsAether.candy_cane, "candy_cane");
		registerItemModels(ItemsAether.yule_log, "yule_log");

		registerItemModels(ItemsAether.swet_sugar, "swet_sugar");

		registerItemModels(ItemsAether.swet_jelly, new ItemModelBuilder("swet_jelly/")
				.add(EntitySwet.Type.BLUE.ordinal(), "blue_swet_jelly")
				.add(EntitySwet.Type.GREEN.ordinal(), "green_swet_jelly")
				.add(EntitySwet.Type.PURPLE.ordinal(), "purple_swet_jelly"));

		registerItemModels(ItemsAether.swet_gel, new ItemModelBuilder("swet_gel/")
				.add(EntitySwet.Type.BLUE.ordinal(), "blue_swet_gel")
				.add(EntitySwet.Type.GREEN.ordinal(), "green_swet_gel")
				.add(EntitySwet.Type.PURPLE.ordinal(), "purple_swet_gel"));

		registerItemModels(ItemsAether.skyroot_bucket, "skyroot_bucket/skyroot_bucket");
		registerItemModels(ItemsAether.skyroot_water_bucket, "skyroot_bucket/skyroot_water_bucket");
		registerItemModels(ItemsAether.skyroot_milk_bucket, "skyroot_bucket/skyroot_milk_bucket");
		registerItemModels(ItemsAether.skyroot_poison_bucket, "skyroot_bucket/skyroot_poison_bucket");

		registerItemModels(ItemsAether.aerwhale_music_disc, "records/aerwhale_music_disc");
		registerItemModels(ItemsAether.labyrinth_music_disc, "records/labyrinth_music_disc");
		registerItemModels(ItemsAether.moa_music_disc, "records/moa_music_disc");
		registerItemModels(ItemsAether.valkyrie_music_disc, "records/valkyrie_music_disc");
		registerItemModels(ItemsAether.recording_892, "records/recording_892");

		registerItemModels(ItemsAether.dart_shooter, new ItemModelBuilder("dart_shooter/")
				.add(ItemDartType.GOLDEN.ordinal(), "golden_dart_shooter")
				.add(ItemDartType.ENCHANTED.ordinal(), "enchanted_dart_shooter")
				.add(ItemDartType.POISON.ordinal(), "poison_dart_shooter"));

		registerItemModels(ItemsAether.dart, new ItemModelBuilder("dart/")
				.add(ItemDartType.GOLDEN.ordinal(), "golden_dart")
				.add(ItemDartType.ENCHANTED.ordinal(), "enchanted_dart")
				.add(ItemDartType.POISON.ordinal(), "poison_dart"));

		registerItemModels(ItemsAether.skyroot_crossbow, "crossbow/skyroot_crossbow");
		registerItemModels(ItemsAether.holystone_crossbow, "crossbow/holystone_crossbow");
		registerItemModels(ItemsAether.zanite_crossbow, "crossbow/zanite_crossbow");
		registerItemModels(ItemsAether.arkenium_crossbow, "crossbow/arkenium_crossbow");
		registerItemModels(ItemsAether.gravitite_crossbow, "crossbow/gravitite_crossbow");

		registerItemModels(ItemsAether.bolt, "bolts/scatterglass_bolt");

		registerItemModels(ItemsAether.skyroot_door_item, "skyroot_door");
		registerItemModels(ItemsAether.secret_skyroot_door_item, "secret_skyroot_door");
		registerItemModels(ItemsAether.arkenium_door_item, "arkenium_door");

		registerItemModels(BlocksAether.skyroot_trapdoor, "skyroot_trapdoor");
		registerItemModels(BlocksAether.secret_skyroot_trapdoor, "secret_skyroot_trapdoor");

		registerItemModels(BlocksAether.skyroot_ladder, "ladders/skyroot_ladder");

		registerItemModels(BlocksAether.skyroot_pressure_plate, "skyroot_pressure_plate");
		registerItemModels(BlocksAether.skyroot_button, "skyroot_button");

		registerItemModels(BlocksAether.wisproot_pressure_plate, "wisproot_pressure_plate");
		registerItemModels(BlocksAether.wisproot_button, "wisproot_button");

		registerItemModels(BlocksAether.greatroot_pressure_plate, "greatroot_pressure_plate");
		registerItemModels(BlocksAether.greatroot_button, "greatroot_button");

		registerItemModels(BlocksAether.holystone_pressure_plate, "holystone_pressure_plate");
		registerItemModels(BlocksAether.holystone_button, "holystone_button");

		registerItemModels(BlocksAether.therawood_pressure_plate, "therawood_pressure_plate");
		registerItemModels(BlocksAether.therawood_button, "therawood_button");

		registerItemModels(BlocksAether.skyroot_chest, "skyroot_chest");
		registerItemModels(BlocksAether.ambrosium_torch, "ambrosium_torch");

		registerItemModels(ItemsAether.icestone, "icestone");

		registerItemModels(ItemsAether.skyroot_sign, "skyroot_sign");

		registerItemModels(BlocksAether.holystone_wall, "aether_wall/holystone_wall");
		registerItemModels(BlocksAether.mossy_holystone_wall, "aether_wall/mossy_holystone_wall");
		registerItemModels(BlocksAether.holystone_brick_wall, "aether_wall/holystone_brick_wall");
		registerItemModels(BlocksAether.skyroot_log_wall, "aether_wall/skyroot_log_wall");
		registerItemModels(BlocksAether.icestone_wall, "aether_wall/icestone_wall");
		registerItemModels(BlocksAether.scatterglass_wall, "aether_wall/scatterglass_wall");
		registerItemModels(BlocksAether.wisproot_log_wall, "aether_wall/wisproot_log_wall");
		registerItemModels(BlocksAether.greatroot_log_wall, "aether_wall/greatroot_log_wall");
		registerItemModels(BlocksAether.therawood_log_wall, "aether_wall/therawood_log_wall");
		registerItemModels(BlocksAether.agiosite_wall, "aether_wall/agiosite_wall");
		registerItemModels(BlocksAether.agiosite_brick_wall, "aether_wall/agiosite_brick_wall");
		registerItemModels(BlocksAether.faded_holystone_brick_wall, "aether_wall/faded_holystone_brick_wall");
		registerItemModels(BlocksAether.sentrystone_brick_wall, "aether_wall/sentrystone_brick_wall");
		registerItemModels(BlocksAether.hellfirestone_brick_wall, "aether_wall/hellfirestone_brick_wall");
		registerItemModels(BlocksAether.therastone_brick_wall, "aether_wall/therastone_brick_wall");

		registerItemModels(BlocksAether.altar, "tesr");
		registerItemModels(BlocksAether.multiblock_dummy, "tesr");
		registerItemModels(BlocksAether.multiblock_dummy_half, "tesr");
		registerItemModels(BlocksAether.present, "tesr");
		registerItemModels(BlocksAether.icestone_cooler, "tesr");
		registerItemModels(BlocksAether.masonry_bench, "tesr");
		registerItemModels(BlocksAether.outpost_campfire, "tesr");
		registerItemModels(BlocksAether.aether_teleporter, "aether_teleporter");

		registerItemModels(ItemsAether.taegore_hide_gloves, "accessories/taegore_hide_gloves");
		registerItemModels(ItemsAether.burrukai_pelt_gloves, "accessories/burrukai_pelt_gloves");
		registerItemModels(ItemsAether.zanite_gloves, "accessories/zanite_gloves");
		registerItemModels(ItemsAether.arkenium_gloves, "accessories/arkenium_gloves");
		registerItemModels(ItemsAether.gravitite_gloves, "accessories/gravitite_gloves");

		registerItemModels(ItemsAether.shard_of_life, "miscellaneous/shard_of_life");

		registerItemModels(ItemsAether.aether_saddle, "aether_saddle");

		registerItemModels(ItemsAether.skyroot_shield, "skyroot_shield");
		registerItemModels(ItemsAether.holystone_shield, "holystone_shield");
		registerItemModels(ItemsAether.zanite_shield, "zanite_shield");
		registerItemModels(ItemsAether.arkenium_shield, "arkenium_shield");
		registerItemModels(ItemsAether.gravitite_shield, "gravitite_shield");

		registerItemModels(BlocksAether.skyroot_slab, "aether_slab/skyroot_slab");
		registerItemModels(BlocksAether.wisproot_slab, "aether_slab/wisproot_slab");
		registerItemModels(BlocksAether.greatroot_slab, "aether_slab/greatroot_slab");
		registerItemModels(BlocksAether.therawood_slab, "aether_slab/therawood_slab");
		registerItemModels(BlocksAether.holystone_slab, "aether_slab/holystone_slab");
		registerItemModels(BlocksAether.therastone_brick_slab, "aether_slab/therastone_brick_slab");
		registerItemModels(BlocksAether.mossy_holystone_slab, "aether_slab/mossy_holystone_slab");
		registerItemModels(BlocksAether.holystone_brick_slab, "aether_slab/holystone_brick_slab");
		registerItemModels(BlocksAether.faded_holystone_brick_slab, "aether_slab/faded_holystone_brick_slab");
		registerItemModels(BlocksAether.icestone_slab, "aether_slab/icestone_slab");
		registerItemModels(BlocksAether.agiosite_slab, "aether_slab/agiosite_slab");
		registerItemModels(BlocksAether.agiosite_brick_slab, "aether_slab/agiosite_brick_slab");
		registerItemModels(BlocksAether.scatterglass_slab, "aether_slab/scatterglass_slab");
		registerItemModels(BlocksAether.sentrystone_brick_slab, "aether_slab/sentrystone_brick_slab");
		registerItemModels(BlocksAether.hellfirestone_brick_slab, "aether_slab/hellfirestone_brick_slab");

		registerItemModels(BlocksAether.skyroot_stairs, "aether_stairs/skyroot_stairs");
		registerItemModels(BlocksAether.wisproot_stairs, "aether_stairs/wisproot_stairs");
		registerItemModels(BlocksAether.greatroot_stairs, "aether_stairs/greatroot_stairs");
		registerItemModels(BlocksAether.therawood_stairs, "aether_stairs/therawood_stairs");
		registerItemModels(BlocksAether.holystone_stairs, "aether_stairs/holystone_stairs");
		registerItemModels(BlocksAether.therastone_brick_stairs, "aether_stairs/therastone_brick_stairs");
		registerItemModels(BlocksAether.mossy_holystone_stairs, "aether_stairs/mossy_holystone_stairs");
		registerItemModels(BlocksAether.holystone_brick_stairs, "aether_stairs/holystone_brick_stairs");
		registerItemModels(BlocksAether.icestone_brick_stairs, "aether_stairs/icestone_brick_stairs");
		registerItemModels(BlocksAether.scatterglass_stairs, "aether_stairs/scatterglass_stairs");
		registerItemModels(BlocksAether.faded_holystone_brick_stairs, "aether_stairs/faded_holystone_brick_stairs");
		registerItemModels(BlocksAether.agiosite_stairs, "aether_stairs/agiosite_stairs");
		registerItemModels(BlocksAether.agiosite_brick_stairs, "aether_stairs/agiosite_brick_stairs");
		registerItemModels(BlocksAether.sentrystone_brick_stairs, "aether_stairs/sentrystone_brick_stairs");
		registerItemModels(BlocksAether.hellfirestone_brick_stairs, "aether_stairs/hellfirestone_brick_stairs");

		registerItemModels(BlocksAether.highlands_snow, "highlands_snow");
		registerItemModels(BlocksAether.highlands_snow_layer, "highlands_snow_height2");
		registerItemModels(BlocksAether.highlands_ice, "highlands_ice");
		registerItemModels(BlocksAether.highlands_packed_ice, "highlands_packed_ice");

		registerItemModels(BlocksAether.woven_sticks, new ItemModelBuilder("woven_sticks/")
				.add(BlockWovenSticks.SKYROOT.getMeta(), "woven_skyroot_sticks"));

		registerItemModels(ItemsAether.moa_egg_item, "moa_egg/moa_egg");
		registerItemModels(ItemsAether.rainbow_moa_egg, "rainbow_moa_egg");

		registerItemModels(BlocksAether.moa_egg, "moa_egg/moa_egg");

		registerItemModels(ItemsAether.cloud_parachute, new ItemModelBuilder("cloud_parachute/")
				.add(EntityParachute.Type.COLD.ordinal(), "cold_cloud_parachute")
				.add(EntityParachute.Type.PURPLE.ordinal(), "purple_cloud_parachute")
				.add(EntityParachute.Type.BLUE.ordinal(), "blue_cloud_parachute"));

		registerItemModels(ItemsAether.irradiated_chunk, "irradiated_chunk");
		registerItemModels(ItemsAether.irradiated_sword, "irradiated_sword");
		registerItemModels(ItemsAether.irradiated_armor, "irradiated_armor");
		registerItemModels(ItemsAether.irradiated_tool, "irradiated_tool");
		registerItemModels(ItemsAether.irradiated_ring, "irradiated_ring");
		registerItemModels(ItemsAether.irradiated_neckwear, "irradiated_neckwear");
		registerItemModels(ItemsAether.irradiated_charm, "irradiated_charm");

		registerItemModels(ItemsAether.irradiated_dust, "irradiated_dust");

		registerItemModels(BlocksAether.incubator, new ItemModelBuilder("incubator/")
				.add(BlockIncubator.LIT_META, "lit")
				.add(BlockIncubator.UNLIT_META, "unlit"));

		registerItemModels(ItemsAether.wrapping_paper, "wrapping_paper");
		registerItemModels(ItemsAether.fried_moa_egg, "fried_moa_egg");

		registerItemModels(ItemsAether.raw_taegore_meat, "raw_taegore_meat");
		registerItemModels(ItemsAether.taegore_steak, "taegore_steak");
		registerItemModels(ItemsAether.taegore_hide, "taegore_hide");
		registerItemModels(ItemsAether.burrukai_rib_cut, "burrukai_rib_cut");
		registerItemModels(ItemsAether.burrukai_ribs, "burrukai_ribs");
		registerItemModels(ItemsAether.burrukai_pelt, "burrukai_pelt");
		registerItemModels(ItemsAether.kirrid_loin, "kirrid_loin");
		registerItemModels(ItemsAether.kirrid_cutlet, "kirrid_cutlet");
		registerItemModels(ItemsAether.valkyrie_wings, "valkyrie_wings");

		registerItemModels(BlocksAether.highlands_ice_crystal, "crystals/highlands_ice_crystal");
		registerItemModels(BlocksAether.candy_cane_block, "candy_cane_block");
		registerItemModels(BlocksAether.candy_cane_wall, "aether_wall/candy_cane_wall");
		registerItemModels(ItemsAether.winter_hat, "miscellaneous/winter_hat");
		registerItemModels(ItemsAether.eggnog, "eggnog");

		registerItemModels(ItemsAether.skyroot_lizard_stick, "skyroot_lizard_stick");
		registerItemModels(ItemsAether.skyroot_lizard_stick_roasted, "skyroot_lizard_stick_roasted");

		registerItemModels(ItemsAether.scatterglass_vial, "scatterglass_vial");
		registerItemModels(ItemsAether.water_vial, "scatterglass_water_vial");
		registerItemModels(ItemsAether.antivenom_vial, "scatterglass_antivenom_vial");
		registerItemModels(ItemsAether.antitoxin_vial, "scatterglass_antitoxin_vial");
		registerItemModels(ItemsAether.bandage, "bandage");
		registerItemModels(ItemsAether.splint, "splint");

		registerItemModels(ItemsAether.crude_scatterglass_shard, "crude_scatterglass_shard");

		registerItemModels(ItemsAether.valkyrie_tea, "tea/valkyrie_tea");

		registerItemModels(ItemsAether.skyroot_pinecone, "skyroot_pinecone");
		registerItemModels(ItemsAether.moa_feed, "feed/moa_feed");
		registerItemModels(ItemsAether.moa_feed_blueberries, "feed/moa_feed_blueberries");
		registerItemModels(ItemsAether.moa_feed_enchanted_blueberries, "feed/moa_feed_enchanted_blueberries");

		registerItemModels(ItemsAether.brettl_rope, "brettl_rope");

		//registerItemModels(ItemsAether.charm_arm_01, "accessories/charms/charm_arm_01");
		//registerItemModels(ItemsAether.charm_arm_02, "accessories/charms/charm_arm_02");
		//registerItemModels(ItemsAether.charm_arm_tgh_01, "accessories/charms/charm_arm_tgh_01");
		//registerItemModels(ItemsAether.charm_arm_tgh_02, "accessories/charms/charm_arm_tgh_02");
		//registerItemModels(ItemsAether.charm_imp_dmg_01, "accessories/charms/charm_imp_dmg_01");
		//registerItemModels(ItemsAether.charm_imp_dmg_02, "accessories/charms/charm_imp_dmg_02");
		//registerItemModels(ItemsAether.charm_atk_spd_01, "accessories/charms/charm_atk_spd_01");
		//registerItemModels(ItemsAether.charm_atk_spd_02, "accessories/charms/charm_atk_spd_02");
		//registerItemModels(ItemsAether.charm_kbk_res_01, "accessories/charms/charm_kbk_res_01");
		//registerItemModels(ItemsAether.charm_kbk_res_02, "accessories/charms/charm_kbk_res_02");
		//registerItemModels(ItemsAether.charm_lck_01, "accessories/charms/charm_lck_01");
		//registerItemModels(ItemsAether.charm_lck_02, "accessories/charms/charm_lck_02");
		registerItemModels(ItemsAether.charm_max_hlt_01, "accessories/charms/charm_max_hlt_01");
		//registerItemModels(ItemsAether.charm_max_hlt_02, "accessories/charms/charm_max_hlt_02");
		registerItemModels(ItemsAether.charm_mve_spd_01, "accessories/charms/charm_mve_spd_01");
		registerItemModels(ItemsAether.charm_mve_spd_02, "accessories/charms/charm_mve_spd_02");
		//registerItemModels(ItemsAether.charm_prc_dmg_01, "accessories/charms/charm_prc_dmg_01");
		//registerItemModels(ItemsAether.charm_prc_dmg_02, "accessories/charms/charm_prc_dmg_02");
		//registerItemModels(ItemsAether.charm_slsh_dmg_01, "accessories/charms/charm_slsh_dmg_01");
		//registerItemModels(ItemsAether.charm_slsh_dmg_02, "accessories/charms/charm_slsh_dmg_02");
		registerItemModels(ItemsAether.charm_res_amb_01, "accessories/charms/charm_res_amb_01");
		registerItemModels(ItemsAether.charm_res_amb_02, "accessories/charms/charm_res_amb_02");
		registerItemModels(ItemsAether.charm_res_bld_01, "accessories/charms/charm_res_bld_01");
		registerItemModels(ItemsAether.charm_res_bld_02, "accessories/charms/charm_res_bld_02");
		registerItemModels(ItemsAether.charm_res_fra_01, "accessories/charms/charm_res_fra_01");
		registerItemModels(ItemsAether.charm_res_fra_02, "accessories/charms/charm_res_fra_02");
		registerItemModels(ItemsAether.charm_res_frz_01, "accessories/charms/charm_res_frz_01");
		registerItemModels(ItemsAether.charm_res_frz_02, "accessories/charms/charm_res_frz_02");
		registerItemModels(ItemsAether.charm_res_grd_brk_01, "accessories/charms/charm_res_grd_brk_01");
		registerItemModels(ItemsAether.charm_res_grd_brk_02, "accessories/charms/charm_res_grd_brk_02");
		registerItemModels(ItemsAether.charm_res_irr_01, "accessories/charms/charm_res_irr_01");
		registerItemModels(ItemsAether.charm_res_irr_02, "accessories/charms/charm_res_irr_02");
		registerItemModels(ItemsAether.charm_res_stn_01, "accessories/charms/charm_res_stn_01");
		registerItemModels(ItemsAether.charm_res_stn_02, "accessories/charms/charm_res_stn_02");
		registerItemModels(ItemsAether.charm_res_tox_01,"accessories/charms/charm_res_tox_01");
		registerItemModels(ItemsAether.charm_res_tox_02,"accessories/charms/charm_res_tox_02");
		registerItemModels(ItemsAether.charm_res_ven_01, "accessories/charms/charm_res_ven_01");
		registerItemModels(ItemsAether.charm_res_ven_02, "accessories/charms/charm_res_ven_02");
		registerItemModels(ItemsAether.charm_res_web_01, "accessories/charms/charm_res_web_01");
		registerItemModels(ItemsAether.charm_res_web_02, "accessories/charms/charm_res_web_02");
	}

	private static void registerSnowyFlower(Block block, String name)
	{
		registerItemModels(block, new ItemModelBuilder("flowers/")
				.add(BlockAetherFlowerBase.NORMAL_META, name)
				.add(BlockAetherFlowerBase.SNOWY_META, name + "_snowy"));
	}

	private static void registerItemModels(final Block block, final String path)
	{
		registerItemModels(getItem(block), path);
	}

	private static void registerItemModels(final Item item, final String path)
	{
		final ModelResourceLocation resource = new ModelResourceLocation(AetherCore.getResourcePath(path), "inventory");

		ModelLoader.setCustomModelResourceLocation(item, 0, resource);
	}

	private static void registerItemModels(final Block block, final ItemModelBuilder builder)
	{
		registerItemModels(getItem(block), builder);
	}

	private static void registerItemModels(final Item item, final ItemModelBuilder builder)
	{
		for (final Map.Entry<Integer, ModelResourceLocation> entry : builder.getRegistrations().entrySet())
		{
			ModelLoader.setCustomModelResourceLocation(item, entry.getKey(), entry.getValue());
		}
	}

	/**
	 * Shorthand utility method for Item.getItemFromBlock(block).
	 */
	private static Item getItem(final Block block)
	{
		return Item.getItemFromBlock(block);
	}
}
