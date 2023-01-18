package net.givewife.additions.mixins;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractMinecartEntity.class)
public abstract class MinecartMixin {

    @Inject(method = "getVelocityMultiplier", at = @At("RETURN"), cancellable = true)
    public void velocityMultiplier(CallbackInfoReturnable<Float> in) {
        AbstractMinecartEntity e = (AbstractMinecartEntity) (Object) this;
        BlockState blockState = e.world.getBlockState(e.getBlockPos());
        if(blockState.getBlock() == Blocks.POWERED_RAIL) {
            if(blockState.getProperties().contains(PoweredRailBlock.POWERED) && blockState.get(PoweredRailBlock.POWERED) == true) {
                in.setReturnValue(500f);
            }
        }
    }


}
