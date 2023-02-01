package net.givewife.additions.util;

import net.givewife.additions.util.positions.Pos;
import net.givewife.additions.util.positions.VecTrail;

public class MathHelper {

    public MathHelper() {
    }

    public double[] rowOp(double[] row1, double[] row2, double amount) {

        if(row1.length != row2.length) {
            System.out.println("[Math Helper] row reduction failed. Row 1: " + getPrint(row1) + " ; Row 2:" + getPrint(row2) + " ; amount: " + amount);
            return row1;
        }
        double[] res = new double[row1.length];

        for(int i = 0; i < row1.length; i++) {

            res[i] = row1[i] + (amount*row2[i]);

        }

        return res;

    }

    public double kwad(double b) {
        return b*b;
    }

    public String getPrint(double[] r) {
        String s = "";
        for(int i =0; i < r.length; i++) {
            if(i < r.length)
                s += Double.toString(r[i]) + ", ";
            else {
                s += Double.toString(r[i]);
            }
        }
        return s;
    }


}
