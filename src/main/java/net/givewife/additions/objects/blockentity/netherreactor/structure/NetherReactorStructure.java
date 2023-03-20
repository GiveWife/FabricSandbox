package net.givewife.additions.objects.blockentity.netherreactor.structure;

import net.givewife.additions.particles.customparticles.HighlightParticle;
import net.givewife.additions.particles.effects.EffectBox;
import net.givewife.additions.particles.effects.EffectHighlightBlock;
import net.givewife.additions.registry.registries.BlockRegistry;
import net.givewife.additions.util.positions.Pos;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class NetherReactorStructure {

    private final BlockPos pos;
    private final int radius = 20;
    private final int height = 10;

    public NetherReactorStructure(BlockPos origin) {
        this.pos = origin;
    }

    /**
     * Tests all blocks around the structure
     */
    public boolean isStructureCorrect(World world) {

        BlockPos pos = this.pos;
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
        pos = pos.down();
        return List.of(pos.north().east(), pos.north(), pos.north().west(), pos.east(), pos.west(), pos.south().east(), pos.south(), pos.south().west(), pos, pos.up(), pos.up(2),
                pos.north().east().up(1), pos.north().west().up(1), pos.south().east().up(1), pos.south().west().up(1),
                pos.north().east().up(2), pos.north().up(2), pos.north().west().up(2), pos.east().up(2), pos.west().up(2), pos.south().east().up(2), pos.south().up(2), pos.south().west().up(2));
    }

    public void clear(World world) {

        for(BlockPos p : getSurrounding(world))
            world.breakBlock(p, false);

        for(BlockPos p : getGround(world))
            world.setBlockState(p, Blocks.GRASS_BLOCK.getDefaultState());

        for(BlockPos p : getStructurePos(pos))
            world.setBlockState(p, Blocks.COBBLESTONE.getDefaultState());

        world.setBlockState(pos, BlockRegistry.NETHER_REACTOR.getDefaultState());

    }

    /**
     * Determines if the surrounding is appropriate for the nether reactor to activate
     */
    public boolean isSurroundingCorrect(World world, boolean printBox) {

        // Get all surrounding blocks
        List<BlockPos> box = getSurrounding(world);
        List<BlockPos> ground = getGround(world);
        List<BlockPos> invalid_positions = getInvalids(box, world, true);
        List<BlockPos> invalid_ground = getInvalids(ground, world, false);

        if(printBox && invalid_positions.size() >= 1) {
            // Initialise the effect
            Pos ne = getFurthest(true, invalid_positions);
            Pos sw = getFurthest(false, invalid_positions);
            //Spawn effect
            EffectBox effect = new EffectBox(ne, sw, 10);
            effect.run(world);
            return false;

        } else if(printBox && invalid_ground.size() >= 1) {

            // Initialise the effect
            Pos ne = getFurthest(true, invalid_ground);
            Pos sw = getFurthest(false, invalid_ground);
            //Spawn effect
            EffectBox effect = new EffectBox(ne, sw, 10);
            effect.run(world);
            return false;

        }

        System.out.println("everything correct, activating");


        return true;

    }

    /**
     * Checks all blocks in the vicinity and determines if the structure can activate 
     * Called from {@link net.givewife.additions.objects.blocks.NetherReactorBlock#onUse(BlockState, World, BlockPos, PlayerEntity, Hand, BlockHitResult)}
     */
    private List<BlockPos> getSurrounding(World world) {

        //Local variable for reference
        BlockPos pos = this.pos.down();
        List<BlockPos> box = new ArrayList<>();
        List<BlockPos> struct = getStructurePos(pos.up());

        // Fill the ground array per level i
        for(int i = 0; i < height; i++) {

            //x-axis
            for(int x = -radius; x < radius; x++) {

                //z-axis
                for(int z = -radius; z < radius; z++) {

                    //layer[((x+radius)*radius*2) + (z+radius)] = pos.north(x).east(z).up(i);
                    if(!struct.contains(pos.north(z).east(x).up(i)))
                        box.add(pos.north(z).east(x).up(i));

                }

            }

        }

        return box;

    }

    private List<BlockPos> getGround(World world) {

        List<BlockPos> ground = new ArrayList<>();

        for(int x = -radius; x < radius; x++) {

            //z-axis
            for(int z = -radius; z < radius; z++) {

                //layer[((x+radius)*radius*2) + (z+radius)] = pos.north(x).east(z).up(i);
                ground.add(this.pos.down(2).north(x).east(z));

            }

        }

        return ground;


    }

    /**
     * Checks for invalids blocks, non air blocks at the structure.
     */
    private List<BlockPos> getInvalids(List<BlockPos> box, World world, boolean shouldBeAir) {

        // Check the generated blocks per level i
        int outerLen = box.size();
        List<BlockPos> invalids = new ArrayList<>();
        for(int i = 0; i < outerLen; i++) {

            // If an air block is found, we return false and print the effect
            if(world.getBlockState(box.get(i)).getBlock() != Blocks.AIR && shouldBeAir) {

                invalids.add(box.get(i));
                EffectBox eff = new EffectBox(new Pos(box.get(i)), new Pos(box.get(i)), 10);
                eff.run(world);

            } else if(world.getBlockState(box.get(i)).getBlock() == Blocks.AIR && !shouldBeAir) {

                invalids.add(box.get(i));
                EffectBox eff = new EffectBox(new Pos(box.get(i)), new Pos(box.get(i)), 10);
                eff.run(world);

            }


        }

        return invalids;

    }

    /**
     * Prints out the list of blocks
     */
    private void print(List<BlockPos> list, World world) {
        for(int i = 0; i < list.size(); i++) {
                System.out.println("Pos " + i + ": " + new Pos(list.get(i)).getPrint() + " (" + world.getBlockState(list.get(i)).getBlock().getName() + ")");
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
