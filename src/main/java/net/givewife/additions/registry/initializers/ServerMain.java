package net.givewife.additions.registry.initializers;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.givewife.additions.registry.MessageRegistry;
import net.minecraft.client.sound.Channel;

public class ServerMain implements DedicatedServerModInitializer {

    @Override
    public void onInitializeServer() {
        System.out.println("Actual server init");
        initializeMessages();
    }

    public static void initializeMessages() {
        System.out.println("[Server] Initializing messages");
        for(int i = 0; i < MessageRegistry.getMessages().length; i++) {
            MessageRegistry.getMessages()[i].registerServer();
        }
    }

}
