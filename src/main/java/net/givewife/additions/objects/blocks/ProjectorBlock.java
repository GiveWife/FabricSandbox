package net.givewife.additions.objects.blocks;

import net.givewife.additions.Main;
import net.givewife.additions.objects.blockentity.ProjectorBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ProjectorBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final IntProperty BRIGHTNESS = IntProperty.of("brightness", 1, 4);

    private int lifetime;
    private final int LIFETIME = 40;
    public ProjectorBlock(AbstractBlock.Settings s) {
        super(s);
        setDefaultState(getDefaultState().with(BRIGHTNESS, 4));
    }

    @Override
    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BRIGHTNESS);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ProjectorBlockEntity(pos, getDefaultState().with(BRIGHTNESS, 4));
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, Main.TILE_ENTITIES.PROJECTOR_BLOCK_ENTITY, (world1, pos, state1, be) -> ProjectorBlockEntity.tick(world1, pos, state1, be));
    }

}
