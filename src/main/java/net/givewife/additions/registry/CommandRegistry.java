package net.givewife.additions.registry;

import net.givewife.additions.registry.commands.CustomCommand;
import net.givewife.additions.registry.commands.HealCommand;

public class CommandRegistry {

    private static CustomCommand HEAL_COMMAND = new HealCommand();

    private static CustomCommand[] commands = new CustomCommand[] {

            HEAL_COMMAND

    };

    public static void registerCommands() {

        for(int i = 0; i < commands.length; i++) {

            commands[i].register();

        }

    }

}
