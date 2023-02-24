package net.givewife.additions.objects.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.givewife.additions.registry.registries.BlockRegistry;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class CustomAir extends Block {

    public CustomAir() {
        super(Settings.of(Material.AIR)

                .air()
                .dropsNothing()
                .noCollision()
                .nonOpaque()

        );
    }

    @Override
    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }


}
