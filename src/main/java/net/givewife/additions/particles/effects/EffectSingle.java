package net.givewife.additions.particles.effects;

import net.givewife.additions.util.positions.Pos;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class EffectSingle extends CustomEffect {

    private final Pos pos;
    private final DefaultParticleType type;

    public EffectSingle(Pos pos, DefaultParticleType type) {
        this.pos = pos;
        this.type = type;
    }

    @Override
    public void run(ServerWorld world) {

        world.spawnParticles(type, pos.x(), pos.y(), pos.z(), 1, 0, 0, 0, 0);

    }

    @Override
    public void run(World world) {
        world.addParticle(type, pos.x(), pos.y(), pos.z(), 0, 0, 0);

    }
}
