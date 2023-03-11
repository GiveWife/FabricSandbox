package net.givewife.additions.particles.printer.figures;

import net.givewife.additions.particles.printer.OffsetPos;
import net.givewife.additions.particles.printer.ParticleFigure;
import net.givewife.additions.particles.printer.TextureGrabber;
import net.givewife.additions.util.positions.Pos;
import net.minecraft.util.math.Direction;

public class NetherPortalFigure extends ParticleFigure {

    public NetherPortalFigure(Pos origin, Direction facing) {
        super(origin, facing, true,false, false);
    }

    @Override
    public FigureMap[] getMaps() {
        return new FigureMap[] {

                new FigureMap(Side.SIDE_S, "nether_portal_ns")

        };
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
    @Override
    public OffsetPos constructer(Side side) {

        int prec = FigureMap.PRECISION;

        if(side == Side.SIDE_E) return (p, row, up) -> p.east((float) row/prec).south(0.5d)
                .up(((float) 1 - (float) up/prec));

        else if(side == Side.SIDE_S) return (p, row, up) -> p.south((float) row/prec).east(0.5d)
                .up(((float) 1 - (float) up/prec));

        else if(side == Side.SIDE_ES) return (p, row, up) -> p.east((float) row/prec)
                .up(((float) 1 - (float) up/prec))
                .south(0.5d);

        else if(side == Side.SIDE_SE) return (p, row, up) -> p.south((float) row/prec)
                .up(((float) 1 - (float) up/prec))
                .east(0.5d);


        else {
            System.out.println("Returned a default lambda that doesn't change the origin: " + side);
            return (p, row, up) -> p;
        }

    }
}
