package net.givewife.additions;

import net.fabricmc.api.ModInitializer;
import net.givewife.additions.registry.ItemRegistry;
import net.givewife.additions.registry.initializers.ServerMain;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger("additions");
    public static final String MODID = "additions";

    @Override
    public void onInitialize() {
        ServerMain.initializeMessages();
        ItemRegistry.register();
    }

}

