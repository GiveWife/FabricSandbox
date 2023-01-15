package net.givewife.additions.data;

import com.sun.jna.platform.win32.OaIdl;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.givewife.additions.registry.registries.MessageRegistry;
import net.minecraft.network.PacketByteBuf;

public class WorldDataHandler {

    public static void register() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerState serverState = ServerState.getServerState(handler.player.world.getServer());
            PlayerState playerState = ServerState.getPlayerState(handler.player);

            // Sending the packet to the player (look at the networking page for more information)
            PacketByteBuf data = PacketByteBufs.create();
            ServerPlayNetworking.send(handler.player, MessageRegistry.JUMP.getIdentifier(), data);
        });
    }

}
