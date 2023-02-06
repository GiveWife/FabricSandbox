package net.givewife.additions.util;

public class DebugHelper {

    private final String DEBUG_CLASS;

    public DebugHelper(String debugClass) {
        this.DEBUG_CLASS = debugClass;
    }

    /**
     * Logs your message
     */
    public void log(String message) {
        System.out.println("[" + DEBUG_CLASS + "] " + message);
    }

    public String intToString(int[] arr) {
        String s = "[";
        for(int i = 0; i < arr.length; i++) {
            s += Integer.toString(arr[i]);
            if(i + 1 < arr.length) s += ", ";
        }
        s += "]";
        return s;
    }

    public String doubleToString(double[] arr) {
        String s = "[";
        for(int i = 0; i < arr.length; i++) {
            s += Double.toString(arr[i]);
            if(i + 1 < arr.length) s += ", ";
        }
        s += "]";
        return s;
    }


}
