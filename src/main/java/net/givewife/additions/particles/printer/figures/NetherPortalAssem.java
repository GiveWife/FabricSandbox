package net.givewife.additions.particles.printer.figures;

import net.givewife.additions.particles.printer.ParticleFigure;
import net.givewife.additions.util.positions.Pos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class NetherPortalAssem {

    public NetherPortalAssem(Pos origin, World world, Direction facing) {
        print(origin, world, facing);
    }

    private void print(Pos pos, World world, Direction facing) {


        // we starten uiterst links
        ParticleFigure[] obs = new ParticleFigure[] {

                // bot
                new SideAllFigure(pos, "obsidian", false, false),
                new SideAllFigure(pos.south(1), "obsidian", true, false),
                new SideAllFigure(pos.south(2), "obsidian", true, false),
                new SideAllFigure(pos.south(3), "obsidian", false, false),


                //pilaar 1
                new SideAllFigure(pos.up(1), "obsidian", false, false),
                new SideAllFigure(pos.up(2), "obsidian", false, false),
                new SideAllFigure(pos.up(3), "obsidian", false, false),
                new SideAllFigure(pos.up(4), "obsidian", true, false),

                // top
                new SideAllFigure(pos.up(4).south(1), "obsidian", true, true),
                new SideAllFigure(pos.up(4).south(2), "obsidian", true, true),
                new SideAllFigure(pos.up(4).south(3), "obsidian", true, false),

                // pilaar 2
                new SideAllFigure(pos.up(3).south(3), "obsidian", false, false),
                new SideAllFigure(pos.up(2).south(3), "obsidian", false, false),
                new SideAllFigure(pos.up(1).south(3), "obsidian", false, false),

        };

        ParticleFigure[] portal = new ParticleFigure[] {

                new NetherPortalFigure(pos.south(1).up(1), facing),
                new NetherPortalFigure(pos.south(1).up(2), facing),
                new NetherPortalFigure(pos.south(1).up(3), facing),
                new NetherPortalFigure(pos.south(2).up(1), facing),
                new NetherPortalFigure(pos.south(2).up(2), facing),
                new NetherPortalFigure(pos.south(2).up(3), facing)

        };



        for(ParticleFigure p : obs)
            p.print(world);

        for(ParticleFigure p : portal) {
            p.print(world);
        }

    }

}
