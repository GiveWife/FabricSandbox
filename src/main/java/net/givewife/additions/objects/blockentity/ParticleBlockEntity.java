package net.givewife.additions.objects.blockentity;

import net.givewife.additions.Main;
import net.givewife.additions.objects.blockentity.netherreactor.NetherReactorEntity;
import net.givewife.additions.objects.blocks.ProjectorBlock;
import net.givewife.additions.registry.registries.BlockRegistry;
import net.givewife.additions.util.EffectSnakeHelper;
import net.givewife.additions.util.positions.Parabola;
import net.givewife.additions.util.positions.Pos;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ParticleBlockEntity extends BlockEntity {

    private final String TICK = "tick";
    private EffectSnakeHelper helper;
    private Parabola[] jumps;
    private int tick, size;
    private boolean isActive = false;

    public ParticleBlockEntity(BlockPos pos, BlockState state) {
        super(Main.TILE_ENTITIES.PARTICLE_ENTITY, pos, state);
    }

    public void tick(World world, BlockPos pos, BlockState state, ParticleBlockEntity be) {

        if(!world.isClient) return;

        if(!isActive) {
            helper = new EffectSnakeHelper(new Pos(this.getPos()), 5.0f, 10, this.world, 10);
            this.jumps = helper.getJumps();
            this.size = jumps.length;
            for(int i = 0; i < jumps.length; i++) {
                if(i+1 < jumps.length) {
                    //System.out.println("For entry (" + i + "): end i: " + jumps[i].offset(100).y() + " - start i+1: " + jumps[i+1].offset(0).y() + " and i: " + jumps[i].getPrint());
                }
            }
            isActive = true;
        }

        int parabolaIndex = (int) Math.floor((double) tick / 100);
        //System.out.println("index: " + parabolaIndex);

        if(parabolaIndex < jumps.length) {

            Pos off = jumps[parabolaIndex].offset(tick % 100);
            //if(tick % 20 == 0) System.out.println("At tick: " + tick + " ; Accessing entry: " + parabolaIndex + " ; at location: " + off.getPrint());
            world.addParticle(ParticleTypes.END_ROD, true, off.x(), off.y(), off.z(), 0, 0, 0);

        }

        update();
        tick++;

    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        nbt.putInt(TICK, tick);
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        tick = nbt.getInt(TICK);
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

    public void update() {
        toUpdatePacket();
        world.updateListeners(pos, world.getBlockState(pos), world.getBlockState(pos), Block.NOTIFY_LISTENERS);
    }

}
