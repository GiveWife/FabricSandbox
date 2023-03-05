package net.givewife.additions.util.positions;

import net.minecraft.datafixer.fix.ChunkPalettedStorageFix;
import net.minecraft.util.math.BlockPos;

/**
 * Small helper class to get x,y,z positions of a block edge.
 * The initial x,y,z position is on the leftmost side, taking north to the left, right to the south and the block
 * expands to the east and south in volume
 *
 *    E     SE
 *    O---O
 *    |   |
 *    X---O > S
 *
 *  The blockpos is marked with X.
 *
 *  Some methods will add an extra +1 to the distance. The south() for example, will already be set to south for the client.
 *  If the client would like to offset 1 extra, we cannot only do 1 times south. Because we would be under the south position
 *  in the ^^ example above.
 */
public class BlockSidePos {

    public static Pos getNorth(BlockPos pos, int expansion) {
        return new Pos(pos.north(expansion).west(expansion));
    }

    public static Pos getSouth(BlockPos pos, int expansion) {
        return new Pos(pos.south(expansion+1).west(expansion));
    }

    public static Pos getEast(BlockPos pos, int expansion) {
        return new Pos(pos.north(expansion).east(expansion+1));
    }

    public static Pos getSouthEast(BlockPos pos, int expansion) {
        return new Pos(pos.south(expansion+1).east(expansion+1));
    }

    /**
     * Get middle on bot side
     */
    public static Pos getMiddleBot(BlockPos pos, int expansion) {
        return new Pos(pos.west(expansion)).south(0.5d);
    }

    /**
     * Get middle on left side
     */
    public static Pos getMiddleLeft(BlockPos pos, int expansion) {
        return new Pos(pos.north(expansion)).east(0.5d);
    }

    /**
     * Get middle on right side
     */
    public static Pos getMiddleRight(BlockPos pos, int expansion) {
        return new Pos(pos.south(expansion+1)).east(0.5d);
    }

    /**
     * Get middle on top side
     */
    public static Pos getMiddleTop(BlockPos pos, int expansion) {
        return new Pos(pos.east(expansion+1)).south(0.5d);
    }



    public static Pos getNorth(BlockPos pos) {
        return new Pos(pos);
    }

    public static Pos getSouth(BlockPos pos) {
        return new Pos(pos.south());
    }

    public static Pos getEast(BlockPos pos) {
        return new Pos(pos.east());
    }

    public static Pos getWest(BlockPos pos) {
        return new Pos(pos.west());
    }

}
