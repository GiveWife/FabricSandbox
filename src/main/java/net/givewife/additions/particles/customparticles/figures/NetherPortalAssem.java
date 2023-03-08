package net.givewife.additions.particles.customparticles.figures;

import net.givewife.additions.particles.customparticles.ParticleDrawer;
import net.givewife.additions.util.positions.Pos;
import net.minecraft.world.World;

public class NetherPortalAssem {

    public NetherPortalAssem(Pos origin, World world) {
        print(origin, world);
    }

    private void print(Pos origin, World world) {

        /*
        // we starten uiterst links
        ParticleDrawer[] obs = new ParticleDrawer[] {

                // bot
                new ObsidianFigure(origin),
                new ObsidianFigure(origin.south(1)),
                new ObsidianFigure(origin.south(2)),
                new ObsidianFigure(origin.south(3)),

                //pilaar 1
                new ObsidianFigure(origin.up(1)),
                new ObsidianFigure(origin.up(2)),
                new ObsidianFigure(origin.up(3)),
                new ObsidianFigure(origin.up(4)),

                // top
                new ObsidianFigure(origin.up(4).south(1)),
                new ObsidianFigure(origin.up(4).south(2)),
                new ObsidianFigure(origin.up(4).south(3)),

                // pilaar 2
                new ObsidianFigure(origin.up(3).south(3)),
                new ObsidianFigure(origin.up(2).south(3)),
                new ObsidianFigure(origin.up(1).south(3)),

        };

        ParticleDrawer[] portal = new ParticleDrawer[] {

                new NetherPortalFigure(origin.up(1).south(1)),
                new NetherPortalFigure(origin.up(1).south(2)),
                new NetherPortalFigure(origin.up(2).south(1)),
                new NetherPortalFigure(origin.up(2).south(2)),
                new NetherPortalFigure(origin.up(3).south(1)),
                new NetherPortalFigure(origin.up(3).south(2)),

        };

        for(ParticleDrawer p : obs)
            p.print(world);

        for(ParticleDrawer p : portal)
            p.print(world);*/

    }

}
