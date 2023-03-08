package net.givewife.additions.particles.customparticles;

import net.givewife.additions.registry.registries.ParticleRegistry;
import net.givewife.additions.util.Tuple;
import net.givewife.additions.util.positions.Pos;
import net.minecraft.world.World;

/**
 * Clean interface for spawning particles
 */
public abstract class ParticleDrawer {

    private ParticlePrinter particlePrinter = new ParticlePrinter();
    private final Pos origin;
    private final Side[] sides;
    private FigureMap[] maps;

    /**
     * Side[] argument determines which sides should be drawn
     */
    public ParticleDrawer(Pos origin, Side[] sides) {
        this.origin = origin;
        this.sides = sides;
    }

    /**
     * Returns the place from where a figure should be spawned
     */
    public Pos getOrigin() {
        return this.origin;
    }

    /**
     * Returns all possible sides for this object
     */
    public abstract Side[] getPossibleSides();

    /**
     * When the object is called for draw, we first check which sides must be printed using the
     * {@link ParticleDrawer#sides} variable
     */
    /*private final Tuple<Pos, float[]>[] getDrawPositions() {



    }*/

    /**
     * Returns all colored maps for this object
     */
    public abstract FigureMap[] getMaps();

    /**
     * Prints the figure
     */
    public final void print(World world) {

        // First initialize the FigureMaps:
        this.maps = getMaps();

        /*
        Tuple<Pos, float[]>[] tuples = getDrawPositions();

        for(Tuple<Pos, float[]> tuple : tuples) {

            particlePrinter.displayColored(ParticleRegistry.WHITE_STAR, tuple.getFirst(), world, tuple.getSecond());

        }*/

    }

    /**
     * Helper class that holds the color map for a specific side
     */
    public static abstract class FigureMap {

        private final Side side;

        public FigureMap(Side side) {
            this.side = side;
        }

        public abstract float[] getMap();

    }


    /**
     * Sides:
     *
     *       SE
     *    O - - O
     *E   |     | ES
     *    |     |
     *    O - - O
     *       S
     */
    public enum Side {
        ALL, TOP, SIDE_E, SIDE_S, SIDE_ES, SIDE_SE, BOT
    }

    /**
     * This will return the
     */
    /**
    protected Tuple<Pos, float[]> assembler(Side side) {



    }*/



}
