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
    private final int[] stageLengths;
    private final boolean debug = true;
    private final ParticleStages.ParticleStage[] stages;

    /**
     * Upon creating this object, all particle stages will be initialized in another class.
     * This class connects to the ticker, which he can call to display the stages.
     */
    public NetherReactorParticles(BlockPos origin) {
        stageLengths = new int[]{
            1000
        };
        STAGE_HOLDER = new ParticleStages(origin, stageLengths);
        this.stages = STAGE_HOLDER.getStages();
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
        if(debug) System.out.println("[Nether Reactor Particles] Stage is -1. Tick was: " + tick + " compared to most ticks: " + allTicks + ". Array: " + debughelp.intToString(this.stageLengths));
        return -1;
    }

    public void runParticles(World world, int tick) {

        for(int i = 0; i < stages.length; i++) {
            //System.out.println("Ticks: " + tick + ", stage: " + getStage(tick) + ", stages [" + i + "] stage: " + STAGE_HOLDER.stages[i].getStage());
            if(stages[i].getStart() >= tick
                && !world.isClient
                && stages[i].canPrint(tick)) {
                //System.out.println("Ticking: " + tick);
                Pos p = stages[i].next(tick);
                ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD, p.x(), p.y(), p.z(), 1, 0, 0, 0, 0);
                //(ServerPlayerEntity viewer, T particle, boolean force, double x, double y, double z, int count, double deltaX, double deltaY, double deltaZ, double speed) {
                //((ServerWorld) world).spawnParticles(world.getPlayers(), ParticleTypes.END_ROD, true, p.x(), p.y(), p.z(), 1, 0, 0, 0, 0);
            }
        }

    }


}
