package net.givewife.additions.registry.keybinds;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.givewife.additions.registry.registries.MessageRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.PacketByteBuf;

public class HealKeybind extends CustomKeybind {

    public HealKeybind(String name, String category, InputUtil.Type inputType, int key) {
        super(name, category, inputType, key);
    }

    @Override
    public void run(MinecraftClient client) {

        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeUuid(client.player.getUuid());

        ClientPlayNetworking.send(MessageRegistry.HEAL_BORK.getIdentifier(), buf);

    }

}
