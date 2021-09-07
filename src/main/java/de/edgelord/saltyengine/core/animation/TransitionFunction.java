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

import java.util.function.Function;

import static java.lang.Math.*;

/**
 * A Function of type float that returns for x of 0 to 1 a value between 0 and
 * 1.
 * <p>
 * Used for transitions in {@link KeyframeAnimation}s.
 * <p>
 * Provides transitions of type sine, expo, bounce and elastic, inspired by
 * easings.net (GPT3)
 */
public interface TransitionFunction extends Function<Float, Float> {

    float N1 = 7.5625f;
    float D1 = 2.75f;
    float C4 = (float) (2.0 * PI / 3.0);
    float C5 = (float) (2.0 * PI / 4.5);

    /**
     * Converts in to out and vice versa
     *
     * @return the inverse (in to out and v.v.) of this transition
     */
    default TransitionFunction inverse() {
        return x -> 1 - apply(1 - x);
    }

    /**
     * Returns a linear transition of form<br> {@code x -> x}
     *
     * @return a linear transition
     */
    static TransitionFunction linear() {
        return x -> x;
    }

    static TransitionFunction easeInSine() {
        return x -> 1f - (float) cos(x * PI / 2f);
    }

    static TransitionFunction easeInOutSine() {
        return x -> -((float) cos(PI * x) - 1) / 2f;
    }

    static TransitionFunction easeInExpo() {
        return x -> x == 0 ? 0 : (float) pow(2, 10 * x - 10);
    }

    static TransitionFunction easeInOutExpo() {
        return x -> {
            if (x == 0) return 0f;
            if (x == 1) return 1f;
            if (x < .5f) return (float) pow(2, 20 * x - 10) / 2f;
            return (float) (2 - pow(2, -20 * x + 10)) / 2f;
        };
    }

    static TransitionFunction easeOutBounce() {
        return x -> {
            if (x < 1f / D1) {
                return N1 * x * x;
            } else if (x < 2f / D1) {
                return N1 * (x -= 1.5f / D1) * x + 0.75f;
            } else if (x < 2.5f / D1) {
                return N1 * (x -= 2.25f / D1) * x + 0.9375f;
            } else {
                return N1 * (x -= 2.625f / D1) * x + 0.984375f;
            }
        };
    }

    static TransitionFunction easeInOutBounce() {
        final TransitionFunction easeOutBounce = easeOutBounce();
        return x -> x < .5f
                ? (1 - easeOutBounce.apply(1 - 2f * x)) / 2f
                : (1 + easeOutBounce.apply(2f * x - 1)) / 2f;
    }

    static TransitionFunction easeInElastic() {
        return x -> {
            if (x == 0) return 0f;
            if (x == 1) return 1f;
            return (float) (-pow(2, 10 * x - 10) * sin((x * 10 - 10.75f) * C4));
        };
    }

    static TransitionFunction easeInOutElastic() {
        return x -> {
            if (x == 0) return 0f;
            if (x == 1) return 1f;
            if (x < .5f)
                return (float) (-(pow(2, 20 * x - 10) * sin((20 * x - 11.125) * C5)) / 2.0);
            return (float) ((pow(2, -20 * x + 10) * sin((20 * x - 11.125) * C5)) / 2.0 + 1);
        };
    }
}
