package net.givewife.additions.registry.keybinds;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.givewife.additions.registry.MessageRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

public class PushKeybind extends CustomKeybind {

    public PushKeybind(String name, String category, InputUtil.Type inputType, int key) {
        super(name, category, inputType, key);
    }

    @Override
    public void run(MinecraftClient client) {

        ClientPlayerEntity player = client.player;
        HitResult hit = client.crosshairTarget;

        if(hit.getType() == HitResult.Type.ENTITY) {

            EntityHitResult entityHit = (EntityHitResult) hit;
            Entity entity = entityHit.getEntity();

            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeUuid(entity.getUuid());
            buf.writeUuid(player.getUuid());

            ClientPlayNetworking.send(MessageRegistry.PUSH.getIdentifier(), buf);

            /*for (ServerPlayerEntity serverPlayer : PlayerLookup.tracking((ServerWorld) client.world, player.getBlockPos())) {
                ServerPlayNetworking.send(serverPlayer, MessageRegistry.PUSH.getIdentifier(), buf);
            }*/

        }

    }
}
