package net.givewife.additions.particles.printer;

import net.givewife.additions.particles.effects.EffectLine;
import net.givewife.additions.util.positions.Pos;
import net.givewife.additions.util.positions.VecTrail;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.Particle;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

/**
 * R = 255
 * B++
 * (255)
 *
 * B ?= 255?
 * R--
 * (255)
 *
 * R ?= 0?
 * G++
 * (255)
 *
 * G ?= 255?
 * B--
 * (255)
 *
 * B ?= 0?
 * R++
 * (255)
 *
 * R ?= 255?
 * G--
 * (255)
 */
public class ColoredParticle {

    private final int maxticks;
    private int currentTick = 0;
    private final float addition;

    public ColoredParticle(int maxticks) {
        this.maxticks = maxticks;
        this.addition = (255*6)/maxticks;
    }

    public void rainbow(DefaultParticleType type, World world, Pos p, int tick) {
        if(world.isClient && currentTick / (6*255) < 1) {
            Particle particle = MinecraftClient.getInstance().particleManager.addParticle(type, p.x(), p.y(), p.z(), 0, 0, 0);
            float[] colors = getColors();
            printarr(colors);
            particle.setColor(colors[0], colors[1], colors[2]);
        }
        currentTick += addition;
    }

    private float[] getColors() {
        if(currentTick % (6*255) >= 1) currentTick %= (6*255);
        // We see how many times 255 has gone by
        int stage = currentTick / 255;
        int rest = currentTick - (stage*255);
        System.out.println("Current tick: " + currentTick + " | stage: " + stage + " | rest: " + rest);
        float scaled = (float) rest/255;
        float inverted = (float) 1 - scaled;

        if(stage == 0) return new float[] {1               , 0              , scaled};
        if(stage == 1) return new float[] {inverted    , 0              , 1};
        if(stage == 2) return new float[] {0               , scaled         , 1};
        if(stage == 3) return new float[] {0               , 1              , inverted};
        if(stage == 4) return new float[] {scaled          , 1              , 0};
        if(stage == 5) return new float[] {1               , inverted   , 0};

        System.out.println("Returned unknown type");
        return new float[] {1, 0, 0};
    }

    public void displayAll(Pos p) {

        p.east(3).up(2);
        VecTrail trail = new VecTrail("trail", p.north(2), p.south(14), 7*255);


        for(int i = 0; i < 7*255; i++) {
            p = trail.offset(i);
            Particle particle = MinecraftClient.getInstance().particleManager.addParticle(ParticleTypes.END_ROD, p.x(), p.y(), p.z(), 0, 0, 0);
            int color = i % 255;
            float scale = (float) color / 255;
            if(i < 255) particle.setColor(scale, 0, 0);
            else if(i >= 255 && i < 2*255) particle.setColor(1, 0, scale);
            else if(i >= 2*255 && i < 3*255) particle.setColor((float) 1 - scale, 0, 1);
            else if(i >= 3*255 && i < 4*255) particle.setColor(0, scale, 1);
            else if(i >= 4*255 && i < 5*255) particle.setColor(0, 1, (float) 1 - scale);
            else if(i >= 5*255 && i < 6*255) particle.setColor(scale, 1, 0);
            else if(i >= 6*255 && i < 7*255) particle.setColor(1, (float) 1-scale, 0);
        }

    }

    private void printarr(float[] arr) {
        StringBuilder s = new StringBuilder();
        s.append("[");
        for(int i = 0; i < arr.length; i++) {
            s.append(Float.toString(arr[i]));
            if(i < arr.length-1) s.append(", ");
            else s.append("]");
        }
        System.out.println("Arr: " + s.toString());
    }



}
