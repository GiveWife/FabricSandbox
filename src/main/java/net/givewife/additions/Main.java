package net.givewife.additions;

import net.fabricmc.api.ModInitializer;
import net.givewife.additions.data.WorldDataHandler;
import net.givewife.additions.registry.registries.*;
import net.minecraft.client.particle.SpriteProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger("additions");
    public static final String MODID = "additions";
    public static BlockEntityRegistry TILE_ENTITIES;

    @Override
    public void onInitialize() {
        BlockRegistry.register();
        MessageRegistry.registerServer();
        ParticleRegistry.register();
        ItemRegistry.register();
        CommandRegistry.registerCommands();
        WorldDataHandler.register();
        TILE_ENTITIES = new BlockEntityRegistry();
    }

}

