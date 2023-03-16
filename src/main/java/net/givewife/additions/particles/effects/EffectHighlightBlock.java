package net.givewife.additions.particles.effects;

import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.givewife.additions.particles.printer.ParticleFigure;
import net.givewife.additions.particles.printer.figures.SideAllFigure;
import net.givewife.additions.util.positions.Pos;
import net.givewife.additions.util.positions.Trail;
import net.givewife.additions.util.positions.VecTrail;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EffectHighlightBlock extends CustomEffect {

    private final BlockPos pos;

    public EffectHighlightBlock(BlockPos pos) {
        this.pos = pos;
    }

    @Override
    public void run(ServerWorld world) {

        ParticleFigure figure = new SideAllFigure(new Pos(this.pos), ParticleFigure.formatMinecraft("cobblestone"));
        figure.print(world);

    }

    @Override
    public void run(World world) {

        ParticleFigure figure = new SideAllFigure(new Pos(this.pos), ParticleFigure.formatMinecraft("cobblestone"));
        figure.print(world);

        Trail trail = new VecTrail("pointer", new Pos(this.pos).east(0.5d).south(0.5d).up(1), new Pos(this.pos).east(0.5d).south(0.5d).up(2), ParticleTypes.FLAME);
        trail.printParticles(world);

    }

}
