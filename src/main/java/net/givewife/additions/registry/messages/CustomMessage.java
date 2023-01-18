package net.givewife.additions.registry.messages;

import net.givewife.additions.Main;
import net.givewife.additions.util.GeneralHelper;
import net.minecraft.util.Identifier;

public abstract class CustomMessage {

    private String name;
    private final Identifier identifier;
    public CustomMessage(String name) {
        this.name = name;
        this.identifier = new Identifier(Main.MODID, name);
    }

    public abstract void registerClient();

    public abstract void registerServer();

    public Identifier getIdentifier() {
        return identifier;
    }

    public void log(String message) {
        System.out.println("[" + name + "] " + message);
    }

}
