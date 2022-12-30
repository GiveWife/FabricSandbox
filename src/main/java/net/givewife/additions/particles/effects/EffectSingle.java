package net.givewife.additions.particles.effects;

import net.givewife.additions.particles.CustomEffect;
import net.givewife.additions.util.Pos;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;

public class EffectSingle extends CustomEffect {

    public EffectSingle() {

    }

    @Override
    public void run(ServerWorld world, Pos pos) {

        world.spawnParticles(ParticleTypes.END_ROD, pos.x(), pos.y(), pos.z(), 1, 0, 0, 0, 0);

    }

}
