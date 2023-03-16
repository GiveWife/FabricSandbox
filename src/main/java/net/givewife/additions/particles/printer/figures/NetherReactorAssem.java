package net.givewife.additions.particles.printer.figures;

import net.givewife.additions.particles.printer.ParticleFigure;
import net.givewife.additions.util.positions.Pos;
import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;

public class NetherReactorAssem {

    public NetherReactorAssem(Pos place, World world) {
        spawn(place, world);
    }

    private void spawn(Pos p, World world) {

        String name = ParticleFigure.formatMinecraft("cobblestone");

        ParticleFigure[] cobble = new ParticleFigure[] {


                new SideAllFigure(p, name),
                new SideAllFigure(p.north(), name),
                new SideAllFigure(p.north(2), name),

                new SideAllFigure(p.west(1), name),
                new SideAllFigure(p.west(1).north(1), name),
                new SideAllFigure(p.west(1).north(2), name),

                new SideAllFigure(p.west(2), name),
                new SideAllFigure(p.west(2).north(1), name),
                new SideAllFigure(p.west(2).north(2), name),

                new SideAllFigure(p.west(2).north(2).up(1), name),
                new SideAllFigure(p.west(2).up(1), name),
                new SideAllFigure(p.north(2).up(1), name),
                new SideAllFigure(p.up(1), name),

                new SideAllFigure(p.up(2), name),
                new SideAllFigure(p.north().up(2), name),
                new SideAllFigure(p.north(2).up(2), name),

                new SideAllFigure(p.west(1).up(2), name),
                new SideAllFigure(p.west(1).north(1).up(2), name),
                new SideAllFigure(p.west(1).north(2).up(2), name),

                new SideAllFigure(p.west(2).up(2), name),
                new SideAllFigure(p.west(2).north(1).up(2), name),
                new SideAllFigure(p.west(2).north(2).up(2), name),

                new SideAllFigure(p.north().west().up(), ParticleFigure.formatMod("nether_reactor"))


        };

        for(ParticleFigure fig : cobble)
            fig.print(world);

    }

}
