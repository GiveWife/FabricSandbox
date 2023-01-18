package net.givewife.additions.messages;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.givewife.additions.data.PlayerState;
import net.givewife.additions.data.ServerState;

import java.util.Map;
import java.util.UUID;

public class JumpMessage extends CustomMessage {

    public JumpMessage(String name) {
        super(name);
    }

    @Override
    public void registerClient() {

        ClientPlayNetworking.registerGlobalReceiver(getIdentifier(), (client, handler, buf, responseSender) -> {

            UUID uuid = buf.readUuid();
            boolean jump = buf.readBoolean();
            client.execute(() -> {

                ServerState state = ServerState.getServerState(client.getServer());
                boolean hasFoundPlayer = false;

                for(Map.Entry<UUID, PlayerState> e : state.players.entrySet()) {
                    if(e.getKey() == uuid) {
                        log("(client) found uuid");
                        PlayerState newState = new PlayerState();
                        newState.hasJumped = true;
                        e.setValue(newState);
                        hasFoundPlayer = true;
                    }
                }

                state.markDirty();
                log("(client) Registered?");

            });
        });


    }

    @Override
    public void registerServer() {

        ServerPlayNetworking.registerGlobalReceiver(getIdentifier(), (server, player, handler, buf, responseSender) -> {

            UUID uuid = buf.readUuid();
            boolean jump = buf.readBoolean();

            server.execute(() -> {

                ServerState state = ServerState.getServerState(server);

                if(!state.players.containsKey(uuid)) {
                    log("(server) new uuid added to list");
                    PlayerState newState = new PlayerState();
                    newState.hasJumped = false;
                    state.players.put(uuid, newState);
                } else {
                    log("(server) uuid already exists, setting it to " + Boolean.toString(jump));
                    for (Map.Entry<UUID, PlayerState> e : state.players.entrySet()) {
                        if (e.getKey().equals(uuid)) {
                            log("(server) found uuid");
                            PlayerState newState = new PlayerState();
                            newState.hasJumped = jump;
                            e.setValue(newState);
                        }
                    }
                }

                state.markDirty();

                log("(server) registered.");

            });

        });

    }


}
