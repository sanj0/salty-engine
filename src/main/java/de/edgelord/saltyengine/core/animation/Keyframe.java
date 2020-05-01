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

/**
 * A <code>Keyframe</code> stores a {@link #timecode}
 * and a corresponding {@link #value}
 */
public class Keyframe {

    /**
     * The timecode of this <code>Keyframe</code>
     */
    private int timecode;

    /**
     * The value of this <code>Keyframe</code>
     */
    private float value;

    /**
     * The constructor.
     *
     * @param timecode the timecode
     * @param value    the value
     */
    public Keyframe(final int timecode, final float value) {
        this.timecode = timecode;
        this.value = value;
    }

    /**
     * Gets {@link #timecode}.
     *
     * @return the value of {@link #timecode}
     */
    public int getTimecode() {
        return timecode;
    }

    /**
     * Sets {@link #timecode}.
     *
     * @param timecode the new value of {@link #timecode}
     */
    public void setTimecode(final int timecode) {
        this.timecode = timecode;
    }

    /**
     * Gets {@link #value}.
     *
     * @return the value of {@link #value}
     */
    public float getValue() {
        return value;
    }

    /**
     * Sets {@link #value}.
     *
     * @param value the new value of {@link #value}
     */
    public void setValue(final float value) {
        this.value = value;
    }
}
