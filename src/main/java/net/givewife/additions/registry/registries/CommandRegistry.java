package net.givewife.additions.registry.registries;

import net.givewife.additions.commands.CustomCommand;
import net.givewife.additions.commands.FeedCommand;
import net.givewife.additions.commands.HealCommand;

public class CommandRegistry {

    private static CustomCommand HEAL_COMMAND = new HealCommand();
    public static CustomCommand FEED_COMMAND = new FeedCommand();

    private static CustomCommand[] commands = new CustomCommand[] {

            HEAL_COMMAND, FEED_COMMAND

    };

    public static void registerCommands() {

        for(int i = 0; i < commands.length; i++) {

            commands[i].register();

        }

    }

}
