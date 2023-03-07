package net.givewife.additions.registry.registries;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fabricmc.fabric.impl.client.particle.ParticleFactoryRegistryImpl;
import net.givewife.additions.Main;
import net.givewife.additions.particles.customparticles.StarParticle;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ParticleRegistry {

    public static final DefaultParticleType WHITE_STAR = FabricParticleTypes.simple();


    public static void register() {


        Registry.register(Registries.PARTICLE_TYPE, new Identifier(Main.MODID, "white_star"), WHITE_STAR);

    }

    public static void registerClient() {

        ParticleFactoryRegistry.getInstance().register(WHITE_STAR, StarParticle.Factory::new);

    }

}
