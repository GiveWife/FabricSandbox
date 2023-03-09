package net.givewife.additions.particles.customparticles;

import net.givewife.additions.util.positions.Pos;

@FunctionalInterface
public interface OffsetPos {

    public Pos offset(Pos origin, int rowIndex, int upIndex);

}
