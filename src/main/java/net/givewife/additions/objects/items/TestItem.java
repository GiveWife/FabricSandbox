package net.givewife.additions.objects.items;

import net.givewife.additions.objects.blocks.ProjectorBlock;
import net.givewife.additions.objects.templates.CustomSettings;
import net.givewife.additions.particles.CustomEffect;
import net.givewife.additions.particles.effects.EffectTrace;
import net.givewife.additions.registry.BlockRegistry;
import net.givewife.additions.util.NbtHelper;
import net.givewife.additions.util.Pos;
import net.givewife.additions.util.PositionUtilities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.visitor.NbtTextFormatter;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TestItem extends Item {

    NbtHelper nbt = new NbtHelper();
    private final String USE = "use";
    private final String COUNT = "count";
    private final int USETIME = 200;

    public TestItem() {
        super(CustomSettings.getDefaultSettings());
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if(!world.isClient) {

            CustomEffect e = new EffectTrace("test", false);
            e.run((ServerWorld) world, new Pos(user));

            ItemStack stack = user.getStackInHand(hand);
            if(!nbt.isIntEqual(USE, 0, stack)) return super.use(world, user, hand);
            nbt.setInt(USE, 1, stack);

        }

        return super.use(world, user, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {

        if(!world.isClient) {

            if(stack.hasNbt()) {

                //Stop when necessary
                if(nbt.isIntEqual(COUNT, 0, stack)) {

                    nbt.setInt(USE, 0, stack);
                    nbt.setInt(COUNT, USETIME, stack);

                }

                //Decrease use time if activated
                if(nbt.getInt(USE, stack) == 1) {

                    nbt.addInt(COUNT, -1, stack);
                    BlockPos pos = entity.getBlockPos().down();

                    BlockPos[] square = PositionUtilities.getSquareAround(pos, 3);

                    //Set blocks
                    for(int i = 0; i < square.length; i++) {

                        //Reduce overhead
                        BlockState inspectedBlock = world.getBlockState(square[i]);

                        //Check if we should replace
                        if(inspectedBlock.getBlock() == Blocks.AIR ||
                                (inspectedBlock.getBlock() == BlockRegistry.PROJECTOR_1)
                                        && inspectedBlock.getProperties().contains(ProjectorBlock.BRIGHTNESS)
                                        && inspectedBlock.get(ProjectorBlock.BRIGHTNESS) <= 3)
                            world.setBlockState(square[i], BlockRegistry.PROJECTOR_1.getDefaultState().with(ProjectorBlock.BRIGHTNESS, 4));
                    }

                }

            } else {

                initNbt(stack);

            }

        }

    }

    private void initNbt(ItemStack stack) {

        nbt.initNbt(stack);
        nbt.setInt(USE, 0, stack);
        nbt.setInt(COUNT, USETIME, stack);

    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(stack.hasNbt() && nbt.isIntEqual(USE, 1, stack)) {
            String count = Integer.toString(nbt.getInt(COUNT, stack));
            tooltip.add(Text.literal("Countdown: ").formatted(Formatting.WHITE).append(Text.literal(count)));
        }
        if(stack.hasNbt() && !nbt.isIntEqual(USE, 1, stack))
            tooltip.add(Text.literal("Inactive").formatted(Formatting.RED));
    }

}
