package net.sirraisinbran.legacy.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.entity.custom.*;

import static net.sirraisinbran.legacy.LegacyMod.MOD_ID;

public class LegacyEntities {

    public static final EntityType<AurochEntity> AUROCH_ENTITY_TYPE = Registry.register(Registry.ENTITY_TYPE, new Identifier(MOD_ID, "auroch"),
            FabricEntityTypeBuilder.<AurochEntity>createMob().spawnGroup(SpawnGroup.CREATURE).entityFactory(AurochEntity::new)
                    .dimensions(EntityDimensions.fixed(1.6f, 1.6f)).defaultAttributes(AurochEntity::createAurochAttributes).build());

    public static final EntityType<ClamorEntity> CLAMOR_ENTITY_TYPE = Registry.register(Registry.ENTITY_TYPE, new Identifier(MOD_ID, "clamor"),
            FabricEntityTypeBuilder.<ClamorEntity>createMob().spawnGroup(SpawnGroup.CREATURE).entityFactory(ClamorEntity::new)
                    .dimensions(EntityDimensions.fixed(.75f, .75f)).defaultAttributes(ClamorEntity::createClamorAttributes).build());

    public static final EntityType<CraulerEntity> CRAULER_ENTITY_TYPE = Registry.register(Registry.ENTITY_TYPE, new Identifier(MOD_ID, "crauler"),
            FabricEntityTypeBuilder.<CraulerEntity>createMob().spawnGroup(SpawnGroup.CREATURE).entityFactory(CraulerEntity::new)
                    .dimensions(EntityDimensions.fixed(.75f, .75f)).defaultAttributes(CraulerEntity::createCraulerAttributes).build());

    public static final EntityType<DarterEntity> DARTER_ENTITY_TYPE = Registry.register(Registry.ENTITY_TYPE, new Identifier(MOD_ID, "darter"),
            FabricEntityTypeBuilder.<DarterEntity>createMob().spawnGroup(SpawnGroup.CREATURE).entityFactory(DarterEntity::new)
                    .dimensions(EntityDimensions.fixed(.75f, .75f)).defaultAttributes(DarterEntity::createDarterAttributes).build());

    public static final EntityType<DodoEntity> DODO_ENTITY_TYPE = Registry.register(Registry.ENTITY_TYPE, new Identifier(MOD_ID, "dodo"),
            FabricEntityTypeBuilder.<DodoEntity>createMob().spawnGroup(SpawnGroup.CREATURE).entityFactory(DodoEntity::new)
                    .dimensions(EntityDimensions.fixed(.75f, .75f)).defaultAttributes(DodoEntity::createDodoAttributes).build());

    public static final EntityType<GlareEntity> GLARE_ENTITY_TYPE = Registry.register(Registry.ENTITY_TYPE, new Identifier(MOD_ID, "glare"),
            FabricEntityTypeBuilder.<GlareEntity>createMob().spawnGroup(SpawnGroup.CREATURE).entityFactory(GlareEntity::new)
                    .dimensions(EntityDimensions.fixed(.75f, .75f)).defaultAttributes(GlareEntity::createGlareAttributes).build());

    public static final EntityType<ShroudEntity> SHROUD_ENTITY_TYPE = Registry.register(Registry.ENTITY_TYPE, new Identifier(MOD_ID, "shroud"),
            FabricEntityTypeBuilder.<ShroudEntity>createMob().spawnGroup(SpawnGroup.CREATURE).entityFactory(ShroudEntity::new)
                    .dimensions(EntityDimensions.fixed(.75f, .75f)).defaultAttributes(ShroudEntity::createShroudAttributes).build());

    public static final EntityType<SporeEntity> SPORE_ENTITY_TYPE = Registry.register(Registry.ENTITY_TYPE, new Identifier(MOD_ID, "spore"),
            FabricEntityTypeBuilder.<SporeEntity>createMob().spawnGroup(SpawnGroup.AMBIENT).entityFactory(SporeEntity::new)
                    .dimensions(EntityDimensions.fixed(.1f, .1f)).defaultAttributes(SporeEntity::createSporeAttributes).build());



}
