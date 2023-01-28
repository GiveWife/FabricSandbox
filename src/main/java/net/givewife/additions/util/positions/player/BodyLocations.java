package net.givewife.additions.util.positions.player;

import net.givewife.additions.util.MathHelper;
import net.givewife.additions.util.positions.Pos;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

public class BodyLocations {

    /**
     * Static fields to spare memory
     */
    private static final double bodyWidth = 0.60d;
    private static final double bodyLength = 0.7d;
    private static final double bodyTopHeight = 1.4d;
    private static final double angle = Math.atan((bodyWidth/2)/(bodyLength/2));
    private static final double hypotenus = Math.abs(((bodyWidth/2)*(bodyWidth/2)) + ((bodyLength/2)*(bodyLength/2)));

    private MathHelper m = new MathHelper();

    public BodyLocations(Pos playerPosition, double yaw) {
    }

    /**
     * Constructor for one time use
     */
    public BodyLocations() {}

    public Pos getPosTopLeft(Pos playerPosition, double yaw) {
        return new Pos(playerPosition.x() + addX(angle, yaw), playerPosition.y() + bodyTopHeight, playerPosition.z() + addZ(angle, yaw));
    }

    public Pos getPosTopRight(Pos playerPosition, double yaw) {
        return new Pos(playerPosition.x() + addX(Math.PI - angle, yaw), playerPosition.y() + bodyTopHeight, playerPosition.z() + addZ(Math.PI - angle, yaw));
    }

    public Pos getPosTopRightBack(Pos playerPosition, double yaw) {
        return new Pos(playerPosition.x() + addX(Math.PI + angle, yaw), playerPosition.y() + bodyTopHeight, playerPosition.z() + addZ(Math.PI + angle, yaw));
    }


    public Pos getPosTopLeftBack(Pos playerPosition, double yaw) {
        return new Pos(playerPosition.x() + addX(-angle, yaw), playerPosition.y() + bodyTopHeight, playerPosition.z() + addZ(-angle, yaw));
    }


    /**
     * Calculates the X that should be added to the player's position for correct display.
     * Includes the angle, so we can change the angle depending on which bodypart we use.
     */
    public double addX(double angle, double bodyYaw) {
        double ang = angle + bodyYaw;
        return Math.cos(ang)*hypotenus;
    }

    public double addZ(double angle, double bodyYaw) {
        double ang = angle + bodyYaw;
        return Math.sin(ang)*hypotenus;
    }

    public void spawn(World world, Pos... pos) {
        int len = pos.length;
        for(int i = 0; i < len; i++) {
            world.addParticle(ParticleTypes.END_ROD, pos[i].x(), pos[i].y(), pos[i].z(), 0, 0, 0);
        }
    }

}
