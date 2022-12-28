package net.givewife.additions.registry;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.givewife.additions.Main;
import net.givewife.additions.registry.messages.BorkHealMessage;
import net.givewife.additions.registry.messages.CustomMessage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class MessageRegistry {

    public static CustomMessage HEAL_BORK = new BorkHealMessage("heal_bork");

    public static CustomMessage[] getMessages() {
        return new CustomMessage[] {HEAL_BORK};
    }

}
