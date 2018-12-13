/*
 * Copyright 2018 Malte Dostal
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

public class LinearKeyframeAnimation {

    private List<Keyframe> keyframes;
    // This List only contains relative Values!
    private HashMap<Integer, Float> animation = new HashMap<>();
    private int end = 0;

    private int currentFrame = -1;

    public LinearKeyframeAnimation(List<Keyframe> keyframes) {
        this.keyframes = keyframes;

        add(0, 0);
    }

    public LinearKeyframeAnimation() {
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

        if (!animationEnded()) {

            currentFrame++;
            return animation.get(currentFrame);
        } else {
            // If there is a request out of the available timeline, there should be no delta returned so 0
            return 0f;
        }
    }

    public void calculateAnimation() {

        currentFrame = 0;

        // Sort the list by the timing of the keyframes
        keyframes.sort(Comparator.comparingInt(Keyframe::getTiming));

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

    public boolean animationEnded() {
        return !(currentFrame < end - 1);
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

    public void restart() {
        currentFrame = -1;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }
}
