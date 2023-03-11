package net.givewife.additions.particles.printer.figures;

import net.givewife.additions.particles.printer.ParticleFigure;
import net.givewife.additions.util.positions.Pos;

public class GlowstoneFigure extends ParticleFigure {

    public GlowstoneFigure(Pos origin) {
        super(origin, true, true);
    }

    @Override
    public FigureMap[] getMaps() {
        return new FigureMap[]{
                new FigureMap(Side.ALL, "glowstone")
        };
    }
}
