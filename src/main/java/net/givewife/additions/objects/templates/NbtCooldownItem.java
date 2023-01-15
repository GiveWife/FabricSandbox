package net.givewife.additions.objects.templates;

import net.givewife.additions.objects.items.ModItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public abstract class NbtCooldownItem extends NbtItem {

    private final String TAG;
    private final int COOLDOWN;
    private final String ACTIVATION;

    public NbtCooldownItem(String tag, int cooldown, String name, Item.Settings settings) {
        super(name, settings);
        this.TAG = tag;
        this.COOLDOWN = cooldown;
        this.ACTIVATION = TAG + "activate";
    }

    /**
     * Sets the values of the nbt compound tag when the item is created
     */
    @Override
    public void initNbt(ItemStack stack) {
        stack.setNbt(getDefault());
    }

    @Override
    public NbtCompound getDefault() {
        NbtCompound nbt = new NbtCompound();
        nbt.putString(ACTIVATION, "off");
        nbt.putInt(TAG, COOLDOWN);
        return nbt;
    }

    /**
     * Handles the activation string in the NBT.
     * Will also initiate NBT setup if it wasn't complete, then recursively call this method again
     */
    public void onClicked(ItemStack stack) {
        if(stack.hasNbt()) {
            stack.getNbt().putString(ACTIVATION, "on");
        } else {
            initNbt(stack);
            onClicked(stack);
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if(!world.isClient && nbt.isStringEqual(ACTIVATION, "off", user.getMainHandStack()))
            onClicked(user.getMainHandStack());

        return super.use(world, user, hand);
    }

    /**
     * Handles the cooldown for this item. When the cooldown reached 0, the item nbt will be reset
     * Will also initiate NBT setup if it wasn't complete, then recursively call this method again
     *
     * This is put into the inventory tick method. If overridden in subclass, simply call superconstructor
     */
    public void handleCooldown(ItemStack stack) {
        if(stack.hasNbt()) {
            nbt.addInt(TAG, -1, stack);
            if(nbt.isIntEqual(TAG, 0, stack)) {
                nbt.setString(ACTIVATION, "off", stack);
                nbt.setInt(TAG, COOLDOWN, stack);
            }
        } else {
            initNbt(stack);
            handleCooldown(stack);
        }
    }



    public abstract void function(ItemStack stack, World world, Entity entity);

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {

        super.inventoryTick(stack, world, entity, slot, selected);

        if(stack.hasNbt() && nbt.isStringEqual(TAG, "on", stack)) {

            //Do stuff
            function(stack, world, entity);

            //Reduce cooldown
            handleCooldown(stack);

        }

    }

}
