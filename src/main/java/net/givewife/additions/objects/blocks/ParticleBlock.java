package net.givewife.additions.objects.blocks;

import net.givewife.additions.Main;
import net.givewife.additions.objects.blockentity.ParticleBlockEntity;
import net.givewife.additions.objects.blockentity.ProjectorBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ParticleBlock extends BlockWithEntity implements BlockEntityProvider {

    public ParticleBlock(AbstractBlock.Settings s) {
        super(s);
        setDefaultState(this.getDefaultState());
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ParticleBlockEntity(pos, getDefaultState());
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, Main.TILE_ENTITIES.PARTICLE_ENTITY, (world1, pos, state1, be) -> be.tick(world1, pos, state1, be));
    }

}
