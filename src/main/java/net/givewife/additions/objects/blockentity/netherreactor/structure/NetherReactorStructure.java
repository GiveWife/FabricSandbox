package net.givewife.additions.objects.blockentity.netherreactor.structure;

import net.givewife.additions.particles.effects.EffectBox;
import net.givewife.additions.registry.registries.BlockRegistry;
import net.givewife.additions.util.positions.Pos;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class NetherReactorStructure {

    private final BlockPos pos;

    public NetherReactorStructure(BlockPos origin) {
        this.pos = origin;
    }

    /**
     * Tests all blocks around the structure
     */
    public boolean isStructureCorrect(World world) {

        BlockPos pos = this.pos.down();
        BlockPos[] base = new BlockPos[] {pos.north().east(), pos.north(), pos.north().west(), pos.east(), pos.west(), pos.south().east(), pos.south(), pos.south().west(), pos};
        pos = pos.up();
        BlockPos[] middle = new BlockPos[] {pos.north().east(), pos.north().west(), pos.south().east(), pos.south().west()};
        pos = pos.up();
        BlockPos[] top = new BlockPos[] {pos.north().east(), pos.north(), pos.north().west(), pos.east(), pos.west(), pos.south().east(), pos.south(), pos.south().west()};

        for(int i = 0; i < base.length; i++) {
            if (!(world.getBlockState(base[i]).getBlock() == Blocks.COBBLESTONE)) return false;
        }

        for(int i = 0; i < middle.length; i++) {
            if (!(world.getBlockState(middle[i]).getBlock() == Blocks.COBBLESTONE)) return false;
        }

        for(int i = 0; i < top.length; i++) {
            if (!(world.getBlockState(top[i]).getBlock() == Blocks.COBBLESTONE)) return false;
        }

        return world.getBlockState(this.pos).getBlock() == BlockRegistry.NETHER_REACTOR;

    }

    public boolean isSurroundingCorrect(World world) {

        //Local variable for reference
        BlockPos pos = this.pos.down();
        int radius = 20;
        int height = 10;
        BlockPos[][] ground = new BlockPos[height][(radius*2)*(radius*2)];

        // Fill the ground array per level i
        for(int i = 0; i < height; i++) {

            BlockPos[] layer = new BlockPos[(radius*2)*(radius*2)];

            //x-axis
            for(int x = -radius; x < radius; x++) {

                //z-axis
                for(int z = -radius; z < radius; z++) {

                    layer[((x+radius)*radius*2) + (z+radius)] = pos.north(x).east(z).up(i);

                }

            }

            ground[i] = layer;

        }

        // Check the generated blocks per level i
        int outerLen = ground.length;
        List<BlockPos> invalids = new ArrayList<BlockPos>();
        for(int i = 0; i < outerLen; i++) {

            int innerLen = ground[i].length;
            for(int j = 0; j < innerLen; j++) {

                // If an air block is found, we return false and print the effect
                if(world.getBlockState(ground[i][j]).getBlock() != Blocks.AIR && !world.isClient) {

                    System.out.println("block: " + world.getBlockState(ground[i][j]).getBlock());
                    invalids.add(ground[i][j]);

                }

            }

        }

        if(invalids.size() >= 1) {
            // Initialise the effect
            EffectBox effect = new EffectBox(getFurthest(true, invalids), getFurthest(false, invalids));
            effect.run((ServerWorld) world);
            return false;
        }

        return true;

    }

    /**
     * Gets the furthest position from the given set of blockposes
     * @pre | make sure the poses list at least contains 1 element array with also 1 element
     */
    private Pos getFurthest(boolean northEast, List<BlockPos> poses) {

        int furthestX = poses.get(0).getX();
        int furthestY = poses.get(0).getY();
        int furthestZ = poses.get(0).getZ();

        int size = poses.size();
        for(int i = 0; i < size; i++) {


            if(northEast) {
                if(poses.get(i).getX() <= furthestX) furthestX = poses.get(i).getX();
                if(poses.get(i).getY() <= furthestY) furthestY = poses.get(i).getY();
                if(poses.get(i).getZ() <= furthestZ) furthestZ = poses.get(i).getZ();
            } else {
                if(poses.get(i).getX() >= furthestX) furthestX = poses.get(i).getX();
                if(poses.get(i).getY() >= furthestY) furthestY = poses.get(i).getY();
                if(poses.get(i).getZ() >= furthestZ) furthestZ = poses.get(i).getZ();
            }


        }

        return new Pos(furthestX, furthestY, furthestZ);

    }


}
