package net.givewife.additions.objects.blockentity.netherreactor.particle;

import net.givewife.additions.util.DebugHelper;
import net.givewife.additions.util.positions.BlockSidePos;
import net.givewife.additions.util.positions.Pos;
import net.givewife.additions.util.positions.Trail;
import net.givewife.additions.util.positions.VecTrail;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class ParticleStageRegistrator {

    private final BlockPos origin;
    private List<ParticleStage> stageRegistry = new ArrayList<>();

    public ParticleStageRegistrator(BlockPos block) {
        this.origin = block.down();
        register();
    }

    public ParticleStage[] getStages() {
        return transform();
    }

    /**
     * Returns all registered {@link ParticleStage} objects.
     *
     * These objects have all information to print particles:
     *
     *      {@link ParticleStage#canPrint(int)} : checks if the particle may be printed at integer tick
     *      {@link ParticleStage#next(int)} : generates a {@link Pos} object via the {@link Trail} object
     *
     *    E     SE
     *    O---O
     *    |   |
     *    X---O > S
     */
    private void register() {

        ParticleThread northThread = new ParticleThread(BlockSidePos.getNorth(origin, 1).up(3), 0);
        ParticleThread southThread = new ParticleThread(BlockSidePos.getSouth(origin, 1).up(3), 0);
        ParticleThread westThread = new ParticleThread(BlockSidePos.getEast(origin, 1).up(3), 0);
        ParticleThread southEastThread = new ParticleThread(BlockSidePos.getSouthEast(origin, 1).up(3), 0);

        // NORTH - Particle going down & to middle
        ParticleThread northBranch = northThread.fork(0);
        northThread.link(northThread.getStart().down(3), 100);
        northBranch.link(northThread.getStart().south(0.5d), 100);

        // SOUTH - Particle going down & to middle
        ParticleThread southBranch = southThread.fork(0);
        southThread.link(southThread.getStart().down(3), 100);
        southBranch.link(southThread.getStart().south(0.5d), 100);

        // EAST - Particle going down & to middle
        ParticleThread eastBranch = southEastThread.fork(0);
        southEastThread.link(southEastThread.getStart().down(3), 100);
        eastBranch.link(southEastThread.getStart().south(0.5d), 100);

        // WEST - Particle going down & to middle
        ParticleThread westBranch = westThread.fork(0);
        westThread.link(westThread.getStart().down(3), 100);
        westBranch.link(westThread.getStart().south(0.5d), 100);


        // Put all stages from the threads in the particlestage list.
        memorize(northThread, southThread, westThread, southEastThread, northBranch, southBranch, eastBranch, westBranch);

    }

    private void memorize(ParticleThread... threads) {
        for(ParticleThread t : threads)
            stageRegistry.addAll(t.thread);

    }

    /**
     * Transforms the list to an array
     */
    private ParticleStage[] transform() {
        ParticleStage[] stages = new ParticleStage[stageRegistry.size()];
        for(int i = 0; i < stageRegistry.size(); i++) {
            stages[i] = stageRegistry.get(i);
        }
        return stages;
    }

    /**
     * Helper class to coordinate particles: start, end, duration, ticks, interval... --> via Trail object
     */
    public static class ParticleStage {

        private final int start, interval, duration;
        private final String id;
        private Trail to;
        private DebugHelper helper;
        private final DefaultParticleType type;

        /**
         * Copy of other constructor, without particle type registration
         */
        public ParticleStage(Pos from, Pos to, int start, int duration, int steps) {
            this.id = builder(from, to);
            this.start = start;
            this.type = ParticleTypes.END_ROD;
            this.duration = duration;
            this.interval = steps > duration ? 1 : duration / steps;
            this.to = new VecTrail(id, from, to, steps);
            this.helper = new DebugHelper(id);
        }

        /**
         * Initializer without steps and id
         */
        public ParticleStage(Pos from, Pos to, int start, int duration) {
            this.id = builder(from, to);
            this.start = start;
            this.type = ParticleTypes.END_ROD;
            this.duration = duration;
            int steps = getSteps(from, to);
            this.interval = steps > duration ? 1 : duration / steps;
            this.to = new VecTrail(id, from, to, steps);
            this.helper = new DebugHelper(id);
        }

        /**
         * Calculates a fitting step for the distance
         */
        private int getSteps(Pos from, Pos to) {
            if(from.distance(to) == 0) System.out.println("Distance is zero!");
            return (int) from.distance(to) * 10;
        }

        /**
         * Creates a fitting ID for this object
         */
        private String builder(Pos from, Pos to) {
            return (new VecTrail("particle_stage",from,to)).getPrint();
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

        /**
         * Returns the ending position for this particle stage
         */
        public Pos getEnd() {
            return this.to.to();
        }

    }

    /**
     * Helper class to create consecutive particle stages without too much code duplication for the programmer
     *
     * Link: (next Position, duration)
     *      --> gets ending position of last particle trail and connects those
     */
    public static class ParticleThread {

        private final Pos start;
        private final int beginTick;
        private List<ParticleStage> thread;

        public ParticleThread(Pos start, int tick) {
            this.start = start;
            this.beginTick = tick;
            this.thread = new ArrayList<>();
        }

        /**
         * Directly links a new {@link ParticleStage} to this thread by putting the end position of the latest stage as the starting position of the new stage
         */
        public void link(Pos to, int duration) {
            if(thread.size() == 0) {
                thread.add(new ParticleStage(start, to, this.beginTick, duration));
            } else {
                ParticleStage latestStage = thread.get(thread.size() - 1);
                thread.add(new ParticleStage(latestStage.getEnd(), to, latestStage.start + latestStage.duration, duration));
            }
        }

        /**
         * Returns a new thread that begins with the latest position in the thread.
         */
        public ParticleThread fork(int start) {
            if(thread.size() == 0) return new ParticleThread(this.start, start);
            else return new ParticleThread(thread.get(thread.size() - 1).getEnd(), start);
        }

        /**
         * Returns the position where last particle stage ends
         */
        public Pos getStart() {
            if(thread.size() > 0) return thread.get(thread.size() - 1).getEnd();
            else return start;
        }

    }

}
