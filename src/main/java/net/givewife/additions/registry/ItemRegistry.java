package net.givewife.additions.registry;

import net.givewife.additions.Main;
import net.givewife.additions.objects.items.ItemLifeStealSword;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ItemRegistry {

    public static final Item BORK = new ItemLifeStealSword();
    public static void register() {

        Registry.register(Registries.ITEM, new Identifier(Main.MODID, "bork"), BORK);
    }

}
