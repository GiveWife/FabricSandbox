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

    private final ParticleStages STAGE_HOLDER;
    private GeneralHelper helper = new GeneralHelper();
    private DebugHelper debughelp = new DebugHelper("NetherReactorParticles");
    private final boolean debug = true;
    private final ParticleStages.ParticleStage[] stages;
    public static final int[] stageLengths = new int[]{
            1000
    };

    /**
     * Upon creating this object, all particle stages will be initialized in another class.
     * This class connects to the ticker, which he can call to display the stages.
     */
    public NetherReactorParticles(BlockPos origin) {
        STAGE_HOLDER = new ParticleStages(origin);
        this.stages = STAGE_HOLDER.getStages();
    }

    public void runParticles(World world, int tick) {

        for(int i = 0; i < stages.length; i++) {
            debughelp.log("stage: " + stages[i]);
            if (!world.isClient && stages[i].canPrint(tick)) {

                Pos p = stages[i].next(tick);
                ((ServerWorld) world).spawnParticles(stages[i].getParticle(), p.x(), p.y(), p.z(), 1, 0, 0, 0, 0);

            }
        }

    }


}
