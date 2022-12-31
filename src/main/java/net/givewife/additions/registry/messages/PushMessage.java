package net.givewife.additions.registry.messages;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.givewife.additions.particles.CustomEffect;
import net.givewife.additions.particles.effects.EffectLine;
import net.givewife.additions.particles.effects.EffectSingle;
import net.givewife.additions.util.Pos;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public class PushMessage extends CustomMessage {

    public PushMessage(String name) {
        super(name);
    }

    @Override
    public void registerClient() {

        ClientPlayNetworking.registerGlobalReceiver(getIdentifier(), (client, handler, buf, responseSender) -> {

            Entity hit = client.world.getPlayerByUuid(buf.readUuid());

            client.execute(() -> {

                System.out.println("[Client] found " + hit.getName() + ".");

            });
        });

    }

    @Override
    public void registerServer() {

        ServerPlayNetworking.registerGlobalReceiver(getIdentifier(), (server, player, handler, buf, responseSender) -> {

            Entity hit = server.getOverworld().getEntity(buf.readUuid());
            PlayerEntity serverplayer = server.getOverworld().getPlayerByUuid(buf.readUuid());

            server.execute(() -> {

                System.out.println("Message sent?");

                double camX = serverplayer.getRotationVec(1f).x;
                double camY = serverplayer.getRotationVec(1f).y;
                double camZ = serverplayer.getRotationVec(1f).z;

                double velocityAddedX = camX * 3F;
                double velocityAddedY = camY * 1.0011F;
                double velocityAddedZ = camZ * 3F;

                double currentVelocityX = serverplayer.getVelocity().x + velocityAddedX;
                double currentVelocityY = serverplayer.getVelocity().y + velocityAddedY;
                double currentVelocityZ = serverplayer.getVelocity().z + velocityAddedZ;

                hit.setVelocity(currentVelocityX, currentVelocityY, currentVelocityZ);

            });

        });

    }
}
