package net.givewife.additions.particles.effects;

import net.givewife.additions.particles.CustomEffect;
import net.givewife.additions.util.positions.Pos;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
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

        double bodyWidth = 0.4d;
        double bodyLength = 0.8d;
        double hypotenus = Math.sqrt( Math.pow(bodyWidth/2, 2) + Math.pow(bodyLength/2, 2)); // 0.471d
        double ang = 0.558599315;
        player.updateLimbs(player, true);
        float bodyYaw = (float) Math.toRadians(player.bodyYaw);
        double diff = ang + bodyYaw;

        double addZ = Math.sin(diff) * hypotenus;
        double addX = Math.cos(diff) * hypotenus;

        Pos center = new Pos(player.getX(), player.getY() + 1.2, player.getZ());
        Pos left = new Pos(player.getX()+addX, player.getY() + 1.2, player.getZ()+addZ);

        //System.out.println("Trying to print at: " + left.getPrint() + "; knowing that hypotenus: " + hypotenus + ", bodyYaw: " + bodyYaw + ", diff: " + diff + ", and addZ: " + addZ + ", addX: " + addX);

        world.spawnParticles(ParticleTypes.END_ROD, left.x(), left.y(), left.z(), 1, 0, 0, 0, 0);
        world.spawnParticles(ParticleTypes.FIREWORK, center.x(), center.y(), center.z(), 1, 0, 0, 0, 0);

    }

    @Override
    public void run(World world) {

        double bodyWidth = 0.4d;
        double bodyLength = 0.8d;
        double hypotenus = Math.sqrt( Math.pow(bodyWidth/2, 2) + Math.pow(bodyLength/2, 2)); // 0.471d
        double ang = 0.558599315;
        //System.out.println("Ang: " + ang + ", computation: " + Math.atan(bodyWidth/bodyLength));
        ang = -Math.atan(bodyWidth/bodyLength);

        float bodyYaw = (float) Math.toRadians(player.bodyYaw);
        double diff = ang + bodyYaw;

        double addZ = Math.sin(diff) * hypotenus;
        double addX = Math.cos(diff) * hypotenus;

        Pos center = new Pos(player.getX(), player.getY() + 1.2, player.getZ());
        Pos left = new Pos(player.getX()+addX, player.getY() + 1.2, player.getZ()+addZ);

        //System.out.println("AATrying to print at: " + left.getPrint() + "; knowing that hypotenus: " + hypotenus + ", bodyYaw: " + bodyYaw + ", diff: " + diff + ", and addZ: " + addZ + ", addX: " + addX);

        world.addParticle(ParticleTypes.END_ROD, left.x(), left.y(), left.z(), 0, 0, 0);


    }

}
