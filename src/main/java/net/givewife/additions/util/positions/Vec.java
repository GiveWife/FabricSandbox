package net.givewife.additions.util.positions;

public class Vec {

    private Pos from, to;

    public Vec(Pos from, Pos to) {
        this.from = from;
        this.to = to;
    }

    public Pos from() {
        return from;
    }

    public Pos to() {
        return to;
    }

    public void log(String message) {
        System.out.println("[Vec] " + message);
    }


}
