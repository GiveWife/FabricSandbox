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
        List<BlockPos> blocks = getStructurePos(pos);
        int size = blocks.size();

        for(int i = 0; i < size; i++) {
            if(blocks.get(i).equals(this.pos)) continue;
            if (!(world.getBlockState(blocks.get(i)).getBlock() == Blocks.COBBLESTONE)) return false;
        }

        return world.getBlockState(this.pos).getBlock() == BlockRegistry.NETHER_REACTOR;

    }

    /**
     * Pass the block under the nether reactor!
     */
    private List<BlockPos> getStructurePos(BlockPos pos) {
        return List.of(pos.north().east(), pos.north(), pos.north().west(), pos.east(), pos.west(), pos.south().east(), pos.south(), pos.south().west(), pos, pos.up(), pos.up(2),
                pos.north().east().up(1), pos.north().west().up(1), pos.south().east().up(1), pos.south().west().up(1),
                pos.north().east().up(2), pos.north().up(2), pos.north().west().up(2), pos.east().up(2), pos.west().up(2), pos.south().east().up(2), pos.south().up(2), pos.south().west().up(2));
    }

    public boolean isSurroundingCorrect(World world) {

        //Local variable for reference
        BlockPos pos = this.pos.down();
        int radius = 20;
        int height = 10;
        List<BlockPos> ground = new ArrayList<BlockPos>();
        List<BlockPos> struct = getStructurePos(pos);

        // Fill the ground array per level i
        for(int i = 0; i < height; i++) {

            //x-axis
            for(int x = -radius; x < radius; x++) {

                //z-axis
                for(int z = -radius; z < radius; z++) {

                    //layer[((x+radius)*radius*2) + (z+radius)] = pos.north(x).east(z).up(i);
                    if(!struct.contains(pos.north(z).east(x).up(i)))
                        ground.add(pos.north(z).east(x).up(i));

                }

            }

        }

        // Check the generated blocks per level i
        int outerLen = ground.size();
        List<BlockPos> invalids = new ArrayList<BlockPos>();
        for(int i = 0; i < outerLen; i++) {

            // If an air block is found, we return false and print the effect
            if(world.getBlockState(ground.get(i)).getBlock() != Blocks.AIR && !world.isClient) {

                invalids.add(ground.get(i));

            }


        }

        if(invalids.size() >= 1) {
            // Initialise the effect
            Pos ne = getFurthest(true, invalids);
            Pos sw = getFurthest(false, invalids);

            //Spawn effect
            EffectBox effect = new EffectBox(ne, sw, 10);
            effect.run((ServerWorld) world);
            return false;
        }

        return true;

    }

    private void print(List<BlockPos> list, World world) {
        for(int i = 0; i < list.size(); i++) {
            if(world.getBlockState(list.get(i)).getBlock() == Blocks.GRASS_BLOCK)
                System.out.println(new Pos(list.get(i)).getPrint() + "\n");
        }
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

            if(!northEast) {
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
