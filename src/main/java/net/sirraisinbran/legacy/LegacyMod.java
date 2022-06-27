package net.sirraisinbran.legacy;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.kyrptonaught.customportalapi.util.CPASoundEventData;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.command.CommandException;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.TeleportTarget;import net.minecraft.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.sirraisinbran.legacy.block.LegacyBlocks;
import net.sirraisinbran.legacy.block.entity.LegacyBlockEntities;
import net.sirraisinbran.legacy.item.LegacyItems;
import net.sirraisinbran.legacy.recipe.LegacyRecipes;
import net.sirraisinbran.legacy.screen.LegacyScreenHandlers;
import net.sirraisinbran.legacy.util.LegacyFlattenables;
import net.sirraisinbran.legacy.util.LegacyHoeables;
import net.sirraisinbran.legacy.util.LegacyRegistries;
import net.sirraisinbran.legacy.world.OtherworldFeatures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib3.GeckoLib;

public class LegacyMod implements ModInitializer {

	// MOD ID
	public static final String MOD_ID = "legacy";
	public static final Logger LOGGER = LoggerFactory.getLogger("MOD_ID");
	public static Identifier locate(String location) {
		if (location.contains(":")) {
			return new Identifier(location);
		} else {
			return new Identifier(MOD_ID, location);
		}
	}


	// Dimension (When doing cave biomes, make sure their max y-value is 32. Above 32 caves generate with water, below generates without.
	private static final RegistryKey<DimensionOptions> DIMENSION_KEY = RegistryKey.of(Registry.DIMENSION_KEY,
			new Identifier(MOD_ID, "otherworld"));
	private static RegistryKey<World> OTHERWORLD_KEY = RegistryKey.of(Registry.WORLD_KEY,
			DIMENSION_KEY.getValue());
	private static final RegistryKey<DimensionType> OTHERWORLD_TYPE_KEY = RegistryKey.of(Registry.DIMENSION_TYPE_KEY,
			new Identifier(MOD_ID, "otherworld_type"));


	// Tags
	public static final TagKey<Block> ORGANIC = TagKey.of(Registry.BLOCK_KEY, new Identifier("legacy", "organic"));
	public static final TagKey<Block> OW_FOLIAGE = TagKey.of(Registry.BLOCK_KEY, new Identifier("legacy", "ow_foliage"));
	public static final TagKey<Block> SOIL = TagKey.of(Registry.BLOCK_KEY, new Identifier("legacy", "soil"));
	public static final TagKey<Block> SCARLET = TagKey.of(Registry.BLOCK_KEY, new Identifier("legacy", "scarlet"));
	public static final TagKey<Block> SCARLET_MUSHROOMS = TagKey.of(Registry.BLOCK_KEY, new Identifier("legacy", "scarlet_mushrooms"));
	public static final TagKey<Block> DEAD_LUSH_BLOCKS = TagKey.of(Registry.BLOCK_KEY, new Identifier("legacy", "dead_lush_blocks"));
	public static final TagKey<Item> ESCENCE = TagKey.of(Registry.ITEM_KEY, new Identifier("legacy", "escence"));
	public static final TagKey<EntityType<?>> QUICKSAND_WALKABLE_MOBS = TagKey.of(Registry.ENTITY_TYPE_KEY, new Identifier("legacy", "quicksand_walkable_mobs"));

	// Configured Features


	@Override
	public void onInitialize() {

		// Init
		GeckoLib.initialize();
		LegacyRegistries.registerModStuffs();
		LegacyBlocks.registerModBlocks();
		LegacyItems.registerModItems();
		LegacyBlockEntities.registerAllEntities();
		LegacyScreenHandlers.registerScreenhandlers();
		LegacyHoeables.tillablesLegacy();
		LegacyFlattenables.addFlattenables();
		OtherworldFeatures.init();
		LegacyRecipes.registerRecipes();



		// Opaque/Transparent Block Textures
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.LUSH_SOIL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.LUSH_GABBRO, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.CYAN_MUSHROOM, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.CYAN_MUSHROOM_WALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.CYAN_MUSHROOM_CEILING, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.SCARLET_BLOCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.SCARLET_BRAIN, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.SCARLET_MUSHROOM, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.SCARLET_MUSHROOM_WALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.SCARLET_MUSHROOM_CEILING, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.SCARLET_PUFFBALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.WHITE_MUSHROOM, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.WHITE_MUSHROOM_WALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.WHITE_MUSHROOM_CEILING, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.EINKORN, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.ZOYSIA, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.TALL_ZOYSIA, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.CACTI, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.SAGUARO, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.OUROBOROS, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.QUICKSAND, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.CLADOXY_LOG, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.STRIPPED_CLADOXY_LOG, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.CYAN_GATEWAY, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.LARGE_EGG, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.GROWING_PEAR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.SOURBREAD_LOAF, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.SOURBREAD_STARTER, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.SOURBREAD_STARTER, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.RUBY_QUARTZ, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.ARAUCARIA_DOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.ARAUCARIA_DOOR, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.ARAUCARIA_TRAPDOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.ARAUCARIA_TRAPDOOR, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.KOKIA_DOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.KOKIA_DOOR, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.KOKIA_TRAPDOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.KOKIA_TRAPDOOR, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.CLADOXY_DOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.CLADOXY_TRAPDOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.GABBRO_WALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.GABBRO_BRICK_WALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.GRIMSTONE_WALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.GRIMSTONE_BRICK_WALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.REDSPOT_SANDSTONE_WALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.SMOOTH_REDSPOT_WALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.CYANESCEN_STEM, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.PORTOBELLO_STEM, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LegacyBlocks.CYANESCEN_BLOCK, RenderLayer.getTranslucent());

		// Otherworld Portal
		CustomPortalBuilder.beginPortal()
				.frameBlock(LegacyBlocks.CHISELED_REDSPOT)
				.forcedSize(5, 3)
				.customPortalBlock(LegacyBlocks.CYAN_GATEWAY)
				.destDimID(new Identifier(MOD_ID, "otherworld"))
				.tintColor(55, 89, 195)
				.lightWithItem(LegacyItems.ESCENCE)
				.onlyLightInOverworld()
				.registerInPortalAmbienceSound(player -> new CPASoundEventData(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP, player.getRandom().nextFloat() * 0.4F + 0.8F, 0.25F))
				.registerPostTPPortalAmbience(player -> new CPASoundEventData(SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, player.getRandom().nextFloat() * 0.4F + 0.8F, 0.25F))
				.registerPortal();

	}

}