package net.givewife.additions.util.positions.player;

import net.givewife.additions.util.MathHelper;
import net.givewife.additions.util.positions.Pos;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

public class BodyLocations {

    private final double bodyWidth = 0.60d;
    private final double bodyLength = 0.7d;
    private final double bodyTopHeight = 1.4d;

    private double bodyYaw;
    private Pos playerPosition;
    private MathHelper m = new MathHelper();

    public BodyLocations(Pos playerPosition, double yaw) {
        this.playerPosition = playerPosition;
        this.bodyYaw = yaw;
    }

    public Pos getPosTopLeft() {
        return new Pos(playerPosition.x() + addX(getAngle()), playerPosition.y() + bodyTopHeight, playerPosition.z() + addZ(getAngle()));
    }

    public Pos getPosTopRight() {
        return new Pos(playerPosition.x() + addX(Math.PI - getAngle()), playerPosition.y() + bodyTopHeight, playerPosition.z() + addZ(Math.PI - getAngle()));
    }

    public Pos getPosTopRightBack() {
        return new Pos(playerPosition.x() + addX(Math.PI + getAngle()), playerPosition.y() + bodyTopHeight, playerPosition.z() + addZ(Math.PI + getAngle()));
    }

    public Pos getPosTopLeftBack() {
        return new Pos(playerPosition.x() + addX(-getAngle()), playerPosition.y() + bodyTopHeight, playerPosition.z() + addZ(-getAngle()));
    }

    /*
     * Returns an angle between the half of the body width & length
     */
    public double getAngle() {
        return Math.atan((bodyWidth/2)/(bodyLength/2));
    }

    /**
     * Returns the hypotenus between the body center and a corner
     */
    public double getHypotenusBody() {
        return Math.abs(m.kwad(bodyWidth/2) + m.kwad(bodyLength/2));
    }

    public double getBodyTopHeight() {
        return bodyTopHeight;
    }

    /**
     * Calculates the X that should be added to the player's position for correct display.
     * Includes the angle, so we can change the angle depending on which bodypart we use.
     */
    public double addX(double angle) {
        double ang = angle + bodyYaw;
        return Math.cos(ang)*getHypotenusBody();
    }

    public double addZ(double angle) {
        double ang = angle + bodyYaw;
        return Math.sin(ang)*getHypotenusBody();
    }

    public void spawn(World world, Pos... pos) {
        for(int i = 0; i < pos.length; i++) {
            world.addParticle(ParticleTypes.END_ROD, pos[i].x(), pos[i].y(), pos[i].z(), 0, 0, 0);
        }
    }

}
