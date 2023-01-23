package net.givewife.additions.particles;

import net.givewife.additions.util.positions.Pos;
import net.minecraft.server.world.ServerWorld;

public abstract class CustomEffect {

    private boolean debug = false;
    private String name = "Undefined";

    /**
     * PI reference
     */
    public final double pi = Math.PI;
    /**
     * One degree represented in radials
     */
    public final double step = (2*Math.PI) / 360;

    public CustomEffect() {}
    public CustomEffect(String name, boolean debug) {
        this.debug = debug;
        this.name = name;
    }

    public double sin(double d) {
        if(debug) System.out.println("   " + name + " sin(" + d + ") = " + Math.sin(d));
        return Math.sin(d);
    }

    public double cos(double d) {
        if(debug) System.out.println("   " + name + " cos(" + d + ") = " + Math.cos(d));
        return Math.cos(d);
    }

    public abstract void run(ServerWorld world);

}
