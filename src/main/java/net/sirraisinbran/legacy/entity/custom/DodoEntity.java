package net.sirraisinbran.legacy.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TurtleEggBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sirraisinbran.legacy.block.LegacyBlocks;
import net.sirraisinbran.legacy.entity.LegacyEntities;
import net.sirraisinbran.legacy.item.LegacyItems;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

public class DodoEntity extends AnimalEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    public int featherDropTime;
    public int eggLayTime;

    public DodoEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.featherDropTime = this.random.nextInt(6000) + 6000;
        this.eggLayTime = this.random.nextInt(10000) + 10000;
        this.ignoreCameraFrustum = true;
    }

    public static DefaultAttributeContainer.Builder createDodoAttributes() {
        return DodoEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 7)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.65D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D);
    }

    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new TemptGoal(this, 1.2f, Ingredient.ofItems(new ItemConvertible[]{LegacyItems.PEAR}), false));
        this.goalSelector.add(1, new WanderAroundGoal(this, 1f, 2));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.2f));
        this.goalSelector.add(2, new WanderAroundPointOfInterestGoal(this, 1f, false));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.75f, 1));
        this.goalSelector.add(4, new LookAroundGoal(this));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
    }

    public void tickMovement() {
        super.tickMovement();
        if (!this.world.isClient && this.isAlive() && !isBaby() && --this.featherDropTime <= 0) {
            this.playSound(SoundEvents.ENTITY_CHICKEN_AMBIENT, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.dropItem(LegacyItems.DODO_FEATHER);
            this.featherDropTime = this.random.nextInt(6000) + 6000;
        }
        this.layEgg();
    }

    public void layEgg(){
        BlockPos blockPos = this.getBlockPos();
        if (!this.world.isClient && this.isAlive() && !isBaby() && --this.eggLayTime <= 0) {
            this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);

            this.setMovementSpeed(0.01F);
            World world = this.world;
            world.playSound((PlayerEntity)null, blockPos, SoundEvents.ENTITY_TURTLE_LAY_EGG, SoundCategory.BLOCKS, 0.3F, 0.9F + world.random.nextFloat() * 0.2F);
            world.setBlockState(blockPos, (BlockState) LegacyBlocks.LARGE_EGG.getDefaultState());


            this.eggLayTime = this.random.nextInt(10000) + 10000;
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_DOLPHIN_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_DOLPHIN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_DOLPHIN_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15f, 1.0f);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {

        return LegacyEntities.DODO_ENTITY_TYPE.create(world);
    }

    @Override
    public void setBaby(boolean baby) {
        super.setBaby(baby);
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    // ANIMATIONS
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.dodo.walking", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.dodo.idle", false));
        return PlayState.CONTINUE;
    }

    /*
    private static class LayEggGoal extends MoveToTargetPosGoal {
        private final DodoEntity dodo;
        LayEggGoal(DodoEntity dodo, double speed) {
            super(dodo, speed, 16);
            this.dodo = dodo;
        }
    }
    */
}