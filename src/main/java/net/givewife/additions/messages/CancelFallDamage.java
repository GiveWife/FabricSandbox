package net.givewife.additions.messages;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.UUID;

public class CancelFallDamage extends CustomMessage {

    public CancelFallDamage(String name) {
        super(name);
    }

    @Override
    public void registerClient() {

        ClientPlayNetworking.registerGlobalReceiver(getIdentifier(), (client, handler, buf, responseSender) -> {

            UUID uuid = buf.readUuid();
            PlayerEntity player =  client.world.getPlayerByUuid(uuid);
            client.execute(() -> {

                player.fallDistance = 0;

                log("(client) trying to negate fall damage how did we get here.....?");

            });
        });


    }

    @Override
    public void registerServer() {

        ServerPlayNetworking.registerGlobalReceiver(getIdentifier(), (server, player, handler, buf, responseSender) -> {

            UUID uuid = buf.readUuid();
            ServerPlayerEntity serverplayer = server.getPlayerManager().getPlayer(uuid);

            server.execute(() -> {

                serverplayer.fallDistance = 0;

                log("(server) Tried cancelling.");

            });

        });

    }


}
