package net.givewife.additions.objects.templates;

import net.givewife.additions.objects.items.ModItem;
import net.givewife.additions.util.NbtHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public abstract class NbtItem extends ModItem implements NbtEnforcer {

    public NbtHelper nbt = new NbtHelper();

    public NbtItem(String name, Item.Settings settings) {
        super(name, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(!stack.hasNbt()) initNbt(stack);
        super.inventoryTick(stack, world, entity, slot, selected);
    }

}
