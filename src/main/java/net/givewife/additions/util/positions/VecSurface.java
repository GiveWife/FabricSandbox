package net.givewife.additions.util.positions;

import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;

public class VecSurface {

    private final Pos from, to, diff;
    private final int steps, lines;
    private final Pos[] lineStart, lineEnd;
    private final VecTrail[] trails;
    public VecSurface(Pos from, Pos to, int lines, int steps) {
        this.from = from;
        this.to = to;
        this.lines = lines;
        this.steps = steps;

        // The coordinates of this vector indicate how much you have to add to "to" to get to "from"
        this.diff = from.subtract(to);
        this.lineStart = computeLine(to, 1);
        this.lineEnd = computeLine(from, -1);

        System.out.println("From: " + from.getPrint() + " ; To: " + to.getPrint() + " ; diff: " + diff.getPrint());

        trails = new VecTrail[lines];
        for(int i = 0; i < lines; i++) {
            trails[i] = new VecTrail("trail:" + Integer.toString(i), lineStart[i], lineEnd[i], 100);
        }
    }

    /**
     * The mod indicates if we should add the difference to the vector or subtract it.
     * (1,3) -> (4,2)
     * diff: (-3, 1)
     * From (4,2), we should add (-3,1) to get to (1,3)
     * From (1,3), we should subtract (-3,1) to get to (4,2)
     */
    private Pos[] computeLine(Pos start, int mod) {

        Pos[] starts = new Pos[lines];

        if(mod == 1) System.out.println("\n\nSTARTS\n\n");
        else System.out.println("\n\nENDS\n\n");

        for(int i = 0; i < lines; i++) {

            starts[i] = new Pos(
                    start.x() + (i*mod*diff.x()/lines),
                    start.y() + (i*mod*diff.y()/lines),
                    start.z() + (i*mod*diff.z()/lines)
            );

            System.out.println("(" + i + "): " + starts[i].getPrint());

        }

        return starts;

    }

    public void print(ServerWorld server) {
        for(int i = 0; i < lines; i++) {
            for(int j = 0; j < 100; j++) {
                Pos next = trails[i].getOffset(j);
                server.spawnParticles(ParticleTypes.END_ROD, next.x(), next.y(), next.z(), 1, 0, 0, 0, 0);
            }
        }
    }

}
