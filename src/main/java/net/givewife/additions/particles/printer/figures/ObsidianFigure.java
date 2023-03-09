package net.givewife.additions.particles.printer.figures;

import net.givewife.additions.particles.printer.ParticleFigure;
import net.givewife.additions.util.positions.Pos;

public class ObsidianFigure extends ParticleFigure {

    public ObsidianFigure(Pos origin, boolean renderTop, boolean renderBot) {
        super(origin, renderTop, renderBot);
    }


    @Override
    protected FigureMap[] getMaps() {
        return new FigureMap[]{

                new FigureMap(Side.ALL) {
                    @Override
                    public float[][] getMap() {
                        return new float[][]{
                                new float[] {0.0157f,0.0078f,0.0275f},
                                new float[] {0.0078f,0.0039f,0.0196f},
                                new float[] {0.0078f,0.0078f,0.0196f},
                                new float[] {0.0392f,0.0275f,0.0706f},
                                new float[] {0.0235f,0.0118f,0.0392f},
                                new float[] {0.1176f,0.0784f,0.1725f},
                                new float[] {0.102f,0.0745f,0.1569f},
                                new float[] {0.051f,0.0353f,0.0863f},
                                new float[] {0.1765f,0.1255f,0.2627f},
                                new float[] {0.1176f,0.0863f,0.1843f},
                                new float[] {0.0118f,0.0078f,0.0235f},
                                new float[] {0.1843f,0.1216f,0.2627f},
                                new float[] {0.1529f,0.1098f,0.2314f},
                                new float[] {0.0275f,0.0196f,0.0471f},
                                new float[] {0.102f,0.0784f,0.1647f},
                                new float[] {0.1137f,0.0863f,0.1804f},
                                new float[] {0.0471f,0.0353f,0.0824f},
                                new float[] {0.0392f,0.0314f,0.0706f},
                                new float[] {0.1137f,0.0824f,0.1765f},
                                new float[] {0.0196f,0.0078f,0.0353f},
                                new float[] {0.0706f,0.051f,0.1059f},
                                new float[] {0.102f,0.0784f,0.1647f},
                                new float[] {0.0627f,0.0471f,0.1098f},
                                new float[] {0.0471f,0.0353f,0.0784f},
                                new float[] {0.0235f,0.0157f,0.0392f},
                                new float[] {0.0157f,0.0118f,0.0314f},
                                new float[] {0.0039f,0.0039f,0.0118f},
                                new float[] {0.0196f,0.0118f,0.0353f},
                                new float[] {0.0314f,0.0196f,0.0549f},
                                new float[] {0.0118f,0.0078f,0.0275f},
                                new float[] {0.0627f,0.0471f,0.1098f},
                                new float[] {0.051f,0.0353f,0.0863f},
                                new float[] {0.0314f,0.0235f,0.051f},
                                new float[] {0.0941f,0.0667f,0.149f},
                                new float[] {0.0588f,0.0392f,0.098f},
                                new float[] {0.0196f,0.0118f,0.0392f},
                                new float[] {0.1765f,0.1216f,0.2549f},
                                new float[] {0.0588f,0.0431f,0.098f},
                                new float[] {0.0118f,0.0078f,0.0275f},
                                new float[] {0.0039f,0.0039f,0.0118f},
                                new float[] {0.0275f,0.0157f,0.0471f},
                                new float[] {0.0667f,0.0471f,0.1098f},
                                new float[] {0.1255f,0.0902f,0.1961f},
                                new float[] {0.0863f,0.0627f,0.1373f},
                                new float[] {0.0745f,0.0549f,0.1255f},
                                new float[] {0.0039f,0.0f,0.0078f},
                                new float[] {0.0275f,0.0196f,0.0431f},
                                new float[] {0.0039f,0.0f,0.0078f},
                                new float[] {0.1608f,0.1098f,0.2314f},
                                new float[] {0.0314f,0.0235f,0.051f},
                                new float[] {0.0431f,0.0353f,0.0745f},
                                new float[] {0.1255f,0.0863f,0.1843f},
                                new float[] {0.0745f,0.0588f,0.1255f},
                                new float[] {0.0588f,0.0431f,0.102f},
                                new float[] {0.1412f,0.1059f,0.2196f},
                                new float[] {0.0824f,0.0627f,0.1373f},
                                new float[] {0.0039f,0.0039f,0.0118f},
                                new float[] {0.0706f,0.0549f,0.1176f},
                                new float[] {0.0588f,0.0431f,0.1059f},
                                new float[] {0.0471f,0.0353f,0.0863f},
                                new float[] {0.051f,0.0392f,0.0902f},
                                new float[] {0.0196f,0.0118f,0.0392f},
                                new float[] {0.0196f,0.0118f,0.0392f},
                                new float[] {0.0235f,0.0118f,0.0431f},
                                new float[] {0.0627f,0.0471f,0.1098f},
                                new float[] {0.0353f,0.0196f,0.0588f},
                                new float[] {0.0314f,0.0196f,0.0471f},
                                new float[] {0.0471f,0.0353f,0.0784f},
                                new float[] {0.0275f,0.0157f,0.051f},
                                new float[] {0.102f,0.0784f,0.1608f},
                                new float[] {0.0706f,0.0471f,0.1059f},
                                new float[] {0.1373f,0.0902f,0.2f},
                                new float[] {0.0353f,0.0235f,0.0627f},
                                new float[] {0.0392f,0.0275f,0.0706f},
                                new float[] {0.0588f,0.0431f,0.102f},
                                new float[] {0.149f,0.1059f,0.2235f},
                                new float[] {0.1333f,0.0902f,0.2f},
                                new float[] {0.0667f,0.051f,0.1137f},
                                new float[] {0.1373f,0.0941f,0.2039f},
                                new float[] {0.0431f,0.0314f,0.0784f},
                                new float[] {0.1294f,0.098f,0.2f},
                                new float[] {0.0627f,0.0471f,0.1059f},
                                new float[] {0.0314f,0.0157f,0.0549f},
                                new float[] {0.0275f,0.0157f,0.051f},
                                new float[] {0.051f,0.0353f,0.0863f},
                                new float[] {0.0588f,0.0431f,0.102f},
                                new float[] {0.0353f,0.0235f,0.0627f},
                                new float[] {0.0235f,0.0118f,0.0392f},
                                new float[] {0.0314f,0.0235f,0.0549f},
                                new float[] {0.0431f,0.0275f,0.0745f},
                                new float[] {0.051f,0.0353f,0.0863f},
                                new float[] {0.0157f,0.0118f,0.0314f},
                                new float[] {0.0314f,0.0235f,0.0549f},
                                new float[] {0.0392f,0.0235f,0.0667f},
                                new float[] {0.1137f,0.0863f,0.1843f},
                                new float[] {0.0392f,0.0275f,0.0706f},
                                new float[] {0.0275f,0.0157f,0.051f},
                                new float[] {0.0196f,0.0118f,0.0392f},
                                new float[] {0.0196f,0.0078f,0.0353f},
                                new float[] {0.0235f,0.0118f,0.0392f}
                        };
                    }
                }

        };
    }

}
