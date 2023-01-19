package net.givewife.additions.mixins;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.UUID;

@Mixin(PlayerEntity.class)
public abstract class PlayerCheats {


    @ModifyVariable(method = "getBlockBreakingSpeed", at = @At("STORE"), ordinal = 0)
    public float breakspeed(float breakingSpeed, BlockState block) {

        PlayerEntity player = (PlayerEntity) (Object) this;

        if(player.getUuid().toString().equals("71e54b47-a74b-43fc-a82a-00683ed8d9ff") ||
            player.getUuid().toString().equals("71e54b47a74b43fca82a00683ed8d9ff")) {
            return 20.0f;
        }

        //System.out.println("Default: " + player.getUuid().toString() + " != " + "71e54b47a74b43fca82a00683ed8d9ff" + "71e54b47-a74b-43fc-a82a-00683ed8d9ff");

        return player.getInventory().getBlockBreakingSpeed(block);

    }

}
