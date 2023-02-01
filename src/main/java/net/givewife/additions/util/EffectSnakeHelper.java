package net.givewife.additions.util;

import net.givewife.additions.util.positions.Parabola;
import net.givewife.additions.util.positions.Pos;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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

    public Parabola[] getJumps() {
        return this.jumps;
    }

    private Parabola[] allocateJumps() {
        // We need a certain amount of Pos objects
        Pos[] jumps = new Pos[bounces];
        jumps[0] = start;
        int counter = jumps.length;

        // We will generate the first block, so we know which direction not to look for again.
        double radial = GeneralHelper.getRadial(0, 2*Math.PI);
        Pos next = checkBlock(calculateBlock(this.start, radial), radial);

        // We will add the rest.
        for(int i = 1; i < counter; i++) {
            radial = GeneralHelper.getRadial(0, 2*Math.PI);
            jumps[i] = checkBlock(calculateBlock(jumps[i-1], radial), radial);
            System.out.println("Calculated: " + jumps[i].getPrint());
        }

        Parabola[] paras = new Parabola[bounces-1];

        for(int i = 0;  i < paras.length; i++) {

            paras[i] = new Parabola(jumps[i], jumps[i+1], 4, 100);

        }

        return paras;
    }

    private Pos checkBlock(Pos pos, double radial) {

        double PI = Math.PI;
        double genRadial = GeneralHelper.getRadial(radial-(PI/4), radial+(PI/4));
        Pos check = calculateBlock(pos, genRadial);

        while(isValid(pos) == null) {
            genRadial = GeneralHelper.getRadial(radial-(PI/4), radial+(PI/4));
            check = calculateBlock(pos, genRadial);
        }

        return check;

    }

    /**
     * Returns a blockpos with same height but offset x,z
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
    private BlockPos isValid(Pos calculated) {

        BlockPos topBlock = calculated.getBlockPos();

        // We will check if we may jump to a certain higher location.
        int up = -(heightLimit-1);
        int counter = up;

        // Loop from calculated.y()-3 to calculated.y()+3 and see which y coord survives.
        for(int i = up; i <= -up; i++) {
            if(world.getBlockState(topBlock.up(i)).getBlock() == Blocks.AIR && counter >= i) counter = i;
        }

        return calculated.getBlockPos().up(counter);

    }

}
