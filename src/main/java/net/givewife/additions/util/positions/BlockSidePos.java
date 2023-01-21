package net.givewife.additions.util.positions;

import net.minecraft.datafixer.fix.ChunkPalettedStorageFix;
import net.minecraft.util.math.BlockPos;

/**
 * Small helper class to get x,y,z positions of a block edge.
 * The initial x,y,z position is on the leftmost side, taking north to the left, right to the south and the block
 * expands to the east and south in volume
 *
 *    E
 *    O---O
 *    |   |
 *    X---O > South
 *
 *  The blockpos is marked with X.
 */
public class BlockSidePos {

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
