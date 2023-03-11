package net.givewife.additions.particles.printer.figures;

import net.givewife.additions.particles.printer.ParticleFigure;
import net.givewife.additions.util.positions.Pos;

public class ObsidianFigure extends ParticleFigure {

    public ObsidianFigure(Pos origin) {
        super(origin, true, false);
    }

    @Override
    public FigureMap[] getMaps() {
        return new FigureMap[] {
                new FigureMap(Side.ALL, "obsidian")
        };
    }

}
