package net.givewife.additions.objects.blockentity;

import net.givewife.additions.Main;
import net.givewife.additions.objects.blocks.ProjectorBlock;
import net.givewife.additions.registry.registries.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ProjectorBlockEntity extends BlockEntity {

    private final String LIFETIME_KEY = "lifetime";
    private static int current_lifetime;
    public int public_lifetime;
    private static final int LIFETIME = 40;

    public ProjectorBlockEntity(BlockPos pos, BlockState state) {
        super(Main.TILE_ENTITIES.PROJECTOR_BLOCK_ENTITY, pos, state);
        current_lifetime = LIFETIME;
    }

    public static void tick(World world, BlockPos pos, BlockState state, ProjectorBlockEntity be) {

        if(world.isClient) return;

        current_lifetime--;

        if(current_lifetime <= 0) {
            if (state.get(ProjectorBlock.BRIGHTNESS) - 1 > 0) {
                //state.with(ProjectorBlock.BRIGHTNESS, state.get(ProjectorBlock.BRIGHTNESS) - 1);
                world.setBlockState(pos, BlockRegistry.PROJECTOR_1.getDefaultState().with(ProjectorBlock.BRIGHTNESS, state.get(ProjectorBlock.BRIGHTNESS) - 1));
                current_lifetime = LIFETIME;
                } else
                    world.setBlockState(pos, Blocks.AIR.getDefaultState());
        }

    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        nbt.putInt(LIFETIME_KEY, current_lifetime);
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        current_lifetime = nbt.getInt(LIFETIME_KEY);
        public_lifetime = nbt.getInt(LIFETIME_KEY);
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
