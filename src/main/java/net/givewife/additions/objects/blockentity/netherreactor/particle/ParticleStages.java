package net.givewife.additions.objects.blockentity.netherreactor.particle;

import net.givewife.additions.objects.blockentity.netherreactor.NetherReactorEntity;
import net.givewife.additions.particles.CustomEffect;
import net.givewife.additions.particles.effects.EffectSingle;
import net.givewife.additions.util.DebugHelper;
import net.givewife.additions.util.positions.BlockSidePos;
import net.givewife.additions.util.positions.Pos;
import net.givewife.additions.util.positions.Trail;
import net.givewife.additions.util.positions.VecTrail;
import net.minecraft.block.BlockState;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ParticleStages {

    private final BlockPos origin;
    private final ParticleStage[] stages;

    /**
     * Initializes this object and calls {@link ParticleStages#registerStages()}
     */
    public ParticleStages(BlockPos origin) {
        this.origin = origin;
        this.stages = registerStages();
    }

    /**
     * Returns all registered stages from {@link ParticleStages#registerStages()}
     */
    public ParticleStage[] getStages() {
        return this.stages;
    }

    /**
     * Returns all registered {@link ParticleStage} objects.
     *
     * These objects have all information to print particles:
     *
     *      {@link ParticleStage#canPrint(int)} : checks if the particle may be printed at integer tick
     *      {@link ParticleStage#next(int)} : generates a {@link Pos} object via the {@link Trail} object
     */
    private ParticleStage[] registerStages() {
        return new ParticleStage[] {
                new ParticleStage("stage_01", BlockSidePos.getNorth(origin), BlockSidePos.getEast(origin).up(), 0, 100, 50),
                new ParticleStage("stage_011", ParticleTypes.FLAME, BlockSidePos.getNorth(origin), BlockSidePos.getEast(origin).up(), 0, 100, 100),
                new ParticleStage("stage_02", BlockSidePos.getNorth(origin), BlockSidePos.getSouth(origin).up(), 0, 100, 50),
                new ParticleStage("stage_03", BlockSidePos.getNorth(origin).east().south(), BlockSidePos.getEast(origin).up(), 0, 100, 50),
                new ParticleStage("stage_04", BlockSidePos.getNorth(origin).east().south(), BlockSidePos.getSouth(origin).up(), 0, 100, 50)
        };
    }


    public static class ParticleStage {

        private final int start, interval, duration;
        private final String id;
        private Trail to;
        private DebugHelper helper;
        private final DefaultParticleType type;

        /**
         * start | at which tick should this effect start?
         * duration | how many ticks should this effect take?
         * steps | in how many steps will the effect be drawn
         */
        public ParticleStage(String id, DefaultParticleType type, Pos from, Pos to, int start, int duration, int steps) {
            this.id = id;
            this.start = start;
            this.type = type;
            this.duration = duration;
            this.interval = steps > duration ? 1 : duration / steps;
            this.to = new VecTrail(id, from, to, steps);
            this.helper = new DebugHelper(id);
        }

        /**
         * Copy of other constructor, without particle type registration
         */
        public ParticleStage(String id, Pos from, Pos to, int start, int duration, int steps) {
            this.id = id;
            this.start = start;
            this.type = ParticleTypes.END_ROD;
            this.duration = duration;
            this.interval = steps > duration ? 1 : duration / steps;
            this.to = new VecTrail(id, from, to, steps);
            this.helper = new DebugHelper(id);
        }

        /**
         * The particle stage has a start tick, we check if the block entity tick has not exceeded that.
         * Further, we will check if the interval value, which says per how many ticks we can print one particle.
         *
         * We check for interval because some particle stages may require less ticks & steps.
         */
        public boolean canPrint(int tick) {
            helper.log("tick - start: " + (tick-start) + " <= " + (duration));
            helper.log("interval: " + interval + ", % : " + ((tick-start)%interval == 0));
            return tick - start <= duration // Make sure tick does not exceed the duration.
                    && (tick-start) % interval == 0; // Make sure we print correctly.
        }

        /**
         * Returns the name of this particle stage. For debugging purposes.
         */
        public String getId() {
            return this.id;
        }

        /**
         * Returns the DefaultParticleType of this particle stage.
         */
        public DefaultParticleType getParticle() {
            return this.type;
        }

        /**
         * The NetherReactorTicker will only call the {@link ParticleStage#next(int)} function,
         * where the tick represents the time from activation. We will scale down this tick to a tick that matches the VecTrail object progress.
         *
         * Example: tick = 1021 ; step = 50 ; duration = 100 ; start = 1000
         *
         * tick - start = 21
         * interval = duration / step = 2
         *
         * 21 % 2 == 0 ? -> this is checked by {@link ParticleStage#canPrint(int)}
         *
         * 20 / 2 = 10
         */
        private int getIntervalTick(int tick) {
            return (tick - start) / interval;
        }

        /**
         * Returns an offset on the particle trail
         */
        public Pos next(int tick) {
            return this.to.offset(getIntervalTick(tick));
        }

    }

}
