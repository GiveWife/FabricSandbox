package net.givewife.additions.particles.customparticles.figures;

import net.givewife.additions.particles.customparticles.ParticleDrawer;
import net.givewife.additions.util.Tuple;
import net.givewife.additions.util.positions.Pos;

public class ObsidianFigure extends ParticleDrawer {

    public ObsidianFigure(Pos pos) {
        super(pos);
    }

    @Override
    public Tuple<Pos, float[]>[] getDrawPositions() {
        return new Tuple[0];
    }
}
