package net.givewife.additions.messages;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.givewife.additions.particles.effects.EffectLine;
import net.givewife.additions.util.Pos;

public class PosMessage extends CustomMessage {

    public PosMessage(String name) { super(name); }

    @Override
    public void registerClient() {

    }

    @Override
    public void registerServer() {
        ServerPlayNetworking.registerGlobalReceiver(getIdentifier(), (server, player, handler, buf, responseSender) -> {

            double x1 = buf.readDouble();
            double y1 = buf.readDouble();
            double z1 = buf.readDouble();
            double x2 = buf.readDouble();
            double y2 = buf.readDouble();
            double z2 = buf.readDouble();

            Pos pos1 = new Pos(x1,y1,z1);
            Pos pos2 = new Pos(x2,y2,z2);

            server.execute(() -> {

                EffectLine line = new EffectLine();

                line.run(server.getOverworld(),pos1, pos2);

            });

        });
    }
}
