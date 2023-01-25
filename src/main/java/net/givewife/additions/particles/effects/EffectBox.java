package net.givewife.additions.particles.effects;

import net.givewife.additions.particles.CustomEffect;
import net.givewife.additions.util.positions.Pos;
import net.givewife.additions.util.positions.VecTrail;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class EffectBox extends CustomEffect {

    private final Pos pos1, pos2;
    private final int stepsPerBlock = 2;

    public EffectBox(Pos pos1, Pos pos2) {
        System.out.println("Pos 1: " + pos1.getPrint() + ", Pos 2: " + pos2.getPrint());
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    @Override
    public void run(ServerWorld world) {

        // Sets the pos coord of the blockpos to the outer end.
        Pos pos2 = this.pos2.east().south();
        double dX = Math.abs(pos2.x() - pos1.x());
        double dY = Math.abs(pos2.y() - pos1.y());
        double dZ = Math.abs(pos2.z() - pos1.z());

        int stepsX = steps(dX);
        int stepsY = steps(dY);
        int stepsZ = steps(dZ);

        VecTrail[] trails = new VecTrail[]{

                new VecTrail("bottomx1", pos1, pos1.east(dX), steps(dX)),
                new VecTrail("bottomx2", pos1, pos1.south(dZ), steps(dZ)),
                new VecTrail("bottomx3", pos1.east(dX), pos2.down(dY), steps(dX)),
                new VecTrail("bottomx4", pos1.south(dZ), pos2.down(dY), steps(dZ)),

                new VecTrail("topx1", pos1.up(dY), pos1.east(dX).up(dY), steps(dX)),
                new VecTrail("topx2", pos1.up(dY), pos1.south(dZ).up(dY), steps(dZ)),
                new VecTrail("topx3", pos1.east(dX).up(dY), pos2, steps(dX)),
                new VecTrail("topx4", pos1.south(dZ).up(dY), pos2, steps(dZ)),

                new VecTrail("side1", pos1, pos1.up(dY), steps(dY)),
                new VecTrail("side2", pos1.east(dX), pos1.east(dX).up(dY), steps(dY)),
                new VecTrail("side3", pos1.east(dX).south(dZ), pos1.east(dX).south(dZ).up(dY), steps(dY)),
                new VecTrail("side4", pos1.south(dZ), pos1.south(dZ).up(dY), steps(dY))

        };

        print(world, trails, stepsX, stepsY, stepsZ);

    }

    private void print(ServerWorld world, VecTrail[] trails, int stepsX, int stepsY, int stepsZ) {

        for(int i = 0; i < trails.length; i++) {

            VecTrail trail = trails[i];

            for(int j = 0; j < trail.getSteps(); j++) {

                Pos p = trail.getOffset(j);

                world.spawnParticles(ParticleTypes.END_ROD, p.x(), p.y(), p.z(), 1, 0, 0, 0, 0);

            }

        }

    }

    private int steps(double distance) {
        return (int) distance * stepsPerBlock;
    }

    @Override
    public void run(World world) {

    }
}
