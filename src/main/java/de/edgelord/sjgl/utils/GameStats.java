/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
 * All rights reserved
 */

package de.edgelord.sjgl.utils;

public class GameStats {

    public static boolean paused = false;
    public static boolean withExperimentalFeatures = false;

    public static boolean isPaused() {
        return paused;
    }

    public static void setPaused(boolean paused) {
        GameStats.paused = paused;
    }

}
