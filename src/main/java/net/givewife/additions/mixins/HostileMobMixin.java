package net.givewife.additions.mixins;

import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HostileEntity.class)
public abstract class HostileMobMixin {

    @Inject(method = "Lnet/minecraft/entity/mob/HostileEntity;isAngryAt(Lnet/minecraft/entity/player/PlayerEntity;)Z", at = @At("HEAD"))
    public void is(PlayerEntity player, CallbackInfoReturnable<Boolean> ci) {
        System.out.println("Returning false");
    }

}
