package net.givewife.additions.objects.templates;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Rarity;

public class CustomSettings {

    public static Item.Settings getDefaultSettings() {
        return new Item.Settings().maxCount(64);
    }

}
