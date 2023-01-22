package net.givewife.additions.objects.blocks;

import net.givewife.additions.Main;
import net.givewife.additions.objects.blockentity.netherreactor.NetherReactorEntity;
import net.givewife.additions.objects.blockentity.netherreactor.NetherReactorTicker;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.DimensionEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class NetherReactorBlock extends BlockWithEntity implements BlockEntityProvider {

    public static final BooleanProperty ACTIVATED = BooleanProperty.of("activated");
    private NetherReactorTicker ticker;

    public NetherReactorBlock(AbstractBlock.Settings s) {
        super(s);
        setDefaultState(getDefaultState().with(ACTIVATED, false));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(world.getBlockEntity(pos) instanceof NetherReactorEntity && !world.isClient) {
            NetherReactorEntity e = (NetherReactorEntity) world.getBlockEntity(pos);
            if(!e.isActive()) {
                e.activate();
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
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
        return checkType(type, Main.TILE_ENTITIES.NETHER_REACTOR_ENTITY, (world1, pos, state1, be) -> be.tick(world1, pos, state1, be));
    }
}
