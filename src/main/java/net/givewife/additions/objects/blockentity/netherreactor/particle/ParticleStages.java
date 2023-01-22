package net.givewife.additions.objects.blockentity.netherreactor.particle;

import net.givewife.additions.particles.CustomEffect;
import net.givewife.additions.particles.effects.EffectSingle;
import net.givewife.additions.util.positions.BlockSidePos;
import net.givewife.additions.util.positions.Pos;
import net.givewife.additions.util.positions.VecTrail;
import net.minecraft.util.math.BlockPos;

public class ParticleStages {

    private final BlockPos origin;
    final ParticleStage[] stages;

    public ParticleStages(BlockPos origin, int[] stageLengths) {
        this.origin = origin;
        stages = registerStages(stageLengths);
    }

    public ParticleStage[] registerStages(int[] stageLengths) {
        return new ParticleStage[] {
                new ParticleStage("stage_01", 0, stageLengths[0], 100, BlockSidePos.getNorth(origin), BlockSidePos.getEast(origin).up()),
                new ParticleStage("stage_02", 0, stageLengths[0], 100, BlockSidePos.getNorth(origin), BlockSidePos.getSouth(origin).up()),
                new ParticleStage("stage_03", 0, stageLengths[0], 100, BlockSidePos.getNorth(origin).east().south(), BlockSidePos.getEast(origin).up()),
                new ParticleStage("stage_04", 0, stageLengths[0], 100, BlockSidePos.getNorth(origin).east().south(), BlockSidePos.getSouth(origin).up())
        };

    }

    public static class ParticleStage {

        private final int stage, ticks, steps;
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
            this.steps = steps;
            System.out.println(id + ": from: " + from.getPrint() + ", to " + to.getPrint());
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
