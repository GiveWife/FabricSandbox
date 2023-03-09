package net.givewife.additions.util.positions;

import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

/**
 * This class is used to represent a trail between the {@link Vec#from()} and {@link Vec#to()}
 *
 * We create a subclass implementation with extra functions in superclass {@link Trail}:
 *
 *      {@link Trail#offset(int)} : determine a {@link Pos} on the {@link Vec} object.
 *      {@link Trail#getSteps()} : determine how many steps we should take between start and stop position from {@link Vec} object
 *      {@link Trail#printParticles(World)} : print the trail in the world
 */
public class VecTrail extends Trail {

    private double xD, yD, zD;
    private double stepX, stepY, stepZ;
    private int steps;
    private final String id;

    public VecTrail(String id, Pos from, Pos to, int steps) {
        super("VecTrail", from, to, steps);
        this.id = id;

        this.xD = from.x() - to.x();
        this.yD = from.y() - to.y();
        this.zD = from.z() - to.z();

        this.steps = steps;

        this.stepX = -xD / steps;
        this.stepY = -yD / steps;
        this.stepZ = -zD / steps;

    }

    public VecTrail(String id, Pos from, Pos to, DefaultParticleType type) {
        super("VecTrail", from, to, (int) (from.distance(to) * 10), type);
        this.id = id;

        this.xD = from.x() - to.x();
        this.yD = from.y() - to.y();
        this.zD = from.z() - to.z();

        this.steps = (int) (from.distance(to) * 10);

        this.stepX = -xD / steps;
        this.stepY = -yD / steps;
        this.stepZ = -zD / steps;

    }

    public VecTrail(String id, Pos from, Pos to, DefaultParticleType type, int steps) {
        super("VecTrail", from, to, steps, type);
        this.id = id;

        this.xD = from.x() - to.x();
        this.yD = from.y() - to.y();
        this.zD = from.z() - to.z();

        this.steps = steps;

        this.stepX = -xD / steps;
        this.stepY = -yD / steps;
        this.stepZ = -zD / steps;

    }

    /**
     * Initializes the steps via 10 per block
     */
    public VecTrail(String id, Pos from, Pos to) {
        super("VecTrail", from, to, (int) (from.distance(to) * 10));
        this.id = id;

        this.xD = from.x() - to.x();
        this.yD = from.y() - to.y();
        this.zD = from.z() - to.z();

        this.steps = (int) from.distance(to) * 10;

        this.stepX = -xD / this.steps;
        this.stepY = -yD / this.steps;
        this.stepZ = -zD / this.steps;

    }

    /**
     * Returns a Pos object that gives an offset of choice on the trail between the two vectors
     */
    @Override
    public Pos offset(int offset) {
        offset = this.getScaledOffset(offset);
        return new Pos(from().x() + (offset * stepX), from().y() + (offset * stepY), from().z() + (offset * stepZ));
    }

    /**
     * Returns a Pos object that goes beyond the 2nd pos
     */
    public Pos getProjectedOffset(int offset) {
        return new Pos(from().x() + (offset*stepX), from().y() + (offset*stepY), from().z() + (offset*stepZ));
    }

    @Override
    public void log(String message) {
        super.log("[Vectrail: + " + this.id + "] " + message);
    }

    public int getSteps() {
        return this.steps;
    }

    public String getId() {
        return this.id;
    }

    //public static VecTrail[] getBox(Pos p1) {



    //}

}
