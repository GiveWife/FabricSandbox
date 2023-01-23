package net.givewife.additions.registry.registries;

import net.givewife.additions.commands.CustomCommand;
import net.givewife.additions.commands.FeedCommand;
import net.givewife.additions.commands.HealCommand;
import net.givewife.additions.commands.SaturateCommand;

public class CommandRegistry {

    private static CustomCommand HEAL_COMMAND = new HealCommand();
    public static CustomCommand FEED_COMMAND = new FeedCommand();
    public static CustomCommand SATURATE_COMMAND = new SaturateCommand();

    private static CustomCommand[] commands = new CustomCommand[] {

            HEAL_COMMAND, FEED_COMMAND, SATURATE_COMMAND

    };

    public static void registerCommands() {

        for(int i = 0; i < commands.length; i++) {

            commands[i].register();

        }

    }

}
