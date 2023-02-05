package net.givewife.additions.particles.effects;

import net.givewife.additions.particles.CustomEffect;
import net.givewife.additions.registry.registries.ParticleRegistry;
import net.givewife.additions.util.positions.Pos;
import net.givewife.additions.util.positions.VecTrail;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class EffectBox extends CustomEffect {

    private final Pos pos1, pos2;
    private final int stepsPerBlock;
    private final VecTrail[] trails;

    public EffectBox(Pos pos1, Pos pos2, int stepsPerBlock) {

        System.out.println("Pos1/ " + pos1.getPrint() + ", Pos2: " + pos2.getPrint());

        final Pos[] fixed = calculate(pos1, pos2);
        assert fixed.length == 2;

        this.pos1 = fixed[0];
        this.pos2 = fixed[1];
        this.stepsPerBlock = stepsPerBlock;
        this.trails = init();

    }

    /**
     * This method makes sure that the conversion from BlockPos to pos goes correctly.
     * BlockPos will always contain the north-west point of the block.
     *
     * To make sure the particles surround all the blocks, sometimes the north-west position
     * should be corrected to some direction.
     */
    private Pos[] calculate(Pos from, Pos to) {

        // We use equal signs because IF the coordinates are the same, < and > would not select one of the coords.
        // If one of the two is higher and the other is equal, or both are equal, the algorithm will always choose
        // one coord to take the upper / changed position

        // Take east up the page, south right to the page
        // Know that coordinates are always north-west of the block.

        /*           F
         *
         *  T
         */
        if(from.x() >= to.x() && from.z() >= to.z()) {
            System.out.println("Case: > > ");
            if(from.y() >= to.y()) return new Pos[]{ from.south().east().up(), to };
            else return new Pos[] { from.south().east(), to.up() };
        }

        /*  F
         *
         *          T
         */
        if(from.x() <= to.x() && from.z() >= to.z()) {
            System.out.println("Case < >");
            if(from.y() >= to.y()) return new Pos[]{ from.east().up(), to.south()};
            else return new Pos[]{ from.east(), to.south().up()};
        }

        /*  T
         *
         *           F
         */
        if(from.x() >= to.x() && from.z() <= to.z()) {
            System.out.println("Case > <");
            if(from.y() >= to.y()) return new Pos[]{ from.south().up(), to.east()};
            else return new Pos[]{ from.south(), to.east().up()};
        }

        /*           T
         *
         *  F
         */
        if(from.x() <= to.x() && from.z() <= to.z()) {
            System.out.println("Case < <");
            if(from.y() >= to.y()) return new Pos[]{ from.up(), to.north().east()};
            else return new Pos[]{ from, to.north().east().up()};
        }

        return new Pos[] {from, to};


    }

    /**
     * Calculates all necessary parts to run this effect.
     * @return
     */
    private VecTrail[] init() {

        double dX = pos2.x() - pos1.x();
        double dY = pos2.y() - pos1.y();
        double dZ = pos2.z() - pos1.z();

        int stepsX = (int) Math.abs(dX * stepsPerBlock);
        int stepsY = (int) Math.abs(dY * stepsPerBlock);
        int stepsZ = (int) Math.abs(dZ * stepsPerBlock);

        if(stepsX == 0 || stepsY == 0 || stepsZ == 0) System.out.println("[EffectBox] stepX: " + stepsX + ", stepY: " + stepsY + ", stepsZ: " + stepsZ);

        return new VecTrail[]{

                // We can start from pos1, and go EAST (X++) -> dX will be negative if from.x() > to.x() -->  east(-x) == - east(x) == west(x)
                //                                SOUTH (Z++)-> dZ will be negative if from.z() > to.z() --> south(-z) == - south(z) == north(z)
                // Operations can all be done on POS1!
                // dY will be negative if pos1.y() > pos2.y() -> so pos1.up(dY) == down!
                new VecTrail("bottomx1", pos1, pos1.east(dX), stepsX),
                new VecTrail("bottomx2", pos1, pos1.south(dZ), stepsZ),
                new VecTrail("bottomx3", pos1.east(dX), pos1.south(dZ).east(dX), stepsZ),
                new VecTrail("bottomx4", pos1.south(dZ), pos1.south(dZ).east(dX), stepsX),

                new VecTrail("topx1", pos1.up(dY), pos1.east(dX).up(dY), stepsX),
                new VecTrail("topx2", pos1.up(dY), pos1.south(dZ).up(dY), stepsZ),
                new VecTrail("topx3", pos1.east(dX).up(dY), pos2, stepsZ),
                new VecTrail("topx4", pos1.south(dZ).up(dY), pos2, stepsX),

                new VecTrail("side1", pos1, pos1.up(dY), stepsY),
                new VecTrail("side2", pos1.east(dX), pos1.east(dX).up(dY), stepsY),
                new VecTrail("side3", pos2.down(dY), pos2, stepsY),
                new VecTrail("side4", pos1.south(dZ), pos1.south(dZ).up(dY), stepsY)

        };

    }

    @Override
    public void run(ServerWorld world) {

        for(int i = 0; i < trails.length; i++) {

            VecTrail trail = trails[i];
            //System.out.println("[Server] Steps: " + trail.getSteps());

            for(int j = 0; j < trail.getSteps(); j++) {

                Pos p = trail.offset(j);
                //System.out.println("[Server] Spawning at: " + p.getPrint());

                world.spawnParticles(ParticleRegistry.WHITE_STAR, p.x(), p.y(), p.z(), 1, 0, 0, 0, 0);

            }

        }

    }

    @Override
    public void run(World world) {

        for(int i = 0; i < trails.length; i++) {

            VecTrail trail = trails[i];
            //System.out.println("[Client] Steps: " + trail.getSteps());

            for(int j = 0; j < trail.getSteps(); j++) {

                Pos p = trail.offset(j);
                //System.out.println("[Client] Spawning at: " + p.getPrint());

                world.addParticle(ParticleRegistry.WHITE_STAR, true, p.x(), p.y(), p.z(), 0, 0, 0);

            }

        }

    }

}
