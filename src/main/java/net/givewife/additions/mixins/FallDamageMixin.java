package net.givewife.additions.mixins;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.givewife.additions.data.PlayerState;
import net.givewife.additions.data.ServerState;
import net.givewife.additions.registry.registries.MessageRegistry;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.UUID;

@Mixin(PlayerEntity.class)
public abstract class FallDamageMixin {

    @Inject(method = "damage", at = @At("HEAD"))
    public void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> info) {

        PlayerEntity player = (PlayerEntity) (Object) this;

        if(player.world.getServer() != null) {

            ServerState state = ServerState.getServerState(player.world.getServer());

            for (Map.Entry<UUID, PlayerState> entry : state.players.entrySet()) {
                if (entry.getKey() == player.getUuid()) {

                    //DEBUG
                    System.out.println("UUID: " + player.getUuid());
                    System.out.println("HasJumped: " + entry.getValue().hasJumped);

                    if (entry.getValue().hasJumped) {

                        //DEBUG
                        System.out.println("Trying to cancel.");

                        //Try cancel!
                        //if (source == DamageSource.FALL && info.isCancellable()) info.setReturnValue(false);

                        System.out.println("Reached this line!");

                    }
                }
            }

        }

    }

    //@Inject(method = "applyDamage", at = @At("TAIL"))
    public void applyDamageTail(DamageSource source, float amount, CallbackInfo info) {
        System.out.println("Amount after: " + amount);
    }

    //@ModifyVariable( method = "applyDamage", at = @At("HEAD"), ordinal = 0)
    public float applyDamage(float amount, DamageSource source) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if(source == DamageSource.FALL && state(player)) {
            System.out.println("Amount to zero");
            return 0;
        }

        return amount;
    }

    @ModifyVariable( method = "applyDamage", at = @At("HEAD"), ordinal = 0)
    public float damage(float amount, DamageSource source) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if(source == DamageSource.FALL && state(player)) {

            PacketByteBuf data = PacketByteBufs.create();
            data.writeUuid(player.getUuid());
            data.writeBoolean(false);

            //Sending to client and server to set to false
            ServerPlayNetworking.send((ServerPlayerEntity) player, MessageRegistry.JUMP.getIdentifier(), data);
            ClientPlayNetworking.send(MessageRegistry.JUMP.getIdentifier(), data);


            return 0;
        }

        return amount;
    }


    //@Inject(method = "handleFallDamage", at = @At("HEAD"))
    public void handleFallDamage(float dist, float damagemult, DamageSource source, CallbackInfoReturnable<Boolean> info) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if(source == DamageSource.FALL && state(player)) {

            System.out.println("Arrived at the destination");
            damagemult = 0;
            dist = 0f;

        }

    }

    //@Inject(method = "isInvulnerableTo", at = @At("HEAD"))
    public void isinvulerable(DamageSource source, CallbackInfoReturnable<Boolean> info) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if(source == DamageSource.FALL && state(player)) {

            info.setReturnValue(false);

        }

    }

    private boolean state(PlayerEntity player) {

        if(player.world.getServer() == null) return false;
        ServerState state = ServerState.getServerState(player.world.getServer());

        for (Map.Entry<UUID, PlayerState> entry : state.players.entrySet()) {
            if (entry.getKey() == player.getUuid()) {

                //DEBUG
                //System.out.println("UUID: " + player.getUuid());
                //System.out.println("HasJumped: " + entry.getValue().hasJumped);

                if (entry.getValue().hasJumped) {

                    //System.out.println("False? " + entry.getValue().hasJumped);
                    return true;

                }
            }
        }

        return false;
    }

}
