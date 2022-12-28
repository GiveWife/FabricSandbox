package net.givewife.additions.registry.initializers;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.givewife.additions.registry.MessageRegistry;
import net.minecraft.client.sound.Channel;

public class ServerMain  {

    //@Override
    public void onInitializeServer() {
        //This doesn't run, why? ServerModInitializer is not recognized?
        System.out.println("Entry point server");
        initializeMessages();
    }

    public static void initializeMessages() {
        System.out.println("[Server] Initializing messages");
        for(int i = 0; i < MessageRegistry.getMessages().length; i++) {
            MessageRegistry.getMessages()[i].registerServer();
        }
    }

}
