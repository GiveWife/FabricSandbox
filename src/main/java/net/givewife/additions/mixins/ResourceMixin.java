package net.givewife.additions.mixins;


import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.ResourceTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NativeImage.class)
public class ResourceMixin {

    @Inject(method = "blend", at = @At("HEAD"))
    public void upload(int x, int y, int color, CallbackInfo ci) {
        System.out.println("at: " + x + ", " + y);
    }

}
