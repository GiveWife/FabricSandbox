package net.givewife.additions.particles.effects;

import net.givewife.additions.particles.CustomEffect;
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

        BodyLocations loc = new BodyLocations(new Pos(player), Math.toRadians(player.bodyYaw));

        double ang = loc.getAngle();
        double hypotenus = loc.getHypotenusBody();

        float bodyYaw = (float) Math.toRadians(player.bodyYaw);
        //System.out.println("Angle in Body: " + ang + " <-> from computation: " + Math.atan(bodyWidth/bodyLength));
        System.out.println(" <-> field: " + Math.toRadians(player.bodyYaw));
        System.out.println("Computed angle: " + (ang + bodyYaw) + " == " + Math.toDegrees(ang+Math.toRadians(player.bodyYaw)));

        double diff = ang + bodyYaw;

        double addZ = Math.sin(diff) * hypotenus;
        double addX = Math.cos(diff) * hypotenus;

        System.out.println("Adds: " + addX + ", adZ: " + addZ);

        Pos center = new Pos(player.getX(), player.getY() + 1.2, player.getZ());

        Pos left = new Pos(player.getX()+addX, player.getY() + loc.getBodyTopHeight(), player.getZ()+addZ);
        Pos right = new Pos(player.getX()+addZ, player.getY() + loc.getBodyTopHeight(), player.getZ()+addX);

        Pos trial = loc.getPosTopLeft();

        //world.addParticle(ParticleTypes.CRIT, trial.x(), trial.y(), trial.z(), 0, 0, 0);
        //world.addParticle(ParticleTypes.END_ROD, left.x(), left.y(), left.z(), 0, 0, 0);
        //world.addParticle(ParticleTypes.CRIT, right.x(), right.y(), right.z(), 0, 0, 0);

        // The 0 spawns on the left side arm when looking south! spawnTrail(0, hypotenus, world, ParticleTypes.CRIT);

        loc.spawn(world,
                loc.getPosTopLeftBack(),
                loc.getPosTopLeft(),
                loc.getPosTopRight(),
                loc.getPosTopRightBack());

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
