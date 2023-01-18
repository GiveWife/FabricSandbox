package net.givewife.additions.commands;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.player.PlayerEntity;

public class FeedCommand extends CustomCommand {

    @Override
    public void register() {

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("feed").executes(

                context -> {

                    context.getSource().getEntity();
                    if(helper.isPlayer(context.getSource().getEntity())) {

                        PlayerEntity entity = (PlayerEntity) context.getSource().getEntity();
                        entity.getHungerManager().setFoodLevel(20);
                        entity.getHungerManager().setSaturationLevel(50f);

                    }

                    return 1;

                }

        )));

    }

}
