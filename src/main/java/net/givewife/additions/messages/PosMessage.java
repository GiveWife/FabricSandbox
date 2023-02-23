package net.givewife.additions.messages;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.givewife.additions.particles.effects.EffectLine;
import net.givewife.additions.util.positions.Pos;

public class PosMessage extends CustomMessage {

    public PosMessage(String name) { super(name); }

    @Override
    public void registerClient() {

    }

    @Override
    public void registerServer() {

        ServerPlayNetworking.registerGlobalReceiver(getIdentifier(), (server, player, handler, buf, responseSender) -> {

            float x1 = buf.readFloat();
            float y1 = buf.readFloat();
            float z1 = buf.readFloat();
            float x2 = buf.readFloat();
            float y2 = buf.readFloat();
            float z2 = buf.readFloat();

            Pos pos1 = new Pos(x1,y1,z1);
            Pos pos2 = new Pos(x2,y2,z2);

            server.execute(() -> {

                EffectLine line = new EffectLine(pos1, pos2, 100);

            });

        });

    }
}
