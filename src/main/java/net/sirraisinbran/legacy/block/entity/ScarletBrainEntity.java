package net.sirraisinbran.legacy.block.entity;

import com.google.common.collect.Lists;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sirraisinbran.legacy.screen.IncantationScreenHandler;
import net.sirraisinbran.legacy.screen.ScarletBrainScreenHandler;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ScarletBrainEntity extends BlockEntity implements NamedScreenHandlerFactory {

    public int availableSpread;
    public int totalShmoo;
    public int totalOuter;
    public int totalEdge;
    public int totalCenter;
    public int spreadTime;
    public boolean canSpread;
    public boolean newBrain;
    Random random = new Random();

    //private final List<BlockPos> blobBlocks;

    public ScarletBrainEntity(BlockPos pos, BlockState state) {
        super(LegacyBlockEntities.SCARLET_BRAIN_BLOCK_ENTITY, pos, state);
        availableSpread = 18;
        totalShmoo = 0;
        totalOuter = 0;
        totalEdge = 0;
        totalCenter = 0;
        canSpread = false;
        newBrain = false;
        spreadTime = random.nextInt(1) + 1;

        //blobBlocks = Lists.newArrayList();
    }

    public static void tick(World world, BlockPos pos, BlockState state, ScarletBrainEntity be) {
        if(--be.spreadTime <= 0 && be.availableSpread > 0) {
            be.canSpread = true;
            be.spreadTime = be.random.nextInt(1) + 1;
        } else {
            be.canSpread = false;
        }
        if(be.availableSpread <=1 ){
            be.newBrain = true;
        }
        //List<BlockPos> list = be.blobBlocks;
    }

    public void updateSpread(World world, BlockPos pos, ScarletBrainEntity be) {
        be.availableSpread = be.availableSpread - 1;
    }
    public void decreaseSpread(World world, BlockPos pos, ScarletBrainEntity be) {
        be.availableSpread = be.availableSpread + 1;
    }
    public void updateTotalShmoo(World world, BlockPos pos, ScarletBrainEntity be) {
        be.totalShmoo = be.totalShmoo + 1;
    }
    public void updateTotalOuter(World world, BlockPos pos, ScarletBrainEntity be) {
        be.totalOuter = be.totalOuter + 1;
    }
    public void updateTotalEdge(World world, BlockPos pos, ScarletBrainEntity be) {
        be.totalEdge = be.totalEdge + 1;
    }
    public void updateTotalCenter(World world, BlockPos pos, ScarletBrainEntity be) {
        be.totalCenter = be.totalCenter + 1;
    }

    @Override
    public void writeNbt(NbtCompound tag) {
        // Save the current value of the number to the tag
        tag.putInt("availableSpread", availableSpread);
        tag.putInt("totalShmoo", totalShmoo);
        tag.putInt("totalOuter", totalOuter);
        tag.putInt("totalEdge", totalEdge);
        tag.putInt("totalCenter", totalCenter);
        tag.putInt("spreadTime", spreadTime);
        tag.putInt("availableSpread", availableSpread);
        tag.putBoolean("canSpread", canSpread);

        super.writeNbt(tag);
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);

        availableSpread = tag.getInt("availableSpread");
        totalShmoo = tag.getInt("totalShmoo");
        totalOuter = tag.getInt("totalOuter");
        totalEdge = tag.getInt("totalEdge");
        totalCenter = tag.getInt("totalCenter");
        spreadTime = tag.getInt("spreadTime");
        availableSpread = tag.getInt("availableSpread");
        canSpread = tag.getBoolean("canSpread");
    }

    @Override
    public Text getDisplayName() {
        return new LiteralText("Scarlet Brain");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new ScarletBrainScreenHandler(syncId, inv);
    }
}
