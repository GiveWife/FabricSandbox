package net.givewife.additions.particles.effects;

import net.givewife.additions.particles.CustomEffect;
import net.givewife.additions.util.positions.Pos;
import net.givewife.additions.util.positions.VecTrail;
import net.givewife.additions.util.positions.player.BodyLocations;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class EffectSimpleBox extends CustomEffect {

    private final PlayerEntity player;
    private final int stepsPerBlock = 10;
    private final VecTrail[] trails;
    private final DefaultParticleType type;

    public EffectSimpleBox(PlayerEntity player) {
        this.player = player;
        this.trails = init();
        this.type = ParticleTypes.END_ROD;
    }

    public EffectSimpleBox(PlayerEntity player, DefaultParticleType type) {
        this.player = player;
        this.trails = init();
        this.type = type;
    }

    /**
     * Calculates all necessary parts to run this effect.
     * @return
     */
    private VecTrail[] init() {

        // Starter variables
        BodyLocations b = new BodyLocations();
        double yaw = Math.toRadians(player.bodyYaw);
        Pos pos = new Pos(player);

        // Get positions body
        Pos topLeftFront = b.getPosTopLeft(pos, yaw);
        Pos topRightFront = b.getPosTopRight(pos, yaw);
        Pos topLeftBack = b.getPosTopLeftBack(pos, yaw);
        Pos topRightBack = b.getPosTopRightBack(pos, yaw);
        Pos botLeftFront = b.getPosTopLeft(pos, yaw).down(0.6d);
        Pos botRightFront = b.getPosTopRight(pos, yaw).down(0.6d);
        Pos botLeftBack = b.getPosTopLeftBack(pos, yaw).down(0.6d);
        Pos botRightBack = b.getPosTopRightBack(pos, yaw).down(0.6d);

        return new VecTrail[]{
                // Front
                new VecTrail("topbox1", topLeftFront, topLeftBack, 10),
                new VecTrail("topbox2", topLeftBack, topRightBack, 10),
                new VecTrail("topbox3", topRightFront, topRightBack, 10),
                new VecTrail("topbox4", topLeftFront, topRightFront, 10),

                // Ribs
                new VecTrail("ribs1", topLeftFront, botLeftFront, 10),
                new VecTrail("ribs2", topLeftBack, botLeftBack, 10),
                new VecTrail("ribs3", topRightFront, botRightFront, 10),
                new VecTrail("ribs4", topRightBack, botRightBack, 10),

                // Ribs
                new VecTrail("botbox1", botLeftFront, botLeftBack, 10),
                new VecTrail("botbox2", botLeftBack, botRightBack, 10),
                new VecTrail("botbox3", botRightBack, botRightFront, 10),
                new VecTrail("botbox4", botRightFront, botRightFront, 10)

        };
    }

    /*
                //Front
            new VecTrail("topbox1", topLeftFront, topLeftBack),
            new VecTrail("topbox2", topLeftBack, topRightBack),
            new VecTrail("topbox3", topRightFront, topRightBack),
            new VecTrail("topbox4", topLeftFront, topRightFront),

     */

    @Override
    public void run(ServerWorld world) {

        for(int i = 0; i < trails.length; i++) {

            VecTrail trail = trails[i];
            //System.out.println("[Server] Steps: " + trail.getSteps());

            for(int j = 0; j < trail.getSteps(); j++) {

                Pos p = trail.getOffset(j);
                //System.out.println("[Server] Spawning at: " + p.getPrint());

                world.spawnParticles(this.type, p.x(), p.y(), p.z(), 1, 0, 0, 0, 0);

            }

        }

    }

    @Override
    public void run(World world) {

        for(int i = 0; i < trails.length; i++) {

            VecTrail trail = trails[i];
            //System.out.println("[Client] Steps: " + trail.getSteps());

            for(int j = 0; j < trail.getSteps(); j++) {

                Pos p = trail.getOffset(j);
                System.out.println("Type: " + trail.getId());
                //System.out.println("[Client] Spawning at: " + p.getPrint());

                world.addParticle(this.type, true, p.x(), p.y(), p.z(), 0, 0, 0);

            }

        }

    }

}
