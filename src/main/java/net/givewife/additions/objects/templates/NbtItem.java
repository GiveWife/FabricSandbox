package net.givewife.additions.objects.templates;

import net.givewife.additions.objects.items.ModItem;
import net.givewife.additions.util.NbtHelper;
import net.minecraft.item.Item;

public class NbtItem extends ModItem {

    public NbtHelper nbt = new NbtHelper();

    public NbtItem(String name, Item.Settings settings) {
        super(name, settings);
    }



}
