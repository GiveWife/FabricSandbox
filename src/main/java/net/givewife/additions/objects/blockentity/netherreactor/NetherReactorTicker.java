package net.givewife.additions.objects.blockentity.netherreactor;

import net.givewife.additions.objects.blockentity.netherreactor.particle.NetherReactorParticles;
import net.givewife.additions.util.GeneralHelper;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NetherReactorTicker {

    private GeneralHelper helper = new GeneralHelper();
    private final NetherReactorParticles PARTICLE_HANDLER;
    private int tick;
    private boolean debug = true;

    public NetherReactorTicker(BlockPos origin, int ticks) {
        tick = ticks;
        this.PARTICLE_HANDLER = new NetherReactorParticles(origin);
    }

    public void tick(World world, BlockPos pos, BlockState state, NetherReactorEntity be) {

        if(debug && tick % 100 == 0) System.out.println("Ticks: " + tick);
        if(!be.isActive()) return;

        this.PARTICLE_HANDLER.runParticles(world, be.getTicks());

        be.increment();

    }

}
