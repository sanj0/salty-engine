package de.me.edgelord.sjgl.utils;

public class Time {

    private static long deltaMillis = 0;

    public static long getDeltaMillis() {
        return deltaMillis;
    }

    public static void setDeltaMillis(long deltaMillis) {
        Time.deltaMillis = deltaMillis;
    }
}
