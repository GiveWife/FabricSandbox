package net.givewife.additions.particles.effects;

import net.givewife.additions.particles.CustomEffect;
import net.givewife.additions.util.positions.Pos;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class EffectTrace extends CustomEffect {

    private final Pos pos;

    public EffectTrace(String name, boolean debug, Pos pos) {
        super(name, debug);
        this.pos = pos;
    }

    @Override
    public void run(ServerWorld world) {

        double t = 0d;

        pos.print();
        while(t < 2*pi) {

            world.spawnParticles(ParticleTypes.END_ROD, pos.x() + cos(t), pos.y(), pos.z() + sin(t), 1, 0, 0, 0, 0);

            //PlayerLookup.tracking(world, pos.getBlockPos()).forEach(player -> world.spawnParticles(player, ParticleTypes.END_ROD, pos.x(), pos.y(), pos.z(), 1));
            //PlayerLookup.tracking(world, pos.getBlockPos()).forEach(player -> world.spawnParticles(player, (ParticleEffect) ParticleTypes.END_ROD, true, pos.x(), pos.y(), pos.z(), 1));


            //world.addParticle(ParticleTypes.END_ROD, true, pos.x() + cos(t), pos.y(), pos.z() + sin(t), 0, 0, 0);

            t += step;

        }

        //world.addImportantParticle();
    }

    @Override
    public void run(World world) {

    }
}
