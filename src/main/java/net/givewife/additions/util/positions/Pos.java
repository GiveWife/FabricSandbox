package net.givewife.additions.util.positions;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

/**
 * Variation of the BlockPos class that holds double values instead of integer values
 */
public class Pos {

    private final double x, y, z;

    public Pos(BlockPos pos) {
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
    }
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
        System.out.println(source + " -> [Pos]: [" + x + ", " + y + ", " + z + "]");
    }

    public void print() {
        System.out.println("  [Pos]: [" + x + ", " + y + ", " + z + "]");
    }

    public Pos up() {
        return new Pos(x, y + 1, z);
    }

    public Pos down() {
        return new Pos(x, y - 1, z);
    }

    public Pos north() {
        return new Pos(x, y, z - 1);
    }

    public Pos east() {
        return new Pos(x+1, y, z);
    }

    public Pos west() {
        return new Pos(x - 1, y, z);
    }

    public Pos south() {
        return new Pos(x, y, z + 1);
    }

}
