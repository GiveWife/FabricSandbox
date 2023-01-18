package net.givewife.additions.registry.registries;

import net.givewife.additions.messages.*;

public class MessageRegistry {

    public static CustomMessage HEAL_BORK = new BorkHealMessage("heal_bork");
    public static CustomMessage PUSH = new PushMessage("push");
    public static CustomMessage POS = new PosMessage("pos");
    public static CustomMessage JUMP = new JumpMessage("jump");
    public static CustomMessage FALL = new JumpMessage("falldamagecancel");

    public static CustomMessage[] getMessages() {
        return new CustomMessage[] {HEAL_BORK, PUSH, POS, JUMP, FALL};
    }

    /**
     * Send messages by:
     *                PacketByteBuf buf = PacketByteBufs.create();
     *                 buf.writeUuid(attacker.getUuid());
     *                 ServerPlayNetworking.send((ServerPlayerEntity) attacker, MessageRegistry.HEAL_BORK.getIdentifier(), buf);
     */

}
