package net.givewife.additions.objects.items;

import net.givewife.additions.Main;
import net.givewife.additions.util.GeneralHelper;
import net.givewife.additions.util.NbtHelper;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class ModItem extends Item {

    public NbtHelper nbt = new NbtHelper();
    public GeneralHelper help = new GeneralHelper();
    public final Identifier id;
    private String name;

    public ModItem(String name, Item.Settings settings) {
        super(settings);
        this.name = name;
        id = new Identifier(Main.MODID, name);
    }

    public Identifier getIdentifier() {
        return this.id;
    }

}
