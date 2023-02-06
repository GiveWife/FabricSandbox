package net.givewife.additions.particles.effects;

import net.givewife.additions.particles.CustomEffect;
import net.givewife.additions.util.DebugHelper;
import net.givewife.additions.util.positions.Pos;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

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

        DebugHelper help = new DebugHelper("particle");

        // One radial = 57.29degrees
        double radianToDegree = 57.29d;
        double add = 0;
        int[] degreesFound = new int[360];

        for(int i = 0; i < 360; i++) {
            world.addParticle(ParticleTypes.END_ROD, pos.x() + sin(i), pos.y(), pos.z() + cos(i), 0, 0, 0);

            add += radianToDegree;
            help.log("add: " + add);
            help.log("Modulo 360: " + (add%360));
            if(degreesFound[(int) add%360] == 0) degreesFound[(int) add%360] = 1;

        }

        help.log("Degrees Found: " + help.intToString(degreesFound));

    }

}
