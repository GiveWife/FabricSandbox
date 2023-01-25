package net.givewife.additions.registry.registries;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.givewife.additions.Main;
import net.givewife.additions.objects.items.*;
import net.givewife.additions.objects.templates.ToolTemplate;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ItemRegistry {

    public static final Item BORK = new ItemLifeStealSword();
    public static final ModItem TEST = new TestItem("test");
    public static final ModItem STOPWATCH = new StopwatchItem("blindness", 500, "stopwatch", new FabricItemSettings().maxCount(1));
    public static final ModItem PROJECTOR = new ProjectorItem("projector");

    public static final ToolTemplate SUPERTOOL = new SuperTool("supertool");


    public static void register() {

        Registry.register(Registries.ITEM, new Identifier(Main.MODID, "bork"), BORK);
        Registry.register(Registries.ITEM, TEST.getIdentifier(), TEST);
        Registry.register(Registries.ITEM, STOPWATCH.getIdentifier(), STOPWATCH);
        Registry.register(Registries.ITEM, SUPERTOOL.getIdentifier(), SUPERTOOL);
        Registry.register(Registries.ITEM, PROJECTOR.getIdentifier(), PROJECTOR);

    }

}
