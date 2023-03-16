package net.givewife.additions.util;

import net.givewife.additions.util.positions.Parabola;
import net.givewife.additions.util.positions.Pos;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;

/**
 * Helper class that generates all parabola's for a snake effect.
 */
public class EffectSnakeHelper {

    private final Parabola[] jumps;
    private final Pos start;
    private final float radius;
    private final int heightLimit, bounces;
    private final World world;

    public EffectSnakeHelper(Pos start, float radius, int heightlimit, World world, int bounces) {
        this.radius = radius;
        this.bounces = bounces;
        this.heightLimit = heightlimit;
        this.world = world;
        this.start = start;
        this.jumps = this.allocateJumps();
    }

    public EffectSnakeHelper(Pos start, float radius, int heightlimit, World world) {
        this.radius = radius;
        this.bounces = 1;
        this.heightLimit = heightlimit;
        this.world = world;
        this.start = start;
        this.jumps = this.allocateJumps();
    }

    public Parabola[] getJumps() {
        return this.jumps;
    }

    /**
     * Generates a parabola starting from the end of the other.
     */
    public Parabola getNextJump(Parabola start) {

        return start;

    }

    private Parabola[] allocateJumps() {
        // We need a certain amount of Pos objects
        Pos[] jumps = new Pos[bounces];
        jumps[0] = start;
        int counter = jumps.length;

        // We will generate the first block, so we know which direction not to look for again.
        // Our first radial.
        double radial = GeneralHelper.getRadial(0, 2*Math.PI);

        // We will add the rest.
        for(int i = 1; i < counter; i++) {
            Tuple<Double, Pos> values = checkBlock(calculateBlock(jumps[i-1], radial), radial);
            jumps[i] = values.getSecond();
            radial = values.getFirst();
        }

        // We make an array that holds all our parabola objects
        Parabola[] paras = new Parabola[bounces-1];

        // We fill the array
        for(int i = 0;  i < paras.length; i++) {

            paras[i] = new Parabola(jumps[i], jumps[i+1], 4, 100);
            //System.out.println("After filling array: " + paras[i].getPrint());

        }

        return paras;
    }

    private Tuple<Double, Pos> checkBlock(Pos pos, double radial) {

        // Start variables: randomly generated radial in the same direction as the previous
        double PI = Math.PI;
        double genRadial = GeneralHelper.getRadial(radial-(PI/4), radial+(PI/4));

        // Get block
        Pos check = calculateBlock(pos, genRadial);
        Pos adjustedHeight = isValid(pos, check);

        // When the blocks in that direction are not valid, we will have to check another direction
        int failAttempts = 0;

        // we check if the adjustedHeight block, is a valid spawn location.
        while(adjustedHeight == null && failAttempts < 6) {

            // If we failed at least 3 times, the fourth time we generate a radial in another direction
            genRadial = failAttempts <= 3 ?
                    GeneralHelper.getRadial(radial-(PI/4), radial+(PI/4))
                    : GeneralHelper.getRadial(radial+(PI/4), radial-(PI/4) + (2*PI));

            // Get new block with new radial
            check = calculateBlock(pos, genRadial);

            // Check the block's surroundings
            adjustedHeight = isValid(pos, check);

            // We increase our failed attempts
            failAttempts++;
        }

        // If it keeps failing, we return the start pos.
        if(failAttempts == 6 && isValid(pos, check) == null) {
            //System.out.println("6 failed attempts");
            return new Tuple<Double, Pos>(genRadial, pos);
        }

        return new Tuple<Double, Pos>(genRadial, adjustedHeight);

    }

    /**
     * Returns a block offset in the xz direction, but same height
     */
    private Pos calculateBlock(Pos start, double radial) {
        double posX = (Math.cos(radial)*radius) + start.x();
        double posZ = (Math.sin(radial)*radius) + start.z();
        double posY = start.y();
        return new Pos(posX, posY, posZ);
    }

    /**
     * The calculated position could be an airblock. This function will offset the Y coordinate
     */
    private Pos isValid(Pos from, Pos to) {

        // In case we don't need to check for heightlimit
        if(heightLimit == -1) {
            int topY = world.getTopY(Heightmap.Type.WORLD_SURFACE, (int) from.x(), (int) from.z());
            return new Pos(to.x(), topY, to.z());
        }

        // We will check if we may jump to a certain higher location.
        int previousY = from.inty();
        int jumpTo = previousY - heightLimit;

        for(int i = -heightLimit; i <= heightLimit; i++) {

            // The highest airblock that has a solid block underneath will be jumped to
            if(world.getBlockState(to.up(i).getBlockPos()).getBlock() == Blocks.AIR
            && world.getBlockState(to.up(i-1).getBlockPos()).getBlock() != Blocks.AIR) {
                jumpTo = i + previousY;
            }

        }

        for(int i = 1; i < heightLimit; i++) {

            if(world.getBlockState(to.up(jumpTo).up(i).getBlockPos()).getBlock() != Blocks.AIR) {
                return null;
            }

        }

        return new Pos(to.x(), jumpTo, to.z());

    }

}
