package net.givewife.additions.objects.blockentity.netherreactor;

import net.givewife.additions.Main;
import net.givewife.additions.objects.blockentity.netherreactor.particle.NetherReactorParticles;
import net.givewife.additions.objects.blockentity.netherreactor.structure.NetherReactorStructure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.DimensionEffects;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * This class holds all handlers to make the entity do its thing.
 *
 * In the block class, a ticker gets passed every tick itself. If we keep the data the ticker uses in this class,
 * the ticker will only use the already existing classes. By creating the ticker, structure handler, particle handler
 * over and over again will stress the memory usage too much.
 */
public class NetherReactorEntity extends BlockEntity {

    private String ACTIVATED = "nether_reactor_active";
    private String TICKS = "nether_reactor_ticks";
    private boolean isActive = false;
    private int ticks;
    private final NetherReactorTicker ticker;
    private final NetherReactorParticles particles;
    private final NetherReactorStructure structure;

    public NetherReactorEntity(BlockPos pos, BlockState state) {
        super(Main.TILE_ENTITIES.NETHER_REACTOR_ENTITY, pos, state);
        particles = new NetherReactorParticles(pos);
        structure = new NetherReactorStructure(pos);
        ticker = new NetherReactorTicker(pos, ticks, particles, structure);
        System.out.println("[TileEntityNetherReactor] This entity has been initialized!");
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

    /**
     * This method passes through this class, so the ticker & particle class only get created once by creation of this object
     */
    public void tick(World world, BlockPos pos, BlockState state, NetherReactorEntity be) {
        ticker.tick(world, pos, state, be);
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

    public NetherReactorStructure getStructureHandler() {
        return this.structure;
    }

}
