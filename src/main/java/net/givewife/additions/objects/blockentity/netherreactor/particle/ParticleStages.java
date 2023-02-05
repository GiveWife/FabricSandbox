package net.givewife.additions.objects.blockentity.netherreactor.particle;

import net.givewife.additions.particles.CustomEffect;
import net.givewife.additions.particles.effects.EffectSingle;
import net.givewife.additions.util.positions.BlockSidePos;
import net.givewife.additions.util.positions.Pos;
import net.givewife.additions.util.positions.VecTrail;
import net.minecraft.util.math.BlockPos;

public class ParticleStages {

    private final BlockPos origin;
    private final ParticleStage[] stages;

    public ParticleStages(BlockPos origin, int[] stageLengths) {
        this.origin = origin;
        stages = registerStages(stageLengths);
    }

    public ParticleStage[] getStages() {
        return this.stages;
    }

    public ParticleStage[] registerStages(int[] stageLengths) {
        return new ParticleStage[] {
                new ParticleStage("stage_01", BlockSidePos.getNorth(origin), BlockSidePos.getEast(origin).up()),
                new ParticleStage("stage_02", BlockSidePos.getNorth(origin), BlockSidePos.getSouth(origin).up()),
                new ParticleStage("stage_03", BlockSidePos.getNorth(origin).east().south(), BlockSidePos.getEast(origin).up()),
                new ParticleStage("stage_04", BlockSidePos.getNorth(origin).east().south(), BlockSidePos.getSouth(origin).up())
        };

    }


    public static class ParticleStage {

        /**
         * interval | determines per how many ticks we try to spawn a particle
         * begintick | reference to when this particle effect started
         */
        private final int stage, interval;
        private VecTrail to;

        /**
         * Stage: when in the continuity it should start executing
         * Ticks: amount of ticks for this full effect
         * Steps: how much particles are shown
         */
        public ParticleStage(String id, Pos from, Pos to, int stage, int duration, int steps) {
            this.stage = stage;
            this.interval = steps > duration ? 1 : duration / steps;
            //System.out.println(id + ": from: " + from.getPrint() + ", to " + to.getPrint());
            this.to = new VecTrail(id, from, to, steps);
        }

        public int getStage() {
            return this.stage;
        }

        /**
         * A particle stage is defined by how many ticks it should take, and how many steps should be taken.
         * To evenly spread out the particles on the ticks, we
         */
        public boolean canPrint(int tick) {
            return tick % interval == 0;
        }

        /**
         *
         */
        public Pos next(int tick) {
            return this.to.offset(tick);
        }

    }

}
