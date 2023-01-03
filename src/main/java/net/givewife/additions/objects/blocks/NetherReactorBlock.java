package net.givewife.additions.objects.blocks;

import net.givewife.additions.Main;
import net.givewife.additions.objects.blockentity.netherreactor.NetherReactorEntity;
import net.givewife.additions.objects.blockentity.netherreactor.NetherReactorTicker;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class NetherReactorBlock extends BlockWithEntity implements BlockEntityProvider {

    public static final BooleanProperty ACTIVATED = BooleanProperty.of("activated");
    private NetherReactorTicker ticker = new NetherReactorTicker();

    public NetherReactorBlock(AbstractBlock.Settings s) {
        super(s);
        setDefaultState(getDefaultState().with(ACTIVATED, false));
    }

    @Override
    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ACTIVATED);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new NetherReactorEntity(pos, getDefaultState().with(ACTIVATED, false));
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, Main.TILE_ENTITIES.NETHER_REACTOR_ENTITY, (world1, pos, state1, be) -> ticker.tick(world1, pos, state1, be));
    }
}
