package net.givewife.additions.particles.effects;

import net.givewife.additions.util.positions.Pos;
import net.givewife.additions.util.positions.Trail;
import net.givewife.additions.util.positions.VecTrail;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * This particle effect is a quick implementation for a VecTrail.
 */
public class EffectLine extends CustomEffect {

    private final Trail trail;
    private final int amount;
    private float[] colors;

    public EffectLine(Pos from, Pos to, int amount) {

        trail = new VecTrail("effectLine", from, to, amount);
        this.amount = amount;

    }

    public EffectLine(Pos from, Pos to, int amount, float[] colors) {

        trail = new VecTrail("effectLine", from, to, amount);
        this.amount = amount;
        this.colors = colors;

    }

    public void run(ServerWorld world, Pos pos1, Pos pos2) {

        double x1 = pos1.x();
        double y1 = pos1.y();
        double z1 = pos1.z();
        double x2 = pos2.x();
        double y2 = pos2.y();
        double z2 = pos2.z();

        double xDiff = x1 - x2;
        double yDiff = y1 - y2;
        double zDiff = z1 - z2;

        // amount of particles used
        double part = 50;
        double stepX = xDiff / part;
        double stepY = yDiff / part;
        double stepZ = zDiff / part;
        System.out.println("x1: " + x1 + ", y1: " + y1 + ", z1: " + z1);
        System.out.println("x2: " + x2 + ", y2: " + y2 + ", z2: " + z2);

        for(int i = 0; i < part; i++) {

            world.spawnParticles(ParticleTypes.END_ROD, x2 + (i*stepX), y2 + (i*stepY), z2 + (i*stepZ), 1, 0, 0, 0, 0);

        }

    }

    @Override
    public void run(ServerWorld world) {
        this.trail.printParticles(world);
    }

    @Override
    public void run(World world) {
        if(colors != null)
            this.trail.printParticles(world);
        else
            this.trail.printColored(world, colors);
    }

}
