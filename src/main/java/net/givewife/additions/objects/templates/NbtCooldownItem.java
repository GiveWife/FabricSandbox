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
    private final int COOLDOWN, DURATION, INTERRUPTCOOLDOWN;
    private final String ACTIVATION, ACTIVE_TIME;
    private final boolean SWITCH;

    /**
     * Tag: unique identifier to setup NBT tags
     * Cooldown: the amount of time the player should wait when the usage time has gone by
     * Duration: the amount of time the item is active after clicking
     * Switchable: Being able to turn off the item when active
     * Interruptcooldown: When switched off before usage time runs, out, how long should the player wait?
     */
    public NbtCooldownItem(String tag, int cooldown, int duration, boolean switchAble, int interruptedCooldown, String name, Item.Settings settings) {
        super(name, settings);
        this.TAG = tag;

        this.SWITCH = switchAble;
        this.COOLDOWN = cooldown;
        this.DURATION = duration;
        this.INTERRUPTCOOLDOWN = interruptedCooldown;

        this.ACTIVATION = TAG + "activate";
        this.ACTIVE_TIME = TAG + "timeactive";
    }

    public String getActivateKey() {
        return this.ACTIVATION;
    }

    public String getCooldownKey() {
        return this.TAG;
    }

    public boolean canSwitch() {
        return SWITCH;
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

        // Determines if the item is active
        nbt.putBoolean(ACTIVATION, false);

        //Determines the amount of time the item will be active for
        nbt.putInt(ACTIVE_TIME, DURATION);
        return nbt;
    }

    /**
     * Handles the activation string in the NBT.
     * Will also initiate NBT setup if it wasn't complete, then recursively call this method again
     */
    public void onClicked(ItemStack stack, PlayerEntity user) {
        if(stack.hasNbt()) {

            // If item was inactive we activate
            if(!stack.getNbt().getBoolean(ACTIVATION))
                stack.getNbt().putBoolean(ACTIVATION, true);
            else {
                stack.getNbt().putBoolean(ACTIVATION, false);
                stack.getNbt().putInt(ACTIVE_TIME, DURATION);
                user.getItemCooldownManager().set(stack.getItem(), COOLDOWN);
            }

        } else {
            initNbt(stack);
            onClicked(stack, user);
        }
    }

    /**
     * Determines if the item may be activated:
     *    WAIT_TIME == 0
     *    ACTIVATION == false
     */
    private boolean canActivate(ItemStack stack) {

        if(stack.hasNbt() && !nbt.getBoolean(ACTIVATION, stack)) {
            return true;
        }
        // Switching the item off
        // We must put the cooldown!
        else if(stack.hasNbt() && nbt.getBoolean(ACTIVATION, stack) && SWITCH) {
            return true;
        }

        return false;

    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack held = user.getMainHandStack();

        // Activation must be off to be able to turn it on.
        if(!world.isClient && canActivate(held))
            onClicked(held, user);

        return super.use(world, user, hand);
    }

    /**
     * Handles the duration for this item. When the duration reached 0, the item nbt will be reset
     * Will also initiate NBT setup if it wasn't complete, then recursively call this method again
     *
     * This is put into the inventory tick method. If overridden in subclass, simply call superconstructor
     */
    public void handleCooldown(ItemStack stack, PlayerEntity user) {
        if(stack.hasNbt()) {
            nbt.addInt(ACTIVE_TIME, -1, stack);
            if(nbt.isIntEqual(ACTIVE_TIME, 0, stack)) {
                nbt.setBoolean(ACTIVATION, false, stack);
                nbt.setInt(ACTIVE_TIME, DURATION, stack);
                user.getItemCooldownManager().set(stack.getItem(), COOLDOWN);
            }
        } else {
            initNbt(stack);
            handleCooldown(stack, user);
        }
    }



    public abstract void function(ItemStack stack, World world, Entity entity);

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {

        if(stack.hasNbt() && nbt.getBoolean(ACTIVATION, stack) && entity instanceof PlayerEntity) {

            //Do stuff
            function(stack, world, entity);

            //Reduce cooldown
            handleCooldown(stack, (PlayerEntity) entity);

        }

    }

}
