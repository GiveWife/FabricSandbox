package net.givewife.additions.objects.blockentity.netherreactor;

import net.givewife.additions.Main;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class NetherReactorEntity extends BlockEntity {

    private String ACTIVATED = "nether_reactor_active";
    private String TICKS = "nether_reactor_ticks";
    private boolean isActive = false;
    private int ticks = 0;

    public NetherReactorEntity(BlockPos pos, BlockState state) {
        super(Main.TILE_ENTITIES.NETHER_REACTOR_ENTITY, pos, state);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        nbt.putBoolean(ACTIVATED, isActive);
        nbt.putInt(TICKS, ticks);
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.isActive = nbt.getBoolean(ACTIVATED);
        this.ticks = nbt.getInt(TICKS);
    }

    public void activate() {
        if(!isActive) {
            this.isActive = true;

            // Synch Data?
            toUpdatePacket();
            world.updateListeners(pos, world.getBlockState(pos), world.getBlockState(pos), Block.NOTIFY_LISTENERS);
        }
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    public boolean isActive() {
        return this.isActive;
    }

    public int getTicks() {
        return this.ticks;
    }

    public void increment() {
        if(!isActive)
            return;
        this.ticks++;
        update();
    }

    public void update() {
        toUpdatePacket();
        world.updateListeners(pos, world.getBlockState(pos), world.getBlockState(pos), Block.NOTIFY_LISTENERS);
    }


}
