package net.givewife.additions.registry.initializers;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.givewife.additions.registry.MessageRegistry;
import net.minecraft.client.sound.Channel;

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
