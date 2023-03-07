package net.givewife.additions.objects.items;

import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.givewife.additions.Main;
import net.givewife.additions.objects.templates.CustomSettings;
import net.givewife.additions.particles.CustomEffect;
import net.givewife.additions.particles.customparticles.StarParticle;
import net.givewife.additions.particles.effects.EffectSimpleCircle;
import net.givewife.additions.particles.effects.EffectSnake;
import net.givewife.additions.util.positions.Parabola;
import net.givewife.additions.util.positions.Pos;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.FireworksSparkParticle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.particle.TotemParticle;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.List;

public class TestItem extends ModItem {

    private final String KEY = "key";
    private final int COOLDOWN = 40;

    //String tag, int cooldown, boolean switchAble, String name,
    public TestItem(String name) {
        super(name, CustomSettings.getDefaultSettings());
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if(!world.isClient) return super.use(world, user, hand);
        Pos pos = new Pos(user);

        CustomEffect circle = new EffectSimpleCircle(pos);


        circle.run(world);


        return super.use(world, user, hand);
    }
}
