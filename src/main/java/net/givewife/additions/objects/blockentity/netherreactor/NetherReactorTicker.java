package net.givewife.additions.objects.blockentity.netherreactor;

import net.givewife.additions.objects.blockentity.netherreactor.particle.NetherReactorParticles;
import net.givewife.additions.objects.blockentity.netherreactor.structure.NetherReactorStructure;
import net.givewife.additions.util.GeneralHelper;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NetherReactorTicker {

    private GeneralHelper helper = new GeneralHelper();
    private final NetherReactorParticles PARTICLE_HANDLER;
    private final NetherReactorStructure STRUCTURE_HANDLER;
    private int tick;
    private boolean debug = true;

    public NetherReactorTicker(BlockPos origin, int ticks, NetherReactorParticles p, NetherReactorStructure s) {
        tick = ticks;
        this.PARTICLE_HANDLER = p;
        this.STRUCTURE_HANDLER = s;
    }

    public void tick(World world, BlockPos pos, BlockState state, NetherReactorEntity be) {

        if(!be.isActive()) return;

        this.PARTICLE_HANDLER.runParticles(world, be.getTicks());

        be.increment();

    }

}
