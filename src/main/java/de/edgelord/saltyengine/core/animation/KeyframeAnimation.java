/*
 * Copyright 2019 Malte Dostal
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.edgelord.saltyengine.core.animation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public abstract class KeyframeAnimation {

    private List<Keyframe> keyframes;
    // This List only contains relative Values!
    private HashMap<Integer, Float> animation = new HashMap<>();
    private int end = 0;

    private int currentFrame = -1;

    private boolean unCalculatedChanges = false;

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
     * @return the next delta-step of the linear keyframe animation
     */
    public float nextDelta() {

        if (unCalculatedChanges) {
            System.out.println("Warning: There are un-calculated changes in a KeyframeAnimation!");
        }

        if (!animationEnded()) {

            currentFrame++;
            return animation.get(currentFrame);
        } else {
            // If there is a request out of the available timeline, there should be no delta, so return 0
            return 0f;
        }
    }

    /**
     * Fills the {@link #animation} with timecode-to-delta values.
     */
    public abstract void calculateAnimation();

    /**
     * Sorts the {@link #keyframes} by their timecode and sets the {@link #end}.
     */
    protected void prepareAnimation() {
        currentFrame = -1;
        unCalculatedChanges = false;
        keyframes.sort(Comparator.comparingInt(Keyframe::getTiming));
        this.end = keyframes.get(keyframes.size() - 1).getTiming();
    }

    public boolean animationEnded() {
        return !(currentFrame < end - 1);
    }

    public void add(Keyframe keyframe) {
        keyframes.add(keyframe);
        unCalculatedChanges = true;
    }

    public void add(int timing, float value) {
        keyframes.add(new Keyframe(timing, value));
        unCalculatedChanges = true;
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

    public void restart() {
        currentFrame = -1;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    protected HashMap<Integer, Float> getAnimation() {
        return animation;
    }

    protected void setAnimation(HashMap<Integer, Float> animation) {
        this.animation = animation;
    }
}
