package net.givewife.additions.util.positions;

/**
 * This class determines a connection between two points in the 3D Minecraft world.
 *
 * Positions are represented with double values using the {@link net.givewife.additions.util.positions.Pos} class.
 */
public class Vec {

    /**
     * Defining objects for this class
     */
    private Pos from, to;

    /**
     * Initializes this object
     */
    public Vec(Pos from, Pos to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start position of this vector object
     */
    public Pos from() {
        return from;
    }

    /**
     * Returns the end position of this vector object
     */
    public Pos to() {
        return to;
    }

    public void log(String message) {
        System.out.println("[Vec] " + message);
    }

}
