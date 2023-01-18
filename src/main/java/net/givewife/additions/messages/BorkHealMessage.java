package net.givewife.additions.messages;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;

public class BorkHealMessage extends CustomMessage {

    public BorkHealMessage(String name) {
        super(name);
    }

    @Override
    public void registerClient() {

        ClientPlayNetworking.registerGlobalReceiver(getIdentifier(), (client, handler, buf, responseSender) -> {

            PlayerEntity player = client.world.getPlayerByUuid(buf.readUuid());

            client.execute(() -> {

                System.out.println("Execute via client");
                player.heal(20.0F);

            });
        });

    }

    @Override
    public void registerServer() {

        ServerPlayNetworking.registerGlobalReceiver(getIdentifier(), (server, player, handler, buf, responseSender) -> {

            PlayerEntity player2 = player.world.getPlayerByUuid(buf.readUuid());

            server.execute(() -> {

                System.out.println("Execute via server");
                player2.heal(3.0F);

            });

        });

    }
}
