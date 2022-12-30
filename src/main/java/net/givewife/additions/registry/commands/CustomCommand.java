package net.givewife.additions.registry.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.givewife.additions.util.GeneralHelper;
import net.minecraft.command.CommandSource;
import net.minecraft.server.command.ServerCommandSource;

public abstract class CustomCommand {

    GeneralHelper helper = new GeneralHelper();
    public CustomCommand() {}

    public abstract void register();

    public LiteralArgumentBuilder<ServerCommandSource> literal(String literal) {
        return LiteralArgumentBuilder.literal(literal);
    }

}
