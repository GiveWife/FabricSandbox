package net.givewife.additions.objects.blockentity.netherreactor.particle;

import net.givewife.additions.util.DebugHelper;
import net.givewife.additions.util.GeneralHelper;
import net.givewife.additions.util.positions.Pos;
import net.givewife.additions.util.positions.VecTrail;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NetherReactorParticles {

    private final ParticleStageRegistrator STAGE_HOLDER;
    private GeneralHelper helper = new GeneralHelper();
    private DebugHelper debughelp = new DebugHelper("NetherReactorParticles");
    private final boolean debug = true;
    private final ParticleStageRegistrator.ParticleStage[] stages;

    /**
     * Upon creating this object, all particle stages will be initialized in another class.
     * This class connects to the ticker, which he can call to display the stages.
     */
    public NetherReactorParticles(BlockPos origin) {
        // ParticleStages will calculate & hold all ParticleStage objects
        STAGE_HOLDER = new ParticleStageRegistrator(origin);
        this.stages = STAGE_HOLDER.getStages();
    }

    /**
     * This method gets called in the {@link net.givewife.additions.objects.blockentity.netherreactor.NetherReactorTicker}, which is in turn connected to the tick method of {@link net.givewife.additions.objects.blockentity.netherreactor.NetherReactorEntity}
     */
    public void runParticles(World world, int tick) {

        // Loop over all our registered stages.
        for(int i = 0; i < stages.length; i++) {

            // Only spawn on server & check if current stage may print on this tick.
            if (world.isClient && stages[i].canPrint(tick)) {

                // Get next Pos for tick;
                Pos p = stages[i].next(tick);

                // Print on server
                world.addParticle(stages[i].getParticle(), p.x(), p.y(), p.z(), 0, 0, 0);

            }
        }

       

    }


}
