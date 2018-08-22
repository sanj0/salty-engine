/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.gameobject.components.animation;

import java.util.*;

public class KeyFrameAnimation {

    private List<KeyFrame> keyFrames;
    // This List only contains relative Values!
    private HashMap<Integer, Float> animation = new HashMap<>();
    private int end = 0;
    private float lastValue = 0;

    private int currentTiming = -1;

    public KeyFrameAnimation(List<KeyFrame> keyFrames) {
        this.keyFrames = keyFrames;
    }

    public KeyFrameAnimation() {
        keyFrames = new ArrayList<>();
    }

    public List<KeyFrame> getKeyFrames() {
        return keyFrames;
    }

    public void setKeyFrames(List<KeyFrame> keyFrames) {
        this.keyFrames = keyFrames;
    }

    /**
     * Important: This method only returns a delta value!
     *
     * @return
     * @throws KeyFrameAnimationHasReachedLastFrameException
     */
    public float nextDelta() throws KeyFrameAnimationHasReachedLastFrameException {

        if (currentTiming < end - 1) {

            currentTiming++;
            return animation.get(currentTiming);
        } else {
            throw new KeyFrameAnimationHasReachedLastFrameException();
        }
    }

    public void calculateAnimation() {

        currentTiming = 0;

        // Sort the list by the timing of the keyFrames
        Collections.sort(keyFrames, Comparator.comparingInt(KeyFrame::getTiming));

        end = keyFrames.get(keyFrames.size() - 1).getTiming();

        int index = 0;

        while (index < keyFrames.size() - 1) {

            int i = 0;
            int duration = keyFrames.get(index + 1).getTiming() - keyFrames.get(index).getTiming();
            float step = (keyFrames.get(index + 1).getKey() - keyFrames.get(index).getKey()) / duration;

            while (i < duration) {

                // System.out.println("timing: " + (i + keyFrames.get(index).getTiming()) + " value: " + step);
                animation.put(i + keyFrames.get(index).getTiming(), step);
                i++;
            }

            index++;
        }
    }

    public void add(KeyFrame keyFrame) {
        keyFrames.add(keyFrame);
    }

    public void add(int timing, float value) {
        keyFrames.add(new KeyFrame(timing, value));
    }
}
