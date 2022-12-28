package net.givewife.additions.registry.initializers;

import net.fabricmc.api.ClientModInitializer;
import net.givewife.additions.registry.MessageRegistry;

public class ClientMain implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        initializeMessages();

    }

    private void initializeMessages() {
        System.out.println("[Client] Initializing messages");
        for(int i = 0; i < MessageRegistry.getMessages().length; i++) {
            MessageRegistry.getMessages()[i].registerServer();
        }
    }

}
