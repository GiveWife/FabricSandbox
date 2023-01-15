package net.givewife.additions;

import net.fabricmc.api.ModInitializer;
import net.givewife.additions.registry.initializers.ServerMain;
import net.givewife.additions.registry.registries.BlockEntityRegistry;
import net.givewife.additions.registry.registries.BlockRegistry;
import net.givewife.additions.registry.registries.CommandRegistry;
import net.givewife.additions.registry.registries.ItemRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger("additions");
    public static final String MODID = "additions";
    public static BlockEntityRegistry TILE_ENTITIES = new BlockEntityRegistry();

    @Override
    public void onInitialize() {
        BlockRegistry.register();
        ServerMain.initializeMessages();
        ItemRegistry.register();
        CommandRegistry.registerCommands();
        //TILE_ENTITIES = new BlockEntityRegistry();
    }

}

