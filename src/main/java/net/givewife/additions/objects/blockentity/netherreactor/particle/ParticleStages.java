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
        stages = registerStages();
    }

    public ParticleStage[] getStages() {
        return this.stages;
    }

    public ParticleStage[] registerStages() {
        return new ParticleStage[] {
                new ParticleStage("stage_01", BlockSidePos.getNorth(origin), BlockSidePos.getEast(origin).up(), 0, 100, 100),
                new ParticleStage("stage_02", BlockSidePos.getNorth(origin), BlockSidePos.getSouth(origin).up(), 0, 100, 100),
                new ParticleStage("stage_03", BlockSidePos.getNorth(origin).east().south(), BlockSidePos.getEast(origin).up(), 0, 100, 100),
                new ParticleStage("stage_04", BlockSidePos.getNorth(origin).east().south(), BlockSidePos.getSouth(origin).up(), 0, 100, 100)
        };
    }


    public static class ParticleStage {

        /**
         * interval | determines per how many ticks we try to spawn a particle
         * begintick | reference to when this particle effect started
         */
        private final int start, interval;
        private VecTrail to;

        /**
         * Stage: when in the continuity it should start executing
         * Ticks: amount of ticks for this full effect
         * Steps: how much particles are shown
         */
        public ParticleStage(String id, Pos from, Pos to, int start, int duration, int steps) {
            this.start = start;
            this.interval = steps > duration ? 1 : duration / steps;
            this.to = new VecTrail(id, from, to, steps);
        }

        public int getStart() {
            return this.start;
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
