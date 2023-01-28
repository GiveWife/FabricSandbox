package net.givewife.additions.particles.effects;

import net.givewife.additions.particles.CustomEffect;
import net.givewife.additions.util.positions.VecTrail;
import net.givewife.additions.util.positions.player.BodyLocations;
import net.givewife.additions.util.positions.Pos;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class EffectPlayer extends CustomEffect {

    private PlayerEntity player;

    public EffectPlayer(PlayerEntity player) {
        this.player = player;
    }

    public void updatePlayer(PlayerEntity p) {
        this.player = p;
    }

    @Override
    public void run(ServerWorld world) {

    }

    @Override
    public void run(World world) {

        BodyLocations loc = new BodyLocations();
        Pos playerPos = new Pos(player);
        double yaw = Math.toRadians(player.bodyYaw);

        VecTrail[] trails = new VecTrail[] {

        };

    }

    private void spawnTrail(double ang, double hypo, World world, DefaultParticleType type) {
        double step = hypo/10;
        for(int i = 0; i < 10; i++) {
            Pos right = new Pos(player.getX()+cosine(ang, i*step), player.getY() + 1.2, player.getZ()+sinus(ang, i*step));
            world.addParticle(type, right.x(), right.y(), right.z(), 0, 0, 0);
        }
    }

    private double sinus(double ang, double hypo) {
        return Math.sin(ang) * hypo;
    }

    private double cosine(double ang, double hypo) {
        return Math.cos(ang) * hypo;
    }

}
