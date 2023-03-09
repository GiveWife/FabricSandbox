package net.givewife.additions.util.positions;

import net.givewife.additions.particles.printer.ParticlePrinter;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * This class represents a trail between two points in space. Since there can be different kinds of trails,
 * lines; parabolas in y; parabolas in x,z;
 *
 * We use the default variables in the superclass {@link Vec}:
 *          {@link Vec#from} and {@link Vec#to}
 *
 * Furthermore, we provide functions for different implementations for subclasses:
 *
 *      1) {@link Trail#printParticles(World)}
 *      2) {@link Trail#printParticles(ServerWorld)}
 *      3) {@link Trail#offset(int)} : how should the next particle between from and to be calculated?
 *
 */
public abstract class Trail extends Vec {

    private final int steps;
    private final String type;
    private final DefaultParticleType particle;
    private ParticlePrinter printer = new ParticlePrinter();

    /**
     * Initializes this object
     */
    public Trail(String type, Pos from, Pos to, int steps) {
        super(from, to);
        this.type = type;
        this.steps = steps;
        this.particle = ParticleTypes.END_ROD;
    }

    /**
     * Initializes this object with a specified particle.
     */
    public Trail(String type, Pos from, Pos to, int steps, DefaultParticleType particle) {
        super(from, to);
        this.type = type;
        this.steps = steps;
        this.particle = particle;
    }

    /**
     * Returns a position that is on a certain distance from the {@link Vec#from}.
     *
     * The position is always between {@link Vec#from} and {@link Vec#to}
     */
    public abstract Pos offset(int offset);

    /**
     * Prints this trail object on the client
     */
    public void printParticles(World world) {
        // Iterate through our steps
        for(int i = 0; i < this.steps; i++) {

            // Get our next offset given current step
            Pos print = offset(i);

            // Print particle at that offset
            printer.display(this.particle, print, world);
        }
    }

    /**
     * Prints this trail object on the server
     */
    public void printParticles(ServerWorld world) {

        // Iterate through our steps
        for(int i = 0; i < this.steps; i++) {

            // Get our next offset given current step
            Pos print = offset(i);

            // Print particle at that offset
            printer.display(this.particle, print, world);
        }
    }

    /**
     * Print the particles in another color on the client
     */
    public void printColored(World world, float[] colors) {
        for(int i = 0; i < this.steps; i++) {

            // Get our next offset given current step
            Pos print = offset(i);

            // Print particle at that offset
            printer.displayColored(this.particle, print, world, colors);
        }
    }

    /**
     * Most safe way to print your vectrail. If the offset exceeds the limit, it will rescale the bigger offset
     */
    public int getScaledOffset(int offset) {
        return isStepInvalid(offset) ? offset % steps : offset;
    }

    /**
     * Determines if the calculated step is not bigger than the initialized steps value.
     */
    public boolean isStepInvalid(int step) {
        return step > steps;
    }

    /**
     * Returns the amount of steps this object has
     */
    public int getSteps() {
        return this.steps;
    }

    /**
     * Returns a printed version of
     */
    public String getPrint() {
        StringBuilder builder = new StringBuilder();
        builder.append("[").append(type).append("] from: ").append(this.from().getPrint()).append(" ; to: ").append(this.to().getPrint());
        return builder.toString();
    }

    public String getType() {
        return this.type;
    }

}
