/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.gameobject.components.animation;

import java.util.*;

public class KeyframeAnimation {

    private List<Keyframe> keyframes;
    // This List only contains relative Values!
    private HashMap<Integer, Float> animation = new HashMap<>();
    private int end = 0;
    private float lastValue = 0;

    private int currentTiming = -1;

    public KeyframeAnimation(List<Keyframe> keyframes) {
        this.keyframes = keyframes;

        add(0, 0);
    }

    public KeyframeAnimation() {
        this(new ArrayList<>());
    }

    public List<Keyframe> getKeyframes() {
        return keyframes;
    }

    public void setKeyframes(List<Keyframe> keyframes) {
        this.keyframes = keyframes;
    }

    /**
     * Important: This method only returns a delta value!
     *
     * @return
     */
    public float nextDelta() {

        if (currentTiming < end - 1) {

            currentTiming++;
            return animation.get(currentTiming);
        } else {
            // If there is request ou of the available timeline, there shoul dbe no delta returned so 0
            return 0;
        }
    }

    public void calculateAnimation() {

        currentTiming = 0;

        // Sort the list by the timing of the keyframes
        Collections.sort(keyframes, Comparator.comparingInt(Keyframe::getTiming));

        end = keyframes.get(keyframes.size() - 1).getTiming();

        int index = 0;

        while (index < keyframes.size() - 1) {

            int i = 0;
            int duration = keyframes.get(index + 1).getTiming() - keyframes.get(index).getTiming();
            float step = (keyframes.get(index + 1).getKey() - keyframes.get(index).getKey()) / duration;

            while (i < duration) {

                // System.out.println("timing: " + (i + keyframes.get(index).getTiming()) + " value: " + step);
                animation.put(i + keyframes.get(index).getTiming(), step);
                i++;
            }

            index++;
        }
    }

    public void add(Keyframe keyframe) {
        keyframes.add(keyframe);
    }

    public void add(int timing, float value) {
        keyframes.add(new Keyframe(timing, value));
    }

    public void remove(Keyframe keyframe) {
        keyframes.remove(keyframe);
    }

    public void removeByTiming(int timing) {

        keyframes.removeIf(keyframe -> keyframe.getTiming() == timing);
    }

    public void removeByKey(float key) {
        keyframes.removeIf(keyframe -> keyframe.getKey() == key);
    }
}
