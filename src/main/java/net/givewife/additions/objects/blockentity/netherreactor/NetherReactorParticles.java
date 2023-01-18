package net.givewife.additions.objects.blockentity.netherreactor;

public class NetherReactorParticles {

    public NetherReactorParticles() {

    }


    public static abstract class ParticleStage {

        private int stage, ticks;
        public ParticleStage(int stage, int ticks) {
            this.stage = stage;
            this.ticks = ticks;
        }

        public void execute(int tick) {};

    }

}
