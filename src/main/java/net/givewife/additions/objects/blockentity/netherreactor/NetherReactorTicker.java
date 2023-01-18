package net.givewife.additions.objects.blockentity.netherreactor;

import net.givewife.additions.objects.blockentity.netherreactor.NetherReactorEntity;
import net.givewife.additions.util.GeneralHelper;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NetherReactorTicker {

    private GeneralHelper helper = new GeneralHelper();
    private int tick;
    public NetherReactorTicker() {
        tick = 0;
    }

    public void tick(World world, BlockPos pos, BlockState state, NetherReactorEntity be) {



        tick++;

    }

}
