package net.givewife.additions.registry.registries;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.givewife.additions.Main;
import net.givewife.additions.registry.messages.BorkHealMessage;
import net.givewife.additions.registry.messages.CustomMessage;
import net.givewife.additions.registry.messages.PosMessage;
import net.givewife.additions.registry.messages.PushMessage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class MessageRegistry {

    public static CustomMessage HEAL_BORK = new BorkHealMessage("heal_bork");
    public static CustomMessage PUSH = new PushMessage("push");
    public static CustomMessage POS = new PosMessage("pos");

    public static CustomMessage[] getMessages() {
        return new CustomMessage[] {HEAL_BORK, PUSH, POS};
    }

    /**
     * Send messages by:
     *                PacketByteBuf buf = PacketByteBufs.create();
     *                 buf.writeUuid(attacker.getUuid());
     *                 ServerPlayNetworking.send((ServerPlayerEntity) attacker, MessageRegistry.HEAL_BORK.getIdentifier(), buf);
     */

}
