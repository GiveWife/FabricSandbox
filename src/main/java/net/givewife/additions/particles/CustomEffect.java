package net.givewife.additions.particles;

import net.givewife.additions.util.Pos;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public abstract class CustomEffect {

    private boolean debug;
    private int tick;
    private String name = "None";

    /**
     * PI reference
     */
    public final double pi = Math.PI;
    /**
     * One degree represented in radials
     */
    public final double step = (2*Math.PI) / 360;

    public CustomEffect() {}
    public CustomEffect(String name, boolean debug, int tick) {
        this.name = name;
        this.debug = debug;
        this.tick = tick;
    }

    public double sin(double d, boolean debug) {
        if(debug) System.out.println("   " + name + " sin(" + d + ") = " + Math.sin(d));
        return Math.sin(d);
    }

    public double sin(double d) {
        return Math.sin(d);
    }

    public double cos(double d, boolean debug) {
        if(debug) System.out.println("   " + name + " cos(" + d + ") = " + Math.cos(d));
        return Math.cos(d);
    }

    public double cos(double d) {
        return Math.cos(d);
    }

    public abstract void run(ServerWorld world, Pos pos);

}
