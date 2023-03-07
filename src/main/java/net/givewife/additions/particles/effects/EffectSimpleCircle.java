package net.givewife.additions.particles.effects;

import net.givewife.additions.Main;
import net.givewife.additions.particles.CustomEffect;
import net.givewife.additions.particles.customparticles.ColoredParticle;
import net.givewife.additions.particles.customparticles.StarParticle;
import net.givewife.additions.registry.registries.ParticleRegistry;
import net.givewife.additions.util.DebugHelper;
import net.givewife.additions.util.positions.Pos;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteContents;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.awt.*;
import java.lang.reflect.Method;

public class EffectSimpleCircle extends CustomEffect {

    private final Pos pos;

    public EffectSimpleCircle(Pos pos) {
        this.pos = pos;
    }

    @Override
    public void run(ServerWorld world) {

    }

    @Override
    public void run(World world) {

        //ParticleEffect s = new StarParticle();
        DebugHelper help = new DebugHelper("particle");

        // One radial = 57.29degrees
        double radianToDegree = 57.29d;
        double add = 0;
        int[] degreesFound = new int[360];
        ColoredParticle p = new ColoredParticle(360);

        for(int i = 0; i < 900; i++) {

            //pos.x() + sin(i), pos.y(), pos.z() + cos(i), 0, 0, 0);
            //p.rainbow(ParticleTypes.END_ROD, world, new Pos(pos.x() + sin(i), pos.y(), pos.z() + cos(i)), i);

            add += radianToDegree;
            if(degreesFound[(int) add%360] == 0) degreesFound[(int) add%360] = 1;

        }

        p.displayAll(pos);

        //help.log("Degrees Found: " + help.intToString(degreesFound));

    }

}
