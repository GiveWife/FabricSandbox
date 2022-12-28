package net.givewife.additions.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public class Pos {

    private double x, y, z;

    public Pos(PlayerEntity e) {
        this.x = e.getX();
        this.y = e.getY();
        this.z = e.getZ();
    }
    public Pos(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public double z() {
        return z;
    }

    public void print(String source) {
        System.out.println(source + " -> [pos]: [" + x + ", " + y + ", " + z + "]");
    }

    public void print() {
        System.out.println("  [Pos]: [" + x + ", " + y + ", " + z + "]");
    }

    public Pos getPos(PlayerEntity e) {
        return new Pos(e.getX(), e.getY(), e.getZ());
    }

    public BlockPos getBlockPos() {
        return new BlockPos((int) x, (int) y, (int) z);
    }

}
