package net.givewife.additions.util;

import net.minecraft.util.math.BlockPos;

public class PositionUtilities {

    public PositionUtilities() {}

    /**
     * Size must be odd and at least 3.
     */
    public static BlockPos[] getSquareAround(BlockPos pos, int size) {

        BlockPos[] array = new BlockPos[size*size];
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        // Variable
        int start = (size - 1)/2;
        int l = 0;

        for(int i = -start; i < start+1; i++) {
            for(int j = -start; j < start+1; j++) {
                array[l++] = new BlockPos(x + i, y, z + j);
            }
        }

        return array;

    }

}
