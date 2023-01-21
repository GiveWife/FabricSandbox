package net.givewife.additions.objects.blockentity.netherreactor;

import net.givewife.additions.util.GeneralHelper;
import net.givewife.additions.util.positions.Pos;
import net.givewife.additions.util.positions.VecTrail;

public class NetherReactorParticles {

    private final ParticleStage[] stages;
    private GeneralHelper helper = new GeneralHelper();
    private final int[] stageLengths;
    private final boolean debug = false;
    public NetherReactorParticles() {
        stageLengths = new int[]{

        };
        stages = new ParticleStage[] {

        };
    }

    private int getLengthStage(int stage) {
        return this.stageLengths[stage];
    }

    private int getStage(int tick) {
        int allTicks = 0;
        for(int i = 0; i < stageLengths.length; i++) {
            allTicks += stageLengths[i];
            if(allTicks <= tick) return stageLengths[i];
        }
        if(debug) System.out.println("[Nether Reactor Particles] Stage is -1. Tick was: " + tick + " compared to most ticks: " + allTicks + ". Array: " + helper.intToString(this.stageLengths));
        return -1;
    }

    public void runParticles(int tick) {

        int stage = getStage(tick);

        for(int i = 0; i < stages.length; i++) {
            if(stages[i].stage == stage) runParticles(tick);
        }

    }


    public static abstract class ParticleStage {

        private int stage, ticks, steps;
        private VecTrail to;
        //private final Pos step;

        /**
         * Stage: when in the continuity is should start executing
         * Ticks: amount of ticks for this full effect
         * Steps: how much particles are shown
         */
        public ParticleStage(String id, int stage, int ticks, int steps, Pos from, Pos to) {
            this.stage = stage;
            this.ticks = ticks;
            this.to = new VecTrail(id, from, to, steps);
        }

        public int getStage() {
            return this.stage;
        }

        public Pos next(int tick) {
            return this.to.getOffset(tick);
        }

    }

}
