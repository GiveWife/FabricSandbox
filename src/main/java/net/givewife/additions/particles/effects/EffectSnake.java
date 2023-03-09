package net.givewife.additions.particles.effects;

import net.givewife.additions.util.EffectSnakeHelper;
import net.givewife.additions.util.positions.Parabola;
import net.givewife.additions.util.positions.Pos;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class EffectSnake extends CustomEffect {

    private final Parabola[] jumps;

    /**
     * @pre | bounces >= 2
     */
    public EffectSnake(Pos start, float radius, int heightlimit, World world, int bounces) {
        if(bounces < 2) bounces = 2;
        EffectSnakeHelper helper = new EffectSnakeHelper(start, radius, heightlimit, world, bounces);
        this.jumps = helper.getJumps();
    }

    public Parabola[] getJumps() {
        return this.jumps;
    }

    @Override
    public void run(World world) {

    }

    @Override
    public void run(ServerWorld world) {

    }

}
