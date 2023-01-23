package net.givewife.additions.util.positions;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

/**
 * Variation of the BlockPos class that holds double values instead of integer values
 */
public class Pos {

    private final float x, y, z;

    public Pos(BlockPos pos) {
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
    }
    public Pos(PlayerEntity e) {
        this.x = (float) e.getX();
        this.y = (float) e.getY();
        this.z = (float) e.getZ();
    }
    public Pos(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float x() {
        return x;
    }

    public float y() {
        return y;
    }

    public float z() {
        return z;
    }

    public void print(String source) {
        System.out.println(source + " -> [Pos]: [" + x + ", " + y + ", " + z + "]");
    }

    public void print() {
        System.out.println("  [Pos]: [" + x + ", " + y + ", " + z + "]");
    }

    public String getPrint() {
        return "  [Pos]: [" + x + ", " + y + ", " + z + "]";
    }

    public Pos up() {
        return new Pos(x, y + 1, z);
    }

    public Pos up(float i) {
        return new Pos(x, y + i, z);
    }

    public Pos down() {
        return new Pos(x, y - 1, z);
    }

    public Pos down(float i) {
        return new Pos(x, y - i, z);
    }

    public Pos north() {
        return new Pos(x, y, z - 1);
    }

    public Pos north(float i) {
        return new Pos(x, y, z - i);
    }

    public Pos east() {
        return new Pos(x+1, y, z);
    }

    public Pos east(float i) {
        return new Pos(x+i, y, z);
    }

    public Pos west() {
        return new Pos(x - 1, y, z);
    }

    public Pos west(float i) {
        return new Pos(x - i, y, z);
    }

    public Pos south() {
        return new Pos(x, y, z + 1);
    }

    public Pos south(float i) {
        return new Pos(x, y, z + i);
    }

    public Pos subtract(Pos pos) {
        return new Pos(x() - pos.x(), y() - pos.y(), z() - pos.z());
    }

}
