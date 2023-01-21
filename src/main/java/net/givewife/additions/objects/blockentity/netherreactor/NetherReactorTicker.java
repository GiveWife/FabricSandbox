package net.givewife.additions.objects.blockentity.netherreactor;

import net.givewife.additions.objects.blockentity.netherreactor.NetherReactorEntity;
import net.givewife.additions.util.GeneralHelper;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NetherReactorTicker {

    private GeneralHelper helper = new GeneralHelper();
    private final NetherReactorParticles PARTICLE_HANDLER;
    private int tick;
    public NetherReactorTicker() {
        tick = 0;
        this.PARTICLE_HANDLER = new NetherReactorParticles();
    }

    public void tick(World world, BlockPos pos, BlockState state, NetherReactorEntity be) {


        this.PARTICLE_HANDLER.runParticles(tick);

        tick++;

    }

}
