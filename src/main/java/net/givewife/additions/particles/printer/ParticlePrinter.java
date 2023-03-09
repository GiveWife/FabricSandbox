package net.givewife.additions.particles.printer;

import net.givewife.additions.util.positions.Pos;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.Particle;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class ParticlePrinter {

    public ParticlePrinter() {

    }

    public void displayColored(DefaultParticleType type, Pos p, World world, float[] colors) {
        if(world.isClient) {
            Particle particle = MinecraftClient.getInstance().particleManager.addParticle(type, p.x(), p.y(), p.z(), 0, 0, 0);
            particle.setColor(colors[0], colors[1], colors[2]);
        }
    }

    public void display(DefaultParticleType type, Pos p, World world) {
        world.addParticle(type, p.x(), p.y(), p.z(), 0, 0, 0);
    }

    public void displayServer(DefaultParticleType type, Pos p, ServerWorld world) {
        world.spawnParticles(type, p.x(), p.y(), p.z(), 1, 0, 0, 0, 0);
    }

}
