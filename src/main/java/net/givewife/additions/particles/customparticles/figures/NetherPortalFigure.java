package net.givewife.additions.particles.customparticles.figures;

import net.givewife.additions.particles.customparticles.ParticleDrawer;
import net.givewife.additions.util.Tuple;
import net.givewife.additions.util.positions.Pos;

public class NetherPortalFigure extends ParticleDrawer {

    public NetherPortalFigure(Pos origin) {
        super(origin);
    }

    @Override
    public Tuple<Pos, float[]>[] getDrawPositions() {

        Pos origin = getOrigin();

        Tuple<Pos, float[]>[] arr = new Tuple[] {

                (new Tuple<Pos, float[]>(origin.up( 1 ).south( 0.0 ).east(0.5), new float[] {0.2941f, 0.0078f, 0.7333f})),
                (new Tuple<Pos, float[]>(origin.up( 1 ).south( 0.0625 ).east(0.5), new float[] {0.4196f, 0.0667f, 0.8078f})),
                (new Tuple<Pos, float[]>(origin.up( 1 ).south( 0.125 ).east(0.5), new float[] {0.451f, 0.0863f, 0.8196f})),
                (new Tuple<Pos, float[]>(origin.up( 1 ).south( 0.1875 ).east(0.5), new float[] {0.3961f, 0.0549f, 0.7961f})),
                (new Tuple<Pos, float[]>(origin.up( 1 ).south( 0.25 ).east(0.5), new float[] {0.3804f, 0.0431f, 0.7882f})),
                (new Tuple<Pos, float[]>(origin.up( 1 ).south( 0.3125 ).east(0.5), new float[] {0.2784f, 0.0039f, 0.7176f})),
                (new Tuple<Pos, float[]>(origin.up( 1 ).south( 0.375 ).east(0.5), new float[] {0.2824f, 0.0039f, 0.7216f})),
                (new Tuple<Pos, float[]>(origin.up( 1 ).south( 0.4375 ).east(0.5), new float[] {0.3922f, 0.051f, 0.7922f})),
                (new Tuple<Pos, float[]>(origin.up( 1 ).south( 0.5 ).east(0.5), new float[] {0.3176f, 0.0157f, 0.749f})),
                (new Tuple<Pos, float[]>(origin.up( 1 ).south( 0.5625 ).east(0.5), new float[] {0.2431f, 0.0f, 0.6784f})),
                (new Tuple<Pos, float[]>(origin.up( 1 ).south( 0.625 ).east(0.5), new float[] {0.3843f, 0.0471f, 0.7882f})),
                (new Tuple<Pos, float[]>(origin.up( 1 ).south( 0.6875 ).east(0.5), new float[] {0.5098f, 0.1412f, 0.8471f})),
                (new Tuple<Pos, float[]>(origin.up( 1 ).south( 0.75 ).east(0.5), new float[] {0.3176f, 0.0157f, 0.749f})),
                (new Tuple<Pos, float[]>(origin.up( 1 ).south( 0.8125 ).east(0.5), new float[] {0.2196f, 0.0f, 0.6431f})),
                (new Tuple<Pos, float[]>(origin.up( 1 ).south( 0.875 ).east(0.5), new float[] {0.2353f, 0.0f, 0.6706f})),
                (new Tuple<Pos, float[]>(origin.up( 1 ).south( 0.9375 ).east(0.5), new float[] {0.3333f, 0.0196f, 0.7569f})),
                (new Tuple<Pos, float[]>(origin.up( 0.9375 ).south( 0.0 ).east(0.5), new float[] {0.6157f, 0.2627f, 0.8863f})),
                (new Tuple<Pos, float[]>(origin.up( 0.9375 ).south( 0.0625 ).east(0.5), new float[] {0.5294f, 0.1608f, 0.8549f})),
                (new Tuple<Pos, float[]>(origin.up( 0.9375 ).south( 0.125 ).east(0.5), new float[] {0.4f, 0.0549f, 0.7961f})),
                (new Tuple<Pos, float[]>(origin.up( 0.9375 ).south( 0.1875 ).east(0.5), new float[] {0.5412f, 0.1725f, 0.8588f})),
                (new Tuple<Pos, float[]>(origin.up( 0.9375 ).south( 0.25 ).east(0.5), new float[] {0.4588f, 0.0941f, 0.8235f})),
                (new Tuple<Pos, float[]>(origin.up( 0.9375 ).south( 0.3125 ).east(0.5), new float[] {0.2275f, 0.0f, 0.6549f})),
                (new Tuple<Pos, float[]>(origin.up( 0.9375 ).south( 0.375 ).east(0.5), new float[] {0.2314f, 0.0f, 0.6627f})),
                (new Tuple<Pos, float[]>(origin.up( 0.9375 ).south( 0.4375 ).east(0.5), new float[] {0.3137f, 0.0157f, 0.749f})),
                (new Tuple<Pos, float[]>(origin.up( 0.9375 ).south( 0.5 ).east(0.5), new float[] {0.2588f, 0.0f, 0.698f})),
                (new Tuple<Pos, float[]>(origin.up( 0.9375 ).south( 0.5625 ).east(0.5), new float[] {0.2196f, 0.0f, 0.6431f})),
                (new Tuple<Pos, float[]>(origin.up( 0.9375 ).south( 0.625 ).east(0.5), new float[] {0.3137f, 0.0157f, 0.7451f})),
                (new Tuple<Pos, float[]>(origin.up( 0.9375 ).south( 0.6875 ).east(0.5), new float[] {0.2902f, 0.0078f, 0.7255f})),
                (new Tuple<Pos, float[]>(origin.up( 0.9375 ).south( 0.75 ).east(0.5), new float[] {0.2549f, 0.0f, 0.698f})),
                (new Tuple<Pos, float[]>(origin.up( 0.9375 ).south( 0.8125 ).east(0.5), new float[] {0.2824f, 0.0039f, 0.7216f})),
                (new Tuple<Pos, float[]>(origin.up( 0.9375 ).south( 0.875 ).east(0.5), new float[] {0.3059f, 0.0118f, 0.7373f})),
                (new Tuple<Pos, float[]>(origin.up( 0.9375 ).south( 0.9375 ).east(0.5), new float[] {0.3294f, 0.0196f, 0.7569f})),
                (new Tuple<Pos, float[]>(origin.up( 0.875 ).south( 0.0 ).east(0.5), new float[] {0.4784f, 0.1137f, 0.8353f})),
                (new Tuple<Pos, float[]>(origin.up( 0.875 ).south( 0.0625 ).east(0.5), new float[] {0.4196f, 0.0667f, 0.8078f})),
                (new Tuple<Pos, float[]>(origin.up( 0.875 ).south( 0.125 ).east(0.5), new float[] {0.6118f, 0.2588f, 0.8863f})),
                (new Tuple<Pos, float[]>(origin.up( 0.875 ).south( 0.1875 ).east(0.5), new float[] {0.4118f, 0.0627f, 0.8039f})),
                (new Tuple<Pos, float[]>(origin.up( 0.875 ).south( 0.25 ).east(0.5), new float[] {0.251f, 0.0f, 0.6902f})),
                (new Tuple<Pos, float[]>(origin.up( 0.875 ).south( 0.3125 ).east(0.5), new float[] {0.2235f, 0.0f, 0.6471f})),
                (new Tuple<Pos, float[]>(origin.up( 0.875 ).south( 0.375 ).east(0.5), new float[] {0.3294f, 0.0196f, 0.7569f})),
                (new Tuple<Pos, float[]>(origin.up( 0.875 ).south( 0.4375 ).east(0.5), new float[] {0.4667f, 0.102f, 0.8275f})),
                (new Tuple<Pos, float[]>(origin.up( 0.875 ).south( 0.5 ).east(0.5), new float[] {0.4706f, 0.1059f, 0.8314f})),
                (new Tuple<Pos, float[]>(origin.up( 0.875 ).south( 0.5625 ).east(0.5), new float[] {0.3333f, 0.0235f, 0.7608f})),
                (new Tuple<Pos, float[]>(origin.up( 0.875 ).south( 0.625 ).east(0.5), new float[] {0.3961f, 0.051f, 0.7961f})),
                (new Tuple<Pos, float[]>(origin.up( 0.875 ).south( 0.6875 ).east(0.5), new float[] {0.4314f, 0.0745f, 0.8118f})),
                (new Tuple<Pos, float[]>(origin.up( 0.875 ).south( 0.75 ).east(0.5), new float[] {0.2588f, 0.0f, 0.698f})),
                (new Tuple<Pos, float[]>(origin.up( 0.875 ).south( 0.8125 ).east(0.5), new float[] {0.2157f, 0.0f, 0.6314f})),
                (new Tuple<Pos, float[]>(origin.up( 0.875 ).south( 0.875 ).east(0.5), new float[] {0.3098f, 0.0118f, 0.7451f})),
                (new Tuple<Pos, float[]>(origin.up( 0.875 ).south( 0.9375 ).east(0.5), new float[] {0.4353f, 0.0784f, 0.8157f})),
                (new Tuple<Pos, float[]>(origin.up( 0.8125 ).south( 0.0 ).east(0.5), new float[] {0.302f, 0.0118f, 0.7373f})),
                (new Tuple<Pos, float[]>(origin.up( 0.8125 ).south( 0.0625 ).east(0.5), new float[] {0.3961f, 0.0549f, 0.7961f})),
                (new Tuple<Pos, float[]>(origin.up( 0.8125 ).south( 0.125 ).east(0.5), new float[] {0.4235f, 0.0706f, 0.8078f})),
                (new Tuple<Pos, float[]>(origin.up( 0.8125 ).south( 0.1875 ).east(0.5), new float[] {0.2392f, 0.0f, 0.6784f})),
                (new Tuple<Pos, float[]>(origin.up( 0.8125 ).south( 0.25 ).east(0.5), new float[] {0.2235f, 0.0f, 0.6471f})),
                (new Tuple<Pos, float[]>(origin.up( 0.8125 ).south( 0.3125 ).east(0.5), new float[] {0.3412f, 0.0235f, 0.7647f})),
                (new Tuple<Pos, float[]>(origin.up( 0.8125 ).south( 0.375 ).east(0.5), new float[] {0.5412f, 0.1725f, 0.8588f})),
                (new Tuple<Pos, float[]>(origin.up( 0.8125 ).south( 0.4375 ).east(0.5), new float[] {0.4039f, 0.0588f, 0.8f})),
                (new Tuple<Pos, float[]>(origin.up( 0.8125 ).south( 0.5 ).east(0.5), new float[] {0.4471f, 0.0863f, 0.8196f})),
                (new Tuple<Pos, float[]>(origin.up( 0.8125 ).south( 0.5625 ).east(0.5), new float[] {0.2824f, 0.0078f, 0.7216f})),
                (new Tuple<Pos, float[]>(origin.up( 0.8125 ).south( 0.625 ).east(0.5), new float[] {0.3451f, 0.0235f, 0.7647f})),
                (new Tuple<Pos, float[]>(origin.up( 0.8125 ).south( 0.6875 ).east(0.5), new float[] {0.6196f, 0.2667f, 0.8863f})),
                (new Tuple<Pos, float[]>(origin.up( 0.8125 ).south( 0.75 ).east(0.5), new float[] {0.3961f, 0.051f, 0.7961f})),
                (new Tuple<Pos, float[]>(origin.up( 0.8125 ).south( 0.8125 ).east(0.5), new float[] {0.2431f, 0.0f, 0.6824f})),
                (new Tuple<Pos, float[]>(origin.up( 0.8125 ).south( 0.875 ).east(0.5), new float[] {0.2196f, 0.0f, 0.6431f})),
                (new Tuple<Pos, float[]>(origin.up( 0.8125 ).south( 0.9375 ).east(0.5), new float[] {0.3647f, 0.0353f, 0.7765f})),
                (new Tuple<Pos, float[]>(origin.up( 0.75 ).south( 0.0 ).east(0.5), new float[] {0.2314f, 0.0f, 0.6667f})),
                (new Tuple<Pos, float[]>(origin.up( 0.75 ).south( 0.0625 ).east(0.5), new float[] {0.3059f, 0.0118f, 0.7412f})),
                (new Tuple<Pos, float[]>(origin.up( 0.75 ).south( 0.125 ).east(0.5), new float[] {0.2196f, 0.0f, 0.6392f})),
                (new Tuple<Pos, float[]>(origin.up( 0.75 ).south( 0.1875 ).east(0.5), new float[] {0.2196f, 0.0f, 0.6353f})),
                (new Tuple<Pos, float[]>(origin.up( 0.75 ).south( 0.25 ).east(0.5), new float[] {0.3961f, 0.0549f, 0.7961f})),
                (new Tuple<Pos, float[]>(origin.up( 0.75 ).south( 0.3125 ).east(0.5), new float[] {0.5216f, 0.149f, 0.851f})),
                (new Tuple<Pos, float[]>(origin.up( 0.75 ).south( 0.375 ).east(0.5), new float[] {0.3922f, 0.051f, 0.7922f})),
                (new Tuple<Pos, float[]>(origin.up( 0.75 ).south( 0.4375 ).east(0.5), new float[] {0.2235f, 0.0f, 0.651f})),
                (new Tuple<Pos, float[]>(origin.up( 0.75 ).south( 0.5 ).east(0.5), new float[] {0.2588f, 0.0f, 0.698f})),
                (new Tuple<Pos, float[]>(origin.up( 0.75 ).south( 0.5625 ).east(0.5), new float[] {0.2549f, 0.0f, 0.6941f})),
                (new Tuple<Pos, float[]>(origin.up( 0.75 ).south( 0.625 ).east(0.5), new float[] {0.2235f, 0.0f, 0.651f})),
                (new Tuple<Pos, float[]>(origin.up( 0.75 ).south( 0.6875 ).east(0.5), new float[] {0.3412f, 0.0235f, 0.7647f})),
                (new Tuple<Pos, float[]>(origin.up( 0.75 ).south( 0.75 ).east(0.5), new float[] {0.4902f, 0.1216f, 0.8392f})),
                (new Tuple<Pos, float[]>(origin.up( 0.75 ).south( 0.8125 ).east(0.5), new float[] {0.4f, 0.0549f, 0.7961f})),
                (new Tuple<Pos, float[]>(origin.up( 0.75 ).south( 0.875 ).east(0.5), new float[] {0.2431f, 0.0f, 0.6824f})),
                (new Tuple<Pos, float[]>(origin.up( 0.75 ).south( 0.9375 ).east(0.5), new float[] {0.2235f, 0.0f, 0.6471f})),
                (new Tuple<Pos, float[]>(origin.up( 0.6875 ).south( 0.0 ).east(0.5), new float[] {0.251f, 0.0f, 0.6902f})),
                (new Tuple<Pos, float[]>(origin.up( 0.6875 ).south( 0.0625 ).east(0.5), new float[] {0.2706f, 0.0039f, 0.7098f})),
                (new Tuple<Pos, float[]>(origin.up( 0.6875 ).south( 0.125 ).east(0.5), new float[] {0.2392f, 0.0f, 0.6745f})),
                (new Tuple<Pos, float[]>(origin.up( 0.6875 ).south( 0.1875 ).east(0.5), new float[] {0.3843f, 0.0431f, 0.7882f})),
                (new Tuple<Pos, float[]>(origin.up( 0.6875 ).south( 0.25 ).east(0.5), new float[] {0.5922f, 0.2275f, 0.8784f})),
                (new Tuple<Pos, float[]>(origin.up( 0.6875 ).south( 0.3125 ).east(0.5), new float[] {0.3882f, 0.0471f, 0.7922f})),
                (new Tuple<Pos, float[]>(origin.up( 0.6875 ).south( 0.375 ).east(0.5), new float[] {0.2275f, 0.0f, 0.6549f})),
                (new Tuple<Pos, float[]>(origin.up( 0.6875 ).south( 0.4375 ).east(0.5), new float[] {0.2196f, 0.0f, 0.6431f})),
                (new Tuple<Pos, float[]>(origin.up( 0.6875 ).south( 0.5 ).east(0.5), new float[] {0.2235f, 0.0f, 0.651f})),
                (new Tuple<Pos, float[]>(origin.up( 0.6875 ).south( 0.5625 ).east(0.5), new float[] {0.2863f, 0.0078f, 0.7255f})),
                (new Tuple<Pos, float[]>(origin.up( 0.6875 ).south( 0.625 ).east(0.5), new float[] {0.2275f, 0.0f, 0.6549f})),
                (new Tuple<Pos, float[]>(origin.up( 0.6875 ).south( 0.6875 ).east(0.5), new float[] {0.2157f, 0.0f, 0.6235f})),
                (new Tuple<Pos, float[]>(origin.up( 0.6875 ).south( 0.75 ).east(0.5), new float[] {0.3176f, 0.0157f, 0.749f})),
                (new Tuple<Pos, float[]>(origin.up( 0.6875 ).south( 0.8125 ).east(0.5), new float[] {0.5294f, 0.1608f, 0.8549f})),
                (new Tuple<Pos, float[]>(origin.up( 0.6875 ).south( 0.875 ).east(0.5), new float[] {0.451f, 0.0902f, 0.8196f})),
                (new Tuple<Pos, float[]>(origin.up( 0.6875 ).south( 0.9375 ).east(0.5), new float[] {0.2235f, 0.0f, 0.651f})),
                (new Tuple<Pos, float[]>(origin.up( 0.625 ).south( 0.0 ).east(0.5), new float[] {0.4863f, 0.1176f, 0.8392f})),
                (new Tuple<Pos, float[]>(origin.up( 0.625 ).south( 0.0625 ).east(0.5), new float[] {0.3961f, 0.051f, 0.7961f})),
                (new Tuple<Pos, float[]>(origin.up( 0.625 ).south( 0.125 ).east(0.5), new float[] {0.3137f, 0.0157f, 0.7451f})),
                (new Tuple<Pos, float[]>(origin.up( 0.625 ).south( 0.1875 ).east(0.5), new float[] {0.5412f, 0.1725f, 0.8588f})),
                (new Tuple<Pos, float[]>(origin.up( 0.625 ).south( 0.25 ).east(0.5), new float[] {0.451f, 0.0863f, 0.8196f})),
                (new Tuple<Pos, float[]>(origin.up( 0.625 ).south( 0.3125 ).east(0.5), new float[] {0.2275f, 0.0f, 0.6588f})),
                (new Tuple<Pos, float[]>(origin.up( 0.625 ).south( 0.375 ).east(0.5), new float[] {0.2275f, 0.0f, 0.6588f})),
                (new Tuple<Pos, float[]>(origin.up( 0.625 ).south( 0.4375 ).east(0.5), new float[] {0.2863f, 0.0078f, 0.7255f})),
                (new Tuple<Pos, float[]>(origin.up( 0.625 ).south( 0.5 ).east(0.5), new float[] {0.2902f, 0.0078f, 0.7255f})),
                (new Tuple<Pos, float[]>(origin.up( 0.625 ).south( 0.5625 ).east(0.5), new float[] {0.3216f, 0.0157f, 0.7529f})),
                (new Tuple<Pos, float[]>(origin.up( 0.625 ).south( 0.625 ).east(0.5), new float[] {0.3647f, 0.0353f, 0.7765f})),
                (new Tuple<Pos, float[]>(origin.up( 0.625 ).south( 0.6875 ).east(0.5), new float[] {0.251f, 0.0f, 0.6902f})),
                (new Tuple<Pos, float[]>(origin.up( 0.625 ).south( 0.75 ).east(0.5), new float[] {0.2235f, 0.0f, 0.651f})),
                (new Tuple<Pos, float[]>(origin.up( 0.625 ).south( 0.8125 ).east(0.5), new float[] {0.3294f, 0.0196f, 0.7569f})),
                (new Tuple<Pos, float[]>(origin.up( 0.625 ).south( 0.875 ).east(0.5), new float[] {0.5333f, 0.1647f, 0.8549f})),
                (new Tuple<Pos, float[]>(origin.up( 0.625 ).south( 0.9375 ).east(0.5), new float[] {0.3569f, 0.0314f, 0.7725f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5625 ).south( 0.0 ).east(0.5), new float[] {0.3686f, 0.0392f, 0.7804f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5625 ).south( 0.0625 ).east(0.5), new float[] {0.2745f, 0.0039f, 0.7137f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5625 ).south( 0.125 ).east(0.5), new float[] {0.2549f, 0.0f, 0.6941f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5625 ).south( 0.1875 ).east(0.5), new float[] {0.3686f, 0.0353f, 0.7804f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5625 ).south( 0.25 ).east(0.5), new float[] {0.3059f, 0.0118f, 0.7412f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5625 ).south( 0.3125 ).east(0.5), new float[] {0.2471f, 0.0f, 0.6863f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5625 ).south( 0.375 ).east(0.5), new float[] {0.2784f, 0.0039f, 0.7176f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5625 ).south( 0.4375 ).east(0.5), new float[] {0.2431f, 0.0f, 0.6824f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5625 ).south( 0.5 ).east(0.5), new float[] {0.4314f, 0.0745f, 0.8118f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5625 ).south( 0.5625 ).east(0.5), new float[] {0.3804f, 0.0431f, 0.7843f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5625 ).south( 0.625 ).east(0.5), new float[] {0.4353f, 0.0784f, 0.8118f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5625 ).south( 0.6875 ).east(0.5), new float[] {0.4196f, 0.0667f, 0.8078f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5625 ).south( 0.75 ).east(0.5), new float[] {0.2314f, 0.0f, 0.6667f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5625 ).south( 0.8125 ).east(0.5), new float[] {0.2275f, 0.0f, 0.6588f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5625 ).south( 0.875 ).east(0.5), new float[] {0.2941f, 0.0078f, 0.7333f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5625 ).south( 0.9375 ).east(0.5), new float[] {0.3451f, 0.0275f, 0.7647f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5 ).south( 0.0 ).east(0.5), new float[] {0.4431f, 0.0824f, 0.8196f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5 ).south( 0.0625 ).east(0.5), new float[] {0.3098f, 0.0118f, 0.7451f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5 ).south( 0.125 ).east(0.5), new float[] {0.2667f, 0.0039f, 0.7098f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5 ).south( 0.1875 ).east(0.5), new float[] {0.3569f, 0.0314f, 0.7765f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5 ).south( 0.25 ).east(0.5), new float[] {0.302f, 0.0118f, 0.7373f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5 ).south( 0.3125 ).east(0.5), new float[] {0.2863f, 0.0078f, 0.7255f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5 ).south( 0.375 ).east(0.5), new float[] {0.2667f, 0.0039f, 0.7098f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5 ).south( 0.4375 ).east(0.5), new float[] {0.2196f, 0.0f, 0.6392f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5 ).south( 0.5 ).east(0.5), new float[] {0.4431f, 0.0824f, 0.8196f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5 ).south( 0.5625 ).east(0.5), new float[] {0.5412f, 0.1725f, 0.8588f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5 ).south( 0.625 ).east(0.5), new float[] {0.6039f, 0.2431f, 0.8824f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5 ).south( 0.6875 ).east(0.5), new float[] {0.3294f, 0.0196f, 0.7569f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5 ).south( 0.75 ).east(0.5), new float[] {0.2431f, 0.0f, 0.6824f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5 ).south( 0.8125 ).east(0.5), new float[] {0.2275f, 0.0f, 0.6588f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5 ).south( 0.875 ).east(0.5), new float[] {0.4118f, 0.0627f, 0.8039f})),
                (new Tuple<Pos, float[]>(origin.up( 0.5 ).south( 0.9375 ).east(0.5), new float[] {0.3843f, 0.0471f, 0.7882f})),
                (new Tuple<Pos, float[]>(origin.up( 0.4375 ).south( 0.0 ).east(0.5), new float[] {0.3529f, 0.0314f, 0.7725f})),
                (new Tuple<Pos, float[]>(origin.up( 0.4375 ).south( 0.0625 ).east(0.5), new float[] {0.2667f, 0.0039f, 0.7059f})),
                (new Tuple<Pos, float[]>(origin.up( 0.4375 ).south( 0.125 ).east(0.5), new float[] {0.3137f, 0.0157f, 0.7451f})),
                (new Tuple<Pos, float[]>(origin.up( 0.4375 ).south( 0.1875 ).east(0.5), new float[] {0.5137f, 0.1451f, 0.8471f})),
                (new Tuple<Pos, float[]>(origin.up( 0.4375 ).south( 0.25 ).east(0.5), new float[] {0.4863f, 0.1216f, 0.8392f})),
                (new Tuple<Pos, float[]>(origin.up( 0.4375 ).south( 0.3125 ).east(0.5), new float[] {0.251f, 0.0f, 0.6902f})),
                (new Tuple<Pos, float[]>(origin.up( 0.4375 ).south( 0.375 ).east(0.5), new float[] {0.2353f, 0.0f, 0.6706f})),
                (new Tuple<Pos, float[]>(origin.up( 0.4375 ).south( 0.4375 ).east(0.5), new float[] {0.2941f, 0.0078f, 0.7333f})),
                (new Tuple<Pos, float[]>(origin.up( 0.4375 ).south( 0.5 ).east(0.5), new float[] {0.3255f, 0.0196f, 0.7529f})),
                (new Tuple<Pos, float[]>(origin.up( 0.4375 ).south( 0.5625 ).east(0.5), new float[] {0.4549f, 0.0941f, 0.8235f})),
                (new Tuple<Pos, float[]>(origin.up( 0.4375 ).south( 0.625 ).east(0.5), new float[] {0.4314f, 0.0745f, 0.8118f})),
                (new Tuple<Pos, float[]>(origin.up( 0.4375 ).south( 0.6875 ).east(0.5), new float[] {0.4431f, 0.0824f, 0.8157f})),
                (new Tuple<Pos, float[]>(origin.up( 0.4375 ).south( 0.75 ).east(0.5), new float[] {0.3647f, 0.0353f, 0.7804f})),
                (new Tuple<Pos, float[]>(origin.up( 0.4375 ).south( 0.8125 ).east(0.5), new float[] {0.2784f, 0.0039f, 0.7176f})),
                (new Tuple<Pos, float[]>(origin.up( 0.4375 ).south( 0.875 ).east(0.5), new float[] {0.3333f, 0.0235f, 0.7608f})),
                (new Tuple<Pos, float[]>(origin.up( 0.4375 ).south( 0.9375 ).east(0.5), new float[] {0.3176f, 0.0157f, 0.749f})),
                (new Tuple<Pos, float[]>(origin.up( 0.375 ).south( 0.0 ).east(0.5), new float[] {0.2745f, 0.0039f, 0.7176f})),
                (new Tuple<Pos, float[]>(origin.up( 0.375 ).south( 0.0625 ).east(0.5), new float[] {0.251f, 0.0f, 0.6902f})),
                (new Tuple<Pos, float[]>(origin.up( 0.375 ).south( 0.125 ).east(0.5), new float[] {0.2157f, 0.0f, 0.6196f})),
                (new Tuple<Pos, float[]>(origin.up( 0.375 ).south( 0.1875 ).east(0.5), new float[] {0.302f, 0.0118f, 0.7373f})),
                (new Tuple<Pos, float[]>(origin.up( 0.375 ).south( 0.25 ).east(0.5), new float[] {0.5216f, 0.1529f, 0.851f})),
                (new Tuple<Pos, float[]>(origin.up( 0.375 ).south( 0.3125 ).east(0.5), new float[] {0.5333f, 0.1647f, 0.8549f})),
                (new Tuple<Pos, float[]>(origin.up( 0.375 ).south( 0.375 ).east(0.5), new float[] {0.302f, 0.0118f, 0.7373f})),
                (new Tuple<Pos, float[]>(origin.up( 0.375 ).south( 0.4375 ).east(0.5), new float[] {0.298f, 0.0078f, 0.7333f})),
                (new Tuple<Pos, float[]>(origin.up( 0.375 ).south( 0.5 ).east(0.5), new float[] {0.4314f, 0.0745f, 0.8118f})),
                (new Tuple<Pos, float[]>(origin.up( 0.375 ).south( 0.5625 ).east(0.5), new float[] {0.3843f, 0.0471f, 0.7882f})),
                (new Tuple<Pos, float[]>(origin.up( 0.375 ).south( 0.625 ).east(0.5), new float[] {0.5961f, 0.2353f, 0.8784f})),
                (new Tuple<Pos, float[]>(origin.up( 0.375 ).south( 0.6875 ).east(0.5), new float[] {0.5294f, 0.1569f, 0.8549f})),
                (new Tuple<Pos, float[]>(origin.up( 0.375 ).south( 0.75 ).east(0.5), new float[] {0.2588f, 0.0f, 0.698f})),
                (new Tuple<Pos, float[]>(origin.up( 0.375 ).south( 0.8125 ).east(0.5), new float[] {0.2235f, 0.0f, 0.651f})),
                (new Tuple<Pos, float[]>(origin.up( 0.375 ).south( 0.875 ).east(0.5), new float[] {0.2784f, 0.0039f, 0.7176f})),
                (new Tuple<Pos, float[]>(origin.up( 0.375 ).south( 0.9375 ).east(0.5), new float[] {0.2588f, 0.0f, 0.702f})),
                (new Tuple<Pos, float[]>(origin.up( 0.3125 ).south( 0.0 ).east(0.5), new float[] {0.3451f, 0.0275f, 0.7686f})),
                (new Tuple<Pos, float[]>(origin.up( 0.3125 ).south( 0.0625 ).east(0.5), new float[] {0.4431f, 0.0824f, 0.8157f})),
                (new Tuple<Pos, float[]>(origin.up( 0.3125 ).south( 0.125 ).east(0.5), new float[] {0.2588f, 0.0f, 0.698f})),
                (new Tuple<Pos, float[]>(origin.up( 0.3125 ).south( 0.1875 ).east(0.5), new float[] {0.2353f, 0.0f, 0.6745f})),
                (new Tuple<Pos, float[]>(origin.up( 0.3125 ).south( 0.25 ).east(0.5), new float[] {0.3451f, 0.0275f, 0.7686f})),
                (new Tuple<Pos, float[]>(origin.up( 0.3125 ).south( 0.3125 ).east(0.5), new float[] {0.5922f, 0.2314f, 0.8784f})),
                (new Tuple<Pos, float[]>(origin.up( 0.3125 ).south( 0.375 ).east(0.5), new float[] {0.5686f, 0.2039f, 0.8706f})),
                (new Tuple<Pos, float[]>(origin.up( 0.3125 ).south( 0.4375 ).east(0.5), new float[] {0.3294f, 0.0196f, 0.7569f})),
                (new Tuple<Pos, float[]>(origin.up( 0.3125 ).south( 0.5 ).east(0.5), new float[] {0.3922f, 0.051f, 0.7922f})),
                (new Tuple<Pos, float[]>(origin.up( 0.3125 ).south( 0.5625 ).east(0.5), new float[] {0.5686f, 0.2039f, 0.8706f})),
                (new Tuple<Pos, float[]>(origin.up( 0.3125 ).south( 0.625 ).east(0.5), new float[] {0.498f, 0.1294f, 0.8431f})),
                (new Tuple<Pos, float[]>(origin.up( 0.3125 ).south( 0.6875 ).east(0.5), new float[] {0.2745f, 0.0039f, 0.7137f})),
                (new Tuple<Pos, float[]>(origin.up( 0.3125 ).south( 0.75 ).east(0.5), new float[] {0.2196f, 0.0f, 0.6431f})),
                (new Tuple<Pos, float[]>(origin.up( 0.3125 ).south( 0.8125 ).east(0.5), new float[] {0.302f, 0.0118f, 0.7373f})),
                (new Tuple<Pos, float[]>(origin.up( 0.3125 ).south( 0.875 ).east(0.5), new float[] {0.5333f, 0.1647f, 0.8549f})),
                (new Tuple<Pos, float[]>(origin.up( 0.3125 ).south( 0.9375 ).east(0.5), new float[] {0.3373f, 0.0235f, 0.7608f})),
                (new Tuple<Pos, float[]>(origin.up( 0.25 ).south( 0.0 ).east(0.5), new float[] {0.3294f, 0.0196f, 0.7569f})),
                (new Tuple<Pos, float[]>(origin.up( 0.25 ).south( 0.0625 ).east(0.5), new float[] {0.5412f, 0.1725f, 0.8588f})),
                (new Tuple<Pos, float[]>(origin.up( 0.25 ).south( 0.125 ).east(0.5), new float[] {0.5137f, 0.1451f, 0.851f})),
                (new Tuple<Pos, float[]>(origin.up( 0.25 ).south( 0.1875 ).east(0.5), new float[] {0.2706f, 0.0039f, 0.7098f})),
                (new Tuple<Pos, float[]>(origin.up( 0.25 ).south( 0.25 ).east(0.5), new float[] {0.2392f, 0.0f, 0.6745f})),
                (new Tuple<Pos, float[]>(origin.up( 0.25 ).south( 0.3125 ).east(0.5), new float[] {0.2902f, 0.0078f, 0.7255f})),
                (new Tuple<Pos, float[]>(origin.up( 0.25 ).south( 0.375 ).east(0.5), new float[] {0.5451f, 0.1765f, 0.8588f})),
                (new Tuple<Pos, float[]>(origin.up( 0.25 ).south( 0.4375 ).east(0.5), new float[] {0.4471f, 0.0863f, 0.8196f})),
                (new Tuple<Pos, float[]>(origin.up( 0.25 ).south( 0.5 ).east(0.5), new float[] {0.4392f, 0.0824f, 0.8157f})),
                (new Tuple<Pos, float[]>(origin.up( 0.25 ).south( 0.5625 ).east(0.5), new float[] {0.5255f, 0.1529f, 0.851f})),
                (new Tuple<Pos, float[]>(origin.up( 0.25 ).south( 0.625 ).east(0.5), new float[] {0.2902f, 0.0078f, 0.7294f})),
                (new Tuple<Pos, float[]>(origin.up( 0.25 ).south( 0.6875 ).east(0.5), new float[] {0.2196f, 0.0f, 0.6353f})),
                (new Tuple<Pos, float[]>(origin.up( 0.25 ).south( 0.75 ).east(0.5), new float[] {0.3098f, 0.0118f, 0.7451f})),
                (new Tuple<Pos, float[]>(origin.up( 0.25 ).south( 0.8125 ).east(0.5), new float[] {0.5255f, 0.1569f, 0.851f})),
                (new Tuple<Pos, float[]>(origin.up( 0.25 ).south( 0.875 ).east(0.5), new float[] {0.5686f, 0.2f, 0.8706f})),
                (new Tuple<Pos, float[]>(origin.up( 0.25 ).south( 0.9375 ).east(0.5), new float[] {0.3216f, 0.0157f, 0.7529f})),
                (new Tuple<Pos, float[]>(origin.up( 0.1875 ).south( 0.0 ).east(0.5), new float[] {0.2863f, 0.0078f, 0.7255f})),
                (new Tuple<Pos, float[]>(origin.up( 0.1875 ).south( 0.0625 ).east(0.5), new float[] {0.2941f, 0.0078f, 0.7333f})),
                (new Tuple<Pos, float[]>(origin.up( 0.1875 ).south( 0.125 ).east(0.5), new float[] {0.5412f, 0.1725f, 0.8588f})),
                (new Tuple<Pos, float[]>(origin.up( 0.1875 ).south( 0.1875 ).east(0.5), new float[] {0.5137f, 0.1451f, 0.8471f})),
                (new Tuple<Pos, float[]>(origin.up( 0.1875 ).south( 0.25 ).east(0.5), new float[] {0.2588f, 0.0f, 0.698f})),
                (new Tuple<Pos, float[]>(origin.up( 0.1875 ).south( 0.3125 ).east(0.5), new float[] {0.2157f, 0.0f, 0.6157f})),
                (new Tuple<Pos, float[]>(origin.up( 0.1875 ).south( 0.375 ).east(0.5), new float[] {0.3059f, 0.0118f, 0.7412f})),
                (new Tuple<Pos, float[]>(origin.up( 0.1875 ).south( 0.4375 ).east(0.5), new float[] {0.3098f, 0.0118f, 0.7451f})),
                (new Tuple<Pos, float[]>(origin.up( 0.1875 ).south( 0.5 ).east(0.5), new float[] {0.3412f, 0.0235f, 0.7647f})),
                (new Tuple<Pos, float[]>(origin.up( 0.1875 ).south( 0.5625 ).east(0.5), new float[] {0.2627f, 0.0f, 0.702f})),
                (new Tuple<Pos, float[]>(origin.up( 0.1875 ).south( 0.625 ).east(0.5), new float[] {0.2196f, 0.0f, 0.6431f})),
                (new Tuple<Pos, float[]>(origin.up( 0.1875 ).south( 0.6875 ).east(0.5), new float[] {0.302f, 0.0118f, 0.7373f})),
                (new Tuple<Pos, float[]>(origin.up( 0.1875 ).south( 0.75 ).east(0.5), new float[] {0.4745f, 0.1098f, 0.8314f})),
                (new Tuple<Pos, float[]>(origin.up( 0.1875 ).south( 0.8125 ).east(0.5), new float[] {0.5373f, 0.1686f, 0.8588f})),
                (new Tuple<Pos, float[]>(origin.up( 0.1875 ).south( 0.875 ).east(0.5), new float[] {0.2745f, 0.0039f, 0.7176f})),
                (new Tuple<Pos, float[]>(origin.up( 0.1875 ).south( 0.9375 ).east(0.5), new float[] {0.2471f, 0.0f, 0.6902f})),
                (new Tuple<Pos, float[]>(origin.up( 0.125 ).south( 0.0 ).east(0.5), new float[] {0.2784f, 0.0039f, 0.7176f})),
                (new Tuple<Pos, float[]>(origin.up( 0.125 ).south( 0.0625 ).east(0.5), new float[] {0.2667f, 0.0039f, 0.7098f})),
                (new Tuple<Pos, float[]>(origin.up( 0.125 ).south( 0.125 ).east(0.5), new float[] {0.3686f, 0.0392f, 0.7804f})),
                (new Tuple<Pos, float[]>(origin.up( 0.125 ).south( 0.1875 ).east(0.5), new float[] {0.651f, 0.3098f, 0.898f})),
                (new Tuple<Pos, float[]>(origin.up( 0.125 ).south( 0.25 ).east(0.5), new float[] {0.4824f, 0.1176f, 0.8353f})),
                (new Tuple<Pos, float[]>(origin.up( 0.125 ).south( 0.3125 ).east(0.5), new float[] {0.251f, 0.0f, 0.6941f})),
                (new Tuple<Pos, float[]>(origin.up( 0.125 ).south( 0.375 ).east(0.5), new float[] {0.2392f, 0.0f, 0.6784f})),
                (new Tuple<Pos, float[]>(origin.up( 0.125 ).south( 0.4375 ).east(0.5), new float[] {0.3098f, 0.0118f, 0.7451f})),
                (new Tuple<Pos, float[]>(origin.up( 0.125 ).south( 0.5 ).east(0.5), new float[] {0.302f, 0.0118f, 0.7373f})),
                (new Tuple<Pos, float[]>(origin.up( 0.125 ).south( 0.5625 ).east(0.5), new float[] {0.2275f, 0.0f, 0.6549f})),
                (new Tuple<Pos, float[]>(origin.up( 0.125 ).south( 0.625 ).east(0.5), new float[] {0.2745f, 0.0039f, 0.7137f})),
                (new Tuple<Pos, float[]>(origin.up( 0.125 ).south( 0.6875 ).east(0.5), new float[] {0.5176f, 0.149f, 0.851f})),
                (new Tuple<Pos, float[]>(origin.up( 0.125 ).south( 0.75 ).east(0.5), new float[] {0.4392f, 0.0824f, 0.8157f})),
                (new Tuple<Pos, float[]>(origin.up( 0.125 ).south( 0.8125 ).east(0.5), new float[] {0.2588f, 0.0f, 0.702f})),
                (new Tuple<Pos, float[]>(origin.up( 0.125 ).south( 0.875 ).east(0.5), new float[] {0.2196f, 0.0f, 0.6392f})),
                (new Tuple<Pos, float[]>(origin.up( 0.125 ).south( 0.9375 ).east(0.5), new float[] {0.2627f, 0.0f, 0.702f})),
                (new Tuple<Pos, float[]>(origin.up( 0.0625 ).south( 0.0 ).east(0.5), new float[] {0.2157f, 0.0f, 0.6235f})),
                (new Tuple<Pos, float[]>(origin.up( 0.0625 ).south( 0.0625 ).east(0.5), new float[] {0.3333f, 0.0196f, 0.7569f})),
                (new Tuple<Pos, float[]>(origin.up( 0.0625 ).south( 0.125 ).east(0.5), new float[] {0.3804f, 0.0431f, 0.7843f})),
                (new Tuple<Pos, float[]>(origin.up( 0.0625 ).south( 0.1875 ).east(0.5), new float[] {0.4118f, 0.0627f, 0.8039f})),
                (new Tuple<Pos, float[]>(origin.up( 0.0625 ).south( 0.25 ).east(0.5), new float[] {0.4235f, 0.0667f, 0.8078f})),
                (new Tuple<Pos, float[]>(origin.up( 0.0625 ).south( 0.3125 ).east(0.5), new float[] {0.3294f, 0.0196f, 0.7569f})),
                (new Tuple<Pos, float[]>(origin.up( 0.0625 ).south( 0.375 ).east(0.5), new float[] {0.3137f, 0.0118f, 0.7451f})),
                (new Tuple<Pos, float[]>(origin.up( 0.0625 ).south( 0.4375 ).east(0.5), new float[] {0.4824f, 0.1137f, 0.8353f})),
                (new Tuple<Pos, float[]>(origin.up( 0.0625 ).south( 0.5 ).east(0.5), new float[] {0.4118f, 0.0627f, 0.8039f})),
                (new Tuple<Pos, float[]>(origin.up( 0.0625 ).south( 0.5625 ).east(0.5), new float[] {0.3059f, 0.0118f, 0.7412f})),
                (new Tuple<Pos, float[]>(origin.up( 0.0625 ).south( 0.625 ).east(0.5), new float[] {0.451f, 0.0863f, 0.8196f})),
                (new Tuple<Pos, float[]>(origin.up( 0.0625 ).south( 0.6875 ).east(0.5), new float[] {0.451f, 0.0902f, 0.8235f})),
                (new Tuple<Pos, float[]>(origin.up( 0.0625 ).south( 0.75 ).east(0.5), new float[] {0.251f, 0.0f, 0.6941f})),
                (new Tuple<Pos, float[]>(origin.up( 0.0625 ).south( 0.8125 ).east(0.5), new float[] {0.2275f, 0.0f, 0.6549f})),
                (new Tuple<Pos, float[]>(origin.up( 0.0625 ).south( 0.875 ).east(0.5), new float[] {0.2588f, 0.0f, 0.698f})),
                (new Tuple<Pos, float[]>(origin.up( 0.0625 ).south( 0.9375 ).east(0.5), new float[] {0.251f, 0.0f, 0.6941f}))

        };


        return arr;
    }
}
