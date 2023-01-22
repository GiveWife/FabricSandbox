package net.givewife.additions.mixins;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


/**
 * Class that changes how modern Minecraft is exploited.
 * We will focus on three main things:
 *      1) Mob farms, Iron farms, Gold farms, XP farms...
 *      2) Enchanting, villager trading, enchanted books...
 *      3) Transportation balances
 *
 */
@Mixin(LivingEntity.class)
public class EntityDropMixin {

    @Inject(at = @At("HEAD"), method = "drop", cancellable = true)
    private void irondrop(DamageSource ds, CallbackInfo ci) {

    }

}
