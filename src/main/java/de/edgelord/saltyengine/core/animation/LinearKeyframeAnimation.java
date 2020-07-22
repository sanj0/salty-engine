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

import java.util.List;

/**
 * A {@link KeyframeAnimation} implementation
 * which uses linear transitions between values.
 */
public class LinearKeyframeAnimation extends KeyframeAnimation {

    /**
     * A constructor.
     *
     * @param keyframes the initial list of
     *                  <code>Keyframe</code>s
     */
    public LinearKeyframeAnimation(final List<Keyframe> keyframes) {
        super(keyframes);
    }

    /**
     * A constructor that initializes an empty
     * animation.
     */
    public LinearKeyframeAnimation() {
        super();
    }

    /**
     * A constructor that initializes the
     * keyframes list with the given vararg of
     * {@link Keyframe}s.
     *
     * @param frames the {@link Keyframe frames}
     *               to initialize the animation
     *               with
     */
    public LinearKeyframeAnimation(final Keyframe... frames) {
        super(frames);
    }

    @Override
    public void calculateAnimation() {
        prepareAnimation();

        int index = 0;
        final List<Keyframe> keyframes = getKeyframes();
        while (index < getKeyframes().size() - 1) {
            int i = 0;
            final int duration = keyframes.get(index + 1).getTimecode() - keyframes.get(index).getTimecode();
            final float step = (keyframes.get(index + 1).getValue() - keyframes.get(index).getValue()) / duration;

            while (i < duration) {
                getAnimation().put(i + keyframes.get(index).getTimecode(), step);
                i++;
            }
            index++;
        }
    }
}
