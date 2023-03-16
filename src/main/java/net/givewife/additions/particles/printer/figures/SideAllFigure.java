package net.givewife.additions.particles.printer.figures;

import net.givewife.additions.particles.printer.ParticleFigure;
import net.givewife.additions.util.positions.Pos;

public class SideAllFigure extends ParticleFigure {

    private final String modelName;

    public SideAllFigure(Pos origin, String modelName) {
        super(origin, true, true);
        this.modelName = modelName;
    }

    public SideAllFigure(Pos origin, String modelName, boolean renderTop, boolean renderBot) {
        super(origin, renderTop, renderBot);
        this.modelName = modelName;
    }

    @Override
    public FigureMap[] getMaps() {
        return new FigureMap[] {
                new FigureMap(Side.ALL, modelName)
        };
    }

}
