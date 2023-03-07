package net.givewife.additions.particles.effects;

import net.givewife.additions.Main;
import net.givewife.additions.particles.CustomEffect;
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

        for(int i = 0; i < 360; i++) {

            //world.addParticle(ParticleTypes.END_ROD, pos.x() + sin(i), pos.y(), pos.z() + cos(i), 0, 0, 0);
            boolean firstMethod = false;
            if(Main.p != null && firstMethod) {
                StarParticle p = new StarParticle((ClientWorld) world, pos.x(), pos.y(), pos.z(), Main.p, 0, 0, 0);
                p.setColor(0, 1, 0);
                if (Main.p != null)
                    p.spawn();
            }
            if(!firstMethod) {

                Particle p = MinecraftClient.getInstance().particleManager.addParticle(ParticleRegistry.WHITE_STAR, pos.x() + sin(i), pos.y(), pos.z() + cos(i), 0, 0, 0);
                p.setColor(0, i, 360-i);

            }

            add += radianToDegree;
            if(degreesFound[(int) add%360] == 0) degreesFound[(int) add%360] = 1;

        }

        //help.log("Degrees Found: " + help.intToString(degreesFound));

    }

    private void identify() {
        Sprite p = Main.p.getSprite(0, 60);
        SpriteContents t = p.getContents();

        System.out.println("Id: " + p.getAtlasId().toString());
        System.out.println("Contents: " + p.getContents());
            System.out.println("   Id: " + t.getId().toString());
            System.out.println("   Dimensions: ");
        System.out.println("Maxlevel: " + (p.getX()/p.getMinU()));
        System.out.println("atlasWidth: " + (p.getY()/p.getMinV()));
        System.out.println("atlasHeight: " + p.getX());
        System.out.println("x" + p.getY());
        MinecraftClient.getInstance().getSpriteAtlas(p.getAtlasId()).apply(new Identifier("additions:white_star"));
    }

}
