package net.givewife.additions.objects.blockentity.netherreactor;

import net.givewife.additions.Main;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class NetherReactorEntity extends BlockEntity {

    public NetherReactorEntity(BlockPos pos, BlockState state) {
        super(Main.TILE_ENTITIES.NETHER_REACTOR_ENTITY, pos, state);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        //Write
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        //Read
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




}
