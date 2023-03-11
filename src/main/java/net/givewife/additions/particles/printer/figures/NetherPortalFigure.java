package net.givewife.additions.particles.printer.figures;

import net.givewife.additions.particles.printer.ParticleFigure;
import net.givewife.additions.particles.printer.TextureGrabber;
import net.givewife.additions.util.positions.Pos;
import net.minecraft.util.math.Direction;

public class NetherPortalFigure extends ParticleFigure {

    public NetherPortalFigure(Pos origin, Direction facing) {
        super(origin, facing, true,true, true);
    }

    @Override
    public FigureMap[] getMaps() {
        return new FigureMap[] {

                new FigureMap(Side.SIDE_S, "nether_portal")

        };
    }

}
