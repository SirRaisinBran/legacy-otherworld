package net.sirraisinbran.legacy.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.block.LegacyBlocks;

public class LegacyBlockEntities {

    public static BlockEntityType<BloomeryBlockEntity> BLOOMERY_BLOCK_ENTITY;
    public static BlockEntityType<WorkbenchBlockEntity> WORKBENCH_BLOCK_ENTITY;
    public static BlockEntityType<IncantationBlockEntity> INCANTATION_BLOCK_ENTITY;
    public static BlockEntityType<ScarletBrainEntity> SCARLET_BRAIN_BLOCK_ENTITY;

    public static void registerAllEntities() {
        BLOOMERY_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(LegacyMod.MOD_ID, "bloomery"),
                FabricBlockEntityTypeBuilder.create(BloomeryBlockEntity::new,
                        LegacyBlocks.BLOOMERY).build(null));
        WORKBENCH_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(LegacyMod.MOD_ID, "workbench"),
                FabricBlockEntityTypeBuilder.create(WorkbenchBlockEntity::new,
                        LegacyBlocks.WORKBENCH).build(null));
        INCANTATION_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(LegacyMod.MOD_ID, "incantation_table"),
                FabricBlockEntityTypeBuilder.create(IncantationBlockEntity::new,
                        LegacyBlocks.INCANTATION_BENCH).build(null));
        SCARLET_BRAIN_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(LegacyMod.MOD_ID, "scarlet_brain"),
                FabricBlockEntityTypeBuilder.create(ScarletBrainEntity::new,
                        LegacyBlocks.SCARLET_BRAIN).build(null));
    }
}
