package net.givewife.additions.util.positions;

public class VecTrail extends Vec {

    private double xD, yD, zD;
    private double stepX, stepY, stepZ;
    private int steps;
    private final String id;
    public VecTrail(String id, Pos from, Pos to, int steps) {
        super(from, to);
        this.id = id;

        this.xD = Math.abs(from.x() - to.x());
        this.yD = Math.abs(from.y() - to.y());
        this.zD = Math.abs(from.z() - to.z());

        this.steps = steps;

        this.stepX = xD / steps;
        this.stepY = yD / steps;
        this.stepZ = zD / steps;
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
}
