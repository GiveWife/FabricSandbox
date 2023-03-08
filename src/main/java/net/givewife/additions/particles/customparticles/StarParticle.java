package net.givewife.additions.particles.customparticles;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fabricmc.fabric.mixin.client.particle.ParticleManagerAccessor;
import net.givewife.additions.Main;
import net.givewife.additions.registry.registries.ParticleRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.*;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;

import java.util.Map;

public class StarParticle extends SpriteBillboardParticle {

    private final String TEXTURE = "white_star";

    public StarParticle(ClientWorld level, double xCoord, double yCoord, double zCoord,
                        SpriteProvider spriteSet, double xd, double yd, double zd) {
        super(level, xCoord, yCoord, zCoord, xd, yd, zd);

        this.velocityMultiplier = 0.0F;
        this.x = xCoord;
        this.y = yCoord;
        this.z = zCoord;
        this.velocityX = 0;
        this.velocityY = 0;
        this.velocityZ = 0;
        this.scale *= 0.75F;
        this.maxAge = 1000;
        this.setSpriteForAge(spriteSet);

        this.red = 1f;
        this.green = 1f;
        this.blue = 1f;
    }

    public void spawn() {
        if(this.sprite != null)
            MinecraftClient.getInstance().particleManager.addParticle(this);
    }

    @Override
    public void setColor(float red, float green, float blue) {
        super.setColor(red, green, blue);
    }

    @Override
    public void tick() {
        super.tick();
        fadeOut();
    }

    @Override
    public void setSpriteForAge(SpriteProvider spriteProvider) {
        if (!this.dead) {
            this.setSprite(spriteProvider.getSprite(this.age, this.maxAge));
        }
    }

    private void fadeOut() {
        this.alpha = (-(1/(float)maxAge) * age + 1);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        public SpriteProvider sprites;

        public Factory(SpriteProvider spriteSet) {
            this.sprites = spriteSet;
            Main.p = spriteSet;
        }

        public Particle createParticle(DefaultParticleType particleType, ClientWorld level, double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new StarParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }

        public SpriteProvider getProvider() {
            return this.sprites;
        }

    }

}
