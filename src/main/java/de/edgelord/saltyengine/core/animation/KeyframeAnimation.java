/*
 * Copyright 2021 Malte Dostal
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
import java.util.Arrays;
import java.util.List;

public class KeyframeAnimation {

    private TransitionFunction transition;
    private List<Keyframe> frames;
    private int head;
    private int duration;
    private int frameIndex = 0;
    private boolean loop = false;

    public KeyframeAnimation(final TransitionFunction transition, final List<Keyframe> frames) {
        this.transition = transition;
        this.frames = frames;

        computeDuration();
    }

    public KeyframeAnimation(final TransitionFunction transition) {
        this(transition, new ArrayList<>());
    }

    public KeyframeAnimation(final TransitionFunction transition, final Keyframe... frames) {
        this(transition, new ArrayList<>(Arrays.asList(frames)));
    }

    private void computeDuration() {
        duration = frames.isEmpty() ? 0 : frames.get(frames.size() - 1).getTimecode();
    }

    public float nextValue() {
        final Keyframe frame = frames.get(frameIndex);
        final Keyframe prevFrame = frameIndex > 0 ? frames.get(frameIndex - 1) : null;
        final float prevValue = prevFrame == null ? 0 : prevFrame.getValue();
        final float prevTimecode = prevFrame == null ? 0 : prevFrame.getTimecode();
        if (head >= frame.getTimecode()) {
            if (frameIndex == frames.size() - 1) {
                if (loop) {
                    reset();
                    return nextValue();
                } else {
                    return frames.get(frameIndex).getValue();
                }
            } else {
                frameIndex++;
                return nextValue();
            }
        } else {
            head++;
            return prevValue + (frame.getValue() - prevValue) * transition.apply((head - 1 - prevTimecode) / (frame.getTimecode() - prevTimecode));
        }
    }

    public boolean finished() {
        return head >= duration;
    }

    public void reset() {
        head = 0;
        frameIndex = 0;
    }

    public void appendFrame(final Keyframe frame) {
        frames.add(frame);
        computeDuration();
    }

    public void appendFrame(final int t, final float v) {
        appendFrame(new Keyframe(t, v));
    }

    public void removeFrame(final Keyframe frame) {
        frames.remove(frame);
        computeDuration();
    }

    public void removeFrame(final int timecode) {
        frames.removeIf(f -> f.getTimecode() == timecode);
        computeDuration();
    }

    public void removeFrame(final float value) {
        frames.removeIf(f -> f.getValue() == value);
        computeDuration();
    }

    /**
     * Gets {@link #transition}.
     *
     * @return the value of {@link #transition}
     */
    public TransitionFunction getTransition() {
        return transition;
    }

    /**
     * Sets {@link #transition}.
     *
     * @param transition the new value of {@link #transition}
     */
    public void setTransition(final TransitionFunction transition) {
        this.transition = transition;
    }

    /**
     * Sets {@link #frames}.
     *
     * @param frames the new value of {@link #frames}
     */
    public void setFrames(final List<Keyframe> frames) {
        this.frames = frames;
        duration = frames.get(frames.size() - 1).getTimecode();
    }

    /**
     * Gets {@link #head}.
     *
     * @return the value of {@link #head}
     */
    public int getHead() {
        return head;
    }

    /**
     * Sets {@link #head}.
     *
     * @param head the new value of {@link #head}
     */
    public void setHead(final int head) {
        this.head = head;
    }

    /**
     * Gets {@link #loop}.
     *
     * @return the value of {@link #loop}
     */
    public boolean isLoop() {
        return loop;
    }

    /**
     * Sets {@link #loop}.
     *
     * @param loop the new value of {@link #loop}
     */
    public void setLoop(final boolean loop) {
        this.loop = loop;
    }
}
