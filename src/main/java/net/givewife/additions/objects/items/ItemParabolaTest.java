package net.givewife.additions.objects.items;

import net.givewife.additions.objects.templates.NbtCooldownItem;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemParabolaTest extends NbtCooldownItem {

    //String tag, int cooldown, int duration, boolean switchAble, int interruptedCooldown, String name, Item.Settings settings
    public ItemParabolaTest(String name, Item.Settings settings) {
        super("itemparabolatest", 2, 2000, true, 0, name, settings);
    }

    @Override
    public void function(ItemStack stack, World world, Entity entity) {
        
    }

}
