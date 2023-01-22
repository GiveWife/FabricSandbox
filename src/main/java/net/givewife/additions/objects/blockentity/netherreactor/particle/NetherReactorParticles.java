package net.givewife.additions.objects.blockentity.netherreactor.particle;

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
    private final int[] stageLengths;
    private final boolean debug = true;

    //TODO Every tick this class gets created, we must circumvent this! Keep everything in tile entity?
    public NetherReactorParticles(BlockPos origin) {
        stageLengths = new int[]{
            1000
        };
        STAGE_HOLDER = new ParticleStages(origin, stageLengths);
    }

    private int getLengthStage(int stage) {
        return this.stageLengths[stage];
    }

    private int getStage(int tick) {
        int allTicks = 0;
        for(int i = 0; i < stageLengths.length; i++) {
            allTicks += stageLengths[i];
            if(tick <= allTicks) return i;
        }
        if(debug) System.out.println("[Nether Reactor Particles] Stage is -1. Tick was: " + tick + " compared to most ticks: " + allTicks + ". Array: " + helper.intToString(this.stageLengths));
        return -1;
    }

    public void runParticles(World world, int tick) {

        for(int i = 0; i < STAGE_HOLDER.stages.length; i++) {
            //System.out.println("Ticks: " + tick + ", stage: " + getStage(tick) + ", stages [" + i + "] stage: " + STAGE_HOLDER.stages[i].getStage());
            if(STAGE_HOLDER.stages[i].getStage() == getStage(tick)
                && !world.isClient) {
                //System.out.println("Ticking: " + tick);
                Pos p = STAGE_HOLDER.stages[i].next(tick);
                ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD, p.x(), p.y(), p.z(), 1, 0, 0, 0, 0);
            }
        }

    }

}
