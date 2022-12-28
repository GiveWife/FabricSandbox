package net.givewife.additions.particles.effects;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.givewife.additions.particles.CustomEffect;
import net.givewife.additions.util.Pos;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EffectTrace extends CustomEffect {

    public EffectTrace(String name, boolean debug, int tick) { super(name, debug, tick); }

    public void run(ServerWorld world, Pos pos) {

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

}
