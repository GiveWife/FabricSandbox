package net.givewife.additions.util;


import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GeneralHelper {

    public GeneralHelper() {}

    /**
     * Get held item from entity
     * returns 0 if the entity is not a PlayerEntity
     */
    public ItemStack heldstack(Entity entity) {
        if(entity instanceof PlayerEntity) {
            return ((PlayerEntity)entity).getMainHandStack();
        }
        return null;
    }

    /**
     * Determines whether the given entity is a player
     */
    public boolean isPlayer(Entity entity) {
        return (entity instanceof PlayerEntity);
    }

    /**
     * Determines whether the given stack is the same as the main hand stack
     */
    public boolean mainhandIs(PlayerEntity player, Item item) {
        return (player.getMainHandStack().getItem() == item);
    }

    /**
     * Determines whether the given stack is the same as the off hand stack
     */
    public boolean offhandIs(PlayerEntity player, Item item) {
        return (player.getOffHandStack().getItem() == item);
    }



}
