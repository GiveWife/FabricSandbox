package net.givewife.additions;

import net.fabricmc.api.ModInitializer;
import net.givewife.additions.registry.BlockEntityRegistry;
import net.givewife.additions.registry.BlockRegistry;
import net.givewife.additions.registry.ItemRegistry;
import net.givewife.additions.registry.initializers.ServerMain;
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
        //TILE_ENTITIES = new BlockEntityRegistry();
    }

}

