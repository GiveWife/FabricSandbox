package net.givewife.additions.util.positions;

import net.givewife.additions.util.MathHelper;
import org.joml.Math;

public class Parabola extends Trail{

    private final Pos start, end;
    private final double height;
    private final double[] coef;
    private final int steps;
    private final VecTrail trail;

    public Parabola(Pos start, Pos end, double height, int steps) {
        super("Parabola", start, end, steps);
        this.start = start;
        this.height = start.y() >= end.y() ? height + start.y() : end.y() + height;
        this.end = end;
        this.coef = calculate();
        this.steps = steps;
        this.trail = new VecTrail("trail", this.start, this.end, steps);
    }

    /**
     * Default steps of 10 per block
     */
    public Parabola(Pos start, Pos end, double height) {
        super("Parabola", start, end, (int) start.distance(end));
        this.start = start;
        this.height = start.y() >= end.y() ? height + start.y() : end.y() + height;
        this.end = end;
        this.coef = calculate();
        this.steps = (int) start.distance(end) * 10;
        this.trail = new VecTrail("trail", this.start, this.end, steps);
    }

    /**
     * This function assumes that start is in an imaginary (0,0) spot. Now we can further assume that
     * our destination position is at a certain (x, 0) from our start, which is exactly the distance.
     *
     * We can simply add an Y coordinate to that: (distxz(start,end), y)
     */
    private double[] calculate() {

        // Get distance between points
        double distance = start.distancexz(end);
        double interY = height - this.start.y();
        double endY = this.end.y() - this.start.y();

        // Oplossingmatrix: c = 0
        double[] row1 = new double[] { distance*distance, distance, endY };
        double[] row2 = new double[] { distance*distance*0.25, distance*0.5, interY };
        MathHelper helper = new MathHelper();

        row1 = helper.rowOp(row1, row2, -row1[0]/row2[0]);
        double b = row1[2] / row1[1];
        double a = (row2[2] - (row2[1]*b)) * (1/row2[0]);

        return new double[]{a, b};

    }

    /**
     * This method allows to get an offset position between the two coordinates on the parabola
     */
    @Override
    public Pos offset(int offset) {
        // Get xz point from trail
        Pos trailPoint = trail.offset(offset);

        // How much "xz" value we add to our startDist
        double distanceStep = start.distancexz(end) / steps;

        // This is our xz value currently
        double distance = offset*distanceStep;
        double yPos = trailPoint.y() + (distance*distance*coef[0]) + (distance*coef[1]);

        return new Pos(trailPoint.x(), yPos, trailPoint.z());
    }

}
