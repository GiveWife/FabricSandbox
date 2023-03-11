package net.givewife.additions.particles.printer;

import net.givewife.additions.registry.registries.ParticleRegistry;
import net.givewife.additions.util.Tuple;
import net.givewife.additions.util.positions.Pos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

/**
 * Clean interface for spawning particles
 */
public abstract class ParticleFigure {

    private ParticlePrinter particlePrinter = new ParticlePrinter();
    private final Pos origin;
    private final Direction facing;
    private FigureMap[] maps;
    private final boolean isFacedFigure, renderTop, renderBot;

    /**
     * For faced figures
     */
    public ParticleFigure(Pos origin, Direction facing, boolean isFacedFigure, boolean renderTop, boolean renderBot) {
        this.origin = origin;
        this.facing = facing;
        this.isFacedFigure = isFacedFigure;
        this.renderTop = renderTop;
        this.renderBot = renderBot;
    }

    /**
     * For non faced textures
     */
    public ParticleFigure(Pos origin, boolean renderTop, boolean renderBot) {
        this.origin = origin;
        this.facing = Direction.UP;
        this.isFacedFigure = false;
        this.renderTop = renderTop;
        this.renderBot = renderBot;
    }

    /**
     * Returns the place from where a figure should be spawned
     */
    public Pos getOrigin() {
        return this.origin;
    }

    /**
     * Returns all colored maps for this object. This is obligatory for every instance, since we need this
     * to give the particles colors.
     */
    public abstract FigureMap[] getMaps();

    /**
     * When the figure maps are initialized, we can then derive which sides have unique textures:
     * ALL: every side the same
     * TOP, BOT...
     * This information is necessary for when the figure is to be drawn. A method must generate
     * the different sides using {@link OffsetPos#offset(Pos, int, int)}.
     */
    private Side[] getInitializedSides() {
        FigureMap[] maps = getMaps();
        Side[] sides = new Side[7];
        int count = 0;

        // Return empty array if invalid!
        if(!(maps.length > 0)) {
            System.out.println("getMaps() returned an empty list in " + this.getClass().getName());
            return sides;
        }

        // Return Side.ALL if an object has that value
        if(maps[0].side == Side.ALL) {
            sides = new Side[] {Side.SIDE_E, Side.SIDE_ES, Side.SIDE_S, Side.SIDE_SE, Side.BOT, Side.TOP};
            return sides;
        }

        // Loop through our maps and gather the sides
        for(FigureMap m : maps) {
            sides[count] = m.side;
            count++;
        }

        return sides;

    }

    /**
     * Prints the figure
     */
    public final void print(World world) {

        // First initialize the FigureMaps:
        this.maps = getMaps();

        PrintInformation[] information = getPrintInformation(maps);


        for(PrintInformation printInfo : information) {

            for(Tuple<Pos, float[]> tuple : printInfo.getPrintInformation())
                particlePrinter.displayColored(ParticleRegistry.WHITE_STAR, tuple.getFirst(), world, tuple.getSecond());

        }

    }


    /**
     * When trying to print objective given Side enum, how must we alter this side for faced blocks?
     */
    private Side correctFacing(Side side) {

        // If the block has no face: grass/stone <-> furnace, dropper... We can safely print out all sides
        if(!isFacedFigure
            || side == Side.TOP
            || side == Side.ALL
            || side == Side.BOT
        ) return side;

        // We assume that each faced figure is initialized with its face at S position == WEST

        // In this case, player faces to SOUTH, face should be at E side.
        if(facing == Direction.SOUTH) {
            return left(side);
        }
        else if(facing == Direction.WEST) {
            return left(left(side));
        }
        else if(facing == Direction.NORTH) {
            return right(side);
        }
        // Default to east
        return side;

    }

    /**
     * Returns the sides that should be printed for this object.
     * We use {@link ParticleFigure#renderBot} and {@link ParticleFigure#renderTop} booleans for this.
     *
     * On the other hand, we must think about what kind of figure maps this object has initialized.
     * The FigureMap objects are initialized on object creation, from there we can see which sides are textures
     */
    private Side[] getSides() {
        // We gather the initialized sides, then we filter.
        Side[] sides = getInitializedSides();
        Side[] filtered = new Side[7];
        int count = 0;

        for(int i = 0; i < sides.length; i++) {

            if(sides[i] == Side.TOP && renderTop) filtered[count] = Side.TOP;
            if(sides[i] == Side.BOT && renderBot) filtered[count] = Side.BOT;
            else filtered[count] = sides[i];

            count++;

        }

        return filtered;

    }

    private Side right(Side side) {
        if(side == Side.SIDE_S) return Side.SIDE_ES;
        else if(side == Side.SIDE_ES) return Side.SIDE_SE;
        else if(side == Side.SIDE_SE) return Side.SIDE_E;
        else return Side.SIDE_S;
    }

    private Side left(Side side) {
        if(side == Side.SIDE_S) return Side.SIDE_E;
        else if(side == Side.SIDE_ES) return Side.SIDE_S;
        else if(side == Side.SIDE_SE) return Side.SIDE_ES;
        else return Side.SIDE_SE;
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
     * This will return the information to print a side of a block
     */
    private PrintInformation[] getPrintInformation(FigureMap[] figureMaps) {

        // We first gather all sides that should be printed -> this length should be equal to FigureMap length!
        Side[] printSides = getSides();


        PrintInformation[] information = new PrintInformation[printSides.length];

        // We loop over all sides, since every sides -> figuremap needs a print information
        // This loop basically combines FigureMap#float[] with Pos which changes dynamically!
        for(int i = 0; i < printSides.length; i++) {

            // We create an instance of our tuple
            Tuple<Pos, float[]>[] positions = new Tuple[FigureMap.PRECISION * FigureMap.PRECISION];
            int count = 0;

            // Replace the given side with a corrected side;
            if(isFacedFigure)
                printSides[i] = correctFacing(printSides[i]);

            // Get our function that changes the Pos object.
            OffsetPos function = constructer(printSides[i]);

            for(int row = 0; row < FigureMap.PRECISION; row++) {

                for(int up = 0; up < FigureMap.PRECISION; up++) {

                    // If we have Side.ALL, we only use the first map in the array
                    float[] rgbForPos = figureMaps.length <= 1 ? figureMaps[0].getMap()[count] : figureMaps[i].getMap()[count];
                    positions[count] = new Tuple<Pos, float[]>(function.offset(origin, row, up), rgbForPos);

                    count++;

                }

            }

            // Fill the array of information with the tuples generated. This represents one side
            information[i] = new PrintInformation(printSides[i], positions);

        }

        return information;

    }

    /**
     * This method is public so any special blocks can be printed in a special way
     */
    public OffsetPos constructer(Side side) {

        int prec = FigureMap.PRECISION;

        if(side == Side.SIDE_E) return (p, row, up) -> p.east((float) row/prec)
                                                        .up(((float) 1 - (float) up/prec));

        else if(side == Side.SIDE_S) return (p, row, up) -> p.south((float) row/prec)
                                                             .up(((float) 1 - (float) up/prec));

        else if(side == Side.SIDE_ES) return (p, row, up) -> p.east((float) row/prec)
                                                              .up(((float) 1 - (float) up/prec))
                                                              .south(1);

        else if(side == Side.SIDE_SE) return (p, row, up) -> p.south((float) row/prec)
                                                              .up(((float) 1 - (float) up/prec))
                                                              .east(1);

        else if(side == Side.TOP) return (p, row, up) -> p.south((float) row/prec)
                                                          .up(1)
                                                          .east((float) (1 - (float) up/prec));

        else if(side == Side.BOT) return (p, row, up) -> p.south((float) row/prec)
                                                          .east((float) (1 - (float) up/prec));



        else {
            System.out.println("Returned a default lambda that doesn't change the origin: " + side);
            return (p, row, up) -> p;
        }

    }



    /**
     * Helper class that holds the color map for a specific side.
     * Gets setup in {@link ParticleFigure#getMaps()} method, which is obligatory in every instance of {@link ParticleFigure}
     */
    public static class FigureMap {

        private final Side side;
        /**
         * The precision of the rgb maps: every texture is lowered to 10x10 from 16x16
         */
        public static final int PRECISION = 10;
        private final float[][] map;

        public FigureMap(Side side, String modelName) {
            this.side = side;
            TextureGrabber grabber = new TextureGrabber(modelName);
            this.map = grabber.getRgb();
        }

        // This should be a double array since we have 10x10 pixels!
        public final float[][] getMap() {
            return this.map;
        }

    }

    /**
     * Helper class that holds positions + color information for a specific side.
     * Gets setup in the {@link ParticleFigure#getPrintInformation(FigureMap[])} method.
     */
    public static class PrintInformation {

        private final Tuple<Pos, float[]>[] printInformation;
        private final Side side;

        public PrintInformation(Side side, Tuple<Pos, float[]>[] printInformation) {
            this.printInformation = printInformation;
            this.side = side;
        }

        public final Tuple<Pos, float[]>[] getPrintInformation() {
            return this.printInformation;
        }

        public final Side getSide() {
            return this.side;
        }

    }




}
