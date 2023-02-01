package net.givewife.additions.util.positions;

import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public abstract class Trail extends Vec {

    private final int steps;
    private final String type;

    public Trail(String type, Pos from, Pos to, int steps) {
        super(from, to);
        this.type = type;
        this.steps = steps;
    }

    public abstract Pos offset(int offset);

    public void printParticles(World world) {
        for(int i = 0; i < this.steps; i++) {
            Pos print = offset(i);
            world.addParticle(ParticleTypes.END_ROD, print.x(), print.y(), print.z(), 0, 0, 0);
        }
    }

    public void printParticles(ServerWorld world) {
        for(int i = 0; i < this.steps; i++) {
            Pos print = offset(i);
            world.spawnParticles(ParticleTypes.END_ROD, print.x(), print.y(), print.z(), 1, 0, 0, 0, 0);
        }
    }

    public int getScaledOffset(int offset) {
        return isStepInvalid(offset) ? offset % steps : offset;
    }

    public boolean isStepInvalid(int step) {
        return step > steps;
    }

    public int getSteps() {
        return this.steps;
    }

    public String getPrint() {
        StringBuilder builder = new StringBuilder();
        builder.append("[").append(type).append("] from: ").append(this.from().getPrint()).append(" ; to: ").append(this.to().getPrint());
        return builder.toString();
    }

    public String getType() {
        return this.type;
    }

}
