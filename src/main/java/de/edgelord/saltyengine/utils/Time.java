/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.utils;

public class Time {

    private static long deltaNanos = 0;
    private static float fps = 0;

    public static long getDeltaNanos() {
        return deltaNanos;
    }

    public static void setDeltaNanos(long deltaNanos) {
        Time.deltaNanos = deltaNanos;
    }

    public static float getFps() {
        return fps;
    }

    public static void setFps(float fps) {
        Time.fps = fps;
    }
}