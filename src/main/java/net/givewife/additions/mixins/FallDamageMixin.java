package net.givewife.additions.mixins;

import net.givewife.additions.data.PlayerState;
import net.givewife.additions.data.ServerState;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.UUID;

@Mixin(PlayerEntity.class)
public abstract class FallDamageMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(CallbackInfo info) {

        PlayerEntity player = (PlayerEntity) (Object) this;
        if(player.world.getServer() == null) return;

        ServerState state = ServerState.getServerState(player.world.getServer());

        for(Map.Entry<UUID, PlayerState> entry : state.players.entrySet()) {
            if(entry.getKey() == player.getUuid()) {
                System.out.println("UUID: " + player.getUuid());
                System.out.println("HasJumped: " + entry.getValue().hasJumped);
                if(entry.getValue().hasJumped) {
                    System.out.println("Trying to cancel.");
                    //Try cancel!
                    player.fallDistance = 0;
                }
            }
        }

    }

}
