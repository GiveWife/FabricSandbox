package net.givewife.additions.util;

public class Tuple<S, T> {

    private final S type1;
    private final T type2;

    public Tuple(S type1, T type2) {
        this.type1 = type1;
        this.type2 = type2;
    }

    public S getFirst() {
        return type1;
    }

    public T getSecond() {
        return type2;
    }

}
