package net.givewife.additions.registry.initializers;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.givewife.additions.registry.registries.MessageRegistry;

public class ServerMain implements DedicatedServerModInitializer {

    @Override
    public void onInitializeServer() {
        initializeMessages();
    }

    public static void initializeMessages() {
        for(int i = 0; i < MessageRegistry.getMessages().length; i++) {
            MessageRegistry.getMessages()[i].registerServer();
        }
    }

}
