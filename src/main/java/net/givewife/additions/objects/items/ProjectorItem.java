package net.givewife.additions.objects.items;

import net.givewife.additions.objects.blocks.ProjectorBlock;
import net.givewife.additions.objects.templates.CustomSettings;
import net.givewife.additions.objects.templates.NbtCooldownItem;
import net.givewife.additions.registry.registries.BlockRegistry;
import net.givewife.additions.util.PositionUtilities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

//TODO sometimes, the projector blocks won't decay
public class ProjectorItem extends NbtCooldownItem {

    //String tag, int cooldown, int duration, boolean switchAble, int interruptedCooldown, String name, Item.Settings settings
    public ProjectorItem(String name) {
        super(name + "_function", 100, 800, true, 0, name, CustomSettings.getDefaultSettings());
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.hasNbt() && nbt.getBoolean(getActivateKey(), stack)) {
            String count = Integer.toString(nbt.getInt(getCooldownKey(), stack));
            tooltip.add(Text.literal("Countdown: ").formatted(Formatting.WHITE).append(Text.literal(count)));
        }
        if (stack.hasNbt() && !nbt.getBoolean(getActivateKey(), stack))
            tooltip.add(Text.literal("Inactive").formatted(Formatting.RED));
    }

    @Override
    public void function(ItemStack stack, World world, Entity entity) {
        BlockPos pos = entity.getBlockPos().down();

        BlockPos[] square = PositionUtilities.getSquareAround(pos, 3);
        ((PlayerEntity)entity).sendMessage(Text.literal(String.valueOf("Cooldown: " + Integer.toString(nbt.getInt(getCooldownKey(), stack)))), true);

        //Set blocks
        for (int i = 0; i < square.length; i++) {

            //Reduce overhead
            BlockState inspectedBlock = world.getBlockState(square[i]);

            //Check if we should replace
            if (inspectedBlock.getBlock() == Blocks.AIR ||
                    (inspectedBlock.getBlock() == BlockRegistry.PROJECTOR_1
                            && inspectedBlock.getProperties().contains(ProjectorBlock.BRIGHTNESS)
                            && inspectedBlock.get(ProjectorBlock.BRIGHTNESS) <= 3))
                world.setBlockState(square[i], BlockRegistry.PROJECTOR_1.getDefaultState().with(ProjectorBlock.BRIGHTNESS, 4));
        }
    }

    @Override
    public boolean allowNbtUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return false;
    }
}