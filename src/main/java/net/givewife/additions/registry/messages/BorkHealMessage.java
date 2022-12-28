package net.givewife.additions.registry.messages;

import io.netty.channel.ChannelHandler;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.givewife.additions.Main;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class BorkHealMessage extends CustomMessage {

    public BorkHealMessage(String name) {
        super(name);
    }

    @Override
    public void registerClient() {


        System.out.println("Client Register heal message");

        ClientPlayNetworking.registerGlobalReceiver(getIdentifier(), (client, handler, buf, responseSender) -> {

            PlayerEntity player = client.world.getPlayerByUuid(buf.readUuid());

            client.execute(() -> {

                player.heal(3.0F);

            });
        });

    }

    @Override
    public void registerServer() {

        System.out.println("Server Register heal message");

        ServerPlayNetworking.registerGlobalReceiver(getIdentifier(), (server, player, handler, buf, responseSender) -> {

            PlayerEntity player2 = player.world.getPlayerByUuid(buf.readUuid());


            server.execute(() -> {

                player2.heal(3.0F);

            });

        });

    }
}

//System.out.println("Hello? amount: " + amount + ", uuid: " + player.getUuidAsString());
// System.out.println("Hello? amount: " + amount + ", uuid: " + player.getUuidAsString());