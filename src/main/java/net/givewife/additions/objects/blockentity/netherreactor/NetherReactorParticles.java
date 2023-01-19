package net.givewife.additions.objects.blockentity.netherreactor;

import net.givewife.additions.util.positions.Pos;
import net.givewife.additions.util.positions.VecTrail;

public class NetherReactorParticles {

    private final ParticleStage[] stages;
    public NetherReactorParticles() {
        stages = new ParticleStage[] {
                    
        };
    }

    public void runParticles() {

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
        public ParticleStage(int stage, int ticks, int steps, Pos from, Pos to) {
            this.stage = stage;
            this.ticks = ticks;
            this.to = new VecTrail(from, to, steps);
        }

        public abstract Pos next(int tick);

    }

}
