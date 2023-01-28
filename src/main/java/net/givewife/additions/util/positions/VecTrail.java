package net.givewife.additions.util.positions;

import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class VecTrail extends Vec {

    private double xD, yD, zD;
    private double stepX, stepY, stepZ;
    private int steps;
    private final String id;
    public VecTrail(String id, Pos from, Pos to, int steps) {
        super(from, to);
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
        super(from, to);
        this.id = id;

        this.xD = from.x() - to.x();
        this.yD = from.y() - to.y();
        this.zD = from.z() - to.z();

        this.steps = (int) getBiggest(xD, yD, zD) * 10;

        this.stepX = -xD / this.steps;
        this.stepY = -yD / this.steps;
        this.stepZ = -zD / this.steps;
    }

    private double getBiggest(double x, double y, double z) {
        if(x >= y && x >= z) return x;
        if(y >= x && y >= z) return y;
        else return z;
    }

    /**
     * Returns a Pos object that gives an offset of choice on the trail between the two vectors
     */
    public Pos getOffset(int offset) {
        offset = getScaledOffset(offset);
        return new Pos(from().x() + (offset * stepX), from().y() + (offset * stepY), from().z() + (offset * stepZ));
    }

    private int getScaledOffset(int offset) {
        return isStepInvalid(offset) ? offset % steps : offset;
    }

    public boolean isStepInvalid(int step) {
        return step > steps;
    }

    @Override
    public void log(String message) {
        super.log("[Vectrail: + " + this.id + "] " + message);
    }

    public int getSteps() {
        return this.steps;
    }

    public void print(World world) {
        for(int i = 0; i < this.steps; i++) {
            Pos print = getOffset(i);
            world.addParticle(ParticleTypes.END_ROD, print.x(), print.y(), print.z(), 0, 0, 0);
        }
    }

    public void print(ServerWorld world) {
        for(int i = 0; i < this.steps; i++) {
            Pos print = getOffset(i);
            world.spawnParticles(ParticleTypes.END_ROD, print.x(), print.y(), print.z(), 1, 0, 0, 0, 0);
        }
    }

    public String getPrint() {

        return "[VecTrail] from: " + from().getPrint() + " --> to: " + to().getPrint();

    }

    //public static VecTrail[] getBox(Pos p1) {



    //}

}
