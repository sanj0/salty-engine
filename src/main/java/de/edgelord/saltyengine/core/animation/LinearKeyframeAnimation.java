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

public class LinearKeyframeAnimation extends KeyframeAnimation {

    public LinearKeyframeAnimation(List<Keyframe> keyframes) {
        super(keyframes);
    }

    public LinearKeyframeAnimation() {
        super();
    }

    @Override
    public void calculateAnimation() {

        prepareAnimation();

        int index = 0;

        List<Keyframe> keyframes = getKeyframes();
        while (index < getKeyframes().size() - 1) {

            int i = 0;
            int duration = keyframes.get(index + 1).getTiming() - keyframes.get(index).getTiming();
            float step = (keyframes.get(index + 1).getKey() - keyframes.get(index).getKey()) / duration;

            while (i < duration) {

                // System.out.println("timing: " + (i + keyframes.get(index).getTiming()) + " value: " + step);
                getAnimation().put(i + keyframes.get(index).getTiming(), step);
                i++;
            }

            index++;
        }
    }
}
