package net.givewife.additions.objects.items;

import net.givewife.additions.objects.templates.CustomSettings;
import net.givewife.additions.particles.CustomEffect;
import net.givewife.additions.particles.effects.EffectTrace;
import net.givewife.additions.util.NbtHelper;
import net.givewife.additions.util.Pos;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TestItem extends Item {

    NbtHelper nbt = new NbtHelper();
    private final String USE = "use";
    private final int USETIME = 201;

    public TestItem() {
        super(CustomSettings.getDefaultSettings());
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if(!world.isClient) {

            CustomEffect e = new EffectTrace("test", false, 0);
            e.run((ServerWorld) world, new Pos(user));

            ItemStack stack = user.getStackInHand(hand);
            if(!nbt.isIntEqual(USE, USETIME, stack)) return super.use(world, user, hand);
            nbt.addInt(USE, -1, stack);

        }

        return super.use(world, user, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {

        if(!world.isClient) {

            if(stack.hasNbt()) {

                //Stop when necessary
                if(nbt.isIntEqual(USE, 0, stack)) {

                    nbt.setInt(USE, USETIME, stack);

                }

                //Decrease use time if activated
                if(nbt.getInt(USE, stack) <= 200) {

                    nbt.addInt(USE, -1, stack);
                    BlockPos pos = entity.getBlockPos();

                    world.setBlockState();

                }

            } else {

                initNbt(stack);

            }

        }

    }

    private void initNbt(ItemStack stack) {

        nbt.initNbt(stack);
        nbt.setInt(USE, USETIME, stack);

    }

}
