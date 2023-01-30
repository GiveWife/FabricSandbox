package net.givewife.additions.particles.effects;

import net.givewife.additions.particles.CustomEffect;
import net.givewife.additions.util.GeneralHelper;
import net.givewife.additions.util.positions.Pos;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EffectParabola extends CustomEffect {

    private final Pos start, end;
    private final float radius;
    private final int heightLimit;
    private final World world;

    public EffectParabola(Pos start, World world, float radius, int heightDiffLimit) {
        this.start = start;
        this.world = world;
        this.heightLimit = heightDiffLimit;
        this.radius = radius;

        this.end = init();
    }

    private Pos init() {
        Pos test = calculateBlock();
        BlockPos p = new BlockPos(Math.floor(test.x()), Math.floor(test.y()), Math.floor(test.z()));

        // We must make sure that the particles will not clip through blocks.
        Block b = world.getBlockState(p).getBlock();
        if(b.i)

    }

    /**
     * The calculated position could be an airblock. This function will offset the Y coordinate
     */
    private BlockPos getTop(Pos calculated) {

        BlockPos topBlock = new BlockPos(calculated.x(), calculated.y(), calculated.z());
        int up = -3;

        // Loop from calculated.y()-3 to calculated.y()+3 and see which y coord survives.
        for(int i = -3; i <= 3; i++) {
            if(world.getBlockState(topBlock.up(i)).getBlock() == Blocks.AIR) up = i;
        }

        return topBlock.up(up);

    }

    /**
     * Returns a blockpos with same height but offset x,z
     */
    private Pos calculateBlock() {
        double radial = GeneralHelper.getRand(0, 2*314)/100;
        double posX = (Math.cos(radial)*radius) + this.start.x();
        double posZ = (Math.sin(radial)*radius) + this.start.z();
        double posY = this.start.y();
        return new Pos(posX, posY, posZ);
    }

    @Override
    public void run(World world) {

    }

    @Override
    public void run(ServerWorld world) {

    }
}
